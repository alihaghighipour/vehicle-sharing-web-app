package com.haghighipour.noleggioveicoli.services;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haghighipour.noleggioveicoli.config.CustomProperties;
import com.haghighipour.noleggioveicoli.dto.StatisticaDto;
import com.haghighipour.noleggioveicoli.dto.VeicoloDto;
import com.haghighipour.noleggioveicoli.entities.AlimentazioneVeicolo;
import com.haghighipour.noleggioveicoli.entities.CategoriaVeicolo;
import com.haghighipour.noleggioveicoli.entities.Veicolo;
import com.haghighipour.noleggioveicoli.repository.VeicoloDAO;
import com.haghighipour.noleggioveicoli.util.FileUploadUtil;

@Service
public class VeicoloServiceImpl implements VeicoloService {

	@Autowired
	private VeicoloDAO repo;
	
	private Map<Integer, Veicolo> veicoli = new HashMap<Integer, Veicolo>();
	
	@PostConstruct
	private void init() {
		List<Veicolo> elencoVeicoli = this.repo.findAll();
		
		if (elencoVeicoli == null) {
			return;
		}
		
		for (Veicolo veicolo: elencoVeicoli) {
			this.veicoli.put(veicolo.getId(), veicolo);
		}
	}
	
	@Override
	public Veicolo addVeicoloAndImmagine(Veicolo veicolo, MultipartFile immagine) {
		Veicolo veicoloSalvato = this.addVeicolo(veicolo);
				
		String uploadDir = CustomProperties.staticResourcesPath + "/" + CustomProperties.baseImgPath + "/" + veicoloSalvato.getId();
		
		try {
			FileUploadUtil.saveFile(uploadDir, veicoloSalvato.getNomeFile(), immagine);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return veicoloSalvato;
	}
	
	@Override
	public Veicolo addVeicolo(Veicolo veicolo) {
		Veicolo veicoloSalvato =  this.repo.save(veicolo);
		this.veicoli.put(veicoloSalvato.getId(), veicoloSalvato);
		return veicoloSalvato;
	}
	
	@Override
	public List<Veicolo> getVeicoli() {
		return this.veicoli.values().stream().toList();
	}

	@Override
	public Veicolo getVeicolo(int id) {
		return this.veicoli.get(id);
	}
	
	@Override
	public List<Veicolo> getRandomVeicoli(int random) {
		List<Veicolo> tmpVeicoli = new ArrayList<Veicolo>(this.veicoli.values().stream().toList());
		Collections.shuffle(tmpVeicoli);
		return random > tmpVeicoli.size() ? tmpVeicoli.subList(0, tmpVeicoli.size()) : tmpVeicoli.subList(0, random);
	}
	
	@Override
	public List<Veicolo> getVeicoliByCategoria(CategoriaVeicolo categoria) {
		return this.veicoli.values().stream()
				   .filter((veicolo) -> veicolo.getCategoria().equals(categoria))
				   .sorted(Comparator.comparing(Veicolo::getModello))
				   .toList();
	}

	@Override
	public List<Veicolo> getVeicoliByAlimentazione(AlimentazioneVeicolo alimentazione) {
		return this.veicoli.values().stream()
				   .filter((veicolo) -> veicolo.getAlimentazione().equals(alimentazione))
				   .toList();
	}

	@Override
	public List<Veicolo> getVeicoliByModello(String modello) {
		return this.veicoli.values().stream()
				   .filter((veicolo) -> veicolo.getModello().equals(modello))
				   .toList();
	}
	
	@Override
	public List<Veicolo> getVeicoliByDisponibilita() {
		return this.veicoli.values().stream()
				   .filter((veicolo) -> veicolo.isDisponibile())
				   .toList();
	}

	@Override
	public Veicolo getVeicoloByDisponibilita(int id) {
		Veicolo veicolo = this.veicoli.get(id);
		
		if (veicolo != null) {
			return veicolo.isDisponibile() ? veicolo : null;
		}
		
		return veicolo;
	}

	@Override
	public List<StatisticaDto> getStatisticaVeicoli() {
		List<StatisticaDto> statistica = new ArrayList<StatisticaDto>();
		
		final double FATTORE_BICICLETTA = 1.0;
		final double FATTORE_MONOPATTINO = 0.95;
		final double FATTORE_AUTO_ELETTRICA = 0.6;
		final double FATTORE_AUTO_IBRIDA = 0.4;
		
		final int numeroBiciclette = Math.toIntExact(
									 this.veicoli.values().stream()
										 .filter((veicolo) -> veicolo.getCategoria().equals(CategoriaVeicolo.BICICLETTA))
										 .count());
		
		final int numeroMonopattini = Math.toIntExact(
									  this.veicoli.values().stream()
				 						  .filter((veicolo) -> veicolo.getCategoria().equals(CategoriaVeicolo.MONOPATTINO))
				 						  .count());
	
		final int numeroAutoElettriche = Math.toIntExact(
										 this.veicoli.values().stream()
						 					 .filter((veicolo) -> veicolo.getCategoria().equals(CategoriaVeicolo.AUTOMOBILE) &&
						 							 			  veicolo.getAlimentazione().equals(AlimentazioneVeicolo.ELETTRICO))
											 .count());
		
		final int numeroAutoIbride = Math.toIntExact(
									 this.veicoli.values().stream()
										 .filter((veicolo) -> veicolo.getCategoria().equals(CategoriaVeicolo.AUTOMOBILE) &&
												 			  veicolo.getAlimentazione().equals(AlimentazioneVeicolo.HYBRID))
										 .count());
	
		statistica.add(new StatisticaDto("Biciclette", FATTORE_BICICLETTA * numeroBiciclette));
		statistica.add(new StatisticaDto("Monopattini", FATTORE_MONOPATTINO * numeroMonopattini));
		statistica.add(new StatisticaDto("Auto Elettriche", FATTORE_AUTO_ELETTRICA * numeroAutoElettriche));
		statistica.add(new StatisticaDto("Auto Ibride", FATTORE_AUTO_IBRIDA * numeroAutoIbride));
		
		return statistica;
	}
	
	@Override
	public void updateDisponibilita(Veicolo veicolo) {
		veicolo.setDisponibile(false);
		veicolo.setDataPrenotazione(new Date(System.currentTimeMillis()));
		this.updateVeicolo(veicolo);
	}
	
	@Override
	public void updateVeicoloAndImmagine(Veicolo veicolo, MultipartFile immagine) {
		Veicolo veicoloModificato = this.updateVeicolo(veicolo);
		
		//FileUploadUtil.deleteDirectory(veicoloModificato);
		
		String uploadDir = CustomProperties.staticResourcesPath + "/" + CustomProperties.baseImgPath + "/" + veicoloModificato.getId();
		
		try {
			FileUploadUtil.saveFile(uploadDir, veicoloModificato.getNomeFile(), immagine);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Veicolo updateVeicolo(Veicolo veicolo) {
		Veicolo veicoloModificato = this.repo.save(veicolo);
		this.veicoli.put(veicoloModificato.getId(), veicoloModificato);
		return veicoloModificato;
	}

	@Override
	public void deleteVeicolo(int id) {
		FileUploadUtil.deleteDirectory(this.getVeicolo(id));
		this.veicoli.remove(id);
		this.repo.deleteById(id);
	}

	@Override
	public Veicolo parseJson(String veicolo, MultipartFile immagine) {
		
		Veicolo oggettoVeicolo = new Veicolo();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			oggettoVeicolo = objectMapper.readValue(veicolo, Veicolo.class);
			
			String nomeFile = StringUtils.cleanPath(immagine.getOriginalFilename());
			oggettoVeicolo.setNomeFile(nomeFile);
			oggettoVeicolo.setTipoFile(immagine.getContentType());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return oggettoVeicolo;
	}

	@Override
	public List<VeicoloDto> getVeicoliDto(List<Veicolo> veicoli) {
		List<VeicoloDto> veicoliDto = veicoli.stream().map(veicolo -> {
			
			VeicoloDto veicoloDto = new VeicoloDto(veicolo);
			
			return veicoloDto;
			
		}).collect(Collectors.toList());
		return veicoliDto;
	}

}