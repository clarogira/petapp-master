package com.petapp.service;

import com.petapp.model.Titulo;
import com.petapp.repository.TituloRepository;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import javax.persistence.PersistenceException;

@Service
public class TitulosService {

    @Autowired
    private TituloRepository tituloRepository;
    
    @Transactional
    public void salvar(Titulo titulo){
        titulo.setDataDeEmissao(new Date());
        tituloRepository.save(titulo);
    }

    @Transactional
    public void excluir(Titulo titulo) {
    	
    		try {
    			
    			tituloRepository.delete(titulo);
    			tituloRepository.flush();
    		} catch (PersistenceException e) {
    			throw new ImpossivelExcluirEntidadeException("Impossível apagar o Titulo.");
    		}
    	}
}
