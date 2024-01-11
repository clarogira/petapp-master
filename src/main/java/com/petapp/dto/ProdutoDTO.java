package com.petapp.dto;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

public class ProdutoDTO {
	private Long codigo;
	private String nome;
	private String foto;
	private BigDecimal valorVenda;
	private BigDecimal peso;
	

	public ProdutoDTO(Long codigo, String nome, String foto, BigDecimal valorVenda, BigDecimal peso) {
		
		this.codigo = codigo;
		this.nome = nome;
		this.foto = StringUtils.isEmpty(foto) ? "semfoto.jpg" : foto;
		this.valorVenda = valorVenda;
		this.peso = peso;
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	

}
