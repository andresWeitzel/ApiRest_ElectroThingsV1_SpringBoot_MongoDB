package com.api.rest.v1.security.exc.usuario;

public class UsuarioIdMismatchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioIdMismatchException() {
	}

	public UsuarioIdMismatchException(String msj) {
		super(msj);
	}

	public UsuarioIdMismatchException(String msj, Throwable cause) {
		super(msj, cause);
	}

	 public UsuarioIdMismatchException(String msj, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(msj, cause, enableSuppression, writableStackTrace);
	    }
}
