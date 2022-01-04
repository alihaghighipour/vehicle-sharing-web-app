package com.haghighipour.noleggioveicoli.integration;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.haghighipour.noleggioveicoli.dto.VeicoloDto;
import com.haghighipour.noleggioveicoli.entities.Veicolo;
import com.haghighipour.noleggioveicoli.services.VeicoloService;

@RestController
@RequestMapping("api/veicoli")
public class VeicoloRestController {

	@Autowired
	private VeicoloService veicoloService;
	
	@GetMapping("")
	public List<VeicoloDto> getVeicoli() {
		List<Veicolo> veicoli = this.veicoloService.getAll();
		List<VeicoloDto> veicoliDto = veicoli.stream().map(veicolo -> {
			
			String immagineDownloadUri = ServletUriComponentsBuilder
										 .fromCurrentContextPath()
										 .path("/api/veicoli/")
										 .path(String.valueOf(veicolo.getId()))
										 .toUriString();
			
			VeicoloDto veicoloDto = new VeicoloDto();
			veicoloDto.setId(veicolo.getId());
			veicoloDto.setCategoria(veicolo.getCategoria());
			veicoloDto.setAlimentazione(veicolo.getAlimentazione());
			veicoloDto.setModello(veicolo.getModello());
			veicoloDto.setColore(veicolo.getColore());
			veicoloDto.setCilindrata(veicolo.getCilindrata());
			veicoloDto.setPosizione(veicolo.getPosizione());
			veicoloDto.setNomeFile(veicolo.getNomeFile());
			veicoloDto.setTipoFile(veicolo.getTipoFile());
			veicoloDto.setUrlImmagine(immagineDownloadUri);
			
			return veicoloDto;
			
		}).collect(Collectors.toList());
		
		return veicoliDto;
	}
	
	@GetMapping("/{id}")
	public Veicolo getVeicoloById(@PathVariable("id") final int id) {
		return this.veicoloService.getOne(id);
	}
	
	@GetMapping("/categoria/{categoria}")
	public List<Veicolo> getVeicoliByCategoria(@PathVariable("categoria") final String categoria) {
		return this.veicoloService.getAllByCategoria(categoria);
	}
	
	@GetMapping("/alimentazione/{alimentazione}")
	public List<Veicolo> getVeicoliByAlimentazione(@PathVariable("alimentazione") final String alimentazione) {
		return this.veicoloService.getAllByAlimentazione(alimentazione);
	}
	
	@GetMapping("/modello/{modello}")
	public List<Veicolo> getVeicoliByModello(@PathVariable("modello") final String modello) {
		return this.veicoloService.getAllByModello(modello);
	}
	
	@GetMapping("/disponibili")
	public List<Veicolo> getVeicoliByDisponibilita() {
		return this.veicoloService.getAllByDisponibilita();
	}
	
	@GetMapping("/disponibili/data")
	public List<Veicolo> getVeicoliByDisponibilita(@RequestParam("data") final Date data) {
		return this.veicoloService.getAllByDisponibilita(data);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, 
							 MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Veicolo> addVeicolo(@RequestPart("veicolo") String veicolo,
											  @RequestPart("immagine") MultipartFile immagine) throws IOException, JsonProcessingException {
		
		Veicolo nuovoVeicolo = this.veicoloService.getJson(veicolo, immagine);
		this.veicoloService.addOne(nuovoVeicolo);
		return new ResponseEntity<Veicolo>(nuovoVeicolo, HttpStatus.CREATED);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateVeicolo(@RequestBody Veicolo veicolo) {
		this.veicoloService.updateOne(veicolo);
	}
	
	@DeleteMapping("/{id}")
	public void deleteVeicolo(@PathVariable("id") final int id) {
		this.veicoloService.deleteOne(id);
	}
}
