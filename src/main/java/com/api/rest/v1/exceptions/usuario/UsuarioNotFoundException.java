package com.api.rest.v1.exceptions.usuario;

public class UsuarioNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException() {
	}

	public UsuarioNotFoundException(String msj) {
		super(msj);
	}

	public UsuarioNotFoundException(String msj, Throwable cause) {
		super(msj, cause);
	}

	public UsuarioNotFoundException(String msj, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(msj, cause, enableSuppression, writableStackTrace);
	}

}
