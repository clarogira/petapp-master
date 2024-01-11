package com.petapp.repository.helper.venda;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.petapp.dto.VendaCategoria;
import com.petapp.dto.VendaMes;
import com.petapp.model.Venda;
import com.petapp.repository.filter.VendaFilter;


public interface VendaRepositoryQueries {
	public Page<Venda> filtrar(VendaFilter filtro, Pageable pageable);

	public Venda buscarComItens(Long codigo);
	
	public BigDecimal valorTotalNoAno();
	public BigDecimal valorTotalNoMes();
	public BigDecimal valorTicketMedioNoAno();
	
	public List<VendaMes> totalPorMes();
	public BigDecimal lucroTotalNoMes();
	
	List<VendaCategoria> totalPorCategoria();

	BigDecimal valorTotalNoDia();
}
