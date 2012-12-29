package com.tymchak.DAO.impl;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import com.tymchak.DAO.UserDAO;
import com.tymchak.entity.User;

@Stateless
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User readByPrincipal() {

		String login = ((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getUserPrincipal()
				.toString();

		return readByLogin(login);
	}

	@Override
	public boolean isLoginFree(String login) {
		return readByLogin(login) == null;
	}
	
	private User readByLogin(String login) {
		User user = null;
		TypedQuery<User> query = em.createQuery(
				"SELECT u FROM User u WHERE u.login = :login", User.class);
		query.setParameter("login", login);
		try {
			user = query.getResultList().get(0);
		} catch (IndexOutOfBoundsException e) {
			// logger.error(e.toString());
			// TODO
		}
		return user;
	}

}
