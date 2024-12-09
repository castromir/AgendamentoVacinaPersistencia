package com.projetoCJ.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.projetoCJ.Agenda;
import com.projetoCJ.Alergia;
import com.projetoCJ.Situacao;
import com.projetoCJ.Usuario;
import com.projetoCJ.Vacina;
import com.projetoCJ.repositories.AgendaRepository;
import com.projetoCJ.repositories.AlergiaRepository;
import com.projetoCJ.repositories.UsuarioRepository;
import com.projetoCJ.repositories.VacinaRepository;
import com.projetoCJ.services.AgendamentoService;
import com.projetoCJ.services.AgendamentoServiceImpl;

@WebServlet(urlPatterns = {"/agendar", "/agendarVacina", "/consultar", "/inserirUsuario", "/inserirVacina", "/inserirAlergia", "/listaUsuario", "/listaVacina", "/listaAlergia", "/removeUsuario", "/removeVacina", "/removeAlergia", "/concluir", "/cancelar"})
public class AgendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
    private UsuarioRepository usuarioRepository;
	
	@Autowired
    private VacinaRepository vacinaRepository;
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private AlergiaRepository alergiaRepository;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        usuarioRepository = context.getBean(UsuarioRepository.class);
        vacinaRepository = context.getBean(VacinaRepository.class);
        agendaRepository = context.getBean(AgendaRepository.class);
        alergiaRepository = context.getBean(AlergiaRepository.class);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,  ServletException {
    	String action = req.getServletPath();
    	if(action.equals("/agendar")) {
    		agendamento(req, resp);
    	} else if(action.equals("/consultar")) {
    		listaAgenda(req, resp);
    	} else if(action.equals("/agendarVacina")) {
    		agendarVacina(req, resp);
    	} else if(action.equals("/inserirUsuario")) {
    		novoUsuario(req, resp);
    	} else if(action.equals("/listaUsuario")) {
    		listaUsuario(req, resp);
    	} else if(action.equals("/removeUsuario")) {
    		removeUsuario(req, resp);
    	} else if(action.equals("/inserirVacina")) {
    		novaVacina(req, resp);
    	} else if(action.equals("/listaVacina")) {
    		listaVacina(req, resp);
    	} else if(action.equals("/removeVacina")) {
    		removeVacina(req, resp);
    	} else if(action.equals("/inserirAlergia")) {
    		novaAlergia(req, resp);
    	} else if(action.equals("/listaAlergia")) {
    		listaAlergia(req, resp);
    	} else if(action.equals("/removeAlergia")) {
    		removeAlergia(req, resp);
    	} else if(action.equals("/cancelar")) {
    		cancelar(req, resp);
    	} else if(action.equals("/concluir")) {
    		concluir(req, resp);
    	}
    }
    
    protected void listaAgenda(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	List<Agenda> listaAgenda = agendaRepository.findAllByOrderBySituacao();
    	List<Agenda> listaDec = agendaRepository.findAllByOrderBySituacaoDesc();
    	
        listaDec.removeIf(agenda -> Situacao.AGENDADO.equals(agenda.getSituacao()));
    	
    	req.setAttribute("listaCompleta", listaAgenda);
    	
    	LocalDate hoje = LocalDate.now();
    	List<Agenda> listaHoje = new ArrayList<>();

    	for (Agenda agenda : listaAgenda) {
    	    LocalDate dataAgenda = agenda.getData().toLocalDate();

    	    if (dataAgenda.equals(hoje)) {
    	        listaHoje.add(agenda);
    	    }

    	}
    	
    	req.setAttribute("listaHoje", listaHoje);
    	req.setAttribute("listaOrdenada", listaDec);
    	
    	req.getRequestDispatcher("/consulta_page.jsp").forward(req, resp);
    }
    
    protected void agendamento(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	List<Usuario> listaUsuarios = usuarioRepository.findAll();
    	List<Vacina> listaVacinas = vacinaRepository.findAll();
    	
    	req.setAttribute("usuarios", listaUsuarios);
    	req.setAttribute("vacinas", listaVacinas);
    	
        req.getRequestDispatcher("/agendamento_page.jsp").forward(req, resp);
    }
    
    protected void agendarVacina(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String usuarioId = req.getParameter("usuarioId");
    	String vacinaId = req.getParameter("vacinaId");
    	String data = req.getParameter("data");
    	
    	if(usuarioId != null && vacinaId != null && data != null) {
	    	Usuario usuario = usuarioRepository.findById(Long.parseLong(usuarioId)).orElse(null);
	    	Vacina vacina = vacinaRepository.findById(Long.parseLong(vacinaId)).orElse(null);
	    	
	    	if(usuario != null && vacina != null) {
	    		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate dataFormatada = LocalDate.parse(data, formato);
	    		
	    		AgendamentoService agendamentoService = new AgendamentoServiceImpl();
	            List<Agenda> agendas = agendamentoService.criarAgendamentos(vacina, usuario, dataFormatada);

	            agendaRepository.saveAll(agendas);
	    	}else {
	    		System.out.println("Erro na request...");
	    	}
    	}else {
    		System.out.println("Erro na request...");
    	}
    	
    	resp.sendRedirect("consultar");
    }
    
    protected void novoUsuario(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String nome = req.getParameter("nome");
    	String data = req.getParameter("dataNascimento");
    	String sexo = req.getParameter("sexo");
    	String cidade = req.getParameter("cidade");
    	String uf = req.getParameter("uf");
    	
    	Date dataNasc = Date.valueOf(data);
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
        usuario.setDataNascimento(dataNasc);
        usuario.setSexo(sexo.charAt(0));
        usuario.setCidade(cidade);
        usuario.setUnidadeFederativa(uf);

        usuarioRepository.save(usuario);
        resp.sendRedirect("listaUsuario");
        
    }
    
    protected void removeUsuario(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	usuarioRepository.deleteById(Long.parseLong(req.getParameter("codigo")));
    	resp.sendRedirect("listaUsuario");
    }
    
    protected void listaUsuario(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	List<Usuario> listaUsuarios = usuarioRepository.findAll();
    	req.setAttribute("usuarios", listaUsuarios);
    	
    	req.getRequestDispatcher("/lista_usuario_page.jsp").forward(req, resp);
    }
    
    protected void novaVacina(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String titulo = req.getParameter("titulo");
    	int doses = Integer.parseInt(req.getParameter("doses"));
    	int periodo = Integer.parseInt(req.getParameter("periodicidade"));
    	int intervalo = Integer.parseInt(req.getParameter("intervalo"));
    	
    	Vacina vacina = new Vacina();
        vacina.setTitulo(titulo);
        vacina.setDoses(doses);
        vacina.setPeriodicidade(periodo);
        vacina.setIntervalo(intervalo);
        
        vacinaRepository.save(vacina);
        resp.sendRedirect("listaVacina");
    }
    
    protected void removeVacina(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	vacinaRepository.deleteById(Long.parseLong(req.getParameter("codigo")));
    	resp.sendRedirect("listaVacina");
    }
    
    protected void listaVacina(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	List<Vacina> listaVacina = vacinaRepository.findAll();
    	req.setAttribute("vacinas", listaVacina);
    	
    	req.getRequestDispatcher("/lista_vacina_page.jsp").forward(req, resp);
    }
    
    protected void novaAlergia(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String nome = req.getParameter("nome");
    	
    	Alergia alergia = new Alergia();
        alergia.setNome(nome);
        
        alergiaRepository.save(alergia);
        resp.sendRedirect("listaAlergia");
    }
    
    protected void removeAlergia(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	alergiaRepository.deleteById(Long.parseLong(req.getParameter("codigo")));
    	resp.sendRedirect("listaAlergia");
    }
    
    protected void listaAlergia(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	List<Alergia> listaAlergia = alergiaRepository.findAll();
    	req.setAttribute("alergias", listaAlergia);
    	
    	req.getRequestDispatcher("/lista_alergia_page.jsp").forward(req, resp);
    }
    
    protected void cancelar(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
           	Long id = Long.parseLong(req.getParameter("codigo"));
            
            agendaRepository.atualizarSituacao(id, Situacao.CANCELADO);

            resp.sendRedirect("consultar");
    }
    
    protected void concluir(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
       	Long id = Long.parseLong(req.getParameter("codigo"));
        
        agendaRepository.atualizarSituacao(id, Situacao.REALIZADO);

        resp.sendRedirect("consultar");
    }
}
