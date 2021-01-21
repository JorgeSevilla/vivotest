package br.com.vivo.telefonica.util;

public class CustomErrorType {
	private String errorMessages;
	
	public CustomErrorType(String errorMessages) {
		super();
		this.errorMessages = errorMessages;
	}
	
	public String getErrorMessages() {
		return errorMessages;
	}
	
	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}

}
