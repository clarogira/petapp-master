package com.petapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petapp.dto.FornecedorDTO;
import com.petapp.model.Fornecedor;
import com.petapp.repository.helper.fornecedor.FornecedorRepositoryQueries;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>, FornecedorRepositoryQueries {
Fornecedor findByCodigo(Long codigo);
List<Fornecedor> findByNomeContainingIgnoreCase(@Param("nome") String nome);
List<Fornecedor> findAllByOrderByNomeAsc();
public Optional<Fornecedor> findByNomeIgnoreCase(String nome);


@Query("select e from Fornecedor e where nome like %?1% or ?1 is null order by e.nome")
Page<Fornecedor> porNome(String nome, Pageable pageable);

@Query("select new com.petapp.dto.FornecedorDTO(codigo, nome) from Fornecedor where lower(nome) like %?1%")
List<FornecedorDTO> filtradas(String nome);

}
