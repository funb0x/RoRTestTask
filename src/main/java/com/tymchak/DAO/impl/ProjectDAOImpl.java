package com.tymchak.DAO.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.tymchak.DAO.ProjectDAO;
import com.tymchak.entity.Project;
import com.tymchak.entity.User;

@Stateless
public class ProjectDAOImpl extends GenericDAOImpl<Project> implements
		ProjectDAO {

	public ProjectDAOImpl() {
		super(Project.class);
	}

	@Override
	public List<Project> getUserProjects(User user, int first, int size) {
		
		TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p WHERE p.user = :user",
				Project.class);
		query.setParameter("user", user);
		
		//query.setMaxResults(size).setFirstResult(first);
		
		List<Project> list = query.getResultList();
		return list;
	}

	@Override
	public int getUserProjectsCount(User user) {
		
		TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p WHERE p.user = :user",
				Project.class);
		query.setParameter("user", user);
		List<Project> list = query.getResultList();
		
		return list.size();
	}

}
