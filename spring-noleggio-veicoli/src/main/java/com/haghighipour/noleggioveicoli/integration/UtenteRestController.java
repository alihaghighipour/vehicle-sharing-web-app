package com.haghighipour.noleggioveicoli.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.haghighipour.noleggioveicoli.dto.UtenteDto;
import com.haghighipour.noleggioveicoli.entities.RuoloUtente;
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
	
	@GetMapping(value = "/autorizzato", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> isAutorizzato(@RequestPart("token") final String token, @RequestPart("ruolo") final String ruolo) {
		Utente utente = this.utenteService.authorizeUtenteWithTokenAndRuolo(token, RuoloUtente.decode(ruolo));
		
		if (utente == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/login", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<UtenteDto> login(@RequestPart("username") final String username, @RequestPart("password") final String password) {
		Utente utente = this.utenteService.loginUtenteWithUsernameAndPassword(username, password);
		
		if (utente == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().body(new UtenteDto(utente));
	}
	
	@PostMapping(value = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> logout(@RequestBody final UtenteDto utenteDto) {
		Utente utente = this.utenteService.logoutUtenteWithToken(utenteDto.getToken());

		if (utente == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok().build();
	}
}
