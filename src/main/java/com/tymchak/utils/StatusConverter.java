package com.tymchak.utils;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.tymchak.DAO.StatusDAO;
import com.tymchak.entity.Status;

public class StatusConverter implements Converter {

	@EJB
	private StatusDAO statusDAO;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1,
			String statusID) {

		if (statusID == null || statusID.length() == 0)
			return null;
		return statusDAO.read(Integer.parseInt(statusID));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {

		if (object == null) {
			return null;
		}

		if (object instanceof Status) {
			Status status = (Status) object;
			return "" + status.getStatusId();
		} else {
			throw new IllegalArgumentException("unexpected type: "
					+ object.getClass().getName());
		}
	}

}
