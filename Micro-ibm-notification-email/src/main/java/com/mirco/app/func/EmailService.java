package com.mirco.app.func;


public interface EmailService {
	
	/**
	 * Send simple email
	 * @param sendTo
	 * @param titel
	 * @param content
	 */
	public void sendSimpleMail(String sendTo, String titel, String content);
	
	/**
	 * Send html email
	 * @param sendTo
	 * @param titel
	 * @param content
	 */
	public void sendHtmlMail(String sendTo, String subject, String content);
}
