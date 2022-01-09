package com.haghighipour.noleggioveicoli.integration;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.haghighipour.noleggioveicoli.dto.StatisticaDto;
import com.haghighipour.noleggioveicoli.dto.VeicoloDto;
import com.haghighipour.noleggioveicoli.entities.AlimentazioneVeicolo;
import com.haghighipour.noleggioveicoli.entities.CategoriaVeicolo;
import com.haghighipour.noleggioveicoli.entities.Veicolo;
import com.haghighipour.noleggioveicoli.services.VeicoloService;

@RestController
@RequestMapping("api/veicoli")
public class VeicoloRestController {

	@Autowired
	private VeicoloService veicoloService;
	
	@GetMapping("")
	public List<VeicoloDto> getAll() {
		List<Veicolo> veicoli = this.veicoloService.getVeicoli();
		return this.veicoloService.getVeicoliDto(veicoli);
	}
	
	@GetMapping("/{id}")
	public VeicoloDto getOneById(@PathVariable("id") final int id) {
		Veicolo veicolo = this.veicoloService.getVeicolo(id);
		
		if (veicolo == null) {
			return new VeicoloDto();
		}
		
		return new VeicoloDto(veicolo);
	}
	
	@GetMapping("/categoria/{categoria}")
	public List<VeicoloDto> getAllByCategoria(@PathVariable("categoria") final String categoria) {
		List<Veicolo> veicoli = this.veicoloService.getVeicoliByCategoria(CategoriaVeicolo.decode(categoria));
		return this.veicoloService.getVeicoliDto(veicoli);
	}
	
	@GetMapping("/alimentazione/{alimentazione}")
	public List<VeicoloDto> getAllByAlimentazione(@PathVariable("alimentazione") final String alimentazione) {
		List<Veicolo> veicoli = this.veicoloService.getVeicoliByAlimentazione(AlimentazioneVeicolo.decode(alimentazione));
		return this.veicoloService.getVeicoliDto(veicoli);
	}
	
	@GetMapping("/modello/{modello}")
	public List<VeicoloDto> getAllByModello(@PathVariable("modello") final String modello) {
		List<Veicolo> veicoli = this.veicoloService.getVeicoliByModello(modello);
		return this.veicoloService.getVeicoliDto(veicoli);
	}
	
	@GetMapping("/disponibili")
	public List<VeicoloDto> getAllByDisponibilita() {
		List<Veicolo> veicoli = this.veicoloService.getVeicoliByDisponibilita();
		return this.veicoloService.getVeicoliDto(veicoli);
	}
	
	@GetMapping("/disponibili/data")
	public List<VeicoloDto> getAllByDisponibilita(@RequestParam("data") final Date data) {
		List<Veicolo> veicoli = this.veicoloService.getVeicoliByDisponibilita(data);
		return this.veicoloService.getVeicoliDto(veicoli);
	}
	
	@GetMapping("/statistica")
	public List<StatisticaDto> getStatistica() {
		return this.veicoloService.getStatisticaVeicoli();
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Veicolo> addOne(@RequestPart("veicolo") String veicolo,
										  @RequestPart("immagine") MultipartFile immagine) throws IOException, JsonProcessingException {
		
		Veicolo nuovoVeicolo = this.veicoloService.parseJson(veicolo, immagine);
		Veicolo veicoloSalvato = this.veicoloService.addVeicoloAndImmagine(nuovoVeicolo, immagine);
		return new ResponseEntity<Veicolo>(veicoloSalvato, HttpStatus.CREATED);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, 
			 				MediaType.MULTIPART_FORM_DATA_VALUE})
	public void updateOne(@RequestPart("veicolo") String veicolo,
						  @RequestPart("immagine") MultipartFile immagine) throws IOException, JsonProcessingException {
		Veicolo veicoloModificato = this.veicoloService.parseJson(veicolo, immagine);
		this.veicoloService.updateVeicoloAndImmagine(veicoloModificato, immagine);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOne(@PathVariable("id") final int id) {
		this.veicoloService.deleteVeicolo(id);
	}
}
