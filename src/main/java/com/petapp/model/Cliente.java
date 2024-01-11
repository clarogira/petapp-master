package com.petapp.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity

public class Cliente  implements Serializable{

	private static final long serialVersionUID = 1L;
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long codigo;


private String nome;

private String telefone_fixo;

private String telefone_celular;

private String email;

private String observacao;

private String rua;

private String complemento;

private String bairro;

private String cep;

@JsonManagedReference
@JsonIgnore
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codigo")
@OneToMany(mappedBy = "cliente" )
private List<Animal> animal = new ArrayList<Animal>();



public List<Animal> getAnimal() {
	return animal;
}

public void setAnimal(List<Animal> animal) {
	this.animal = animal;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getTelefone_fixo() {
	return telefone_fixo;
}

public void setTelefone_fixo(String telefone_fixo) {
	this.telefone_fixo = telefone_fixo;
}


public String getTelefone_celular() {
	return telefone_celular;
}

public void setTelefone_celular(String telefone_celular) {
	this.telefone_celular = telefone_celular;
}

public String getObservacao() {
	return observacao;
}

public void setObservacao(String observacao) {
	this.observacao = observacao;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Long getCodigo() {
	return codigo;
}

public void setCodigo(Long codigo) {
	this.codigo = codigo;
}

public String getRua() {
	return rua;
}

public void setRua(String rua) {
	this.rua = rua;
}

public String getComplemento() {
	return complemento;
}

public void setComplemento(String complemento) {
	this.complemento = complemento;
}

public String getBairro() {
	return bairro;
}

public void setBairro(String bairro) {
	this.bairro = bairro;
}

public String getCep() {
	return cep;
}

public void setCep(String cep) {
	this.cep = cep;

}

public boolean isNova() {
	return codigo == null;
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
	Cliente other = (Cliente) obj;
	if (codigo == null) {
		if (other.codigo != null)
			return false;
	} else if (!codigo.equals(other.codigo))
		return false;
	return true;
}



}
