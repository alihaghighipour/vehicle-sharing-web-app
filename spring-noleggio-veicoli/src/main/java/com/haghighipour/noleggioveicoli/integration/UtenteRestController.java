package com.haghighipour.noleggioveicoli.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haghighipour.noleggioveicoli.entities.Utente;
import com.haghighipour.noleggioveicoli.services.UtenteService;

@RestController
@RequestMapping("api/utenti")
public class UtenteRestController {
	
	@Autowired
	private UtenteService utenteService;
	
	@PostMapping
	public ResponseEntity<Utente> addUtente(@RequestBody Utente utente) {
		this.utenteService.addOne(utente);
		return new ResponseEntity<Utente>(utente, HttpStatus.CREATED);
	}
}
