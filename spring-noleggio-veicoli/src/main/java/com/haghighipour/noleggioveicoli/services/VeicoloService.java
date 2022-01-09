package com.haghighipour.noleggioveicoli.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.haghighipour.noleggioveicoli.dto.StatisticaDto;
import com.haghighipour.noleggioveicoli.dto.VeicoloDto;
import com.haghighipour.noleggioveicoli.entities.AlimentazioneVeicolo;
import com.haghighipour.noleggioveicoli.entities.CategoriaVeicolo;
import com.haghighipour.noleggioveicoli.entities.Veicolo;

@Service
public interface VeicoloService {
	
	Veicolo addVeicoloAndImmagine(Veicolo veicolo, MultipartFile immagine);
	Veicolo addVeicolo(Veicolo veicolo);
	List<Veicolo> getVeicoli();
	Veicolo getVeicolo(int id);
	List<Veicolo> getRandomVeicoli(int random);
	List<Veicolo> getVeicoliByCategoria(CategoriaVeicolo categoria);
	List<Veicolo> getVeicoliByAlimentazione(AlimentazioneVeicolo alimentazione);
	List<Veicolo> getVeicoliByModello(String modello);
	List<Veicolo> getVeicoliByDisponibilita();
	List<Veicolo> getVeicoliByDisponibilita(Date data);
	List<StatisticaDto> getStatisticaVeicoli();
	void updateVeicoloAndImmagine(Veicolo veicolo, MultipartFile immagine);
	Veicolo updateVeicolo(Veicolo veicolo);
	void deleteVeicolo(int id);
	
	Veicolo parseJson(String veicolo, MultipartFile immagine);
	List<VeicoloDto> getVeicoliDto(List<Veicolo> veicoli);
}
