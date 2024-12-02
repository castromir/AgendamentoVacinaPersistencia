package com.projetoCJ.controller;

import java.util.List;

import com.projetoCJ.services.AgendaService;

import jakarta.persistence.EntityManager;

public class AgendaController 
{

    private final AgendaService agendaService;

    public AgendaController(EntityManager entityManager) {
        this.agendaService = new AgendaService(entityManager);
    }

    public void exibirAgendasDoDia() {
        List<Object[]> agendas = agendaService.listarAgendasDoDia();
        agendas.forEach(agenda -> System.out.println("ID: " + agenda[0] + ", Data: " + agenda[1] + ", Status: " + agenda[3]));
    }

    public void exibirAgendasCanceladas() {
        List<Object[]> agendasCanceladas = agendaService.listarAgendasPorStatus("Cancelada");
        agendasCanceladas.forEach(agenda -> System.out.println("ID: " + agenda[0] + ", Status: " + agenda[3]));
    }
}

