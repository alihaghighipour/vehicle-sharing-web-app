package com.haghighipour.noleggioveicoli.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.haghighipour.noleggioveicoli.entities.Veicolo;

@Service
public interface VeicoloService {
	
	void addOne(Veicolo veicolo);
	List<Veicolo> getAll();
	Veicolo getOne(int id);
	List<Veicolo> getAllByCategoria(String categoria);
	List<Veicolo> getAllByAlimentazione(String alimentazione);
	List<Veicolo> getAllByModello(String modello);
	List<Veicolo> getAllByDisponibilita();
	void updateOne(Veicolo veicolo);
	void deleteOne(int id);
}
