package com.petapp.repository;

import com.petapp.model.Fornecedor;
import com.petapp.model.Titulo;
import com.petapp.repository.helper.titulo.TituloRepositoryQueries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long>, TituloRepositoryQueries{

    @Query(value = "select t from Titulo t join fetch t.tipoDePagamento " +
            "where t.descricao like %?1% and (t.fornecedor = ?2 or ?2 is null)",

            countQuery = "select count (t1) from Titulo as t1 " +
                    "where t1.descricao like %?1% and (t1.fornecedor = ?2 or ?2 is null)"
    )
    Page<Titulo> filtrados(String descricao, Fornecedor fornecedor, Pageable pageable);

    @Query("select distinct t.fornecedor from Titulo as t")
    List<Fornecedor> fornecedoresDosTitulos();
}
