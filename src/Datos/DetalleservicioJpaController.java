/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import Entidades.Detalleservicio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Pedidos;
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
public class DetalleservicioJpaController implements Serializable {

    public DetalleservicioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleservicio detalleservicio) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Pedidos pedidosOrphanCheck = detalleservicio.getPedidos();
        if (pedidosOrphanCheck != null) {
            Detalleservicio oldDetalleservicioOfPedidos = pedidosOrphanCheck.getDetalleservicio();
            if (oldDetalleservicioOfPedidos != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Pedidos " + pedidosOrphanCheck + " already has an item of type Detalleservicio whose pedidos column cannot be null. Please make another selection for the pedidos field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidos pedidos = detalleservicio.getPedidos();
            if (pedidos != null) {
                pedidos = em.getReference(pedidos.getClass(), pedidos.getIdpedido());
                detalleservicio.setPedidos(pedidos);
            }
            Servicios serviciosIdservicio = detalleservicio.getServiciosIdservicio();
            if (serviciosIdservicio != null) {
                serviciosIdservicio = em.getReference(serviciosIdservicio.getClass(), serviciosIdservicio.getIdservicio());
                detalleservicio.setServiciosIdservicio(serviciosIdservicio);
            }
            em.persist(detalleservicio);
            if (pedidos != null) {
                pedidos.setDetalleservicio(detalleservicio);
                pedidos = em.merge(pedidos);
            }
            if (serviciosIdservicio != null) {
                serviciosIdservicio.getDetalleservicioList().add(detalleservicio);
                serviciosIdservicio = em.merge(serviciosIdservicio);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleservicio(detalleservicio.getPedidosIdpedido()) != null) {
                throw new PreexistingEntityException("Detalleservicio " + detalleservicio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleservicio detalleservicio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleservicio persistentDetalleservicio = em.find(Detalleservicio.class, detalleservicio.getPedidosIdpedido());
            Pedidos pedidosOld = persistentDetalleservicio.getPedidos();
            Pedidos pedidosNew = detalleservicio.getPedidos();
            Servicios serviciosIdservicioOld = persistentDetalleservicio.getServiciosIdservicio();
            Servicios serviciosIdservicioNew = detalleservicio.getServiciosIdservicio();
            List<String> illegalOrphanMessages = null;
            if (pedidosNew != null && !pedidosNew.equals(pedidosOld)) {
                Detalleservicio oldDetalleservicioOfPedidos = pedidosNew.getDetalleservicio();
                if (oldDetalleservicioOfPedidos != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Pedidos " + pedidosNew + " already has an item of type Detalleservicio whose pedidos column cannot be null. Please make another selection for the pedidos field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pedidosNew != null) {
                pedidosNew = em.getReference(pedidosNew.getClass(), pedidosNew.getIdpedido());
                detalleservicio.setPedidos(pedidosNew);
            }
            if (serviciosIdservicioNew != null) {
                serviciosIdservicioNew = em.getReference(serviciosIdservicioNew.getClass(), serviciosIdservicioNew.getIdservicio());
                detalleservicio.setServiciosIdservicio(serviciosIdservicioNew);
            }
            detalleservicio = em.merge(detalleservicio);
            if (pedidosOld != null && !pedidosOld.equals(pedidosNew)) {
                pedidosOld.setDetalleservicio(null);
                pedidosOld = em.merge(pedidosOld);
            }
            if (pedidosNew != null && !pedidosNew.equals(pedidosOld)) {
                pedidosNew.setDetalleservicio(detalleservicio);
                pedidosNew = em.merge(pedidosNew);
            }
            if (serviciosIdservicioOld != null && !serviciosIdservicioOld.equals(serviciosIdservicioNew)) {
                serviciosIdservicioOld.getDetalleservicioList().remove(detalleservicio);
                serviciosIdservicioOld = em.merge(serviciosIdservicioOld);
            }
            if (serviciosIdservicioNew != null && !serviciosIdservicioNew.equals(serviciosIdservicioOld)) {
                serviciosIdservicioNew.getDetalleservicioList().add(detalleservicio);
                serviciosIdservicioNew = em.merge(serviciosIdservicioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleservicio.getPedidosIdpedido();
                if (findDetalleservicio(id) == null) {
                    throw new NonexistentEntityException("The detalleservicio with id " + id + " no longer exists.");
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
            Detalleservicio detalleservicio;
            try {
                detalleservicio = em.getReference(Detalleservicio.class, id);
                detalleservicio.getPedidosIdpedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleservicio with id " + id + " no longer exists.", enfe);
            }
            Pedidos pedidos = detalleservicio.getPedidos();
            if (pedidos != null) {
                pedidos.setDetalleservicio(null);
                pedidos = em.merge(pedidos);
            }
            Servicios serviciosIdservicio = detalleservicio.getServiciosIdservicio();
            if (serviciosIdservicio != null) {
                serviciosIdservicio.getDetalleservicioList().remove(detalleservicio);
                serviciosIdservicio = em.merge(serviciosIdservicio);
            }
            em.remove(detalleservicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleservicio> findDetalleservicioEntities() {
        return findDetalleservicioEntities(true, -1, -1);
    }

    public List<Detalleservicio> findDetalleservicioEntities(int maxResults, int firstResult) {
        return findDetalleservicioEntities(false, maxResults, firstResult);
    }

    private List<Detalleservicio> findDetalleservicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleservicio.class));
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

    public Detalleservicio findDetalleservicio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleservicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleservicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleservicio> rt = cq.from(Detalleservicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Detalleservicio> buscarServicios(int id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM basedatos_sage.detalleservicio WHERE pedidos_idpedido = "+id,Detalleservicio.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
