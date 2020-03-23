/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Entidades.Detalleentregablespaquete;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Paquetes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ferra
 */
public class DetalleentregablespaqueteJpaController implements Serializable {

    public DetalleentregablespaqueteJpaController() {
         this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleentregablespaquete detalleentregablespaquete) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquetes paquetesIdpaquete = detalleentregablespaquete.getPaquetesIdpaquete();
            if (paquetesIdpaquete != null) {
                paquetesIdpaquete = em.getReference(paquetesIdpaquete.getClass(), paquetesIdpaquete.getIdpaquete());
                detalleentregablespaquete.setPaquetesIdpaquete(paquetesIdpaquete);
            }
            em.persist(detalleentregablespaquete);
            if (paquetesIdpaquete != null) {
                paquetesIdpaquete.getDetalleentregablespaqueteList().add(detalleentregablespaquete);
                paquetesIdpaquete = em.merge(paquetesIdpaquete);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleentregablespaquete detalleentregablespaquete) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleentregablespaquete persistentDetalleentregablespaquete = em.find(Detalleentregablespaquete.class, detalleentregablespaquete.getPrueba());
            Paquetes paquetesIdpaqueteOld = persistentDetalleentregablespaquete.getPaquetesIdpaquete();
            Paquetes paquetesIdpaqueteNew = detalleentregablespaquete.getPaquetesIdpaquete();
            if (paquetesIdpaqueteNew != null) {
                paquetesIdpaqueteNew = em.getReference(paquetesIdpaqueteNew.getClass(), paquetesIdpaqueteNew.getIdpaquete());
                detalleentregablespaquete.setPaquetesIdpaquete(paquetesIdpaqueteNew);
            }
            detalleentregablespaquete = em.merge(detalleentregablespaquete);
            if (paquetesIdpaqueteOld != null && !paquetesIdpaqueteOld.equals(paquetesIdpaqueteNew)) {
                paquetesIdpaqueteOld.getDetalleentregablespaqueteList().remove(detalleentregablespaquete);
                paquetesIdpaqueteOld = em.merge(paquetesIdpaqueteOld);
            }
            if (paquetesIdpaqueteNew != null && !paquetesIdpaqueteNew.equals(paquetesIdpaqueteOld)) {
                paquetesIdpaqueteNew.getDetalleentregablespaqueteList().add(detalleentregablespaquete);
                paquetesIdpaqueteNew = em.merge(paquetesIdpaqueteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleentregablespaquete.getPrueba();
                if (findDetalleentregablespaquete(id) == null) {
                    throw new NonexistentEntityException("The detalleentregablespaquete with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleentregablespaquete detalleentregablespaquete;
            try {
                detalleentregablespaquete = em.getReference(Detalleentregablespaquete.class, id);
                detalleentregablespaquete.getPrueba();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleentregablespaquete with id " + id + " no longer exists.", enfe);
            }
            Paquetes paquetesIdpaquete = detalleentregablespaquete.getPaquetesIdpaquete();
            if (paquetesIdpaquete != null) {
                paquetesIdpaquete.getDetalleentregablespaqueteList().remove(detalleentregablespaquete);
                paquetesIdpaquete = em.merge(paquetesIdpaquete);
            }
            em.remove(detalleentregablespaquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleentregablespaquete> findDetalleentregablespaqueteEntities() {
        return findDetalleentregablespaqueteEntities(true, -1, -1);
    }

    public List<Detalleentregablespaquete> findDetalleentregablespaqueteEntities(int maxResults, int firstResult) {
        return findDetalleentregablespaqueteEntities(false, maxResults, firstResult);
    }

    private List<Detalleentregablespaquete> findDetalleentregablespaqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleentregablespaquete.class));
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

    public Detalleentregablespaquete findDetalleentregablespaquete(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleentregablespaquete.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Detalleentregablespaquete> buscarEntregables(int id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM basedatos_sage.detalleentregablespaquete WHERE paquetes_idpaquete =  "+id+" AND entregables_identregable IS NOT NULL",Detalleentregablespaquete.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getDetalleentregablespaqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleentregablespaquete> rt = cq.from(Detalleentregablespaquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
