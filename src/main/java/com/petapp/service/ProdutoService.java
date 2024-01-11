package com.petapp.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petapp.model.Produto;
import com.petapp.repository.ProdutoRepository;
import com.petapp.service.event.produto.ProdutoSalvoEvent;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
import com.petapp.service.exception.NomeProdutoJaCadastradoException;
import com.petapp.storage.FotoStorage;
@Service
public class ProdutoService {
@Autowired
private ProdutoRepository pr;
@Autowired
private ApplicationEventPublisher publisher;
@Autowired
private FotoStorage fotoStorage;

@Transactional
public void salvar(Produto produto) {
	
	Optional<Produto> produtoOptional = pr.findByNomeIgnoreCase(produto.getNome());
	
	
    if (produtoOptional.isPresent()) {
		throw new NomeProdutoJaCadastradoException("Produto já  cadastrado no banco de dados");
	}
    
	pr.save(produto);
	publisher.publishEvent(new ProdutoSalvoEvent(produto));
}
@Transactional
public void excluir(Produto produto) {
	
		try {
			String foto = produto.getFoto();
			pr.delete(produto);
			pr.flush();
			fotoStorage.excluir(foto);
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar produto. Já foi usada em alguma venda.");
		}
	}
	
}