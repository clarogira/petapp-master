package com.petapp.repository.filter;


import java.time.LocalDate;

import com.petapp.model.Fornecedor;
import com.petapp.model.Situacao;
import com.petapp.model.Tipo;
import com.petapp.model.TipoDePagamento;

public class TituloFilter {
	
    private String descricao;
    private LocalDate dataDeValidadeDe;
    private LocalDate dataDeValidadeAte;
    private TipoDePagamento tipoDePagamento;   
    private Tipo tipo;  
    private Situacao situacao;
    private Fornecedor fornecedor;
    
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	

	public LocalDate getDataDeValidadeDe() {
		return dataDeValidadeDe;
	}

	public void setDataDeValidadeDe(LocalDate dataDeValidadeDe) {
		this.dataDeValidadeDe = dataDeValidadeDe;
	}

	public LocalDate getDataDeValidadeAte() {
		return dataDeValidadeAte;
	}

	public void setDataDeValidadeAte(LocalDate dataDeValidadeAte) {
		this.dataDeValidadeAte = dataDeValidadeAte;
	}

	public TipoDePagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
    
}
