package com.petapp.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petapp.model.Fornecedor;
import com.petapp.repository.FornecedorRepository;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
import com.petapp.service.exception.NomeFornecedorJaCadastradoException;
@Service
public class FornecedorService {
@Autowired
private FornecedorRepository fr;
@Transactional
public void salvar(Fornecedor fornecedor) {
	Optional<Fornecedor> fornecedorOptional = fr.findByNomeIgnoreCase(fornecedor.getNome());
	if (fornecedorOptional.isPresent()) {
		throw new NomeFornecedorJaCadastradoException("Fornecedor já  cadastrado no banco de dados");
	}          
	
	fr.save(fornecedor);
}

@Transactional
public void excluir(Fornecedor fornecedor) {
	
		try {
			
			fr.delete(fornecedor);
			fr.flush();
			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar fornecedor. Já foi usada em alguma produto.");
		}
	}
}
