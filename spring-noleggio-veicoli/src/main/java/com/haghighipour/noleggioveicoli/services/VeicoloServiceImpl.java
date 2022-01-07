package com.haghighipour.noleggioveicoli.services;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haghighipour.noleggioveicoli.config.CustomProperties;
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
		return this.repo.save(veicolo);
	}
	
	@Override
	public List<Veicolo> getVeicoli() {
		return this.repo.findAll();
	}

	@Override
	public Veicolo getVeicolo(int id) {
		return this.repo.findById(id).get();
	}
	
	@Override
	public List<Veicolo> getVeicoliByCategoria(CategoriaVeicolo categoria) {
		return this.getVeicoli().stream()
				   .filter((veicolo) -> veicolo.getCategoria().equals(categoria))
				   .toList();
	}

	@Override
	public List<Veicolo> getVeicoliByAlimentazione(AlimentazioneVeicolo alimentazione) {
		return this.getVeicoli().stream()
				   .filter((veicolo) -> veicolo.getAlimentazione().equals(alimentazione))
				   .toList();
	}

	@Override
	public List<Veicolo> getVeicoliByModello(String modello) {
		return this.getVeicoli().stream()
				   .filter((veicolo) -> veicolo.getModello().equals(modello))
				   .toList();
	}
	
	@Override
	public List<Veicolo> getVeicoliByDisponibilita() {
		return this.getVeicoli().stream()
				   .filter((veicolo) -> veicolo.isDisponibile())
				   .toList();
	}

	@Override
	public List<Veicolo> getVeicoliByDisponibilita(Date data) {
		return this.getVeicoli().stream()
				   .filter((veicolo) -> veicolo.getDataPrenotazione() == null || !veicolo.getDataPrenotazione().equals(data))
				   .toList();
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
		return this.repo.save(veicolo);
	}

	@Override
	public void deleteVeicolo(int id) {
		FileUploadUtil.deleteDirectory(this.getVeicolo(id));
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
