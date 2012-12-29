package com.tymchak.DAO;

import java.util.List;

public interface GenericDAO<T> {

	public void create(T newInstance);

	public T read(int id);

	public void update(T transientObject);

	public void delete(T persistentObject);
	
	public List<T> getAll();
	
	public int count();
}
