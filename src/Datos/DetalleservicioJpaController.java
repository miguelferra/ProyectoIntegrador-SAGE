/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import Entidades.Detalleservicio;
import Entidades.DetalleservicioPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Paquetes;
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

    public void create(Detalleservicio detalleservicio) throws PreexistingEntityException, Exception {
        if (detalleservicio.getDetalleservicioPK() == null) {
            detalleservicio.setDetalleservicioPK(new DetalleservicioPK());
        }
        detalleservicio.getDetalleservicioPK().setServiciosIdservicio(detalleservicio.getServicios().getIdservicio());
        detalleservicio.getDetalleservicioPK().setPaquetesIdpaquete(detalleservicio.getPaquetes().getIdpaquete());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquetes paquetes = detalleservicio.getPaquetes();
            if (paquetes != null) {
                paquetes = em.getReference(paquetes.getClass(), paquetes.getIdpaquete());
                detalleservicio.setPaquetes(paquetes);
            }
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
            if (paquetes != null) {
                paquetes.getDetalleservicioList().add(detalleservicio);
                paquetes = em.merge(paquetes);
            }
            if (pedidosIdpedido != null) {
                pedidosIdpedido.getDetalleservicioList().add(detalleservicio);
                pedidosIdpedido = em.merge(pedidosIdpedido);
            }
            if (servicios != null) {
                servicios.getDetalleservicioList().add(detalleservicio);
                servicios = em.merge(servicios);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleservicio(detalleservicio.getDetalleservicioPK()) != null) {
                throw new PreexistingEntityException("Detalleservicio " + detalleservicio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleservicio detalleservicio) throws NonexistentEntityException, Exception {
        detalleservicio.getDetalleservicioPK().setServiciosIdservicio(detalleservicio.getServicios().getIdservicio());
        detalleservicio.getDetalleservicioPK().setPaquetesIdpaquete(detalleservicio.getPaquetes().getIdpaquete());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleservicio persistentDetalleservicio = em.find(Detalleservicio.class, detalleservicio.getDetalleservicioPK());
            Paquetes paquetesOld = persistentDetalleservicio.getPaquetes();
            Paquetes paquetesNew = detalleservicio.getPaquetes();
            Pedidos pedidosIdpedidoOld = persistentDetalleservicio.getPedidosIdpedido();
            Pedidos pedidosIdpedidoNew = detalleservicio.getPedidosIdpedido();
            Servicios serviciosOld = persistentDetalleservicio.getServicios();
            Servicios serviciosNew = detalleservicio.getServicios();
            if (paquetesNew != null) {
                paquetesNew = em.getReference(paquetesNew.getClass(), paquetesNew.getIdpaquete());
                detalleservicio.setPaquetes(paquetesNew);
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
            if (paquetesOld != null && !paquetesOld.equals(paquetesNew)) {
                paquetesOld.getDetalleservicioList().remove(detalleservicio);
                paquetesOld = em.merge(paquetesOld);
            }
            if (paquetesNew != null && !paquetesNew.equals(paquetesOld)) {
                paquetesNew.getDetalleservicioList().add(detalleservicio);
                paquetesNew = em.merge(paquetesNew);
            }
            if (pedidosIdpedidoOld != null && !pedidosIdpedidoOld.equals(pedidosIdpedidoNew)) {
                pedidosIdpedidoOld.getDetalleservicioList().remove(detalleservicio);
                pedidosIdpedidoOld = em.merge(pedidosIdpedidoOld);
            }
            if (pedidosIdpedidoNew != null && !pedidosIdpedidoNew.equals(pedidosIdpedidoOld)) {
                pedidosIdpedidoNew.getDetalleservicioList().add(detalleservicio);
                pedidosIdpedidoNew = em.merge(pedidosIdpedidoNew);
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
                DetalleservicioPK id = detalleservicio.getDetalleservicioPK();
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

    public void destroy(DetalleservicioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleservicio detalleservicio;
            try {
                detalleservicio = em.getReference(Detalleservicio.class, id);
                detalleservicio.getDetalleservicioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleservicio with id " + id + " no longer exists.", enfe);
            }
            Paquetes paquetes = detalleservicio.getPaquetes();
            if (paquetes != null) {
                paquetes.getDetalleservicioList().remove(detalleservicio);
                paquetes = em.merge(paquetes);
            }
            Pedidos pedidosIdpedido = detalleservicio.getPedidosIdpedido();
            if (pedidosIdpedido != null) {
                pedidosIdpedido.getDetalleservicioList().remove(detalleservicio);
                pedidosIdpedido = em.merge(pedidosIdpedido);
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

    public Detalleservicio findDetalleservicio(DetalleservicioPK id) {
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
            Query q = em.createNativeQuery("SELECT * FROM basedatos_sage.detalleservicio WHERE paquetes_idpaquete = "+id,Detalleservicio.class);
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
