package com.petapp.repository.helper.titulo;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.petapp.model.Titulo;
import com.petapp.repository.filter.TituloFilter;
import com.petapp.repository.paginacao.PaginacaoUtil;

public class TituloRepositoryImpl implements TituloRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Page<Titulo> filtrar(TituloFilter filtro, Pageable pageable) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Titulo.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

		
	
	private Long total(TituloFilter filtro) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Titulo.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}



	private void adicionarFiltro(TituloFilter filtro, Criteria criteria) {
		if (filtro != null) {
				
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}
			
			if (filtro.getSituacao() != null) {
				criteria.add(Restrictions.eq("situacao", filtro.getSituacao()));
			}
			if (filtro.getTipo() != null) {
				criteria.add(Restrictions.eq("tipo", filtro.getTipo()));
			}
			if (filtro.getTipoDePagamento() != null) {
				criteria.add(Restrictions.eq("tipoDePagamento", filtro.getTipoDePagamento()));
			}
			
			if (filtro.getDataDeValidadeDe() != null) {
				LocalDate desde = filtro.getDataDeValidadeDe();
				criteria.add(Restrictions.ge("dataDeValidade", desde));
			}
			
			if (filtro.getDataDeValidadeAte() != null) {
				LocalDate ate = filtro.getDataDeValidadeAte();
				criteria.add(Restrictions.le("dataDeValidade", ate));
			}
			if (filtro.getFornecedor() != null) {
				criteria.add(Restrictions.eq("fornecedor", filtro.getFornecedor()));
			}
					
		}
		
	}
}
