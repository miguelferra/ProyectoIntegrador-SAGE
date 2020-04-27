/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Entidades.Detalleentregablespedido;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Entregables;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ferra
 */
public class DetalleentregablespedidoJpaController implements Serializable {

    public DetalleentregablespedidoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleentregablespedido detalleentregablespedido) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entregables entregablesIdentregable = detalleentregablespedido.getEntregablesIdentregable();
            if (entregablesIdentregable != null) {
                entregablesIdentregable = em.getReference(entregablesIdentregable.getClass(), entregablesIdentregable.getIdentregable());
                detalleentregablespedido.setEntregablesIdentregable(entregablesIdentregable);
            }
            em.persist(detalleentregablespedido);
            if (entregablesIdentregable != null) {
                entregablesIdentregable.getDetalleentregablespedidoList().add(detalleentregablespedido);
                entregablesIdentregable = em.merge(entregablesIdentregable);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleentregablespedido(detalleentregablespedido.getPedidosIdpedido()) != null) {
                throw new PreexistingEntityException("Detalleentregablespedido " + detalleentregablespedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleentregablespedido detalleentregablespedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleentregablespedido persistentDetalleentregablespedido = em.find(Detalleentregablespedido.class, detalleentregablespedido.getPedidosIdpedido());
            Entregables entregablesIdentregableOld = persistentDetalleentregablespedido.getEntregablesIdentregable();
            Entregables entregablesIdentregableNew = detalleentregablespedido.getEntregablesIdentregable();
            if (entregablesIdentregableNew != null) {
                entregablesIdentregableNew = em.getReference(entregablesIdentregableNew.getClass(), entregablesIdentregableNew.getIdentregable());
                detalleentregablespedido.setEntregablesIdentregable(entregablesIdentregableNew);
            }
            detalleentregablespedido = em.merge(detalleentregablespedido);
            if (entregablesIdentregableOld != null && !entregablesIdentregableOld.equals(entregablesIdentregableNew)) {
                entregablesIdentregableOld.getDetalleentregablespedidoList().remove(detalleentregablespedido);
                entregablesIdentregableOld = em.merge(entregablesIdentregableOld);
            }
            if (entregablesIdentregableNew != null && !entregablesIdentregableNew.equals(entregablesIdentregableOld)) {
                entregablesIdentregableNew.getDetalleentregablespedidoList().add(detalleentregablespedido);
                entregablesIdentregableNew = em.merge(entregablesIdentregableNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleentregablespedido.getPedidosIdpedido();
                if (findDetalleentregablespedido(id) == null) {
                    throw new NonexistentEntityException("The detalleentregablespedido with id " + id + " no longer exists.");
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
            Detalleentregablespedido detalleentregablespedido;
            try {
                detalleentregablespedido = em.getReference(Detalleentregablespedido.class, id);
                detalleentregablespedido.getPedidosIdpedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleentregablespedido with id " + id + " no longer exists.", enfe);
            }
            Entregables entregablesIdentregable = detalleentregablespedido.getEntregablesIdentregable();
            if (entregablesIdentregable != null) {
                entregablesIdentregable.getDetalleentregablespedidoList().remove(detalleentregablespedido);
                entregablesIdentregable = em.merge(entregablesIdentregable);
            }
            em.remove(detalleentregablespedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleentregablespedido> findDetalleentregablespedidoEntities() {
        return findDetalleentregablespedidoEntities(true, -1, -1);
    }

    public List<Detalleentregablespedido> findDetalleentregablespedidoEntities(int maxResults, int firstResult) {
        return findDetalleentregablespedidoEntities(false, maxResults, firstResult);
    }

    private List<Detalleentregablespedido> findDetalleentregablespedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleentregablespedido.class));
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

    public Detalleentregablespedido findDetalleentregablespedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleentregablespedido.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Detalleentregablespedido> buscarEntregables(int id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM basedatos_sage.detalleentregablespedido WHERE pedidos_idpedido =  "+id,Detalleentregablespedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getDetalleentregablespedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleentregablespedido> rt = cq.from(Detalleentregablespedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
