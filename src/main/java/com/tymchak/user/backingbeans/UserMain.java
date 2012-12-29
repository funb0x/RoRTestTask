package com.tymchak.user.backingbeans;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.transaction.UserTransaction;
import org.richfaces.component.UIExtendedDataTable;

import com.tymchak.DAO.ProjectDAO;
import com.tymchak.DAO.StatusDAO;
import com.tymchak.DAO.TaskDAO;
import com.tymchak.DAO.UserDAO;
import com.tymchak.entity.Project;
import com.tymchak.entity.Task;
import com.tymchak.entity.User;
import com.tymchak.utils.JsfUtil;

//import com.tymchak.utils.Statuses;

public class UserMain {

	@EJB
	private UserDAO userDAO;
	@EJB
	private ProjectDAO projectDAO;
	@EJB
	private TaskDAO taskDAO;
	@EJB
	private StatusDAO statusDAO;
	@Resource
	private UserTransaction ut;

	// @EJB
	// private Statuses statuses;

	private DataModel<Project> projects;
	private DataModel<Task> tasks;

	private Collection<Object> selection;

	private List<SelectItem> statuses;

	private Project selectedProject;
	private Project tempProject;
	private Project newProject;

	private Task newTask;
	private Task tempTask;

	@SuppressWarnings("unused")
	private String loggedUser;
	
	public UserMain() {
		projects = new ListDataModel<>();
		tasks = new ListDataModel<>();

		newProject = new Project();
		newTask = new Task();

		// statuses = Statuses.getInstance();
	}

	public void addNewProject() {

		User user = userDAO.readByPrincipal();
		newProject.setUser(user);

		projectDAO.create(newProject);

		newProject.setProjectId(null);
		newProject.setProjectName(null);
		newProject.setProjectDescription(null);
		newProject.setUser(null);
	}

	public void deleteProject() {
		try {
			try {
				ut.begin();
				List<Task> taskList = taskDAO
						.getProjectTasks(tempProject, 0, 0);

				for (Task task : taskList) {
					taskDAO.delete(task);
				}

				projectDAO.delete(tempProject);
				ut.commit();
			} catch (Exception e) {
				ut.rollback();
			}
		} catch (Exception e) {

		}
	}

	public void editProject() {
		projectDAO.update(tempProject);
	}

	public void onRowSelect(AjaxBehaviorEvent event) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();

		for (Object selectionKey : selection) {
			dataTable.setRowKey(selectionKey);

			if (dataTable.isRowAvailable()) {
				selectedProject = (Project) dataTable.getRowData();
			}
		}
	}

	public void initProject() {
		tempProject = projects.getRowData();
	}

	public void addNewTask() {
		newTask.setProject(tempProject);
		newTask.setStatus(statusDAO.getAll().get(0));
				
		taskDAO.create(newTask);
		
		newTask.setProject(null);
		newTask.setStatus(null);
		newTask.setTaskDeadline(null);
		newTask.setTaskDescription(null);
		newTask.setTaskId(null);
		newTask.setTaskName(null);
		newTask.setTaskPriority(null);

	}

	public void deleteTask() {
		taskDAO.delete(tempTask);
	}

	public void editTask() {
		taskDAO.update(tempTask);
		System.out.println("tempTask = " + tempTask);
	}

	public void initTask() {
		tempTask = tasks.getRowData();
	}

	public String logout() {
		JsfUtil.getSession().invalidate();
		return "ok";
	}

	// gs

	public DataModel<Project> getProjects() {

		User user = userDAO.readByPrincipal();

		projects.setWrappedData(projectDAO.getUserProjects(user, 0, 0));
		return projects;
	}

	public void setProjects(DataModel<Project> projects) {
		this.projects = projects;
	}

	public DataModel<Task> getTasks() {
		tasks.setWrappedData(taskDAO.getProjectTasks(selectedProject, 0, 0));
		return tasks;
	}

	public void setTasks(DataModel<Task> tasks) {
		this.tasks = tasks;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		// this.selectedProject = projects.getRowData();
	}

	public Project getNewProject() {
		return newProject;
	}

	public void setNewProject(Project newProject) {
		this.newProject = newProject;
	}

	public Project getTempProject() {
		return tempProject;
	}

	public void setTempProject(Project tempProject) {
		this.tempProject = tempProject;
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public Task getNewTask() {
		return newTask;
	}

	public void setNewTask(Task newTask) {
		this.newTask = newTask;
	}

	public Task getTempTask() {
		return tempTask;
	}

	public void setTempTask(Task tempTask) {
		this.tempTask = tempTask;
	}

	public List<SelectItem> getStatuses() {
		if (statuses == null) {
			statuses = JsfUtil.getSelectItems(statusDAO.getAll());
		}
		return statuses;
	}

	public void setStatuses(List<SelectItem> statuses) {
		this.statuses = statuses;
	}

	public String getLoggedUser() {
		return  JsfUtil.getRequest().getUserPrincipal().toString();
	}

	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}
	
}
