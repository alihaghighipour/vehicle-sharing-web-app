package com.haghighipour.noleggioveicoli.entities;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaVeicolo {
	AUTOMOBILE ("Automobile"),
	MONOPATTINO ("Monopattino"),
	BICICLETTA ("Bicicletta"),
	MOTORINO ("Motorino");
	
	private final String categoria;
	
	private CategoriaVeicolo(String categoria) {
		this.categoria = categoria;
	}
	
	@JsonCreator
	public static CategoriaVeicolo decode(final String categoria) { 
		return Stream.of(CategoriaVeicolo.values())
					 .filter(categoriaEnum -> categoriaEnum.categoria.equals(categoria))
					 .findFirst()
					 .orElse(null);
	}
	
	@JsonValue
	public String getCategoria() {
		return this.categoria;
	}
}
