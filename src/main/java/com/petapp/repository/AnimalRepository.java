package com.petapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petapp.model.Animal;
import com.petapp.repository.helper.animal.AnimalRepositoryQueries;

public interface AnimalRepository extends JpaRepository<Animal, Long>, AnimalRepositoryQueries {
	
     Animal findByCodigo(Long codigo);

//	Iterable<Animal> findByNomeContainingIgnoreCase(String nome);
	public List<Animal> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

	

	
	
	
	
	
}
