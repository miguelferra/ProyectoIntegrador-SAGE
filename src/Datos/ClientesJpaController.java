/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import Entidades.Clientes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class ClientesJpaController implements Serializable {

    public ClientesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientes clientes) {
        if (clientes.getPedidosList() == null) {
            clientes.setPedidosList(new ArrayList<Pedidos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedidos> attachedPedidosList = new ArrayList<Pedidos>();
            for (Pedidos pedidosListPedidosToAttach : clientes.getPedidosList()) {
                pedidosListPedidosToAttach = em.getReference(pedidosListPedidosToAttach.getClass(), pedidosListPedidosToAttach.getIdpedido());
                attachedPedidosList.add(pedidosListPedidosToAttach);
            }
            clientes.setPedidosList(attachedPedidosList);
            em.persist(clientes);
            for (Pedidos pedidosListPedidos : clientes.getPedidosList()) {
                Clientes oldClientesOfPedidosListPedidos = pedidosListPedidos.getClientes();
                pedidosListPedidos.setClientes(clientes);
                pedidosListPedidos = em.merge(pedidosListPedidos);
                if (oldClientesOfPedidosListPedidos != null) {
                    oldClientesOfPedidosListPedidos.getPedidosList().remove(pedidosListPedidos);
                    oldClientesOfPedidosListPedidos = em.merge(oldClientesOfPedidosListPedidos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientes clientes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentClientes = em.find(Clientes.class, clientes.getIdcliente());
            List<Pedidos> pedidosListOld = persistentClientes.getPedidosList();
            List<Pedidos> pedidosListNew = clientes.getPedidosList();
            List<String> illegalOrphanMessages = null;
            for (Pedidos pedidosListOldPedidos : pedidosListOld) {
                if (!pedidosListNew.contains(pedidosListOldPedidos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedidos " + pedidosListOldPedidos + " since its clientes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedidos> attachedPedidosListNew = new ArrayList<Pedidos>();
            for (Pedidos pedidosListNewPedidosToAttach : pedidosListNew) {
                pedidosListNewPedidosToAttach = em.getReference(pedidosListNewPedidosToAttach.getClass(), pedidosListNewPedidosToAttach.getIdpedido());
                attachedPedidosListNew.add(pedidosListNewPedidosToAttach);
            }
            pedidosListNew = attachedPedidosListNew;
            clientes.setPedidosList(pedidosListNew);
            clientes = em.merge(clientes);
            for (Pedidos pedidosListNewPedidos : pedidosListNew) {
                if (!pedidosListOld.contains(pedidosListNewPedidos)) {
                    Clientes oldClientesOfPedidosListNewPedidos = pedidosListNewPedidos.getClientes();
                    pedidosListNewPedidos.setClientes(clientes);
                    pedidosListNewPedidos = em.merge(pedidosListNewPedidos);
                    if (oldClientesOfPedidosListNewPedidos != null && !oldClientesOfPedidosListNewPedidos.equals(clientes)) {
                        oldClientesOfPedidosListNewPedidos.getPedidosList().remove(pedidosListNewPedidos);
                        oldClientesOfPedidosListNewPedidos = em.merge(oldClientesOfPedidosListNewPedidos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clientes.getIdcliente();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pedidos> pedidosListOrphanCheck = clientes.getPedidosList();
            for (Pedidos pedidosListOrphanCheckPedidos : pedidosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Clientes (" + clientes + ") cannot be destroyed since the Pedidos " + pedidosListOrphanCheckPedidos + " in its pedidosList field has a non-nullable clientes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(clientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
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

    public Clientes findClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Clientes buscarClienteNombre(String nombre, String apellido){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * FROM bd_sage.clientes WHERE nombre = "+ "'"+nombre+"'"+ "AND apellido = "+ "'"+apellido + "'",Clientes.class);
            return (Clientes) q.getSingleResult();
        } finally {
            em.close();
        }
    }
}
