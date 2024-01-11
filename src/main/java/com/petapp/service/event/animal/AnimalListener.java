package com.petapp.service.event.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.petapp.storage.FotoStorage;

@Component
public class AnimalListener {
	@Autowired
	private FotoStorage fotoStorage;
	private String foto;
	
@EventListener(condition="#evento.temFoto() and #evento.novaFoto")
public void animalSalvo(AnimalSalvoEvent evento) {
	foto=evento.getAnimal().getFoto();
	System.out.println("tem foto>>>>>>>>>>>>>>>>> -"+ evento.getAnimal().getFoto());
	fotoStorage.salvar(foto);
}
}
