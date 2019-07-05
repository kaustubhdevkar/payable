package com.altimetrik.view;

import java.util.List;
/**
 * This is interface for view classes
 * @author kdevkar
 *
 * @param <E>
 */
public interface Viewable<E> {
	void list(E obj);
	void listAll(List<E> obj);
	E takeInput();
	void printMessage(String message);
	
}
