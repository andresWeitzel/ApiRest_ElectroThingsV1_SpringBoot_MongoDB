package com.api.rest.v1.exceptions.producto;

public class ProductoValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ProductoValidationException() {

	}

	public ProductoValidationException(String msj) {
		super(msj);
	}

	public ProductoValidationException(Throwable cause) {
		super(cause);
	}

	public ProductoValidationException(String msj, Throwable cause) {
		super(msj, cause);
	}
	
	 public ProductoValidationException(String msj, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(msj, cause, enableSuppression, writableStackTrace);
	    }

}
