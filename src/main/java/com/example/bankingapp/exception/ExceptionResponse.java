package com.example.bankingapp.exception;

public class ExceptionResponse {
	private String errorMessage;
	private String requestedURI;
	private String timeStamp;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getRequestedURI() {
		return requestedURI;
	}
	public void setRequestedURI(String requestedURI) {
		this.requestedURI = requestedURI;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public ExceptionResponse(String errorMessage, String requestedURI, String timeStamp) {
		super();
		this.errorMessage = errorMessage;
		this.requestedURI = requestedURI;
		this.timeStamp = timeStamp;
	}
	public ExceptionResponse() {
		super();
	}
	

}

