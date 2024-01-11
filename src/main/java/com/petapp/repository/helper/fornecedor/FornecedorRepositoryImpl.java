package com.petapp.repository.helper.fornecedor;

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

import com.petapp.model.Fornecedor;
import com.petapp.repository.filter.FornecedorFilter;
import com.petapp.repository.paginacao.PaginacaoUtil;

public class FornecedorRepositoryImpl implements FornecedorRepositoryQueries{
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Page<Fornecedor> filtrar(FornecedorFilter filtro, Pageable pageable) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Fornecedor.class);

	 paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(FornecedorFilter filtro) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Fornecedor.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(FornecedorFilter filtro, Criteria criteria) {
		if (filtro != null) {

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (filtro.getTelefone() != null) {
				criteria.add(Restrictions.ilike("telefone", filtro.getTelefone(), MatchMode.ANYWHERE));
			}

			if (filtro.getRepresentante() != null) {
				criteria.add(Restrictions.ilike("representante", filtro.getRepresentante(), MatchMode.ANYWHERE));
			}

			if (filtro.getTelefoneRepresentante() != null) {
				criteria.add(Restrictions.ilike("telefoneRepresentante", filtro.getTelefoneRepresentante(), MatchMode.ANYWHERE));
			}

			if (filtro.getRamo() != null) {
				criteria.add(Restrictions.eq("ramo", filtro.getRamo()));
			}
			if (filtro.getObservacao() != null) {
				criteria.add(Restrictions.ilike("observacao", filtro.getObservacao(), MatchMode.ANYWHERE));
			}
			if (filtro.getCnpj() != null) {
				criteria.add(Restrictions.ilike("cnpj", filtro.getCnpj(), MatchMode.ANYWHERE));
			}
			
			
		}
	}

}
