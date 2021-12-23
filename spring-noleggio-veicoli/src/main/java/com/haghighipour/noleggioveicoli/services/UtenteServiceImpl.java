package com.haghighipour.noleggioveicoli.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haghighipour.noleggioveicoli.entities.Utente;
import com.haghighipour.noleggioveicoli.repository.UtenteDAO;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDAO repo;
	
	@Override
	public void addOne(Utente utente) {
		this.repo.save(utente);
	}

}
