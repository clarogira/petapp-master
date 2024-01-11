package com.petapp.repository.helper.titulo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petapp.model.Titulo;
import com.petapp.repository.filter.TituloFilter;

public interface TituloRepositoryQueries {
	public Page<Titulo> filtrar(TituloFilter filtro, Pageable pageable);
}