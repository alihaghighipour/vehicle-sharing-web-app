package com.haghighipour.noleggioveicoli.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.haghighipour.noleggioveicoli.entities.Utente;

@Service
public interface UtenteService {
	void addUtente(Utente utente);
	Utente getUtenteByUsernameAndPassword(final String username, final String password);
	List<Utente> getUtenti();
	void updateUtente(Utente utente);
}
