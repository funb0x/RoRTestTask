package com.tymchak.entity;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TASK")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TASK_ID")
	private Integer taskId;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "STATUS_ID")
	private Status status;

	@Column(name = "TASK_NAME")
	private String taskName;

	@Column(name = "TASK_PRIORITY")
	private Integer taskPriority;

	@Column(name = "TASK_DESCRIPTION")
	private String taskDescription;

	@Temporal(TemporalType.DATE)
	@Column(name = "TASK_DEADLINE")
	private Date taskDeadline;

	public Task() {
	}

	public Task(Project project, String name) {
		this.project = project;
		this.taskName = name;
	}

	public Task(Project project, Status status, String name, Integer priority,
			String description, Date deadline) {
		this.project = project;
		this.status = status;
		this.taskName = name;
		this.taskPriority = priority;
		this.taskDescription = description;
		this.taskDeadline = deadline;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(Integer taskPriority) {
		this.taskPriority = taskPriority;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public java.util.Date getTaskDeadline() {
		return taskDeadline;
	}

	public void setTaskDeadline(java.util.Date taskDeadline) {
		this.taskDeadline = taskDeadline;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project newProject) {
		this.project = newProject;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status newStatus) {
		this.status = newStatus;
	}

	@Override
	public String toString() {
		return taskId + " " + taskName + " " + taskPriority + " "
				+ taskDescription + " " + taskDeadline + " " + project + " "
				+ status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		return true;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((project == null) ? 0 : project.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Task other = (Task) obj;
//		if (project == null) {
//			if (other.project != null)
//				return false;
//		} else if (!project.equals(other.project))
//			return false;
//		return true;
//	}

}