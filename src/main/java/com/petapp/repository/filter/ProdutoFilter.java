package com.petapp.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.petapp.model.Categoria;
import com.petapp.model.Fornecedor;

public class ProdutoFilter {
	private String nome;

	private String descricao;

	private Integer estoqueDe;
	private Integer estoqueAte;
	
	private Categoria categoria;

	private BigDecimal pesoDe;
	
	private BigDecimal pesoAte;
	
	private LocalDate validadeDe;
	
	private LocalDate validadeAte;
	
	private BigDecimal valorCompraAte;
	
	private BigDecimal valorCompraDe;
	
	private BigDecimal valorVendaDe;
	
	private BigDecimal valorVendaAte;
	
	private Fornecedor fornecedor;

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

	public Integer getEstoqueDe() {
		return estoqueDe;
	}

	public void setEstoqueDe(Integer estoqueDe) {
		this.estoqueDe = estoqueDe;
	}

	public Integer getEstoqueAte() {
		return estoqueAte;
	}

	public void setEstoqueAte(Integer estoqueAte) {
		this.estoqueAte = estoqueAte;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	

	public BigDecimal getPesoDe() {
		return pesoDe;
	}

	public void setPesoDe(BigDecimal pesoDe) {
		this.pesoDe = pesoDe;
	}

	public BigDecimal getPesoAte() {
		return pesoAte;
	}

	public void setPesoAte(BigDecimal pesoAte) {
		this.pesoAte = pesoAte;
	}

	public LocalDate getValidadeDe() {
		return validadeDe;
	}

	public void setValidadeDe(LocalDate validadeDe) {
		this.validadeDe = validadeDe;
	}

	public LocalDate getValidadeAte() {
		return validadeAte;
	}

	public void setValidadeAte(LocalDate validadeAte) {
		this.validadeAte = validadeAte;
	}

	public BigDecimal getValorCompraAte() {
		return valorCompraAte;
	}

	public void setValorCompraAte(BigDecimal valorCompraAte) {
		this.valorCompraAte = valorCompraAte;
	}

	public BigDecimal getValorCompraDe() {
		return valorCompraDe;
	}

	public void setValorCompraDe(BigDecimal valorCompraDe) {
		this.valorCompraDe = valorCompraDe;
	}

	public BigDecimal getValorVendaDe() {
		return valorVendaDe;
	}

	public void setValorVendaDe(BigDecimal valorVendaDe) {
		this.valorVendaDe = valorVendaDe;
	}

	public BigDecimal getValorVendaAte() {
		return valorVendaAte;
	}

	public void setValorVendaAte(BigDecimal valorVendaAte) {
		this.valorVendaAte = valorVendaAte;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
}
