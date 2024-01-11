package com.petapp.service.event.venda;

import com.petapp.model.Venda;

public class VendaEvent  {

	private Venda venda;

	public VendaEvent(Venda venda) {
		this.venda = venda;
	}

	public Venda getVenda() {
		return venda;
	}

}