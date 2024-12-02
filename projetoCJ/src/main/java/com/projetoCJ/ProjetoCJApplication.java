package com.projetoCJ;

import com.projetoCJ.services.AgendamentoService;
import com.projetoCJ.services.AgendamentoServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.CommandLineRunner;
import com.projetoCJ.repositories.*;
import com.projetoCJ.Usuario;
import com.projetoCJ.Vacina;
import com.projetoCJ.Agenda;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.projetoCJ.repositories")
public class ProjetoCJApplication 
{

    public static void main(String[] args) 
    {
        SpringApplication.run(ProjetoCJApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AgendaRepository agendaRepository, 
                             UsuarioRepository usuarioRepository, 
                             VacinaRepository vacinaRepository) {
        return args -> {
            Usuario usuario = new Usuario();
            usuario.setNome("João Silva");
            usuario.setDataNascimento(java.sql.Date.valueOf("1990-01-01"));
            usuario.setSexo('M');
            usuario.setCidade("Goiânia");
            usuario.setUnidadeFederativa("GO");

            usuarioRepository.save(usuario);

            Vacina vacina = new Vacina();
            vacina.setTitulo("Vacina COVID-19");
            vacina.setDoses(2);
            vacina.setPeriodicidade(3); // Meses
            vacina.setIntervalo(1);
            vacinaRepository.save(vacina);

            LocalDate dataInicial = LocalDate.now();

            AgendamentoService agendamentoService = new AgendamentoServiceImpl();
            List<Agenda> agendas = agendamentoService.criarAgendamentos(vacina, usuario, dataInicial);

            agendaRepository.saveAll(agendas);

            System.out.println("Agendamentos criados:");
            agendaRepository.findAll().forEach(System.out::println);
        };
    }
}
