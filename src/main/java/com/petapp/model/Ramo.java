package com.petapp.model;

public enum Ramo {
	
	ACESSORIO("Acessório"),
	HIGIENE("Higiene"),
	PETISCO("Petisco"),
	RACAO("Ração"),
	REMEDIO("Remédio"),
	BANHOETOSA("Banho&Tosa");

	private String ramo;

	private Ramo(String ramo) {
		this.ramo = ramo;
	}

	public String getRamo() {
		return ramo;
	}

	public void setCategoria(String ramo) {
		this.ramo = ramo;
	}
	
}
