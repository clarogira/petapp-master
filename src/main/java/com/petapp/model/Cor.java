package com.petapp.model;

public enum Cor {
	
AMARELO("Amarelo"),
BRANCO("Branco"),
CASTANHO("Castanho"),
CHOCOLATE("Chocolate"),
CINZENTO("Cinzento"),
CREME("Creme"),
DOURADO("Dourado"),
PRETO("Preto"),
VERMELHO("Vermelho"),
BICOLOR("Bicolor"),
TIGRADO("Tigrado"),
TRICOLOR("Tricolor");
	
	public String cor;

	private Cor(String cor) {
		this.cor = cor;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
	
}
