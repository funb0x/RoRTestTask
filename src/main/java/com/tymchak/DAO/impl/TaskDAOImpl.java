package com.tymchak.DAO.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.tymchak.DAO.TaskDAO;
import com.tymchak.entity.Project;
import com.tymchak.entity.Task;

@Stateless
public class TaskDAOImpl extends GenericDAOImpl<Task> implements TaskDAO {

	public TaskDAOImpl() {
		super(Task.class);
	}

	@Override
	public List<Task> getProjectTasks(Project project, int first, int size) {
		
		TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.project = :project",
				Task.class);
		query.setParameter("project", project);
		//query.setMaxResults(size).setFirstResult(first);
		List<Task> list = query.getResultList();	
		return list;
	}

	@Override
	public int getProjectTasksCount(Project project) {
		
		TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.project = :project",
				Task.class);
		query.setParameter("project", project);
		List<Task> list = query.getResultList();
		
		return list.size();
	}

}
