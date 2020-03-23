/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Entidades.Detalleentregablespedido;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Entregables;
import Entidades.Pedidos;
import java.util.ArrayList;
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

    public void create(Detalleentregablespedido detalleentregablespedido) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Entregables entregablesOrphanCheck = detalleentregablespedido.getEntregables();
        if (entregablesOrphanCheck != null) {
            Detalleentregablespedido oldDetalleentregablespedidoOfEntregables = entregablesOrphanCheck.getDetalleentregablespedido();
            if (oldDetalleentregablespedidoOfEntregables != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Entregables " + entregablesOrphanCheck + " already has an item of type Detalleentregablespedido whose entregables column cannot be null. Please make another selection for the entregables field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entregables entregables = detalleentregablespedido.getEntregables();
            if (entregables != null) {
                entregables = em.getReference(entregables.getClass(), entregables.getIdentregable());
                detalleentregablespedido.setEntregables(entregables);
            }
            Pedidos pedidosIdpedido = detalleentregablespedido.getPedidosIdpedido();
            if (pedidosIdpedido != null) {
                pedidosIdpedido = em.getReference(pedidosIdpedido.getClass(), pedidosIdpedido.getIdpedido());
                detalleentregablespedido.setPedidosIdpedido(pedidosIdpedido);
            }
            em.persist(detalleentregablespedido);
            if (entregables != null) {
                entregables.setDetalleentregablespedido(detalleentregablespedido);
                entregables = em.merge(entregables);
            }
            if (pedidosIdpedido != null) {
                pedidosIdpedido.getDetalleentregablespedidoList().add(detalleentregablespedido);
                pedidosIdpedido = em.merge(pedidosIdpedido);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleentregablespedido(detalleentregablespedido.getEntregablesIdentregable()) != null) {
                throw new PreexistingEntityException("Detalleentregablespedido " + detalleentregablespedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleentregablespedido detalleentregablespedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleentregablespedido persistentDetalleentregablespedido = em.find(Detalleentregablespedido.class, detalleentregablespedido.getEntregablesIdentregable());
            Entregables entregablesOld = persistentDetalleentregablespedido.getEntregables();
            Entregables entregablesNew = detalleentregablespedido.getEntregables();
            Pedidos pedidosIdpedidoOld = persistentDetalleentregablespedido.getPedidosIdpedido();
            Pedidos pedidosIdpedidoNew = detalleentregablespedido.getPedidosIdpedido();
            List<String> illegalOrphanMessages = null;
            if (entregablesNew != null && !entregablesNew.equals(entregablesOld)) {
                Detalleentregablespedido oldDetalleentregablespedidoOfEntregables = entregablesNew.getDetalleentregablespedido();
                if (oldDetalleentregablespedidoOfEntregables != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Entregables " + entregablesNew + " already has an item of type Detalleentregablespedido whose entregables column cannot be null. Please make another selection for the entregables field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (entregablesNew != null) {
                entregablesNew = em.getReference(entregablesNew.getClass(), entregablesNew.getIdentregable());
                detalleentregablespedido.setEntregables(entregablesNew);
            }
            if (pedidosIdpedidoNew != null) {
                pedidosIdpedidoNew = em.getReference(pedidosIdpedidoNew.getClass(), pedidosIdpedidoNew.getIdpedido());
                detalleentregablespedido.setPedidosIdpedido(pedidosIdpedidoNew);
            }
            detalleentregablespedido = em.merge(detalleentregablespedido);
            if (entregablesOld != null && !entregablesOld.equals(entregablesNew)) {
                entregablesOld.setDetalleentregablespedido(null);
                entregablesOld = em.merge(entregablesOld);
            }
            if (entregablesNew != null && !entregablesNew.equals(entregablesOld)) {
                entregablesNew.setDetalleentregablespedido(detalleentregablespedido);
                entregablesNew = em.merge(entregablesNew);
            }
            if (pedidosIdpedidoOld != null && !pedidosIdpedidoOld.equals(pedidosIdpedidoNew)) {
                pedidosIdpedidoOld.getDetalleentregablespedidoList().remove(detalleentregablespedido);
                pedidosIdpedidoOld = em.merge(pedidosIdpedidoOld);
            }
            if (pedidosIdpedidoNew != null && !pedidosIdpedidoNew.equals(pedidosIdpedidoOld)) {
                pedidosIdpedidoNew.getDetalleentregablespedidoList().add(detalleentregablespedido);
                pedidosIdpedidoNew = em.merge(pedidosIdpedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleentregablespedido.getEntregablesIdentregable();
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
                detalleentregablespedido.getEntregablesIdentregable();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleentregablespedido with id " + id + " no longer exists.", enfe);
            }
            Entregables entregables = detalleentregablespedido.getEntregables();
            if (entregables != null) {
                entregables.setDetalleentregablespedido(null);
                entregables = em.merge(entregables);
            }
            Pedidos pedidosIdpedido = detalleentregablespedido.getPedidosIdpedido();
            if (pedidosIdpedido != null) {
                pedidosIdpedido.getDetalleentregablespedidoList().remove(detalleentregablespedido);
                pedidosIdpedido = em.merge(pedidosIdpedido);
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
