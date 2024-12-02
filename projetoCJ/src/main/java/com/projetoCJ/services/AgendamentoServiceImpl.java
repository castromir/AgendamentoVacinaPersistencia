package com.projetoCJ.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projetoCJ.Agenda;
import com.projetoCJ.Situacao;
import com.projetoCJ.Usuario;
import com.projetoCJ.Vacina;

@Service
public class AgendamentoServiceImpl implements AgendamentoService
{

    public List<Agenda> criarAgendamentos(Vacina vacina, Usuario usuario, LocalDate dataInicial) 
    {
        List<Agenda> agendamentos = new ArrayList<>();

        if (vacina.getDoses() <= 0) 
        {
            throw new IllegalArgumentException("A vacina deve ter pelo menos 1 dose.");
        }

        LocalDate dataAtual = dataInicial;

        for (int i = 1; i <= vacina.getDoses(); i++)
        {
            Agenda agenda = new Agenda();
            agenda.setUsuario(usuario);
            agenda.setVacina(vacina);
            agenda.setData(java.sql.Date.valueOf(dataAtual));
            agenda.setSituacao(Situacao.AGENDADO);

            agendamentos.add(agenda);

            if (i < vacina.getDoses()) 
            { // não calcula após a última dose
                dataAtual = calcularProximaData(dataAtual, vacina.getPeriodicidade(), vacina.getIntervalo());
            }
        }

        return agendamentos;
    }

    private LocalDate calcularProximaData(LocalDate dataAtual, int periodicidade, int intervalo) 
    {
        switch (periodicidade) 
        {
            case 1: // Dias
                return dataAtual.plusDays(intervalo);
            case 2: // Semanas
                return dataAtual.plusWeeks(intervalo);
            case 3: // Meses
                return dataAtual.plusMonths(intervalo);
            case 4: // Anos
                return dataAtual.plusYears(intervalo);
            default:
                throw new IllegalArgumentException("Periodicidade inválida");
        }
    }
}

