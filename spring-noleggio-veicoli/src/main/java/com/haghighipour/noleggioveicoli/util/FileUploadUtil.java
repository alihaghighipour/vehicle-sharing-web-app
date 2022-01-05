package com.haghighipour.noleggioveicoli.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.haghighipour.noleggioveicoli.config.CustomProperties;
import com.haghighipour.noleggioveicoli.entities.Veicolo;

public class FileUploadUtil {
	
	public static void saveFile(String uploadDir, String nomeFile, MultipartFile file) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try (InputStream inputStream = file.getInputStream()) {
			
			Path filePath = uploadPath.resolve(nomeFile);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + nomeFile, ioe);
		}
		
	}
	
	public static void deleteDirectory(Veicolo veicolo) {
		
		try {
			
			String dir = CustomProperties.basePath + "/" + veicolo.getId();
			if(Files.exists(Paths.get(dir))) {
				FileUtils.deleteDirectory(new File(dir));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
