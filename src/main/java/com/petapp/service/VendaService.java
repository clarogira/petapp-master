package com.petapp.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petapp.model.Status;
import com.petapp.model.Venda;
import com.petapp.repository.VendaRepository;
import com.petapp.service.event.venda.VendaEvent;
import com.petapp.service.exception.ImpossivelExcluirEntidadeException;
@Service
public class VendaService {
	
@Autowired
private VendaRepository vr;

@Autowired
private ApplicationEventPublisher publisher;

@Transactional
public void salvar(Venda venda) {
	if (venda.isNova()) {
		venda.setDataCriacao(LocalDate.now());
		
	} else {
		Venda vendaExistente = vr.findByCodigoOrderByCodigoDesc(venda.getCodigo());
		venda.setDataCriacao(vendaExistente.getDataCriacao());
	}
	if (venda.getValorDesconto() == null) {
		venda.setValorDesconto(new BigDecimal(0.00));
	}		
	vr.save(venda);
}


@Transactional
public void salvarFechar(Venda venda) {
    venda.setStatus(Status.FECHADO);	
    salvar(venda);
    publisher.publishEvent(new VendaEvent(venda));
    }

@Transactional
public void excluir(Venda venda) {
	
		try {
		
			vr.delete(venda);
			vr.flush();
			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar Venda.");
		}
	}

@Transactional
public void cancelar(Venda venda) {
	Venda vendaExistente = vr.findByCodigoOrderByCodigoDesc(venda.getCodigo());
	
	vendaExistente.setStatus(Status.CANCELADA);
	vr.save(vendaExistente);
}
}