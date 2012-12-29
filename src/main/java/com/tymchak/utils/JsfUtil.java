package com.tymchak.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class JsfUtil {
	
	private JsfUtil(){}
	
	public static List<SelectItem> getSelectItems(List<?> entities) {
	    
	    List<SelectItem> items = new ArrayList<SelectItem>(entities.size());
	    for (Object x : entities) {
	        items.add(new SelectItem(x, x.toString()));
	    }
	    return items;
	}
	
	public static FacesContext getContext(){
		return FacesContext.getCurrentInstance();
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest) getContext().getExternalContext().getRequest();
	}
	
	public static void createSession(){
		getRequest().getSession(true);
	}
	
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
		
	public static void addMessage(String dest, String message){
		getContext().addMessage(dest, new FacesMessage(message));	
	}
		
}
