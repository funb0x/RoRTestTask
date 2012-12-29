package com.tymchak.utils;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;

import com.tymchak.DAO.StatusDAO;
import com.tymchak.entity.Status;

@Singleton
public class Statuses {

	@EJB
	private StatusDAO statusDAO;

	private static List<Status> statuses;

	public Status getStatuses(int a) {
		if (statuses == null) {
			statuses = statusDAO.getAll();
		}
		return statuses.get(a);
	}
}
