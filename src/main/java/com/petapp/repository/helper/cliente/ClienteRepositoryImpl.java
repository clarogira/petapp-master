package com.petapp.repository.helper.cliente;

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
import com.petapp.model.Cliente;
import com.petapp.repository.filter.ClienteFilter;
import com.petapp.repository.paginacao.PaginacaoUtil;

public class ClienteRepositoryImpl implements ClienteRepositoryQueries {
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cliente.class);

		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(ClienteFilter filtro) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cliente.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ClienteFilter filtro, Criteria criteria) {
		if (filtro != null) {

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (filtro.getBairro() != null) {
				criteria.add(Restrictions.ilike("bairro", filtro.getBairro(), MatchMode.ANYWHERE));
			}

			if (filtro.getCep() != null) {
				criteria.add(Restrictions.ilike("cep", filtro.getCep(), MatchMode.ANYWHERE));
			}

			if (filtro.getComplemento() != null) {
				criteria.add(Restrictions.ilike("complemento", filtro.getComplemento(), MatchMode.ANYWHERE));
			}

			if (filtro.getEmail() != null) {
				criteria.add(Restrictions.ilike("email", filtro.getEmail(), MatchMode.ANYWHERE));
			}
			if (filtro.getObservacao() != null) {
				criteria.add(Restrictions.ilike("observacao", filtro.getObservacao(), MatchMode.ANYWHERE));
			}
			if (filtro.getRua() != null) {
				criteria.add(Restrictions.ilike("rua", filtro.getRua(), MatchMode.ANYWHERE));
			}
			if (filtro.getTelefone_celular()!= null) {
				criteria.add(Restrictions.ilike("telefone_celular", filtro.getTelefone_celular(), MatchMode.ANYWHERE));
			}
			if (filtro.getTelefone_fixo()!= null) {
				criteria.add(Restrictions.ilike("telefone_fixo", filtro.getTelefone_fixo(), MatchMode.ANYWHERE));
			}
		}
	}

}
