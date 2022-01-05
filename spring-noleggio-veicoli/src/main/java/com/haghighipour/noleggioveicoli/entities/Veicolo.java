package com.haghighipour.noleggioveicoli.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.haghighipour.noleggioveicoli.config.CustomProperties;

@Entity
@Table(name = "veicoli")
public class Veicolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String categoria;
	private String alimentazione;
	private String modello;
	private String colore;
	private int cilindrata;
	private String posizione;
	private boolean isDisponibile;
	private Date dataPrenotazione;
	private String nomeFile;
	private String tipoFile;
	
	public Veicolo() {
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
	
	public boolean isDisponibile() {
		return isDisponibile;
	}
	
	public void setDisponibile(boolean isDisponibile) {
		this.isDisponibile = isDisponibile;
	}
	
	public Date getDataPrenotazione() {
		return dataPrenotazione;
	}
	
	public void setDataPrenotazione(Date dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
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
	
	@Transient
	public String getUrlImmagine() {
		if(this.getNomeFile() == null || this.getNomeFile().equals("")) {
			return "/" + CustomProperties.defaultImg;
		}
		
		return "/" + CustomProperties.basePath + "/" + this.getId() + "/" + this.getNomeFile();
	}
	
}
