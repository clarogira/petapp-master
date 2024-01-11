package com.petapp.service.event.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.petapp.model.ItemVenda;
import com.petapp.model.Produto;
import com.petapp.repository.ProdutoRepository;

@Component
public class VendaListener {

	@Autowired
	private ProdutoRepository pr;
	
	@EventListener
	public void vendaFechada(VendaEvent vendaEvent) {
		for (ItemVenda item : vendaEvent.getVenda().getItens()) {
			Produto produto = pr.findByCodigo(item.getProduto().getCodigo());
			produto.setEstoque(produto.getEstoque() - item.getQuantidade());
			pr.save(produto);
		}
	}
	
}
