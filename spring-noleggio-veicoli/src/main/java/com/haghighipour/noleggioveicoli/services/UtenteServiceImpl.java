package com.haghighipour.noleggioveicoli.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haghighipour.noleggioveicoli.entities.RuoloUtente;
import com.haghighipour.noleggioveicoli.entities.Utente;
import com.haghighipour.noleggioveicoli.repository.UtenteDAO;
import com.haghighipour.noleggioveicoli.util.TokenGeneratorUtil;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteDAO repo;

	@Override
	public Utente loginUtenteWithUsernameAndPassword(final String username, final String password) {
		Utente utente = this.repo.findByUsernameAndPassword(username, password);
		
		if (utente != null) {
			utente.setToken(TokenGeneratorUtil.generateNewToken());
			this.updateUtente(utente);
		}
		
		return utente;
	}

	@Override
	public Utente authorizeUtenteWithTokenAndRuolo(final String token, final RuoloUtente ruolo) {
		return this.repo.findByTokenAndRuolo(token, ruolo);
	}

	@Override
	public Utente logoutUtenteWithToken(final String token) {
		Utente utente = this.repo.findByToken(token);
		
		if (utente != null) {
			utente.setToken(null);
			this.updateUtente(utente);
		}
		
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