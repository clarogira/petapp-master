package com.petapp.repository.filter;

import com.petapp.model.Cor;
import com.petapp.model.Raca;
import com.petapp.model.Cliente;

public class AnimalFilter {
private String nome;
private Cliente cliente;
private Raca raca;
private Cor cor;
private String observacao;

public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}

public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
public Raca getRaca() {
	return raca;
}
public void setRaca(Raca raca) {
	this.raca = raca;
}
public Cor getCor() {
	return cor;
}
public void setCor(Cor cor) {
	this.cor = cor;
}
public String getObservacao() {
	return observacao;
}
public void setObservacao(String observacao) {
	this.observacao = observacao;
}


}
