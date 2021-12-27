package com.haghighipour.noleggioveicoli.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haghighipour.noleggioveicoli.entities.Veicolo;

public interface VeicoloDAO extends JpaRepository<Veicolo, Integer> {
	
}
