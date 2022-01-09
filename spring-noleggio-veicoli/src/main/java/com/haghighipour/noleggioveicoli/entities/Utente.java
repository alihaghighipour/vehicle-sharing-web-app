package com.haghighipour.noleggioveicoli.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "utenti")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String firma;
	@Enumerated(EnumType.ORDINAL)
	private RuoloUtente ruolo;
	private String nome;
	private String cognome;
	private Date dataNascita;
	private String email;
	private Date dataIscrizione;
	private String token;
	
	public Utente() {
		
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @param firma
	 * @param ruolo
	 * @param nome
	 * @param cognome
	 * @param dataNascita
	 * @param email
	 * @param dataIscrizione
	 */
	public Utente(String username,
				  String password,
				  String firma,
				  RuoloUtente ruolo,
				  String nome,
				  String cognome,
				  Date dataNascita,
				  String email,
				  Date dataIscrizione) {
		
		this.username = username;
		this.password = password;
		this.firma = firma;
		this.ruolo = ruolo;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.email = email;
		this.dataIscrizione = dataIscrizione;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirma() {
		return firma;
	}
	
	public void setFirma(String firma) {
		this.firma = firma;
	}
	
	public RuoloUtente getRuolo() {
		return ruolo;
	}

	public void setRuolo(RuoloUtente ruolo) {
		this.ruolo = ruolo;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public Date getDataNascita() {
		return dataNascita;
	}
	
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getDataIscrizione() {
		return dataIscrizione;
	}
	
	public void setDataIscrizione(Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
