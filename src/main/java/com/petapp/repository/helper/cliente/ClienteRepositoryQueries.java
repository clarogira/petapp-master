package com.petapp.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petapp.model.Cliente;
import com.petapp.repository.filter.ClienteFilter;

public interface ClienteRepositoryQueries {
	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
}
