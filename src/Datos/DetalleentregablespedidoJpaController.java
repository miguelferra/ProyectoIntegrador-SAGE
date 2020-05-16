/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Entidades.Detalleentregablespedido;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Entregables;
import Entidades.Pedidos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public void create(Detalleentregablespedido detalleentregablespedido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entregables entregables = detalleentregablespedido.getEntregables();
            if (entregables != null) {
                entregables = em.getReference(entregables.getClass(), entregables.getIdentregable());
                detalleentregablespedido.setEntregables(entregables);
            }
            Pedidos pedidos = detalleentregablespedido.getPedidos();
            if (pedidos != null) {
                pedidos = em.getReference(pedidos.getClass(), pedidos.getIdpedido());
                detalleentregablespedido.setPedidos(pedidos);
            }
            em.persist(detalleentregablespedido);
            if (entregables != null) {
                entregables.getDetalleentregablespedidoList().add(detalleentregablespedido);
                entregables = em.merge(entregables);
            }
            if (pedidos != null) {
                pedidos.getDetalleentregablespedidoList().add(detalleentregablespedido);
                pedidos = em.merge(pedidos);
            }
            em.getTransaction().commit();
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
            Detalleentregablespedido persistentDetalleentregablespedido = em.find(Detalleentregablespedido.class, detalleentregablespedido.getIdDetalleEntregable());
            Entregables entregablesOld = persistentDetalleentregablespedido.getEntregables();
            Entregables entregablesNew = detalleentregablespedido.getEntregables();
            Pedidos pedidosOld = persistentDetalleentregablespedido.getPedidos();
            Pedidos pedidosNew = detalleentregablespedido.getPedidos();
            if (entregablesNew != null) {
                entregablesNew = em.getReference(entregablesNew.getClass(), entregablesNew.getIdentregable());
                detalleentregablespedido.setEntregables(entregablesNew);
            }
            if (pedidosNew != null) {
                pedidosNew = em.getReference(pedidosNew.getClass(), pedidosNew.getIdpedido());
                detalleentregablespedido.setPedidos(pedidosNew);
            }
            detalleentregablespedido = em.merge(detalleentregablespedido);
            if (entregablesOld != null && !entregablesOld.equals(entregablesNew)) {
                entregablesOld.getDetalleentregablespedidoList().remove(detalleentregablespedido);
                entregablesOld = em.merge(entregablesOld);
            }
            if (entregablesNew != null && !entregablesNew.equals(entregablesOld)) {
                entregablesNew.getDetalleentregablespedidoList().add(detalleentregablespedido);
                entregablesNew = em.merge(entregablesNew);
            }
            if (pedidosOld != null && !pedidosOld.equals(pedidosNew)) {
                pedidosOld.getDetalleentregablespedidoList().remove(detalleentregablespedido);
                pedidosOld = em.merge(pedidosOld);
            }
            if (pedidosNew != null && !pedidosNew.equals(pedidosOld)) {
                pedidosNew.getDetalleentregablespedidoList().add(detalleentregablespedido);
                pedidosNew = em.merge(pedidosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleentregablespedido.getIdDetalleEntregable();
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
                detalleentregablespedido.getIdDetalleEntregable();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleentregablespedido with id " + id + " no longer exists.", enfe);
            }
            Entregables entregables = detalleentregablespedido.getEntregables();
            if (entregables != null) {
                entregables.getDetalleentregablespedidoList().remove(detalleentregablespedido);
                entregables = em.merge(entregables);
            }
            Pedidos pedidos = detalleentregablespedido.getPedidos();
            if (pedidos != null) {
                pedidos.getDetalleentregablespedidoList().remove(detalleentregablespedido);
                pedidos = em.merge(pedidos);
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
    
    public List<Detalleentregablespedido> buscarEntregables(int id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM basedatos_sage.detalleentregablespedido WHERE pedidos_idpedido =  "+id,Detalleentregablespedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void eliminarDetalleEntregable(int id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM bd_sage.detalleentregablespedido WHERE pedidos_idpedido = "+id,Detalleentregablespedido.class);
            List<Detalleentregablespedido> listaEntregables = q.getResultList();
            for (Detalleentregablespedido entregable : listaEntregables) {
                destroy(entregable.getIdDetalleEntregable());
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DetalleentregablespedidoJpaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }

    
}
