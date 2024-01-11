package com.petapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petapp.model.Fornecedor;
import com.petapp.model.Produto;
import com.petapp.repository.helper.produto.ProdutoRepositoryQueries;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoRepositoryQueries {
	Produto findByCodigo(Long codigoProduto);

 List<Produto> findByNomeContainingIgnoreCase(@Param("nome") String nome);
 public Optional<Produto> findByNomeIgnoreCase(String nome);
	
	 @Query("select  p.fornecedor from Produto as p")
	 List<Fornecedor> fornecedoresComProdutos();

	
}
   