package com.haghighipour.noleggioveicoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haghighipour.noleggioveicoli.entities.RuoloUtente;
import com.haghighipour.noleggioveicoli.entities.Utente;

public interface UtenteDAO extends JpaRepository<Utente, Integer> {
	Utente findByUsernameAndPassword(String username, String password);
	Utente findByTokenAndRuolo(String token, RuoloUtente ruolo);
	Utente findByToken(String token);
}
