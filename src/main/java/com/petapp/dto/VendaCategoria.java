package com.petapp.dto;

public class VendaCategoria {
	public String mes;
	public Integer total_acessorio;
	public Integer total_banho;
	public Integer total_higiene ;
	public Integer total_petisco; 
	public Integer total_racao ;
	public Integer total_remedio;
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Integer getTotal_acessorio() {
		return total_acessorio;
	}
	public void setTotal_acessorio(Integer total_acessorio) {
		this.total_acessorio = total_acessorio;
	}
	public Integer getTotal_banho() {
		return total_banho;
	}
	public void setTotal_banho(Integer total_banho) {
		this.total_banho = total_banho;
	}
	public Integer getTotal_higiene() {
		return total_higiene;
	}
	public void setTotal_higiene(Integer total_higiene) {
		this.total_higiene = total_higiene;
	}
	public Integer getTotal_petisco() {
		return total_petisco;
	}
	public void setTotal_petisco(Integer total_petisco) {
		this.total_petisco = total_petisco;
	}
	public Integer getTotal_racao() {
		return total_racao;
	}
	public void setTotal_racao(Integer total_racao) {
		this.total_racao = total_racao;
	}
	public Integer getTotal_remedio() {
		return total_remedio;
	}
	public void setTotal_remedio(Integer total_remedio) {
		this.total_remedio = total_remedio;
	}
	public VendaCategoria() {
	
	}
	public VendaCategoria(String mes, Integer total_acessorio, Integer total_banho, Integer total_higiene,
			Integer total_petisco, Integer total_racao, Integer total_remedio) {
		super();
		this.mes = mes;
		this.total_acessorio = total_acessorio;
		this.total_banho = total_banho;
		this.total_higiene = total_higiene;
		this.total_petisco = total_petisco;
		this.total_racao = total_racao;
		this.total_remedio = total_remedio;
	}

}
