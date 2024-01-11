package com.petapp.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "titulo")
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_de_emissao")
    private Date dataDeEmissao;

    private String descricao;


    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_de_validade")
    private LocalDate dataDeValidade;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_do_pagamento")
    private Date dataDoPagamento;
    
    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "valor")
    private BigDecimal valorPago;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "valor_original")
    private BigDecimal valorOriginal;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fornecedor_codigo", referencedColumnName = "codigo", nullable = false)
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipodepagamento_codigo", referencedColumnName = "codigo", nullable = false)
    private TipoDePagamento tipoDePagamento;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getDataDeEmissao() {
        return dataDeEmissao;
    }

    public void setDataDeEmissao(Date dataDeEmissao) {
        this.dataDeEmissao = dataDeEmissao;
    }

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public Date getDataDoPagamento() {
        return dataDoPagamento;
    }

    public void setDataDoPagamento(Date dataDoPagamento) {
        this.dataDoPagamento = dataDoPagamento;
    }

       public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public TipoDePagamento getTipoDePagamento() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Titulo{" +
                "codigo=" + codigo +
                ", dataDeEmissao=" + dataDeEmissao +
                ", descricao='" + descricao + '\'' +
                ", dataDeValidade=" + dataDeValidade +
                ", dataDoPagamento=" + dataDoPagamento +
                ", valorPago=" + valorPago +
                ", valorOriginal=" + valorOriginal +
                ", fornecedor=" + fornecedor +
                ", tipoDePagamento=" + tipoDePagamento +
                ", tipo=" + tipo +
                ", situacao=" + situacao +
                '}';
    }
}
