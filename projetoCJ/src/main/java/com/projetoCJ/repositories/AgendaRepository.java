package com.projetoCJ.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetoCJ.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
