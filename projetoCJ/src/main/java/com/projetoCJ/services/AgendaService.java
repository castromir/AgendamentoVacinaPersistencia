package com.projetoCJ.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

public class AgendaService 
{
	@PersistenceContext
    private EntityManager entityManager;

    // listagens
    public List<Object[]> listarAlergias() {
        return entityManager.createQuery("SELECT a.id, a.nome FROM Alergia a", Object[].class).getResultList();
    }

    public List<Object[]> listarVacinas() {
        return entityManager.createQuery("SELECT v.id, v.titulo, v.doses FROM Vacina v", Object[].class).getResultList();
    }

    public List<Object[]> listarUsuarios() {
        return entityManager.createQuery("SELECT u.id, u.nome, u.email FROM Usuario u", Object[].class).getResultList();
    }

    public List<Object[]> listarAgendas() {
        return entityManager.createQuery("SELECT ag.id, ag.data, ag.horario, ag.status FROM Agenda ag", Object[].class).getResultList();
    }

    // listagem por status
    public List<Object[]> listarAgendasPorStatus(String status) 
    {
        return entityManager.createQuery(
            "SELECT ag.id, ag.data, ag.horario, ag.status FROM Agenda ag WHERE ag.status = :status",
            Object[].class)
            .setParameter("status", status)
            .getResultList();
    }

    // listagem do dia corrente
    public List<Object[]> listarAgendasDoDia() 
    {
        LocalDate hoje = LocalDate.now();
        return entityManager.createQuery(
            "SELECT ag.id, ag.data, ag.horario, ag.status " +
            "FROM Agenda ag " +
            "WHERE ag.data = :hoje " +
            "ORDER BY " +
            "   CASE ag.status " +
            "       WHEN 'Agendada' THEN 1 " +
            "       WHEN 'Realizada' THEN 2 " +
            "       WHEN 'Cancelada' THEN 3 " +
            "       ELSE 4 " +
            "   END ASC",
            Object[].class)
            .setParameter("hoje", hoje)
            .getResultList();
    }
}

