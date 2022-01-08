package com.haghighipour.noleggioveicoli.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.haghighipour.noleggioveicoli.dto.UtenteDto;
import com.haghighipour.noleggioveicoli.entities.Utente;
import com.haghighipour.noleggioveicoli.services.UtenteService;

@RestController
@RequestMapping("api/utenti")
public class UtenteRestController {
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping
	public List<Utente> getAll() {
		return this.utenteService.getUtenti();
	}
	
	@PostMapping(value = "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public UtenteDto getOne(@RequestPart("username") final String username, @RequestPart("password") final String password) {
		Utente utente = this.utenteService.getUtenteByUsernameAndPassword(username, password);
		return new UtenteDto(utente);
	}
	
	@PostMapping
	public ResponseEntity<Utente> addOne(@RequestBody Utente utente) {
		this.utenteService.addUtente(utente);
		return new ResponseEntity<Utente>(utente, HttpStatus.CREATED);
	}
}
