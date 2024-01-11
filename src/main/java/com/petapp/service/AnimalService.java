package com.petapp.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petapp.model.Animal;
import com.petapp.repository.AnimalRepository;
import com.petapp.service.event.animal.AnimalSalvoEvent;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
import com.petapp.storage.FotoStorage;
@Service
public class AnimalService {
@Autowired
private AnimalRepository ar;
@Autowired
private ApplicationEventPublisher publisher;

@Autowired
private FotoStorage fotoStorage;

@Transactional
public void salvar(Animal animal) {
	ar.save(animal);
	publisher.publishEvent(new AnimalSalvoEvent(animal));

}

@Transactional
public void excluir(Animal animal) {
	
		try {
			String foto = animal.getFoto();
			ar.delete(animal);
			ar.flush();
			fotoStorage.excluir(foto);
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar Animal. Consta em alguum Banho.");
		}
	}
}
