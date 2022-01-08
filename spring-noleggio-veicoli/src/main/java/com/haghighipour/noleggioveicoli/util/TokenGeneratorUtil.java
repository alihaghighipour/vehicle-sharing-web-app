package com.haghighipour.noleggioveicoli.util;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGeneratorUtil {
	
	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder encoder = Base64.getUrlEncoder();
	
	public static String generateNewToken() {
		byte[] randomBytes = new byte[24];
		secureRandom.nextBytes(randomBytes);
		return encoder.encodeToString(randomBytes);
	}
}
