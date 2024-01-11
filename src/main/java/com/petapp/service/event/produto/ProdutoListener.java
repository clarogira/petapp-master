package com.petapp.service.event.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.petapp.storage.FotoStorage;

@Component
public class ProdutoListener {
	@Autowired
	private FotoStorage fotoStorage;
	private String foto;
	
	@EventListener(condition="#evento.temFoto() and #evento.novaFoto")
public void animalSalvo(ProdutoSalvoEvent evento) {
	foto=evento.getProduto().getFoto();
	System.out.println("tem foto>>>>>>>>>>>>>>>>> -"+ evento.getProduto().getFoto());
	fotoStorage.salvar(foto);
}
}
