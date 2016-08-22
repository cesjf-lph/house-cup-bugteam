/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Aluno;
import Classes.Professor;
import br.cesjf.lpwsd.dao.AlunoJpaController;
import br.cesjf.lpwsd.dao.ProfessorJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
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
@WebServlet(name = "ProfessorController", urlPatterns = {"/criar", "/listar","/pontuar"})
public class ProfessorController extends HttpServlet {

    @PersistenceUnit(unitName = "Projeto-pu")
    EntityManagerFactory emf;

    @Resource(name = "java:comp/UserTransaction")
    UserTransaction ut;

    AlunoJpaController ajc = new AlunoJpaController(ut, emf);
    Aluno a = new Aluno();

    ProfessorJpaController pjc = new ProfessorJpaController(ut, emf);
    Professor p = new Professor();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("/criar")) {
            try {
                ajc.create(a);
                pjc.create(p);
            } catch (Exception ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getRequestURI().contains("/listar")) {
            listAll(request, response);
        }
        else if (request.getRequestURI().contains("/pontuar")) {   
            edit(request, response);
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
            aluno.setPontos(aluno.getPontos()+Integer.parseInt(pont));

            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();

            listAll(request, response);
    }

    private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryStr = "SELECT a FROM Aluno a";

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery(queryStr);
        List<Aluno> alunosL = q.getResultList();
        request.setAttribute("alunos", alunosL);
        request.getRequestDispatcher("/WEB-INF/listAll.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
