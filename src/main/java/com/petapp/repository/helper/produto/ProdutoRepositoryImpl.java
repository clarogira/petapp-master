package com.petapp.repository.helper.produto;

import java.time.LocalDate;
import java.util.List;
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

import com.petapp.dto.ProdutoDTO;
import com.petapp.dto.ValorItensEstoque;
import com.petapp.model.Produto;
import com.petapp.repository.filter.ProdutoFilter;
import com.petapp.repository.paginacao.PaginacaoUtil;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);

		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	@Override
	public List<ProdutoDTO> porNome(String pesquisaPorNome) {
	 String jpql = "select new com.petapp.dto.ProdutoDTO(codigo, nome, foto, valorVenda, peso) " 
	+ "from Produto where lower(nome) like :pesquisaPorNome";
	 List<ProdutoDTO> produtosFiltrados = manager.createQuery(jpql, ProdutoDTO.class)
			 .setParameter("pesquisaPorNome", "%" + pesquisaPorNome.toLowerCase() + "%")
			 .getResultList();
     return produtosFiltrados;
	}
	
	@Override
	public ValorItensEstoque valorItensEstoque() {
		String query = "select new com.petapp.dto.ValorItensEstoque(SUM(valor_compra * estoque), SUM(estoque)) from Produto";
		return manager.createQuery(query, ValorItensEstoque.class).getSingleResult();
	}
	
	private void adicionarFiltro(ProdutoFilter filtro, Criteria criteria) {
		if (filtro != null) {

			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
			if (filtro.getValorCompraDe() != null) {
				criteria.add(Restrictions.ge("valorCompra", filtro.getValorCompraDe()));
			}
     		if (filtro.getValorCompraAte() != null) {
				criteria.add(Restrictions.le("valorCompra", filtro.getValorCompraAte()));
			}
			if (filtro.getValorVendaDe() != null) {
				criteria.add(Restrictions.ge("valorVenda", filtro.getValorVendaDe()));
			}

			if (filtro.getValorVendaAte() != null) {
				criteria.add(Restrictions.le("valorVenda", filtro.getValorVendaAte()));
			}
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}
			if (filtro.getEstoqueDe() != null) {
				criteria.add(Restrictions.ge("estoque", filtro.getEstoqueDe()));
			}

			if (filtro.getEstoqueAte() != null) {
				criteria.add(Restrictions.le("estoque", filtro.getEstoqueAte()));
			}

			if (filtro.getCategoria() != null) {
				criteria.add(Restrictions.eq("categoria", filtro.getCategoria()));
			}
			if (filtro.getFornecedor() != null) {
				criteria.add(Restrictions.eq("fornecedor", filtro.getFornecedor()));
			}
			if (filtro.getPesoDe() != null) {
				criteria.add(Restrictions.ge("peso", filtro.getPesoDe()));
			}

			if (filtro.getPesoAte() != null) {
				criteria.add(Restrictions.le("peso", filtro.getPesoAte()));
			}
			
			if (filtro.getValidadeDe() != null) {
				LocalDate desde = filtro.getValidadeDe();
				criteria.add(Restrictions.ge("validade", desde));
			}
			
			if (filtro.getValidadeAte() != null) {
				LocalDate ate = filtro.getValidadeAte();
				criteria.add(Restrictions.le("validade", ate));
			}
					
		}
		
	}
	private Long total(ProdutoFilter filtro) {
		@SuppressWarnings("deprecation")
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Produto.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	

}
