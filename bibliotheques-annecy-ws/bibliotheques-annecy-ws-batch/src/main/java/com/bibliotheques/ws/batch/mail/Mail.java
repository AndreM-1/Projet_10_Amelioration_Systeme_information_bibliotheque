package com.bibliotheques.ws.batch.mail;

/**
 * Bean lié à la structure d'un mail
 * @author André Monnier
 *
 */
public class Mail implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6331046074112296668L;
	
	//==================== Attributs ====================
	private String to;
	private String subject;
	private String body;
	
	// ==================== Constructeurs ====================
	/**
	 * Constructeur par défaut.
	 */
	public Mail() { 
		
	}
	
	/**
	 * Constructeur avec paramètres.
	 * @param to : Le destinataire du mail
	 * @param subject : Le sujet (ou titre) du mail
	 * @param body : Le contenu du mail
	 */
	public Mail(String to, String subject, String body) {
		this.to=to;
		this.subject=subject;
		this.body=body;
	}
	
	// ==================== Getters/Setters ====================
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	// ==================== Méthodes ====================
	public String toString() {
		final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
		final String vSeparateur = ", ";
		vStB.append(" {")
		.append("to=").append(to)
		.append(vSeparateur).append("subject=\"").append(subject).append('"')
		.append(vSeparateur).append("body=\"").append(body).append('"')
		.append("}");
		return vStB.toString();
	}
}