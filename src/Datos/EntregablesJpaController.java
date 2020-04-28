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
import Entidades.Detalleentregablespedido;
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

    public void create(Entregables entregables) {
        if (entregables.getDetalleentregablespedidoList() == null) {
            entregables.setDetalleentregablespedidoList(new ArrayList<Detalleentregablespedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalleentregablespedido> attachedDetalleentregablespedidoList = new ArrayList<Detalleentregablespedido>();
            for (Detalleentregablespedido detalleentregablespedidoListDetalleentregablespedidoToAttach : entregables.getDetalleentregablespedidoList()) {
                detalleentregablespedidoListDetalleentregablespedidoToAttach = em.getReference(detalleentregablespedidoListDetalleentregablespedidoToAttach.getClass(), detalleentregablespedidoListDetalleentregablespedidoToAttach.getIdDetalleEntregable());
                attachedDetalleentregablespedidoList.add(detalleentregablespedidoListDetalleentregablespedidoToAttach);
            }
            entregables.setDetalleentregablespedidoList(attachedDetalleentregablespedidoList);
            em.persist(entregables);
            for (Detalleentregablespedido detalleentregablespedidoListDetalleentregablespedido : entregables.getDetalleentregablespedidoList()) {
                Entregables oldEntregablesOfDetalleentregablespedidoListDetalleentregablespedido = detalleentregablespedidoListDetalleentregablespedido.getEntregables();
                detalleentregablespedidoListDetalleentregablespedido.setEntregables(entregables);
                detalleentregablespedidoListDetalleentregablespedido = em.merge(detalleentregablespedidoListDetalleentregablespedido);
                if (oldEntregablesOfDetalleentregablespedidoListDetalleentregablespedido != null) {
                    oldEntregablesOfDetalleentregablespedidoListDetalleentregablespedido.getDetalleentregablespedidoList().remove(detalleentregablespedidoListDetalleentregablespedido);
                    oldEntregablesOfDetalleentregablespedidoListDetalleentregablespedido = em.merge(oldEntregablesOfDetalleentregablespedidoListDetalleentregablespedido);
                }
            }
            em.getTransaction().commit();
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
            List<Detalleentregablespedido> detalleentregablespedidoListOld = persistentEntregables.getDetalleentregablespedidoList();
            List<Detalleentregablespedido> detalleentregablespedidoListNew = entregables.getDetalleentregablespedidoList();
            List<String> illegalOrphanMessages = null;
            for (Detalleentregablespedido detalleentregablespedidoListOldDetalleentregablespedido : detalleentregablespedidoListOld) {
                if (!detalleentregablespedidoListNew.contains(detalleentregablespedidoListOldDetalleentregablespedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleentregablespedido " + detalleentregablespedidoListOldDetalleentregablespedido + " since its entregables field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleentregablespedido> attachedDetalleentregablespedidoListNew = new ArrayList<Detalleentregablespedido>();
            for (Detalleentregablespedido detalleentregablespedidoListNewDetalleentregablespedidoToAttach : detalleentregablespedidoListNew) {
                detalleentregablespedidoListNewDetalleentregablespedidoToAttach = em.getReference(detalleentregablespedidoListNewDetalleentregablespedidoToAttach.getClass(), detalleentregablespedidoListNewDetalleentregablespedidoToAttach.getIdDetalleEntregable());
                attachedDetalleentregablespedidoListNew.add(detalleentregablespedidoListNewDetalleentregablespedidoToAttach);
            }
            detalleentregablespedidoListNew = attachedDetalleentregablespedidoListNew;
            entregables.setDetalleentregablespedidoList(detalleentregablespedidoListNew);
            entregables = em.merge(entregables);
            for (Detalleentregablespedido detalleentregablespedidoListNewDetalleentregablespedido : detalleentregablespedidoListNew) {
                if (!detalleentregablespedidoListOld.contains(detalleentregablespedidoListNewDetalleentregablespedido)) {
                    Entregables oldEntregablesOfDetalleentregablespedidoListNewDetalleentregablespedido = detalleentregablespedidoListNewDetalleentregablespedido.getEntregables();
                    detalleentregablespedidoListNewDetalleentregablespedido.setEntregables(entregables);
                    detalleentregablespedidoListNewDetalleentregablespedido = em.merge(detalleentregablespedidoListNewDetalleentregablespedido);
                    if (oldEntregablesOfDetalleentregablespedidoListNewDetalleentregablespedido != null && !oldEntregablesOfDetalleentregablespedidoListNewDetalleentregablespedido.equals(entregables)) {
                        oldEntregablesOfDetalleentregablespedidoListNewDetalleentregablespedido.getDetalleentregablespedidoList().remove(detalleentregablespedidoListNewDetalleentregablespedido);
                        oldEntregablesOfDetalleentregablespedidoListNewDetalleentregablespedido = em.merge(oldEntregablesOfDetalleentregablespedidoListNewDetalleentregablespedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entregables.getIdentregable();
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<Detalleentregablespedido> detalleentregablespedidoListOrphanCheck = entregables.getDetalleentregablespedidoList();
            for (Detalleentregablespedido detalleentregablespedidoListOrphanCheckDetalleentregablespedido : detalleentregablespedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Entregables (" + entregables + ") cannot be destroyed since the Detalleentregablespedido " + detalleentregablespedidoListOrphanCheckDetalleentregablespedido + " in its detalleentregablespedidoList field has a non-nullable entregables field.");
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

    public Entregables findEntregables(Integer id) {
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
