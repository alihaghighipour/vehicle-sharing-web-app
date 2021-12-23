package com.haghighipour.noleggioveicoli.services;

import org.springframework.stereotype.Service;

import com.haghighipour.noleggioveicoli.entities.Utente;

@Service
public interface UtenteService {
	void addOne(Utente utente);
}
