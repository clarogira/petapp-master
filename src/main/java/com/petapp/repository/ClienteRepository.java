package com.petapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petapp.model.Cliente;
import com.petapp.repository.helper.cliente.ClienteRepositoryQueries;

public interface ClienteRepository extends JpaRepository<Cliente, Long>, ClienteRepositoryQueries {
	Cliente findByCodigo(Long codigo);

	public List<Cliente> findByNomeContainingIgnoreCase(String nome);
	public Optional<Cliente> findByNomeIgnoreCase(String nome);
	public List<Cliente> findAllByOrderByNomeAsc();
	
}
