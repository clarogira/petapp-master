package com.petapp.service.exception;

public class NomeServicoJaCadastradoException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public NomeServicoJaCadastradoException(String message) {
		super(message);
	}

}