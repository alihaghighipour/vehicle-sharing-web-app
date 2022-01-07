package com.haghighipour.noleggioveicoli.dto;

import com.haghighipour.noleggioveicoli.entities.AlimentazioneVeicolo;
import com.haghighipour.noleggioveicoli.entities.CategoriaVeicolo;
import com.haghighipour.noleggioveicoli.entities.Veicolo;

public class VeicoloDto {
	private int id;
	private CategoriaVeicolo categoria;
	private AlimentazioneVeicolo alimentazione;
	private String modello;
	private String colore;
	private int cilindrata;
	private String posizione;
	private String nomeFile;
	private String tipoFile;
	private String urlImmagine;
	
	public VeicoloDto() {
		
	}
	
	public VeicoloDto (Veicolo veicolo) {
		this.id = veicolo.getId();
		this.categoria = veicolo.getCategoria();
		this.alimentazione = veicolo.getAlimentazione();
		this.modello = veicolo.getModello();
		this.colore = veicolo.getColore();
		this.cilindrata = veicolo.getCilindrata();
		this.posizione = veicolo.getPosizione();
		this.nomeFile = veicolo.getNomeFile();
		this.tipoFile = veicolo.getTipoFile();
		this.urlImmagine = veicolo.getUrlImmagine();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public CategoriaVeicolo getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaVeicolo categoria) {
		this.categoria = categoria;
	}

	public AlimentazioneVeicolo getAlimentazione() {
		return alimentazione;
	}

	public void setAlimentazione(AlimentazioneVeicolo alimentazione) {
		this.alimentazione = alimentazione;
	}

	public String getModello() {
		return modello;
	}
	
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	public String getColore() {
		return colore;
	}
	
	public void setColore(String colore) {
		this.colore = colore;
	}
	
	public int getCilindrata() {
		return cilindrata;
	}
	
	public void setCilindrata(int cilindrata) {
		this.cilindrata = cilindrata;
	}
	
	public String getPosizione() {
		return posizione;
	}
	
	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}
	
	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getTipoFile() {
		return tipoFile;
	}

	public void setTipoFile(String tipoFile) {
		this.tipoFile = tipoFile;
	}

	public String getUrlImmagine() {
		return urlImmagine;
	}
	
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
}
