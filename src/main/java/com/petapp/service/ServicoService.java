package com.petapp.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petapp.model.Servico;
import com.petapp.repository.ServicoRepository;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
import com.petapp.service.exception.NomeServicoJaCadastradoException;
@Service
public class ServicoService {
@Autowired
private ServicoRepository sr;
@Transactional
public void salvar(Servico servico) {
	Optional<Servico> servicoOptional = sr.findByNomeIgnoreCase(servico.getNome());
	if (servicoOptional.isPresent()) {
		throw new NomeServicoJaCadastradoException("Serviço já  cadastrado no banco de dados");
	}   
	sr.save(servico);
}

@Transactional
public void excluir(Servico servico) {
	
		try {
			sr.delete(servico);
			sr.flush();
			} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar serviço. Já foi usado em alguma OS.");
		}
	}
}
