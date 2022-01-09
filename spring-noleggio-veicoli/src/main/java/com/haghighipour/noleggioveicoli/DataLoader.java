package com.haghighipour.noleggioveicoli;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.haghighipour.noleggioveicoli.entities.RuoloUtente;
import com.haghighipour.noleggioveicoli.entities.Utente;
import com.haghighipour.noleggioveicoli.repository.UtenteDAO;

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private UtenteDAO repo;
	
	@Override
	public void run(String... args) throws Exception {
		this.repo.deleteAll();
		
		this.repo.save(new Utente("admin", "admin", "Amministratore dei servizi", RuoloUtente.ADMIN, "Paolino", "Paperino", Date.valueOf("1990-08-20"), "paolino.paperino@paperopoli.com", new Date(System.currentTimeMillis())));
		this.repo.save(new Utente("utente", "utente", "Utente con diritti minimi", RuoloUtente.UTENTE, "Pippo", "foo", Date.valueOf("1996-08-18"), "pippo.foo@paperopoli.com", new Date(System.currentTimeMillis())));
	}

}
