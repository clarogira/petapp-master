package com.petapp.service.exception;

public class NomeProdutoJaCadastradoException extends RuntimeException{
private static final long serialVersionUID = 1L;
	
	public NomeProdutoJaCadastradoException(String message) {
		super(message);
	}

}