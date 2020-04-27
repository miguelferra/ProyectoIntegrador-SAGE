/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.NonexistentEntityException;
import Datos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Actividades;
import Entidades.Detalleactividades;
import Entidades.DetalleactividadesPK;
import Entidades.Empleados;
import Entidades.Pedidos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ferra
 */
public class DetalleactividadesJpaController implements Serializable {

    public DetalleactividadesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleactividades detalleactividades) throws PreexistingEntityException, Exception {
        if (detalleactividades.getDetalleactividadesPK() == null) {
            detalleactividades.setDetalleactividadesPK(new DetalleactividadesPK());
        }
        detalleactividades.getDetalleactividadesPK().setPedidosIdpedido(detalleactividades.getPedidos().getIdpedido());
        detalleactividades.getDetalleactividadesPK().setActividadesIdactividad(detalleactividades.getActividades().getIdactividad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividades actividades = detalleactividades.getActividades();
            if (actividades != null) {
                actividades = em.getReference(actividades.getClass(), actividades.getIdactividad());
                detalleactividades.setActividades(actividades);
            }
            Empleados empleadosRFC = detalleactividades.getEmpleadosRFC();
            if (empleadosRFC != null) {
                empleadosRFC = em.getReference(empleadosRFC.getClass(), empleadosRFC.getRfc());
                detalleactividades.setEmpleadosRFC(empleadosRFC);
            }
            Pedidos pedidos = detalleactividades.getPedidos();
            if (pedidos != null) {
                pedidos = em.getReference(pedidos.getClass(), pedidos.getIdpedido());
                detalleactividades.setPedidos(pedidos);
            }
            em.persist(detalleactividades);
            if (actividades != null) {
                actividades.getDetalleactividadesList().add(detalleactividades);
                actividades = em.merge(actividades);
            }
            if (empleadosRFC != null) {
                empleadosRFC.getDetalleactividadesList().add(detalleactividades);
                empleadosRFC = em.merge(empleadosRFC);
            }
            if (pedidos != null) {
                pedidos.getDetalleactividadesList().add(detalleactividades);
                pedidos = em.merge(pedidos);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleactividades(detalleactividades.getDetalleactividadesPK()) != null) {
                throw new PreexistingEntityException("Detalleactividades " + detalleactividades + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleactividades detalleactividades) throws NonexistentEntityException, Exception {
        detalleactividades.getDetalleactividadesPK().setPedidosIdpedido(detalleactividades.getPedidos().getIdpedido());
        detalleactividades.getDetalleactividadesPK().setActividadesIdactividad(detalleactividades.getActividades().getIdactividad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleactividades persistentDetalleactividades = em.find(Detalleactividades.class, detalleactividades.getDetalleactividadesPK());
            Actividades actividadesOld = persistentDetalleactividades.getActividades();
            Actividades actividadesNew = detalleactividades.getActividades();
            Empleados empleadosRFCOld = persistentDetalleactividades.getEmpleadosRFC();
            Empleados empleadosRFCNew = detalleactividades.getEmpleadosRFC();
            Pedidos pedidosOld = persistentDetalleactividades.getPedidos();
            Pedidos pedidosNew = detalleactividades.getPedidos();
            if (actividadesNew != null) {
                actividadesNew = em.getReference(actividadesNew.getClass(), actividadesNew.getIdactividad());
                detalleactividades.setActividades(actividadesNew);
            }
            if (empleadosRFCNew != null) {
                empleadosRFCNew = em.getReference(empleadosRFCNew.getClass(), empleadosRFCNew.getRfc());
                detalleactividades.setEmpleadosRFC(empleadosRFCNew);
            }
            if (pedidosNew != null) {
                pedidosNew = em.getReference(pedidosNew.getClass(), pedidosNew.getIdpedido());
                detalleactividades.setPedidos(pedidosNew);
            }
            detalleactividades = em.merge(detalleactividades);
            if (actividadesOld != null && !actividadesOld.equals(actividadesNew)) {
                actividadesOld.getDetalleactividadesList().remove(detalleactividades);
                actividadesOld = em.merge(actividadesOld);
            }
            if (actividadesNew != null && !actividadesNew.equals(actividadesOld)) {
                actividadesNew.getDetalleactividadesList().add(detalleactividades);
                actividadesNew = em.merge(actividadesNew);
            }
            if (empleadosRFCOld != null && !empleadosRFCOld.equals(empleadosRFCNew)) {
                empleadosRFCOld.getDetalleactividadesList().remove(detalleactividades);
                empleadosRFCOld = em.merge(empleadosRFCOld);
            }
            if (empleadosRFCNew != null && !empleadosRFCNew.equals(empleadosRFCOld)) {
                empleadosRFCNew.getDetalleactividadesList().add(detalleactividades);
                empleadosRFCNew = em.merge(empleadosRFCNew);
            }
            if (pedidosOld != null && !pedidosOld.equals(pedidosNew)) {
                pedidosOld.getDetalleactividadesList().remove(detalleactividades);
                pedidosOld = em.merge(pedidosOld);
            }
            if (pedidosNew != null && !pedidosNew.equals(pedidosOld)) {
                pedidosNew.getDetalleactividadesList().add(detalleactividades);
                pedidosNew = em.merge(pedidosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleactividadesPK id = detalleactividades.getDetalleactividadesPK();
                if (findDetalleactividades(id) == null) {
                    throw new NonexistentEntityException("The detalleactividades with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleactividadesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleactividades detalleactividades;
            try {
                detalleactividades = em.getReference(Detalleactividades.class, id);
                detalleactividades.getDetalleactividadesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleactividades with id " + id + " no longer exists.", enfe);
            }
            Actividades actividades = detalleactividades.getActividades();
            if (actividades != null) {
                actividades.getDetalleactividadesList().remove(detalleactividades);
                actividades = em.merge(actividades);
            }
            Empleados empleadosRFC = detalleactividades.getEmpleadosRFC();
            if (empleadosRFC != null) {
                empleadosRFC.getDetalleactividadesList().remove(detalleactividades);
                empleadosRFC = em.merge(empleadosRFC);
            }
            Pedidos pedidos = detalleactividades.getPedidos();
            if (pedidos != null) {
                pedidos.getDetalleactividadesList().remove(detalleactividades);
                pedidos = em.merge(pedidos);
            }
            em.remove(detalleactividades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleactividades> findDetalleactividadesEntities() {
        return findDetalleactividadesEntities(true, -1, -1);
    }

    public List<Detalleactividades> findDetalleactividadesEntities(int maxResults, int firstResult) {
        return findDetalleactividadesEntities(false, maxResults, firstResult);
    }

    private List<Detalleactividades> findDetalleactividadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleactividades.class));
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

    public Detalleactividades findDetalleactividades(DetalleactividadesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleactividades.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleactividadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleactividades> rt = cq.from(Detalleactividades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
