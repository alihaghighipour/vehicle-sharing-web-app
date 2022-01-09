package com.haghighipour.noleggioveicoli.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.haghighipour.noleggioveicoli.entities.RuoloUtente;
import com.haghighipour.noleggioveicoli.entities.Utente;

@Service
public interface UtenteService {
	Utente loginUtenteWithUsernameAndPassword(final String username, final String password);
	Utente authorizeUtenteWithTokenAndRuolo(final String token, final RuoloUtente ruolo);
	Utente logoutUtenteWithToken(final String token);
	List<Utente> getUtenti();
	void updateUtente(Utente utente);
}
