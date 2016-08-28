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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "ProfessorController", urlPatterns = {"/criar", "/listar", "/pontuar"})
public class ProfessorController extends HttpServlet {

    @PersistenceUnit(unitName = "Projeto-pu")
    EntityManagerFactory emf;

    @Resource(name = "java:comp/UserTransaction")
    UserTransaction ut;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AlunoJpaController ajc = new AlunoJpaController(ut, emf);
        ProfessorJpaController pjc = new ProfessorJpaController(ut, emf);
        EventosJpaController ejc = new EventosJpaController(ut, emf);

        if (request.getRequestURI().contains("/criar")) {
                    Aluno a = new Aluno();
        Professor p = new Professor();
            try {
////                p.getId(Long.parseLong(request.getParameter("professor")));
////                a.getId(Long.parseLong(request.getParameter("aluno")));
                p.getNome(request.getParameter("professor"));
                a.getId(Long.parseLong(request.getParameter("aluno")));
                int ponto = Integer.parseInt(request.getParameter("Cponto"));
                Eventos e = new Eventos(a, p, ponto);
                ejc.create(e);
               listAll(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getRequestURI().contains("/listar")) {
            listAll(request, response);
        } else if (request.getRequestURI().contains("/pontuar")) {

            List<Aluno> alunoL = ajc.findAlunoEntities();
            request.setAttribute("alunos", alunoL);

            List<Professor> professorL = pjc.findProfessorEntities();
            request.setAttribute("professores", professorL);

            request.getRequestDispatcher("/WEB-INF/pontuar.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String pont = request.getParameter("ponto");

        EntityManager em = emf.createEntityManager();
        Aluno aluno = em.find(Aluno.class, id);

        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();

        listAll(request, response);
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EventosJpaController ejc = new EventosJpaController(ut, emf);
        List<Eventos> eventosL = ejc.findEventosEntities();
        request.setAttribute("eventos", eventosL);
        request.getRequestDispatcher("/WEB-INF/listAll.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
//                a.setNome("Teste");
//                a.setGrupo("1");
//                a.setPeriodo("2015");
//                p.setNome("TestePO");
//                ajc.create(a);
//                pjc.create(p);
//                Integer ponto = 10;