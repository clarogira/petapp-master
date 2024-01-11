package com.petapp.model;

public enum Status {
	ABERTO("Aberto"),
	AGENDADO("Agendado"),
	PENDENTE("Pendente"),
	FECHADO("Fechado"), 
	CANCELADA ("Cancelado");
	
	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}