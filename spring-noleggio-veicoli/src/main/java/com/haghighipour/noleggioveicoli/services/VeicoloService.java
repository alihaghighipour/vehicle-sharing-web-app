package com.haghighipour.noleggioveicoli.services;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.haghighipour.noleggioveicoli.entities.Veicolo;

@Service
public interface VeicoloService {
	
	void addOne(Veicolo veicolo, MultipartFile file) throws IOException;
	void addOne(Veicolo veicolo);
	List<Veicolo> getAll();
	Veicolo getOne(int id);
	List<Veicolo> getAllByCategoria(String categoria);
	List<Veicolo> getAllByAlimentazione(String alimentazione);
	List<Veicolo> getAllByModello(String modello);
	List<Veicolo> getAllByDisponibilita();
	List<Veicolo> getAllByDisponibilita(Date data);
	void updateOne(Veicolo veicolo);
	void deleteOne(int id);
}
