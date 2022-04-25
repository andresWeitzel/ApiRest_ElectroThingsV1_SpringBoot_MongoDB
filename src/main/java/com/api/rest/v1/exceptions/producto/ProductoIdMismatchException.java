package com.api.rest.v1.exceptions.producto;

public class ProductoIdMismatchException extends RuntimeException{
	
	public ProductoIdMismatchException() {

	}

	public ProductoIdMismatchException(String msj) {
		super(msj);
	}

	public ProductoIdMismatchException(Throwable cause) {
		super(cause);
	}

	public ProductoIdMismatchException(String msj, Throwable cause) {
		super(msj, cause);
	}
	
	 public ProductoIdMismatchException(String msj, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(msj, cause, enableSuppression, writableStackTrace);
	    }
}
