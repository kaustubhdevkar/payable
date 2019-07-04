package com.altimetrik.view;

import java.util.List;

public interface Viewable<E> {
	void list(E obj);
	void listAll(List<E> obj);
	E takeInput();
	void printMessage(String message);
	
}
