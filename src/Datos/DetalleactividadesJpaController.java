/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
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
import java.util.ArrayList;
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

    public void create(Detalleactividades detalleactividades) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (detalleactividades.getDetalleactividadesPK() == null) {
            detalleactividades.setDetalleactividadesPK(new DetalleactividadesPK());
        }
        detalleactividades.getDetalleactividadesPK().setPedidosIdpedido(detalleactividades.getPedidos().getIdpedido());
        detalleactividades.getDetalleactividadesPK().setActividadesIdactividad(detalleactividades.getActividades().getIdactividad());
        List<String> illegalOrphanMessages = null;
        Actividades actividadesOrphanCheck = detalleactividades.getActividades();
        if (actividadesOrphanCheck != null) {
            Detalleactividades oldDetalleactividadesOfActividades = actividadesOrphanCheck.getDetalleactividades();
            if (oldDetalleactividadesOfActividades != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Actividades " + actividadesOrphanCheck + " already has an item of type Detalleactividades whose actividades column cannot be null. Please make another selection for the actividades field.");
            }
        }
        Pedidos pedidosOrphanCheck = detalleactividades.getPedidos();
        if (pedidosOrphanCheck != null) {
            Detalleactividades oldDetalleactividadesOfPedidos = pedidosOrphanCheck.getDetalleactividades();
            if (oldDetalleactividadesOfPedidos != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Pedidos " + pedidosOrphanCheck + " already has an item of type Detalleactividades whose pedidos column cannot be null. Please make another selection for the pedidos field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividades actividades = detalleactividades.getActividades();
            if (actividades != null) {
                actividades = em.getReference(actividades.getClass(), actividades.getIdactividad());
                detalleactividades.setActividades(actividades);
            }
            Empleados empleados = detalleactividades.getEmpleados();
            if (empleados != null) {
                empleados = em.getReference(empleados.getClass(), empleados.getRfc());
                detalleactividades.setEmpleados(empleados);
            }
            Pedidos pedidos = detalleactividades.getPedidos();
            if (pedidos != null) {
                pedidos = em.getReference(pedidos.getClass(), pedidos.getIdpedido());
                detalleactividades.setPedidos(pedidos);
            }
            em.persist(detalleactividades);
            if (actividades != null) {
                actividades.setDetalleactividades(detalleactividades);
                actividades = em.merge(actividades);
            }
            if (empleados != null) {
                empleados.getDetalleactividadesList().add(detalleactividades);
                empleados = em.merge(empleados);
            }
            if (pedidos != null) {
                pedidos.setDetalleactividades(detalleactividades);
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

    public void edit(Detalleactividades detalleactividades) throws IllegalOrphanException, NonexistentEntityException, Exception {
        detalleactividades.getDetalleactividadesPK().setPedidosIdpedido(detalleactividades.getPedidos().getIdpedido());
        detalleactividades.getDetalleactividadesPK().setActividadesIdactividad(detalleactividades.getActividades().getIdactividad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleactividades persistentDetalleactividades = em.find(Detalleactividades.class, detalleactividades.getDetalleactividadesPK());
            Actividades actividadesOld = persistentDetalleactividades.getActividades();
            Actividades actividadesNew = detalleactividades.getActividades();
            Empleados empleadosOld = persistentDetalleactividades.getEmpleados();
            Empleados empleadosNew = detalleactividades.getEmpleados();
            Pedidos pedidosOld = persistentDetalleactividades.getPedidos();
            Pedidos pedidosNew = detalleactividades.getPedidos();
            List<String> illegalOrphanMessages = null;
            if (actividadesNew != null && !actividadesNew.equals(actividadesOld)) {
                Detalleactividades oldDetalleactividadesOfActividades = actividadesNew.getDetalleactividades();
                if (oldDetalleactividadesOfActividades != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Actividades " + actividadesNew + " already has an item of type Detalleactividades whose actividades column cannot be null. Please make another selection for the actividades field.");
                }
            }
            if (pedidosNew != null && !pedidosNew.equals(pedidosOld)) {
                Detalleactividades oldDetalleactividadesOfPedidos = pedidosNew.getDetalleactividades();
                if (oldDetalleactividadesOfPedidos != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Pedidos " + pedidosNew + " already has an item of type Detalleactividades whose pedidos column cannot be null. Please make another selection for the pedidos field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (actividadesNew != null) {
                actividadesNew = em.getReference(actividadesNew.getClass(), actividadesNew.getIdactividad());
                detalleactividades.setActividades(actividadesNew);
            }
            if (empleadosNew != null) {
                empleadosNew = em.getReference(empleadosNew.getClass(), empleadosNew.getRfc());
                detalleactividades.setEmpleados(empleadosNew);
            }
            if (pedidosNew != null) {
                pedidosNew = em.getReference(pedidosNew.getClass(), pedidosNew.getIdpedido());
                detalleactividades.setPedidos(pedidosNew);
            }
            detalleactividades = em.merge(detalleactividades);
            if (actividadesOld != null && !actividadesOld.equals(actividadesNew)) {
                actividadesOld.setDetalleactividades(null);
                actividadesOld = em.merge(actividadesOld);
            }
            if (actividadesNew != null && !actividadesNew.equals(actividadesOld)) {
                actividadesNew.setDetalleactividades(detalleactividades);
                actividadesNew = em.merge(actividadesNew);
            }
            if (empleadosOld != null && !empleadosOld.equals(empleadosNew)) {
                empleadosOld.getDetalleactividadesList().remove(detalleactividades);
                empleadosOld = em.merge(empleadosOld);
            }
            if (empleadosNew != null && !empleadosNew.equals(empleadosOld)) {
                empleadosNew.getDetalleactividadesList().add(detalleactividades);
                empleadosNew = em.merge(empleadosNew);
            }
            if (pedidosOld != null && !pedidosOld.equals(pedidosNew)) {
                pedidosOld.setDetalleactividades(null);
                pedidosOld = em.merge(pedidosOld);
            }
            if (pedidosNew != null && !pedidosNew.equals(pedidosOld)) {
                pedidosNew.setDetalleactividades(detalleactividades);
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
                actividades.setDetalleactividades(null);
                actividades = em.merge(actividades);
            }
            Empleados empleados = detalleactividades.getEmpleados();
            if (empleados != null) {
                empleados.getDetalleactividadesList().remove(detalleactividades);
                empleados = em.merge(empleados);
            }
            Pedidos pedidos = detalleactividades.getPedidos();
            if (pedidos != null) {
                pedidos.setDetalleactividades(null);
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
