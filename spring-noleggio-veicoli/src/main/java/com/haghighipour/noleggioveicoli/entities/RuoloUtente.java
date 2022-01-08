package com.haghighipour.noleggioveicoli.entities;

public enum RuoloUtente {
	ADMIN ("Admin"),
	UTENTE ("Utente");
	
	private String ruolo;
	
	private RuoloUtente(final String ruolo) {
		this.ruolo = ruolo;
	}
}
