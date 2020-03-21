/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Detalleentregables;
import Entidades.Entregables;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ferra
 */
public class EntregablesJpaController implements Serializable {

    public EntregablesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entregables entregables) throws PreexistingEntityException, Exception {
        if (entregables.getDetalleentregablesList() == null) {
            entregables.setDetalleentregablesList(new ArrayList<Detalleentregables>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalleentregables> attachedDetalleentregablesList = new ArrayList<Detalleentregables>();
            for (Detalleentregables detalleentregablesListDetalleentregablesToAttach : entregables.getDetalleentregablesList()) {
                detalleentregablesListDetalleentregablesToAttach = em.getReference(detalleentregablesListDetalleentregablesToAttach.getClass(), detalleentregablesListDetalleentregablesToAttach.getDetalleentregablesPK());
                attachedDetalleentregablesList.add(detalleentregablesListDetalleentregablesToAttach);
            }
            entregables.setDetalleentregablesList(attachedDetalleentregablesList);
            em.persist(entregables);
            for (Detalleentregables detalleentregablesListDetalleentregables : entregables.getDetalleentregablesList()) {
                Entregables oldEntregablesOfDetalleentregablesListDetalleentregables = detalleentregablesListDetalleentregables.getEntregables();
                detalleentregablesListDetalleentregables.setEntregables(entregables);
                detalleentregablesListDetalleentregables = em.merge(detalleentregablesListDetalleentregables);
                if (oldEntregablesOfDetalleentregablesListDetalleentregables != null) {
                    oldEntregablesOfDetalleentregablesListDetalleentregables.getDetalleentregablesList().remove(detalleentregablesListDetalleentregables);
                    oldEntregablesOfDetalleentregablesListDetalleentregables = em.merge(oldEntregablesOfDetalleentregablesListDetalleentregables);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEntregables(entregables.getIdentregable()) != null) {
                throw new PreexistingEntityException("Entregables " + entregables + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entregables entregables) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entregables persistentEntregables = em.find(Entregables.class, entregables.getIdentregable());
            List<Detalleentregables> detalleentregablesListOld = persistentEntregables.getDetalleentregablesList();
            List<Detalleentregables> detalleentregablesListNew = entregables.getDetalleentregablesList();
            List<String> illegalOrphanMessages = null;
            for (Detalleentregables detalleentregablesListOldDetalleentregables : detalleentregablesListOld) {
                if (!detalleentregablesListNew.contains(detalleentregablesListOldDetalleentregables)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleentregables " + detalleentregablesListOldDetalleentregables + " since its entregables field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleentregables> attachedDetalleentregablesListNew = new ArrayList<Detalleentregables>();
            for (Detalleentregables detalleentregablesListNewDetalleentregablesToAttach : detalleentregablesListNew) {
                detalleentregablesListNewDetalleentregablesToAttach = em.getReference(detalleentregablesListNewDetalleentregablesToAttach.getClass(), detalleentregablesListNewDetalleentregablesToAttach.getDetalleentregablesPK());
                attachedDetalleentregablesListNew.add(detalleentregablesListNewDetalleentregablesToAttach);
            }
            detalleentregablesListNew = attachedDetalleentregablesListNew;
            entregables.setDetalleentregablesList(detalleentregablesListNew);
            entregables = em.merge(entregables);
            for (Detalleentregables detalleentregablesListNewDetalleentregables : detalleentregablesListNew) {
                if (!detalleentregablesListOld.contains(detalleentregablesListNewDetalleentregables)) {
                    Entregables oldEntregablesOfDetalleentregablesListNewDetalleentregables = detalleentregablesListNewDetalleentregables.getEntregables();
                    detalleentregablesListNewDetalleentregables.setEntregables(entregables);
                    detalleentregablesListNewDetalleentregables = em.merge(detalleentregablesListNewDetalleentregables);
                    if (oldEntregablesOfDetalleentregablesListNewDetalleentregables != null && !oldEntregablesOfDetalleentregablesListNewDetalleentregables.equals(entregables)) {
                        oldEntregablesOfDetalleentregablesListNewDetalleentregables.getDetalleentregablesList().remove(detalleentregablesListNewDetalleentregables);
                        oldEntregablesOfDetalleentregablesListNewDetalleentregables = em.merge(oldEntregablesOfDetalleentregablesListNewDetalleentregables);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = entregables.getIdentregable();
                if (findEntregables(id) == null) {
                    throw new NonexistentEntityException("The entregables with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entregables entregables;
            try {
                entregables = em.getReference(Entregables.class, id);
                entregables.getIdentregable();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entregables with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detalleentregables> detalleentregablesListOrphanCheck = entregables.getDetalleentregablesList();
            for (Detalleentregables detalleentregablesListOrphanCheckDetalleentregables : detalleentregablesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Entregables (" + entregables + ") cannot be destroyed since the Detalleentregables " + detalleentregablesListOrphanCheckDetalleentregables + " in its detalleentregablesList field has a non-nullable entregables field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(entregables);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entregables> findEntregablesEntities() {
        return findEntregablesEntities(true, -1, -1);
    }

    public List<Entregables> findEntregablesEntities(int maxResults, int firstResult) {
        return findEntregablesEntities(false, maxResults, firstResult);
    }

    private List<Entregables> findEntregablesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entregables.class));
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

    public Entregables findEntregables(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entregables.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntregablesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entregables> rt = cq.from(Entregables.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
