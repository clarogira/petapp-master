package com.petapp.repository.helper.servico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petapp.model.Servico;
import com.petapp.repository.filter.ServicoFilter;

public interface ServicoRepositoryQueries {
	public Page<Servico> filtrar(ServicoFilter filtro, Pageable pageable);
}
