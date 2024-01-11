package com.petapp.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petapp.model.Cliente;
import com.petapp.repository.ClienteRepository;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
import com.petapp.service.exception.NomeClienteJaCadastradoException;
@Service
public class ClienteService {
@Autowired
private ClienteRepository cr;
@Transactional
public void salvar(Cliente cliente) {
	Optional<Cliente> clienteOptional = cr.findByNomeIgnoreCase(cliente.getNome());
	if (clienteOptional.isPresent()) {
		throw new NomeClienteJaCadastradoException("Cliente já  cadastrado no banco de dados");
	}             
	cr.save(cliente);
}

@Transactional
public void excluir(Cliente cliente) {
	
		try {
			cr.delete(cliente);
			cr.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar Cliente. Já foi usada em alguma venda.");
		}
	}
}
