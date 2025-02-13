package com.ticarum.apirest.excepciones;

public class NoExisteHistoricoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoExisteHistoricoException(String msg) {
		super(msg);
	}
	
}
