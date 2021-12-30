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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.haghighipour.noleggioveicoli.entities.Veicolo;
import com.haghighipour.noleggioveicoli.services.VeicoloService;

@RestController
@RequestMapping("api/veicoli")
public class VeicoloRestController {

	@Autowired
	private VeicoloService veicoloService;
	
	@GetMapping("")
	public List<Veicolo> getVeicoli() {
		return this.veicoloService.getAll();
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
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veicolo> addVeicolo(@RequestBody Veicolo veicolo) {
		this.veicoloService.addOne(veicolo);
		return new ResponseEntity<Veicolo>(veicolo, HttpStatus.CREATED);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<Veicolo> addVeicolo(Veicolo veicolo, @RequestParam("immagine") MultipartFile file) {
		
		System.out.println(file);
		try {
			this.veicoloService.addOne(veicolo, file);
			return new ResponseEntity<Veicolo>(veicolo, HttpStatus.CREATED);
			
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Veicolo>(veicolo, HttpStatus.EXPECTATION_FAILED);
		}
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
