package com.ticarum.apirest.excepciones;

public class NoExisteEntidadException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoExisteEntidadException(String msg) {
		super(msg);
	}
}
