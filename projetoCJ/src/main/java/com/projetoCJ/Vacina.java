package com.projetoCJ;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;


@Entity
@Table(name="VACINAS")
public class Vacina 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id_vacina")
	private Long id;
	
	@OneToMany(mappedBy = "vacina")   //relacionamento com agenda
    private List<Agenda> agendas;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(name = "doses", nullable = false) 
	@Min(value = 1, message = "O valor mínimo de doses é 1")
	private int doses;
	
	@Column
	@Min(value = 1, message = "A peridiocidade começa de 1")
	private int periodicidade;
	
	@Column
	private int intervalo;	
	
	private String descricao;
	
	// ------------- Métodos --------------
	
	
	// ------------- Getter and Setters --------------
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDoses() {
        return doses;
    }

    public void setDoses(int doses) {
        this.doses = doses;
    }

    public int getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(int periodicidade) {
        this.periodicidade = periodicidade;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }
}
