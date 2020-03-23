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
        Servicios serviciosOrphanCheck = detalleservicio.getServicios();
        if (serviciosOrphanCheck != null) {
            Detalleservicio oldDetalleservicioOfServicios = serviciosOrphanCheck.getDetalleservicio();
            if (oldDetalleservicioOfServicios != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Servicios " + serviciosOrphanCheck + " already has an item of type Detalleservicio whose servicios column cannot be null. Please make another selection for the servicios field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidos pedidosIdpedido = detalleservicio.getPedidosIdpedido();
            if (pedidosIdpedido != null) {
                pedidosIdpedido = em.getReference(pedidosIdpedido.getClass(), pedidosIdpedido.getIdpedido());
                detalleservicio.setPedidosIdpedido(pedidosIdpedido);
            }
            Servicios servicios = detalleservicio.getServicios();
            if (servicios != null) {
                servicios = em.getReference(servicios.getClass(), servicios.getIdservicio());
                detalleservicio.setServicios(servicios);
            }
            em.persist(detalleservicio);
            if (pedidosIdpedido != null) {
                pedidosIdpedido.getDetalleservicioList().add(detalleservicio);
                pedidosIdpedido = em.merge(pedidosIdpedido);
            }
            if (servicios != null) {
                servicios.setDetalleservicio(detalleservicio);
                servicios = em.merge(servicios);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleservicio(detalleservicio.getServiciosIdservicio()) != null) {
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
            Detalleservicio persistentDetalleservicio = em.find(Detalleservicio.class, detalleservicio.getServiciosIdservicio());
            Pedidos pedidosIdpedidoOld = persistentDetalleservicio.getPedidosIdpedido();
            Pedidos pedidosIdpedidoNew = detalleservicio.getPedidosIdpedido();
            Servicios serviciosOld = persistentDetalleservicio.getServicios();
            Servicios serviciosNew = detalleservicio.getServicios();
            List<String> illegalOrphanMessages = null;
            if (serviciosNew != null && !serviciosNew.equals(serviciosOld)) {
                Detalleservicio oldDetalleservicioOfServicios = serviciosNew.getDetalleservicio();
                if (oldDetalleservicioOfServicios != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Servicios " + serviciosNew + " already has an item of type Detalleservicio whose servicios column cannot be null. Please make another selection for the servicios field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pedidosIdpedidoNew != null) {
                pedidosIdpedidoNew = em.getReference(pedidosIdpedidoNew.getClass(), pedidosIdpedidoNew.getIdpedido());
                detalleservicio.setPedidosIdpedido(pedidosIdpedidoNew);
            }
            if (serviciosNew != null) {
                serviciosNew = em.getReference(serviciosNew.getClass(), serviciosNew.getIdservicio());
                detalleservicio.setServicios(serviciosNew);
            }
            detalleservicio = em.merge(detalleservicio);
            if (pedidosIdpedidoOld != null && !pedidosIdpedidoOld.equals(pedidosIdpedidoNew)) {
                pedidosIdpedidoOld.getDetalleservicioList().remove(detalleservicio);
                pedidosIdpedidoOld = em.merge(pedidosIdpedidoOld);
            }
            if (pedidosIdpedidoNew != null && !pedidosIdpedidoNew.equals(pedidosIdpedidoOld)) {
                pedidosIdpedidoNew.getDetalleservicioList().add(detalleservicio);
                pedidosIdpedidoNew = em.merge(pedidosIdpedidoNew);
            }
            if (serviciosOld != null && !serviciosOld.equals(serviciosNew)) {
                serviciosOld.setDetalleservicio(null);
                serviciosOld = em.merge(serviciosOld);
            }
            if (serviciosNew != null && !serviciosNew.equals(serviciosOld)) {
                serviciosNew.setDetalleservicio(detalleservicio);
                serviciosNew = em.merge(serviciosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleservicio.getServiciosIdservicio();
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
                detalleservicio.getServiciosIdservicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleservicio with id " + id + " no longer exists.", enfe);
            }
            Pedidos pedidosIdpedido = detalleservicio.getPedidosIdpedido();
            if (pedidosIdpedido != null) {
                pedidosIdpedido.getDetalleservicioList().remove(detalleservicio);
                pedidosIdpedido = em.merge(pedidosIdpedido);
            }
            Servicios servicios = detalleservicio.getServicios();
            if (servicios != null) {
                servicios.setDetalleservicio(null);
                servicios = em.merge(servicios);
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
    
    public List<Detalleservicio> buscarServicios(int id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM basedatos_sage.detalleservicio WHERE pedidos_idpedido = "+id,Detalleservicio.class);
            return q.getResultList();
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
    
}
