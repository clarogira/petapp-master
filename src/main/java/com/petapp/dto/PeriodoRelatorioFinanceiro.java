package com.petapp.dto;

import java.time.LocalDate;

import com.petapp.model.Situacao;
import com.petapp.model.Tipo;


public class PeriodoRelatorioFinanceiro {

	private LocalDate dataDe;
	private LocalDate dataAte;
	private Tipo tipo;  
    private Situacao situacao;
	
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
	public LocalDate getDataDe() {
		return dataDe;
	}
	public void setDataDe(LocalDate dataDe) {
		this.dataDe = dataDe;
	}
	public LocalDate getDataAte() {
		return dataAte;
	}
	public void setDataAte(LocalDate dataAte) {
		this.dataAte = dataAte;
	}

	
   
}
