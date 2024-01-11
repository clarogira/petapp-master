package com.petapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;

	@NotBlank(message = "Animal é obrigatório")
	private String nome;
	@Enumerated(EnumType.STRING)
	private Cor cor;
	@Enumerated(EnumType.STRING)
	private Raca raca;

	private String observacao;

	@Transient
	private boolean novaFoto;

	private String foto;
	
	@JsonBackReference
	//@JsonIdentityInfo(			  generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigo")
	@NotNull(message = "O Cliente é obrigatório")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_codigo", referencedColumnName = "codigo", nullable = false)
	private Cliente cliente;

	

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	

	public Raca getRaca() {
		return raca;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getSemFoto() {
		return !StringUtils.isEmpty(foto) ? foto : "semfoto.jpg";
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	

	public boolean isNova() {
		return codigo == null;
	}

	public boolean isNovaFoto() {
		return novaFoto;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}
	public void setNovaFoto(boolean novaFoto) {
		this.novaFoto = novaFoto;
	}
	public boolean getNovaFoto() {
		return novaFoto;
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
		Animal other = (Animal) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
