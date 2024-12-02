package com.projetoCJ.services;

import java.time.LocalDate;
import java.util.List;
import com.projetoCJ.Agenda;
import com.projetoCJ.Usuario;
import com.projetoCJ.Vacina;

public interface AgendamentoService {
    List<Agenda> criarAgendamentos(Vacina vacina, Usuario usuario, LocalDate dataInicial);
}
