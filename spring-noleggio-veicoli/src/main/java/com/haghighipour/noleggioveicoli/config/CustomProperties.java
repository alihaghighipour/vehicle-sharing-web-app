package com.haghighipour.noleggioveicoli.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomProperties {
	
	public static String hostPath = "http://localhost:9020/noleggio-veicoli";
	public static String staticResourcesPath = "src/main/resources/static";
	public static String baseImgPath = "img/veicoli";
	public static String defaultImgPath = "img/veicoli/imgNotFound.jpg";
}