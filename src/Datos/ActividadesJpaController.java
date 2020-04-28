/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Entidades.Actividades;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Detalleactividades;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ferra
 */
public class ActividadesJpaController implements Serializable {

    public ActividadesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividades actividades) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleactividades detalleactividades = actividades.getDetalleactividades();
            if (detalleactividades != null) {
                detalleactividades = em.getReference(detalleactividades.getClass(), detalleactividades.getDetalleactividadesPK());
                actividades.setDetalleactividades(detalleactividades);
            }
            em.persist(actividades);
            if (detalleactividades != null) {
                Actividades oldActividadesOfDetalleactividades = detalleactividades.getActividades();
                if (oldActividadesOfDetalleactividades != null) {
                    oldActividadesOfDetalleactividades.setDetalleactividades(null);
                    oldActividadesOfDetalleactividades = em.merge(oldActividadesOfDetalleactividades);
                }
                detalleactividades.setActividades(actividades);
                detalleactividades = em.merge(detalleactividades);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividades actividades) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividades persistentActividades = em.find(Actividades.class, actividades.getIdactividad());
            Detalleactividades detalleactividadesOld = persistentActividades.getDetalleactividades();
            Detalleactividades detalleactividadesNew = actividades.getDetalleactividades();
            List<String> illegalOrphanMessages = null;
            if (detalleactividadesOld != null && !detalleactividadesOld.equals(detalleactividadesNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detalleactividades " + detalleactividadesOld + " since its actividades field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (detalleactividadesNew != null) {
                detalleactividadesNew = em.getReference(detalleactividadesNew.getClass(), detalleactividadesNew.getDetalleactividadesPK());
                actividades.setDetalleactividades(detalleactividadesNew);
            }
            actividades = em.merge(actividades);
            if (detalleactividadesNew != null && !detalleactividadesNew.equals(detalleactividadesOld)) {
                Actividades oldActividadesOfDetalleactividades = detalleactividadesNew.getActividades();
                if (oldActividadesOfDetalleactividades != null) {
                    oldActividadesOfDetalleactividades.setDetalleactividades(null);
                    oldActividadesOfDetalleactividades = em.merge(oldActividadesOfDetalleactividades);
                }
                detalleactividadesNew.setActividades(actividades);
                detalleactividadesNew = em.merge(detalleactividadesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actividades.getIdactividad();
                if (findActividades(id) == null) {
                    throw new NonexistentEntityException("The actividades with id " + id + " no longer exists.");
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
            Actividades actividades;
            try {
                actividades = em.getReference(Actividades.class, id);
                actividades.getIdactividad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividades with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Detalleactividades detalleactividadesOrphanCheck = actividades.getDetalleactividades();
            if (detalleactividadesOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Actividades (" + actividades + ") cannot be destroyed since the Detalleactividades " + detalleactividadesOrphanCheck + " in its detalleactividades field has a non-nullable actividades field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(actividades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividades> findActividadesEntities() {
        return findActividadesEntities(true, -1, -1);
    }

    public List<Actividades> findActividadesEntities(int maxResults, int firstResult) {
        return findActividadesEntities(false, maxResults, firstResult);
    }

    private List<Actividades> findActividadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividades.class));
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

    public Actividades findActividades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividades.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividades> rt = cq.from(Actividades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
