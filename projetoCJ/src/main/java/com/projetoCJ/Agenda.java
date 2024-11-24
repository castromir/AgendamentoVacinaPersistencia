package com.projetoCJ;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="AGENDAS")
public class Agenda 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id_agenda")
	private Long id;
	
	@ManyToOne            //relacionamento com vacina
    @JoinColumn(name = "id_vacina", nullable = false)
    private Vacina vacina;
	
	@ManyToOne            //relacionamento com usuario
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false) //tem que ter alguma situacao
	private Situacao situacao;
	
	@Column(name="data_situacao", nullable = false)
	private Date dataSituacao;
	
	@Column(name="data", nullable = false)
	private Date data;
	
	private Time hora;
	private String observacoes;
	
	// ------------- Getter and Setters --------------
	
	public Date getDataSituacao() {
		return dataSituacao;
	}
	public void setDataSituacao(Date dataSituacao) {
		this.dataSituacao = dataSituacao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Situacao getSituacao() {
		return situacao;
	}
	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
}


