/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.Aluno;
import br.cesjf.lpwsd.dao.AlunoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
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
@WebServlet(name = "teste", urlPatterns = {"/teste"})
public class ProfessorController1 extends HttpServlet {

    @PersistenceUnit(unitName = "Projeto-pu")
    EntityManagerFactory emf;

    @Resource(name = "java:comp/UserTransaction")
    UserTransaction ut;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          AlunoJpaController ajc= new AlunoJpaController(ut,emf);
          Aluno a =new Aluno();
        try {
            ajc.create(a);
        } catch (Exception ex) {
            Logger.getLogger(ProfessorController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
