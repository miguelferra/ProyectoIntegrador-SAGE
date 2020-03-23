/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import Entidades.Detalleentregables;
import Entidades.DetalleentregablesPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Entregables;
import Entidades.Paquetes;
import Entidades.Pedidos;
import com.sun.xml.internal.ws.api.server.Module;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author ferra
 */
public class DetalleentregablesJpaController implements Serializable {

    public DetalleentregablesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleentregables detalleentregables) throws PreexistingEntityException, Exception {
        if (detalleentregables.getDetalleentregablesPK() == null) {
            detalleentregables.setDetalleentregablesPK(new DetalleentregablesPK());
        }
        detalleentregables.getDetalleentregablesPK().setPaquetesIdpaquete(detalleentregables.getPaquetes().getIdpaquete());
        detalleentregables.getDetalleentregablesPK().setEntregablesIdentregable(detalleentregables.getEntregables().getIdentregable());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Entregables entregables = detalleentregables.getEntregables();
            if (entregables != null) {
                entregables = em.getReference(entregables.getClass(), entregables.getIdentregable());
                detalleentregables.setEntregables(entregables);
            }
            Paquetes paquetes = detalleentregables.getPaquetes();
            if (paquetes != null) {
                paquetes = em.getReference(paquetes.getClass(), paquetes.getIdpaquete());
                detalleentregables.setPaquetes(paquetes);
            }
            Pedidos pedidosIdpedido = detalleentregables.getPedidosIdpedido();
            if (pedidosIdpedido != null) {
                pedidosIdpedido = em.getReference(pedidosIdpedido.getClass(), pedidosIdpedido.getIdpedido());
                detalleentregables.setPedidosIdpedido(pedidosIdpedido);
            }
            em.persist(detalleentregables);
            if (entregables != null) {
                entregables.getDetalleentregablesList().add(detalleentregables);
                entregables = em.merge(entregables);
            }
            if (paquetes != null) {
                paquetes.getDetalleentregablesList().add(detalleentregables);
                paquetes = em.merge(paquetes);
            }
            if (pedidosIdpedido != null) {
                pedidosIdpedido.getDetalleentregablesList().add(detalleentregables);
                pedidosIdpedido = em.merge(pedidosIdpedido);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleentregables(detalleentregables.getDetalleentregablesPK()) != null) {
                throw new PreexistingEntityException("Detalleentregables " + detalleentregables + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleentregables detalleentregables) throws NonexistentEntityException, Exception {
        detalleentregables.getDetalleentregablesPK().setPaquetesIdpaquete(detalleentregables.getPaquetes().getIdpaquete());
        detalleentregables.getDetalleentregablesPK().setEntregablesIdentregable(detalleentregables.getEntregables().getIdentregable());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleentregables persistentDetalleentregables = em.find(Detalleentregables.class, detalleentregables.getDetalleentregablesPK());
            Entregables entregablesOld = persistentDetalleentregables.getEntregables();
            Entregables entregablesNew = detalleentregables.getEntregables();
            Paquetes paquetesOld = persistentDetalleentregables.getPaquetes();
            Paquetes paquetesNew = detalleentregables.getPaquetes();
            Pedidos pedidosIdpedidoOld = persistentDetalleentregables.getPedidosIdpedido();
            Pedidos pedidosIdpedidoNew = detalleentregables.getPedidosIdpedido();
            if (entregablesNew != null) {
                entregablesNew = em.getReference(entregablesNew.getClass(), entregablesNew.getIdentregable());
                detalleentregables.setEntregables(entregablesNew);
            }
            if (paquetesNew != null) {
                paquetesNew = em.getReference(paquetesNew.getClass(), paquetesNew.getIdpaquete());
                detalleentregables.setPaquetes(paquetesNew);
            }
            if (pedidosIdpedidoNew != null) {
                pedidosIdpedidoNew = em.getReference(pedidosIdpedidoNew.getClass(), pedidosIdpedidoNew.getIdpedido());
                detalleentregables.setPedidosIdpedido(pedidosIdpedidoNew);
            }
            detalleentregables = em.merge(detalleentregables);
            if (entregablesOld != null && !entregablesOld.equals(entregablesNew)) {
                entregablesOld.getDetalleentregablesList().remove(detalleentregables);
                entregablesOld = em.merge(entregablesOld);
            }
            if (entregablesNew != null && !entregablesNew.equals(entregablesOld)) {
                entregablesNew.getDetalleentregablesList().add(detalleentregables);
                entregablesNew = em.merge(entregablesNew);
            }
            if (paquetesOld != null && !paquetesOld.equals(paquetesNew)) {
                paquetesOld.getDetalleentregablesList().remove(detalleentregables);
                paquetesOld = em.merge(paquetesOld);
            }
            if (paquetesNew != null && !paquetesNew.equals(paquetesOld)) {
                paquetesNew.getDetalleentregablesList().add(detalleentregables);
                paquetesNew = em.merge(paquetesNew);
            }
            if (pedidosIdpedidoOld != null && !pedidosIdpedidoOld.equals(pedidosIdpedidoNew)) {
                pedidosIdpedidoOld.getDetalleentregablesList().remove(detalleentregables);
                pedidosIdpedidoOld = em.merge(pedidosIdpedidoOld);
            }
            if (pedidosIdpedidoNew != null && !pedidosIdpedidoNew.equals(pedidosIdpedidoOld)) {
                pedidosIdpedidoNew.getDetalleentregablesList().add(detalleentregables);
                pedidosIdpedidoNew = em.merge(pedidosIdpedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleentregablesPK id = detalleentregables.getDetalleentregablesPK();
                if (findDetalleentregables(id) == null) {
                    throw new NonexistentEntityException("The detalleentregables with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleentregablesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleentregables detalleentregables;
            try {
                detalleentregables = em.getReference(Detalleentregables.class, id);
                detalleentregables.getDetalleentregablesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleentregables with id " + id + " no longer exists.", enfe);
            }
            Entregables entregables = detalleentregables.getEntregables();
            if (entregables != null) {
                entregables.getDetalleentregablesList().remove(detalleentregables);
                entregables = em.merge(entregables);
            }
            Paquetes paquetes = detalleentregables.getPaquetes();
            if (paquetes != null) {
                paquetes.getDetalleentregablesList().remove(detalleentregables);
                paquetes = em.merge(paquetes);
            }
            Pedidos pedidosIdpedido = detalleentregables.getPedidosIdpedido();
            if (pedidosIdpedido != null) {
                pedidosIdpedido.getDetalleentregablesList().remove(detalleentregables);
                pedidosIdpedido = em.merge(pedidosIdpedido);
            }
            em.remove(detalleentregables);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleentregables> findDetalleentregablesEntities() {
        return findDetalleentregablesEntities(true, -1, -1);
    }

    public List<Detalleentregables> findDetalleentregablesEntities(int maxResults, int firstResult) {
        return findDetalleentregablesEntities(false, maxResults, firstResult);
    }

    private List<Detalleentregables> findDetalleentregablesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleentregables.class));
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

    public Detalleentregables findDetalleentregables(DetalleentregablesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleentregables.class, id);            
        } finally {
            em.close();
        }
    }
    
    public List<Detalleentregables> buscarEntregables(int id){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM basedatos_sage.detalleentregables WHERE paquetes_idpaquete = "+id,Detalleentregables.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getDetalleentregablesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleentregables> rt = cq.from(Detalleentregables.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
