package com.haghighipour.noleggioveicoli.dto;

public class StatisticaDto {
	
	private String tipologiaVeicolo;
	private double risparmio;
	
	public StatisticaDto() {
		
	}
	
	public StatisticaDto(String tipologiaVeicolo, double risparmio) {
		this.tipologiaVeicolo = tipologiaVeicolo;
		this.risparmio = risparmio;
	}

	public String getTipologiaVeicolo() {
		return tipologiaVeicolo;
	}
	
	public void setTipologiaVeicolo(String tipologiaVeicolo) {
		this.tipologiaVeicolo = tipologiaVeicolo;
	}

	public double getRisparmio() {
		return risparmio;
	}

	public void setRisparmio(double risparmio) {
		this.risparmio = risparmio;
	}
}