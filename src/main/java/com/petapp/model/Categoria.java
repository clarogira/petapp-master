package com.petapp.model;

public enum Categoria {
	
	ACESSORIO("Acessório"),
	HIGIENE("Higiene"),
	PETISCO("Petisco"),
	RACAO("Ração"),
	REMEDIO("Remédio"),
	BANHOETOSA("Banho&Tosa");

	private String categoria;

	private Categoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
