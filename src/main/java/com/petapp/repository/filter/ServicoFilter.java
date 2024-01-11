package com.petapp.repository.filter;

import java.math.BigDecimal;


public class ServicoFilter {
private String nome;
	
private BigDecimal valorDe;
private BigDecimal valorAte;
	
private String descricao;

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getDescricao() {
	return descricao;
}

public void setDescricao(String descricao) {
	this.descricao = descricao;
}

public BigDecimal getValorDe() {
	return valorDe;
}

public void setValorDe(BigDecimal valorDe) {
	this.valorDe = valorDe;
}

public BigDecimal getValorAte() {
	return valorAte;
}

public void setValorAte(BigDecimal valorAte) {
	this.valorAte = valorAte;
}


}
