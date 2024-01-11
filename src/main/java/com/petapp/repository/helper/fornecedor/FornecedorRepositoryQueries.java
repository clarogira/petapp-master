package com.petapp.repository.helper.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petapp.model.Fornecedor;
import com.petapp.repository.filter.FornecedorFilter;

public interface FornecedorRepositoryQueries {
	
	public Page<Fornecedor> filtrar(FornecedorFilter filtro, Pageable pageable);
}
