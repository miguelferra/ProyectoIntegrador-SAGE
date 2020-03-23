/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Detalleservicio;
import Entidades.Servicios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ferra
 */
public class ServiciosJpaController implements Serializable {

    public ServiciosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicios servicios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleservicio detalleservicio = servicios.getDetalleservicio();
            if (detalleservicio != null) {
                detalleservicio = em.getReference(detalleservicio.getClass(), detalleservicio.getServiciosIdservicio());
                servicios.setDetalleservicio(detalleservicio);
            }
            em.persist(servicios);
            if (detalleservicio != null) {
                Servicios oldServiciosOfDetalleservicio = detalleservicio.getServicios();
                if (oldServiciosOfDetalleservicio != null) {
                    oldServiciosOfDetalleservicio.setDetalleservicio(null);
                    oldServiciosOfDetalleservicio = em.merge(oldServiciosOfDetalleservicio);
                }
                detalleservicio.setServicios(servicios);
                detalleservicio = em.merge(detalleservicio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicios servicios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios persistentServicios = em.find(Servicios.class, servicios.getIdservicio());
            Detalleservicio detalleservicioOld = persistentServicios.getDetalleservicio();
            Detalleservicio detalleservicioNew = servicios.getDetalleservicio();
            List<String> illegalOrphanMessages = null;
            if (detalleservicioOld != null && !detalleservicioOld.equals(detalleservicioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detalleservicio " + detalleservicioOld + " since its servicios field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (detalleservicioNew != null) {
                detalleservicioNew = em.getReference(detalleservicioNew.getClass(), detalleservicioNew.getServiciosIdservicio());
                servicios.setDetalleservicio(detalleservicioNew);
            }
            servicios = em.merge(servicios);
            if (detalleservicioNew != null && !detalleservicioNew.equals(detalleservicioOld)) {
                Servicios oldServiciosOfDetalleservicio = detalleservicioNew.getServicios();
                if (oldServiciosOfDetalleservicio != null) {
                    oldServiciosOfDetalleservicio.setDetalleservicio(null);
                    oldServiciosOfDetalleservicio = em.merge(oldServiciosOfDetalleservicio);
                }
                detalleservicioNew.setServicios(servicios);
                detalleservicioNew = em.merge(detalleservicioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicios.getIdservicio();
                if (findServicios(id) == null) {
                    throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios servicios;
            try {
                servicios = em.getReference(Servicios.class, id);
                servicios.getIdservicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Detalleservicio detalleservicioOrphanCheck = servicios.getDetalleservicio();
            if (detalleservicioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicios (" + servicios + ") cannot be destroyed since the Detalleservicio " + detalleservicioOrphanCheck + " in its detalleservicio field has a non-nullable servicios field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(servicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicios> findServiciosEntities() {
        return findServiciosEntities(true, -1, -1);
    }

    public List<Servicios> findServiciosEntities(int maxResults, int firstResult) {
        return findServiciosEntities(false, maxResults, firstResult);
    }

    private List<Servicios> findServiciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicios.class));
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

    public Servicios findServicios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicios.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicios> rt = cq.from(Servicios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
