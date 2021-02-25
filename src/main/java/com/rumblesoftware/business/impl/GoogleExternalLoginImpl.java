package com.rumblesoftware.business.impl;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.rumblesoftware.business.ExternalTokenValidator;
import com.rumblesoftware.enums.ExternalTokenType;
import com.rumblesoftware.exception.ExternalTokenValidationException;
import com.rumblesoftware.exception.InvalidExternalTokenException;
import com.rumblesoftware.io.builders.LoginDetailsBuilder;
import com.rumblesoftware.io.input.dto.ExternalTokenDataDto;
import com.rumblesoftware.io.input.dto.LoginDetailsDto;

/**
 * Class responsible for authenticate an user based in his google account details
 * @author Cleiton
 *
 */
@PropertySource("classpath:application.properties")
@Component
public class GoogleExternalLoginImpl implements ExternalTokenValidator {

	/**
	 * Client ID from the APP
	 */
	@Value(value = "${moneysaver.google.api.id}")
	public String clientID;
	
	/**
	 * Get an instance of logger in order to log error messages
	 */
	private Logger log = LoggerFactory.getLogger(GoogleExternalLoginImpl.class);

	/**
	 * Method responsible for verify token validity
	 */
	@Override
	public LoginDetailsDto verifyTokenValidity(ExternalTokenDataDto token) {		
		LoginDetailsDto loginDetails = new LoginDetailsDto();
		LoginDetailsBuilder lb = new LoginDetailsBuilder();
		
		log.debug("Verifying user token (Google)...");
		
		if(token.getTokenType() != ExternalTokenType.Google)
			return null;
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
				.setAudience(Collections.singletonList(clientID))
				.build();

		// (Receive idTokenString by HTTPS POST)
		try {
			
			GoogleIdToken idToken = verifier.verify(token.getToken());
			if (idToken != null) {
				Payload payload = idToken.getPayload();

				// Print user identifier
				String userId = payload.getSubject();
				System.out.println("User ID: " + userId);

				// Get profile information from payload
				String email = (String) payload.get("email");
				String name = (String) payload.get("given_name");
				String familyName = (String) payload.get("family_name");

				loginDetails = lb
						.withExternalId(userId)
						.withExternalIdType(token.getTokenType())
						.withName(name)
						.withSurname(familyName)
						.withEmail(email)
						.build();

			} else {
				log.error("Invalid Token!");
				throw new InvalidExternalTokenException();
			}
		} catch (Exception e) {
			log.error("Error during external validation. Message : " + e.getMessage());
			throw new ExternalTokenValidationException();
		}

		log.debug("delivering token validation result...");
		return loginDetails;
	}

}
