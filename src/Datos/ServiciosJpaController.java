/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
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

    public void create(Servicios servicios) throws PreexistingEntityException, Exception {
        if (servicios.getDetalleservicioList() == null) {
            servicios.setDetalleservicioList(new ArrayList<Detalleservicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalleservicio> attachedDetalleservicioList = new ArrayList<Detalleservicio>();
            for (Detalleservicio detalleservicioListDetalleservicioToAttach : servicios.getDetalleservicioList()) {
                detalleservicioListDetalleservicioToAttach = em.getReference(detalleservicioListDetalleservicioToAttach.getClass(), detalleservicioListDetalleservicioToAttach.getDetalleservicioPK());
                attachedDetalleservicioList.add(detalleservicioListDetalleservicioToAttach);
            }
            servicios.setDetalleservicioList(attachedDetalleservicioList);
            em.persist(servicios);
            for (Detalleservicio detalleservicioListDetalleservicio : servicios.getDetalleservicioList()) {
                Servicios oldServiciosOfDetalleservicioListDetalleservicio = detalleservicioListDetalleservicio.getServicios();
                detalleservicioListDetalleservicio.setServicios(servicios);
                detalleservicioListDetalleservicio = em.merge(detalleservicioListDetalleservicio);
                if (oldServiciosOfDetalleservicioListDetalleservicio != null) {
                    oldServiciosOfDetalleservicioListDetalleservicio.getDetalleservicioList().remove(detalleservicioListDetalleservicio);
                    oldServiciosOfDetalleservicioListDetalleservicio = em.merge(oldServiciosOfDetalleservicioListDetalleservicio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicios(servicios.getIdservicio()) != null) {
                throw new PreexistingEntityException("Servicios " + servicios + " already exists.", ex);
            }
            throw ex;
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
            List<Detalleservicio> detalleservicioListOld = persistentServicios.getDetalleservicioList();
            List<Detalleservicio> detalleservicioListNew = servicios.getDetalleservicioList();
            List<String> illegalOrphanMessages = null;
            for (Detalleservicio detalleservicioListOldDetalleservicio : detalleservicioListOld) {
                if (!detalleservicioListNew.contains(detalleservicioListOldDetalleservicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleservicio " + detalleservicioListOldDetalleservicio + " since its servicios field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleservicio> attachedDetalleservicioListNew = new ArrayList<Detalleservicio>();
            for (Detalleservicio detalleservicioListNewDetalleservicioToAttach : detalleservicioListNew) {
                detalleservicioListNewDetalleservicioToAttach = em.getReference(detalleservicioListNewDetalleservicioToAttach.getClass(), detalleservicioListNewDetalleservicioToAttach.getDetalleservicioPK());
                attachedDetalleservicioListNew.add(detalleservicioListNewDetalleservicioToAttach);
            }
            detalleservicioListNew = attachedDetalleservicioListNew;
            servicios.setDetalleservicioList(detalleservicioListNew);
            servicios = em.merge(servicios);
            for (Detalleservicio detalleservicioListNewDetalleservicio : detalleservicioListNew) {
                if (!detalleservicioListOld.contains(detalleservicioListNewDetalleservicio)) {
                    Servicios oldServiciosOfDetalleservicioListNewDetalleservicio = detalleservicioListNewDetalleservicio.getServicios();
                    detalleservicioListNewDetalleservicio.setServicios(servicios);
                    detalleservicioListNewDetalleservicio = em.merge(detalleservicioListNewDetalleservicio);
                    if (oldServiciosOfDetalleservicioListNewDetalleservicio != null && !oldServiciosOfDetalleservicioListNewDetalleservicio.equals(servicios)) {
                        oldServiciosOfDetalleservicioListNewDetalleservicio.getDetalleservicioList().remove(detalleservicioListNewDetalleservicio);
                        oldServiciosOfDetalleservicioListNewDetalleservicio = em.merge(oldServiciosOfDetalleservicioListNewDetalleservicio);
                    }
                }
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
            List<Detalleservicio> detalleservicioListOrphanCheck = servicios.getDetalleservicioList();
            for (Detalleservicio detalleservicioListOrphanCheckDetalleservicio : detalleservicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicios (" + servicios + ") cannot be destroyed since the Detalleservicio " + detalleservicioListOrphanCheckDetalleservicio + " in its detalleservicioList field has a non-nullable servicios field.");
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
