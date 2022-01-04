package com.haghighipour.noleggioveicoli.dto;

public class VeicoloDto {
	private int id;
	private String categoria;
	private String alimentazione;
	private String modello;
	private String colore;
	private int cilindrata;
	private String posizione;
	private String nomeFile;
	private String tipoFile;
	private String urlImmagine;
	
	public VeicoloDto() {
		
	}
	
	public VeicoloDto(int id, String categoria, String alimentazione, String modello,
					  String colore, int cilindrata, String posizione, String nomeFile,
					  String tipoFile, String urlImmagine) {
		this.id = id;
		this.categoria = categoria;
		this.alimentazione = alimentazione;
		this.modello = modello;
		this.colore = colore;
		this.cilindrata = cilindrata;
		this.posizione = posizione;
		this.nomeFile = nomeFile;
		this.tipoFile = tipoFile;
		this.urlImmagine = urlImmagine;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getAlimentazione() {
		return alimentazione;
	}
	
	public void setAlimentazione(String alimentazione) {
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
