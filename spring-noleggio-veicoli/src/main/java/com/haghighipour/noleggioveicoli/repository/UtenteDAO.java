package com.haghighipour.noleggioveicoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haghighipour.noleggioveicoli.entities.Utente;

public interface UtenteDAO extends JpaRepository<Utente, Integer> {

}
