package com.petapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petapp.model.Venda;
import com.petapp.repository.helper.venda.VendaRepositoryQueries;

public interface VendaRepository extends JpaRepository<Venda, Long>, VendaRepositoryQueries {
	Venda	findByCodigoOrderByCodigoDesc(Long codigo);
	

	


	
}
