package com.projetoCJ;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="ALERGIAS")
public class Alergia 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id_alergia")
	private Long id;
	
	@ManyToMany(mappedBy = "alergias") //relacionamento com usuario
    private List<Usuario> usuarios;
	
	@Column
	private String nome;

	// ------------- Getter and Setters --------------
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
