package com.petapp.repository.helper.animal;

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
import com.petapp.model.Animal;
import com.petapp.repository.filter.AnimalFilter;
import com.petapp.repository.paginacao.PaginacaoUtil;

public class AnimalRepositoryImpl implements AnimalRepositoryQueries {
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Page<Animal> filtrar(AnimalFilter filtro, Pageable pageable) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Animal.class);

		paginacaoUtil.preparar(criteria, pageable);

		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(AnimalFilter filtro) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Animal.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(AnimalFilter filtro, Criteria criteria) {
		if (filtro != null) {

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}

			if (filtro.getCliente() != null) {
				criteria.add(Restrictions.eq("cliente", filtro.getCliente()));
			}

			if (filtro.getCor() != null) {
				criteria.add(Restrictions.eq("cor", filtro.getCor()));
			}

			if (filtro.getRaca() != null) {
				criteria.add(Restrictions.eq("raca", filtro.getRaca()));
			}

			if (filtro.getObservacao() != null) {
				criteria.add(Restrictions.ilike("observacao", filtro.getObservacao(), MatchMode.ANYWHERE));
			}
		}
	}

}
