package com.tymchak.DAO;

import java.util.List;

import com.tymchak.entity.Project;
import com.tymchak.entity.User;

public interface ProjectDAO extends GenericDAO<Project> {

	public abstract List<Project> getUserProjects(User user, int first, int size);

	public abstract int getUserProjectsCount(User user);
}
