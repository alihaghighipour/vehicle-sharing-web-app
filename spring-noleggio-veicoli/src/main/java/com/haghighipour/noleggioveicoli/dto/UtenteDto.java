package com.haghighipour.noleggioveicoli.dto;

import com.haghighipour.noleggioveicoli.entities.Utente;

public class UtenteDto {
	private String token;
	
	public UtenteDto() {
		
	}
	
	public UtenteDto(Utente utente) {
		this.token = utente.getToken();
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
