package com.petapp.repository.helper.animal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petapp.model.Animal;
import com.petapp.repository.filter.AnimalFilter;

public interface AnimalRepositoryQueries {
	
 public Page<Animal> filtrar(AnimalFilter filtro, Pageable pageable);
}
