package com.petapp.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataCriacao;
	
	private BigDecimal valorDesconto;

	private BigDecimal valorFrete;
	
	private BigDecimal valorTotal  = BigDecimal.ZERO;
	private BigDecimal lucroTotal  = BigDecimal.ZERO;
	
	private String observacao;
	
	private LocalDate dataHoraEntrega;
	

	@Enumerated(EnumType.STRING)
	private FormaDePagamento formaDePagamento;

	@ManyToOne
	@JoinColumn(name = "codigo_cliente")
	private Cliente cliente;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ABERTO;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private List<ItemVenda> itens = new ArrayList<>();

	@Transient
	private String uuid;
	

	
	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setDataCriacao(LocalDate localDate) {
		this.dataCriacao = localDate;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getLucroTotal() {
		return lucroTotal;
	}

	public void setLucroTotal(BigDecimal lucroTotal) {
		this.lucroTotal = lucroTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public LocalDate getDataHoraEntrega() {
		return dataHoraEntrega;
	}

	public void setDataHoraEntrega(LocalDate localDate) {
		this.dataHoraEntrega = localDate;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public boolean isSalvarPermitido() {
		return !status.equals(Status.CANCELADA);
	}
	
	public boolean isSalvarProibido() {
		return !isSalvarPermitido();
	}
	
	public boolean isNova() {
		return codigo == null;
	}
	
	public void adicionarItens(List<ItemVenda> itens) {
		this.itens = itens;
		this.itens.forEach(i -> i.setVenda(this));
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	
	public void calcularValorTotal() {
		BigDecimal valorTotalItens = getItens().stream()
				.map(ItemVenda::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		
		this.valorTotal = calcularValorTotal(valorTotalItens, getValorFrete(), getValorDesconto());
	}
	

	public void calcularLucroTotal() {
		BigDecimal lucroTotalItens = getItens().stream()
				.map(ItemVenda::getLucroTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
		
		this.lucroTotal = calcularLucroTotal(lucroTotalItens);
	}
	
	
	private BigDecimal calcularLucroTotal(BigDecimal lucroTotalItens) {
		BigDecimal lucroTotal = lucroTotalItens;
		return lucroTotal;
	
	}

	public Long getDiasCriacao() {
		LocalDate inicio = dataCriacao != null ? dataCriacao : LocalDate.now();
		return ChronoUnit.DAYS.between(inicio, LocalDate.now());
	}
	
	private BigDecimal calcularValorTotal(BigDecimal valorTotalItens, BigDecimal valorFrete, BigDecimal valorDesconto) {
		
	
		BigDecimal valorTotal = valorTotalItens
				.add(Optional.ofNullable(valorFrete).orElse(BigDecimal.ZERO))
				.subtract(Optional.ofNullable(valorDesconto).orElse(BigDecimal.ZERO));
		return valorTotal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
}
