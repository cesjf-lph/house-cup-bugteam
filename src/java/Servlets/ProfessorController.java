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
import java.io.IOException;
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

@WebServlet(name = "ProfessorController", urlPatterns = {"/criar", "/listar", "/pontuar", "/placar"})
public class ProfessorController extends HttpServlet {

    @PersistenceUnit(unitName = "Projeto-pu")//é necessario informar para fazer a persistencia
    EntityManagerFactory emf;

    @Resource(name = "java:comp/UserTransaction")//é necessario informar para fazer a persistencia, projeto-pu é a persistence.xml
    UserTransaction ut;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getRequestURI().contains("/listar")) {
            listAll(request, response);
        } else if (request.getRequestURI().contains("/pontuar")) {
            AlunoJpaController ajc = new AlunoJpaController(ut, emf);//JpaController é classe DAO, que seria funçoes para acesso ao BD
            ProfessorJpaController pjc = new ProfessorJpaController(ut, emf);

            List<Aluno> alunoL = ajc.findAlunoEntities();//pega os alunos do banco e joga numa lista
            request.setAttribute("alunos", alunoL);

            List<Professor> professorL = pjc.findProfessorEntities();
            request.setAttribute("professores", professorL);

            request.getRequestDispatcher("/WEB-INF/pontuar.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("/placar")) {
            EventosJpaController dao =new EventosJpaController(ut, emf);
            
            request.setAttribute("pg", dao.getEventosCount2());
             request.getRequestDispatcher("/WEB-INF/placar.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EventosJpaController ejc = new EventosJpaController(ut, emf);
        if (request.getRequestURI().contains("/criar")) {

            Aluno a = new Aluno();
            Professor p = new Professor();
            try {
                p.setNome(request.getParameter("professor"));//pega os atributos(nome) do professor
                a.setNome(request.getParameter("aluno"));

                a.setId(Long.parseLong(request.getParameter("aluno")));
                p.setId(Long.parseLong(request.getParameter("professor")));
                int ponto = Integer.parseInt(request.getParameter("Cponto"));
                Eventos e = new Eventos(a, p, ponto);
                ejc.create(e);
                listAll(request, response);// lista todos os eventos
            } catch (Exception ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }

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
