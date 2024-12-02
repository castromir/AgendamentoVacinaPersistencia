package com.projetoCJ;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="USUARIOS")
public class Usuario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="id_usuario")
	private Long id;
	
	@OneToMany(mappedBy = "usuario") //relacionamento com agenda
    private List<Agenda> agendas;
	
	@ManyToMany  //relacionamento com alergia
    @JoinTable
    (
        name = "usuario_alergia",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_alergia")
    )
    private List<Alergia> alergias;
	
	@Column
	private char sexo;
	
	@Column(name="UF")
	private String unidadeFederativa;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name="data_nascimento", nullable = false)
	private Date dataNascimento;
	
	@Column(nullable = false)
	private String cidade;
	
	@Transient
	private String logradouro;
	
	@Transient
	private String setor;
	
	// ------------- Getter and Setters --------------
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getUnidadeFederativa() {
		return unidadeFederativa;
	}
	public void setUnidadeFederativa(String unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
