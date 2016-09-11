/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Aluno;
import Classes.Eventos;
import Classes.Professor;
import br.cesjf.lpwsd.dao.AlunoJpaController;
import br.cesjf.lpwsd.dao.EventosJpaController;
import br.cesjf.lpwsd.dao.ProfessorJpaController;
import br.cesjf.lpwsd.dao.exceptions.RollbackFailureException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

@WebServlet(name = "ProfessorController", urlPatterns = {"/listar", "/listaPorSem", "/pontuar", "/placar"})
public class ProfessorController extends HttpServlet {

    @PersistenceUnit(unitName = "Projeto-pu")//é necessario informar para fazer a persistencia
    EntityManagerFactory emf;

    @Resource(name = "java:comp/UserTransaction")//é necessario informar para fazer a persistencia, projeto-pu é a persistence.xml
    UserTransaction ut;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("/pontuar")) {
            AlunoJpaController ajc = new AlunoJpaController(ut, emf);//JpaController é classe DAO, que seria funçoes para acesso ao BD
            ProfessorJpaController pjc = new ProfessorJpaController(ut, emf);

            List<Aluno> alunoL = ajc.findAlunoEntities();//pega os alunos do banco e joga numa lista
            request.setAttribute("alunos", alunoL);

            List<Professor> professorL = pjc.findProfessorEntities();
            request.setAttribute("professores", professorL);

            request.getRequestDispatcher("/WEB-INF/pontuar.jsp").forward(request, response);

        } else if (request.getRequestURI().contains("/placar")) {

            request.getRequestDispatcher("/WEB-INF/placar.jsp").forward(request, response);

        } else if (request.getRequestURI().contains("/listar")) {

            listAll(request, response);
        } else if (request.getRequestURI().contains("/listaPorSem")) {
            request.getRequestDispatcher("/WEB-INF/listAll.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EventosJpaController ejc = new EventosJpaController(ut, emf);
        int s = Integer.parseInt(request.getParameter("semestre"));
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2014);
        c.set(Calendar.MONTH, Calendar.FEBRUARY);
        c.set(Calendar.DAY_OF_MONTH, 2);

        ArrayList<Date> listadata = new ArrayList<Date>();// lista para carregar as datas
        listadata.add(c.getTime());
        for (int i = 1; i <= 10; i++) {
            c.add(Calendar.MONTH, 6);
            listadata.add(c.getTime());
        }
        if (request.getRequestURI().contains("/pontuar")) {

            Aluno a = new Aluno();
            Professor p = new Professor();
            try {
                p.setNome(request.getParameter("professor"));//pega os atributos(nome) do professor
                a.setNome(request.getParameter("aluno"));

                a.setId(Long.parseLong(request.getParameter("aluno")));
                p.setId(Long.parseLong(request.getParameter("professor")));
                int ponto = Integer.parseInt(request.getParameter("Cponto"));

                Date d = new Date();
                Eventos e = new Eventos(a, p, ponto, d);
                ejc.create(e);
                listAll(request, response);// lista todos os eventos
            } catch (Exception ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (request.getRequestURI().contains("/listaPorSem")) {

            request.setAttribute("eventos", ejc.getEventosCount3(listadata.get(s), listadata.get(s + 1)));

            request.getRequestDispatcher("/WEB-INF/listAll.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("/placar")) {

            request.setAttribute("pg", ejc.getEventosCount2(listadata.get(s), listadata.get(s + 1)));

            request.getRequestDispatcher("/WEB-INF/placar.jsp").forward(request, response);
        }
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EventosJpaController ejc = new EventosJpaController(ut, emf);
        List<Eventos> eventosL = ejc.findEventosEntities();
        request.setAttribute("eventos", eventosL);//pega lista de eventos que direciona para listAll
        request.getRequestDispatcher("/WEB-INF/listAll.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
