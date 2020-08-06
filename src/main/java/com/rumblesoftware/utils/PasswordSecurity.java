package com.rumblesoftware.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.rumblesoftware.exception.PasswordHashException;

@Component
public class PasswordSecurity {

	public static final int INTERACTIONS = 1500; //65535
	public static final int KEY_LENGTH = 512;
	public static final String ALGORITHM = "PBKDF2WithHmacSHA512";
	
	private static Logger logger = LogManager.getLogger(PasswordSecurity.class);

	private static final String MSG_EXCEPTION_HASHING_EX = "password.hashing.exception.message";
	
	private static final String MSG_LOG_HASHING = "password.hashing.log.message";
	
	@Autowired
	private static MessageSource ms;
	
	public static String generateRandomSalt() {
		SecureRandom random = new SecureRandom();
		
		// Define the size of the Salt
		byte salt[] = new byte[32];
		
		// Generate random information
		random.nextBytes(salt);
		
		// Encode to string base 64 to be stored in the database
		return Base64.getEncoder().encodeToString(salt);
	}

	public Optional<String> hashPassword(String password, String salt) {

		// cast the password into char array
		char[] passwordChars = password.toCharArray();
		
		// cast salt into bytes again
		byte[] saltBytes = salt.getBytes();

		// generate a secret key
		PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, INTERACTIONS, KEY_LENGTH);

		// emptying the variable in order to avoid keep data in memory
		Arrays.fill(passwordChars, Character.MIN_VALUE);

		try {
			// Create a secret key factory using the right algorithm
			SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
			
			// generate the secure password
			byte[] securePassword = fac.generateSecret(spec).getEncoded();
			
			// cast to a string base 64 to store in the database
			return Optional.of(Base64.getEncoder().encodeToString(securePassword));

		} catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
			logger.error(String.format(ms.getMessage(MSG_LOG_HASHING,null,Locale.getDefault()), ex.toString()));
			throw new PasswordHashException(ms.getMessage(MSG_EXCEPTION_HASHING_EX, null, Locale.getDefault()));

		} finally {
			spec.clearPassword();
		}
	}
	
	public boolean verifyPassword (String password, String key, String salt) {
	    Optional<String> optEncrypted = hashPassword(password, salt);
	    if (!optEncrypted.isPresent()) return false;
	    return optEncrypted.get().equals(key);
	  }
	
	
}
