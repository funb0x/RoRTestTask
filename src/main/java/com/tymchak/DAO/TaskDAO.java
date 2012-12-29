package com.tymchak.DAO;

import java.util.List;

import com.tymchak.entity.Project;
import com.tymchak.entity.Task;

public interface TaskDAO extends GenericDAO<Task> {

	public abstract List<Task> getProjectTasks(Project project, int first,
			int size);

	public abstract int getProjectTasksCount(Project project);
}
