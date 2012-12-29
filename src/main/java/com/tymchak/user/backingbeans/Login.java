package com.tymchak.user.backingbeans;

import javax.faces.context.FacesContext;

public class Login {

	private String message;

	public String getMessage() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		message = (String) facesContext.getExternalContext()
				.getRequestParameterMap().get("message");
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
