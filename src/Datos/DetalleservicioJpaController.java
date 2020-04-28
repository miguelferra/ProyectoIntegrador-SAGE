/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Entidades.Detalleservicio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Pedidos;
import Entidades.Servicios;
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

    public void create(Detalleservicio detalleservicio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidos pedidos = detalleservicio.getPedidos();
            if (pedidos != null) {
                pedidos = em.getReference(pedidos.getClass(), pedidos.getIdpedido());
                detalleservicio.setPedidos(pedidos);
            }
            Servicios servicios = detalleservicio.getServicios();
            if (servicios != null) {
                servicios = em.getReference(servicios.getClass(), servicios.getIdservicio());
                detalleservicio.setServicios(servicios);
            }
            em.persist(detalleservicio);
            if (pedidos != null) {
                pedidos.getDetalleservicioList().add(detalleservicio);
                pedidos = em.merge(pedidos);
            }
            if (servicios != null) {
                servicios.getDetalleservicioList().add(detalleservicio);
                servicios = em.merge(servicios);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleservicio detalleservicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleservicio persistentDetalleservicio = em.find(Detalleservicio.class, detalleservicio.getIdDetalleServicio());
            Pedidos pedidosOld = persistentDetalleservicio.getPedidos();
            Pedidos pedidosNew = detalleservicio.getPedidos();
            Servicios serviciosOld = persistentDetalleservicio.getServicios();
            Servicios serviciosNew = detalleservicio.getServicios();
            if (pedidosNew != null) {
                pedidosNew = em.getReference(pedidosNew.getClass(), pedidosNew.getIdpedido());
                detalleservicio.setPedidos(pedidosNew);
            }
            if (serviciosNew != null) {
                serviciosNew = em.getReference(serviciosNew.getClass(), serviciosNew.getIdservicio());
                detalleservicio.setServicios(serviciosNew);
            }
            detalleservicio = em.merge(detalleservicio);
            if (pedidosOld != null && !pedidosOld.equals(pedidosNew)) {
                pedidosOld.getDetalleservicioList().remove(detalleservicio);
                pedidosOld = em.merge(pedidosOld);
            }
            if (pedidosNew != null && !pedidosNew.equals(pedidosOld)) {
                pedidosNew.getDetalleservicioList().add(detalleservicio);
                pedidosNew = em.merge(pedidosNew);
            }
            if (serviciosOld != null && !serviciosOld.equals(serviciosNew)) {
                serviciosOld.getDetalleservicioList().remove(detalleservicio);
                serviciosOld = em.merge(serviciosOld);
            }
            if (serviciosNew != null && !serviciosNew.equals(serviciosOld)) {
                serviciosNew.getDetalleservicioList().add(detalleservicio);
                serviciosNew = em.merge(serviciosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleservicio.getIdDetalleServicio();
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
                detalleservicio.getIdDetalleServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleservicio with id " + id + " no longer exists.", enfe);
            }
            Pedidos pedidos = detalleservicio.getPedidos();
            if (pedidos != null) {
                pedidos.getDetalleservicioList().remove(detalleservicio);
                pedidos = em.merge(pedidos);
            }
            Servicios servicios = detalleservicio.getServicios();
            if (servicios != null) {
                servicios.getDetalleservicioList().remove(detalleservicio);
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
    
    public void eliminarDetalleServicio(int id){
        EntityManager em = getEntityManager();
        try {
            int count = em.createQuery("DELETE FROM bd_sage.detalleservicio WHERE pedidos_idpedido= "+id).executeUpdate();
        } finally {
            em.close();
        }
    }
}
