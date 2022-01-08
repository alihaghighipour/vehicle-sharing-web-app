package com.haghighipour.noleggioveicoli.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haghighipour.noleggioveicoli.entities.Utente;
import com.haghighipour.noleggioveicoli.repository.UtenteDAO;
import com.haghighipour.noleggioveicoli.util.TokenGeneratorUtil;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDAO repo;
	
	@Override
	public void addUtente(Utente utente) {
		this.repo.save(utente);
	}

	@Override
	public Utente getUtenteByUsernameAndPassword(final String username, final String password) {
		Utente utente = this.repo.findByUsernameAndPassword(username, password).get();
		
		if (utente == null) {
			throw new IllegalStateException("Utente non esiste!");
		}
		
		utente.setToken(TokenGeneratorUtil.generateNewToken());
		this.updateUtente(utente);
		return utente;
	}

	@Override
	public List<Utente> getUtenti() {
		return this.repo.findAll();
	}

	@Override
	public void updateUtente(Utente utente) {
		this.repo.save(utente);
	}

}
