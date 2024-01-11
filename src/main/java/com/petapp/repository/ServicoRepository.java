package com.petapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petapp.model.Servico;
import com.petapp.repository.helper.servico.ServicoRepositoryQueries;

public interface ServicoRepository extends JpaRepository<Servico, Long>,ServicoRepositoryQueries {
	public Optional<Servico> findByNomeIgnoreCase(String nome);
	Servico findByCodigo(Long codigo);
}
