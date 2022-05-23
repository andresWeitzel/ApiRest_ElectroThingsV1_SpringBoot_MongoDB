package com.api.rest.v1.exceptions.producto;

public class ProductoNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductoNotFoundException() {

	}

	public ProductoNotFoundException(String msj) {
		super(msj);
	}

	public ProductoNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProductoNotFoundException(String msj, Throwable cause) {
		super(msj, cause);
	}
	
	 public ProductoNotFoundException(String msj, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(msj, cause, enableSuppression, writableStackTrace);
	    }
}
