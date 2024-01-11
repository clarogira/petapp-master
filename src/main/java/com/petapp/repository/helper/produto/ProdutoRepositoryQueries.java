package com.petapp.repository.helper.produto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petapp.dto.ProdutoDTO;
import com.petapp.dto.ValorItensEstoque;
import com.petapp.model.Produto;
import com.petapp.repository.filter.ProdutoFilter;


public interface ProdutoRepositoryQueries {
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);
	
	public List<ProdutoDTO> porNome(String pesquisaPorNome);

	public ValorItensEstoque valorItensEstoque();
}
