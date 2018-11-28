package com.bibliotheques.ws.batch;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;

@Named("configuration")
public class Configuration {
	
	@Value("${adresse.editionService}")
	private String adresseEditionService;
	
	@Value("${adresse.utilisateurService}")
	private String adresseUtilisateurService;
	
	@Value("${mail.titre}")
	private String titre;
	
	@Value("${mail.premier.message}")
	private String premierMessage;
	
	@Value("${mail.deuxieme.message}")
	private String deuxiemeMessage;
	
	@Value("${mail.conclusion}")
	private String conclusion;
	
	@Value("${mail.signature}")
	private String signature;
	
	@Value("${spring.mail.host}")
	private String serveurSmtpHost;
	
	@Value("${spring.mail.port}")
	private int serveurSmtpPort;
	
	@Value("${spring.mail.username}")
	private String serveurSmtpUsername;
	
	@Value("${spring.mail.password}")
	private String serveurSmtpPassword;
	
	@Value("${spring.mail.protocol}")
	private String serveurSmtpProtocol;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String serveurSmtpAuth;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String serveurSmtpTlsEnable;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.required}")
	private String serveurSmtpTlsRequired;
	
	@Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
	private String serveurSmtpConnectionTimeout;
	
	@Value("${spring.mail.properties.mail.smtp.timeout}")
	private String serveurSmtpTimeout;
	
	@Value("${spring.mail.properties.mail.smtp.writetimeout}")
	private String serveurSmtpWriteTimeout;
	
	@Value("${spring.mail.properties.mail.debug}")
	private String serveurSmtpDebug;
	
	@Value("${spring.mail.default-encoding}")
	private String serveurSmtpDefaultEncoding;

	//On a uniquement besoin des getters.
	public String getAdresseEditionService() {
		return adresseEditionService;
	}

	public String getAdresseUtilisateurService() {
		return adresseUtilisateurService;
	}

	public String getTitre() {
		return titre;
	}

	public String getPremierMessage() {
		return premierMessage;
	}

	public String getDeuxiemeMessage() {
		return deuxiemeMessage;
	}

	public String getConclusion() {
		return conclusion;
	}

	public String getSignature() {
		return signature;
	}

	public String getServeurSmtpHost() {
		return serveurSmtpHost;
	}

	public int getServeurSmtpPort() {
		return serveurSmtpPort;
	}

	public void setServeurSmtpPort(int serveurSmtpPort) {
		this.serveurSmtpPort = serveurSmtpPort;
	}

	public String getServeurSmtpUsername() {
		return serveurSmtpUsername;
	}

	public String getServeurSmtpPassword() {
		return serveurSmtpPassword;
	}
	
	public String getServeurSmtpProtocol() {
		return serveurSmtpProtocol;
	}

	public String getServeurSmtpAuth() {
		return serveurSmtpAuth;
	}

	public String getServeurSmtpTlsEnable() {
		return serveurSmtpTlsEnable;
	}

	public String getServeurSmtpTlsRequired() {
		return serveurSmtpTlsRequired;
	}

	public String getServeurSmtpConnectionTimeout() {
		return serveurSmtpConnectionTimeout;
	}

	public String getServeurSmtpTimeout() {
		return serveurSmtpTimeout;
	}

	public String getServeurSmtpWriteTimeout() {
		return serveurSmtpWriteTimeout;
	}

	public String getServeurSmtpDebug() {
		return serveurSmtpDebug;
	}

	public String getServeurSmtpDefaultEncoding() {
		return serveurSmtpDefaultEncoding;
	}
}