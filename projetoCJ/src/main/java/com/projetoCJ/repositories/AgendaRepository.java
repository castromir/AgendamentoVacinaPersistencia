package com.projetoCJ.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetoCJ.Agenda;
import com.projetoCJ.Situacao;

import jakarta.transaction.Transactional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
	@Modifying
	@Transactional
	@Query("UPDATE Agenda a SET a.situacao = :situacao, a.dataSituacao = CURRENT_DATE WHERE a.id = :id")
	void atualizarSituacao(@Param("id") Long id, @Param("situacao") Situacao situacao);
	
	List<Agenda> findAllByOrderBySituacao();
	
	List<Agenda> findAllByOrderBySituacaoDesc();
}
