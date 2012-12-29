package com.tymchak.DAO.impl;

import javax.ejb.Stateless;
import com.tymchak.DAO.StatusDAO;
import com.tymchak.entity.Status;

@Stateless
public class StatusDAOImpl extends GenericDAOImpl<Status> implements StatusDAO {

	public StatusDAOImpl() {
		super(Status.class);
	}
}
