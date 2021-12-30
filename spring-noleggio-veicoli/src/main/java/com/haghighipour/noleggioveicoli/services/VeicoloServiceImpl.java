package com.haghighipour.noleggioveicoli.services;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.haghighipour.noleggioveicoli.entities.Veicolo;
import com.haghighipour.noleggioveicoli.repository.VeicoloDAO;

@Service
public class VeicoloServiceImpl implements VeicoloService {

	@Autowired
	private VeicoloDAO repo;
	
	@Override
	public void addOne(Veicolo veicolo, MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		veicolo.setFileName(fileName);
		veicolo.setFileType(file.getContentType());
		veicolo.setImmagine(file.getBytes());
		
		this.repo.save(veicolo);
	}
	
	@Override
	public void addOne(Veicolo veicolo) {
		this.repo.save(veicolo);
	}
	
	@Override
	public List<Veicolo> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Veicolo getOne(int id) {
		return this.repo.findById(id).get();
	}
	
	@Override
	public List<Veicolo> getAllByCategoria(String categoria) {
		return this.getAll().stream()
				   .filter((veicolo) -> veicolo.getCategoria().equals(categoria))
				   .toList();
	}

	@Override
	public List<Veicolo> getAllByAlimentazione(String alimentazione) {
		return this.getAll().stream()
				   .filter((veicolo) -> veicolo.getAlimentazione().equals(alimentazione))
				   .toList();
	}

	@Override
	public List<Veicolo> getAllByModello(String modello) {
		return this.getAll().stream()
				   .filter((veicolo) -> veicolo.getModello().equals(modello))
				   .toList();
	}
	
	@Override
	public List<Veicolo> getAllByDisponibilita() {
		return this.getAll().stream()
				   .filter((veicolo) -> veicolo.isDisponibile())
				   .toList();
	}

	@Override
	public List<Veicolo> getAllByDisponibilita(Date data) {
		return this.getAll().stream()
				   .filter((veicolo) -> veicolo.getDataPrenotazione() == null || !veicolo.getDataPrenotazione().equals(data))
				   .toList();
	}
	
	@Override
	public void updateOne(Veicolo veicolo) {
		this.repo.save(veicolo);
	}

	@Override
	public void deleteOne(int id) {
		this.repo.deleteById(id);
	}

}
