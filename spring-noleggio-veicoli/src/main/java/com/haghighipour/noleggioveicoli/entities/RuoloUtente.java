package com.haghighipour.noleggioveicoli.entities;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RuoloUtente {
	ADMIN ("Admin"),
	UTENTE ("Utente");
	
	private String ruolo;
	
	private RuoloUtente(final String ruolo) {
		this.ruolo = ruolo;
	}
	
	@JsonCreator
	public static RuoloUtente decode(final String ruolo) { 
		return Stream.of(RuoloUtente.values())
					 .filter(ruoloEnum -> ruoloEnum.ruolo.equals(ruolo))
					 .findFirst()
					 .orElse(null);
	}
	
	@JsonValue
	public String getRuolo() {
		return this.ruolo;
	}
}
