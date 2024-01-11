package com.petapp.dto;

public class ValorItensEstoque {

	private long valor;
	private long totalItens;
	public ValorItensEstoque(long valor, long totalItens) {
		
		this.valor = valor;
		this.totalItens = totalItens;
	}
	public ValorItensEstoque() {
		
	}
	public long getValor() {
		return valor;
	}
	public void setValor(long valor) {
		this.valor = valor;
	}
	public long getTotalItens() {
		return totalItens;
	}
	public void setTotalItens(long totalItens) {
		this.totalItens = totalItens;
	}
		
	
}
