package com.petapp.model;

public enum FormaDePagamento {
	
	ZERO("Selecione"),
	CREDITO("Crédito"),
	DEBITO("Débito"),
	DINHEIRO("Dinheiro"),
	FIADO("Notinha");
	
	private String descricao;

	private FormaDePagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	}
