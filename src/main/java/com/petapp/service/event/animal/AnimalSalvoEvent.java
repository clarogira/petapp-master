package com.petapp.service.event.animal;

import org.springframework.util.StringUtils;

import com.petapp.model.Animal;

public class AnimalSalvoEvent {
private Animal animal;

public AnimalSalvoEvent(Animal animal) {
	super();
	this.animal = animal;
	
}
public Animal getAnimal() {
	return animal;
}

public void setAnimal(Animal animal) {
	this.animal = animal;
}
public boolean temFoto() {
	return !StringUtils.isEmpty(animal.getFoto());
}
public boolean isNovaFoto() {
	return animal.isNovaFoto();
}
}
