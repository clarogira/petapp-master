package com.petapp.dto;

public class VendaDia {

	private String dia;
	private Integer total;

	public VendaDia() {
	}

	public VendaDia(String mes, Integer total) {
		this.dia = mes;
		this.total = total;
	}

	public String getMes() {
		return dia;
	}

	public void setMes(String mes) {
		this.dia = mes;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
