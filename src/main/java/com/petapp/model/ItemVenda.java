package com.petapp.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	private Integer quantidade;
	private BigDecimal valorUnitario;
	private BigDecimal lucroUnitario;
	
	@ManyToOne
	@JoinColumn(name = "codigo_produto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "codigo_venda")
	private Venda venda;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public BigDecimal getLucroUnitario() {
		return lucroUnitario;
	}

	public void setLucroUnitario(BigDecimal lucroUnitario) {
		this.lucroUnitario = lucroUnitario;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public BigDecimal getValorTotal(){
		return valorUnitario.multiply(new BigDecimal(quantidade));
	}
	public BigDecimal getLucroTotal(){
		return lucroUnitario.multiply(new BigDecimal(quantidade));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}
