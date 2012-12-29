package com.tymchak.user.backingbeans;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.tymchak.DAO.UserDAO;
import com.tymchak.entity.User;
import com.tymchak.utils.JsfUtil;

public class Register {

	@EJB
	private UserDAO userDAO;

	private String login;
	private String pass;
	private String message;

	public Register() {
		message = (String) JsfUtil.getContext().getExternalContext()
				.getRequestParameterMap().get("message");
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String tryRegister() {

		if (userDAO.isLoginFree(login)) {
			userDAO.create(new User(login, pass, "USER"));
			HttpServletRequest request = JsfUtil.getRequest();

			try {
				request.login(login, pass);
			} catch (ServletException e) {
				message = "ooops. Something gone wrong...";
				return "fail";
			}
			return "success";
		} else {
			message = "login " + login + " is currently used!!!";
		}
		return "fail";
	}
}
