package com.altimetrik.dao;

import java.util.List;

import com.altimetrik.dao.DatabaseException;
/**
 * Interface for data layer classes
 * @author kdevkar
 *
 * @param <E>
 */
public interface DataAccess<E> {
	void addToDatabase(E obj) throws DatabaseException;
	E getFromDatabase(int id) throws DatabaseException;
	List<E> getAllFromDatabase() throws DatabaseException;
}
