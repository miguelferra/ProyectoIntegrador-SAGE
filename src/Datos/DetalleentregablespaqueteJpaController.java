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
            Paquetes paquetes = detalleentregablespaquete.getPaquetes();
            if (paquetes != null) {
                paquetes = em.getReference(paquetes.getClass(), paquetes.getIdpaquete());
                detalleentregablespaquete.setPaquetes(paquetes);
            }
            em.persist(detalleentregablespaquete);
            if (paquetes != null) {
                paquetes.getDetalleentregablespaqueteList().add(detalleentregablespaquete);
                paquetes = em.merge(paquetes);
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
            Paquetes paquetesOld = persistentDetalleentregablespaquete.getPaquetes();
            Paquetes paquetesNew = detalleentregablespaquete.getPaquetes();
            if (paquetesNew != null) {
                paquetesNew = em.getReference(paquetesNew.getClass(), paquetesNew.getIdpaquete());
                detalleentregablespaquete.setPaquetes(paquetesNew);
            }
            detalleentregablespaquete = em.merge(detalleentregablespaquete);
            if (paquetesOld != null && !paquetesOld.equals(paquetesNew)) {
                paquetesOld.getDetalleentregablespaqueteList().remove(detalleentregablespaquete);
                paquetesOld = em.merge(paquetesOld);
            }
            if (paquetesNew != null && !paquetesNew.equals(paquetesOld)) {
                paquetesNew.getDetalleentregablespaqueteList().add(detalleentregablespaquete);
                paquetesNew = em.merge(paquetesNew);
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
            Paquetes paquetes = detalleentregablespaquete.getPaquetes();
            if (paquetes != null) {
                paquetes.getDetalleentregablespaqueteList().remove(detalleentregablespaquete);
                paquetes = em.merge(paquetes);
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
    
    public List<Detalleentregablespaquete> buscarEntregables(int id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM bd_sage.detalleentregablespaquete WHERE paquetes_idpaquete =  "+id+" AND entregables_identregable IS NOT NULL",Detalleentregablespaquete.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Detalleentregablespaquete> buscarServicios(int id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM bd_sage.detalleentregablespaquete WHERE paquetes_idpaquete =  "+id+" AND servicios_idservicio IS NOT NULL",Detalleentregablespaquete.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
