package com.petapp.service.event.produto;

import org.springframework.util.StringUtils;

import com.petapp.model.Produto;

public class ProdutoSalvoEvent {
private Produto produto;

public ProdutoSalvoEvent(Produto produto) {
	super();
	this.produto = produto;
	
}
public Produto getProduto() {
	return produto;
}

public void setProduto(Produto produto) {
	this.produto = produto;
}
public boolean temFoto() {
	return !StringUtils.isEmpty(produto.getFoto());
}
public boolean isNovaFoto() {
	return produto.isNovaFoto();
}
}