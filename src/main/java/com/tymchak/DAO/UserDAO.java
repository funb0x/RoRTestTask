package com.tymchak.DAO;

import com.tymchak.entity.User;

public interface UserDAO extends GenericDAO<User> {
	
	public abstract User readByPrincipal();
	
	public abstract boolean isLoginFree(String login);
}
