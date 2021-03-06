/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lpwsd.dao;

import Classes.Eventos;
import br.cesjf.lpwsd.dao.exceptions.NonexistentEntityException;
import br.cesjf.lpwsd.dao.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Filipe
 */
public class EventosJpaController implements Serializable {

    public EventosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Eventos eventos) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(eventos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Eventos eventos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            eventos = em.merge(eventos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = eventos.getId();
                if (findEventos(id) == null) {
                    throw new NonexistentEntityException("The eventos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Eventos eventos;
            try {
                eventos = em.getReference(Eventos.class, id);
                eventos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventos with id " + id + " no longer exists.", enfe);
            }
            em.remove(eventos);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Eventos> findEventosEntities() {
        return findEventosEntities(true, -1, -1);
    }

    public List<Eventos> findEventosEntities(int maxResults, int firstResult) {
        return findEventosEntities(false, maxResults, firstResult);
    }

    private List<Eventos> findEventosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Eventos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Eventos findEventos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Eventos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEventosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Eventos> rt = cq.from(Eventos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     public List<Object[]> getEventosCount2(Date i, Date f) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Object[]> q = em.createQuery("SELECT e.aluno.grupo AS GRUPO, SUM(e.pontos) AS TOTAL FROM Eventos AS e WHERE e.data BETWEEN :data1 AND :data2 GROUP BY e.aluno.grupo",Object[].class);
            q.setParameter("data1", i);
            q.setParameter("data2", f);

            return q.getResultList();
        } finally {
            em.close();
        }
    }
     public List<Eventos> getEventosCount3(Date i, Date f)  {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Eventos> q = em.createQuery("SELECT e FROM Eventos e WHERE e.data BETWEEN :data1 AND :data2",Eventos.class);
            q.setParameter("data1", i);
            q.setParameter("data2", f);
            
            return q.getResultList();
        } 
        finally {
            em.close();
        }
    }
}
