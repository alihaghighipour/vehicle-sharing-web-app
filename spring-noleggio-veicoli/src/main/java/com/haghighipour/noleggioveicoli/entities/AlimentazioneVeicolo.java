package com.haghighipour.noleggioveicoli.entities;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AlimentazioneVeicolo {
	ELETTRICO ("Elettrico"),
	IBRIDO ("Ibrido"),
	BENZINA ("Benzina"),
	DIESEL ("Diesel");
	
	private final String alimentazione;
	
	private AlimentazioneVeicolo(String alimentazione) {
		this.alimentazione = alimentazione;
	}
	
	@JsonCreator
	public static AlimentazioneVeicolo decode(final String alimentazione) { 
		return Stream.of(AlimentazioneVeicolo.values())
					 .filter(alimentazioneEnum -> alimentazioneEnum.alimentazione.equals(alimentazione))
					 .findFirst()
					 .orElse(null);
	}
	
	@JsonValue
	public String getAlimentazione() {
		return this.alimentazione;
	}
}
