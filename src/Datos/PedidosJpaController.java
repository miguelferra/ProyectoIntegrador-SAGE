/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.exceptions.IllegalOrphanException;
import Datos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Detalleactividades;
import Entidades.Clientes;
import Entidades.Paquetes;
import Entidades.Detalleservicio;
import java.util.ArrayList;
import java.util.List;
import Entidades.Detalleentregablespedido;
import Entidades.Pedidos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ferra
 */
public class PedidosJpaController implements Serializable {

    public PedidosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedidos pedidos) {
        if (pedidos.getDetalleservicioList() == null) {
            pedidos.setDetalleservicioList(new ArrayList<Detalleservicio>());
        }
        if (pedidos.getDetalleentregablespedidoList() == null) {
            pedidos.setDetalleentregablespedidoList(new ArrayList<Detalleentregablespedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleactividades detalleactividades = pedidos.getDetalleactividades();
            if (detalleactividades != null) {
                detalleactividades = em.getReference(detalleactividades.getClass(), detalleactividades.getDetalleactividadesPK());
                pedidos.setDetalleactividades(detalleactividades);
            }
            Clientes clientes = pedidos.getClientes();
            if (clientes != null) {
                clientes = em.getReference(clientes.getClass(), clientes.getIdcliente());
                pedidos.setClientes(clientes);
            }
            Paquetes paquetes = pedidos.getPaquetes();
            if (paquetes != null) {
                paquetes = em.getReference(paquetes.getClass(), paquetes.getIdpaquete());
                pedidos.setPaquetes(paquetes);
            }
            List<Detalleservicio> attachedDetalleservicioList = new ArrayList<Detalleservicio>();
            for (Detalleservicio detalleservicioListDetalleservicioToAttach : pedidos.getDetalleservicioList()) {
                detalleservicioListDetalleservicioToAttach = em.getReference(detalleservicioListDetalleservicioToAttach.getClass(), detalleservicioListDetalleservicioToAttach.getIdDetalleServicio());
                attachedDetalleservicioList.add(detalleservicioListDetalleservicioToAttach);
            }
            pedidos.setDetalleservicioList(attachedDetalleservicioList);
            List<Detalleentregablespedido> attachedDetalleentregablespedidoList = new ArrayList<Detalleentregablespedido>();
            for (Detalleentregablespedido detalleentregablespedidoListDetalleentregablespedidoToAttach : pedidos.getDetalleentregablespedidoList()) {
                detalleentregablespedidoListDetalleentregablespedidoToAttach = em.getReference(detalleentregablespedidoListDetalleentregablespedidoToAttach.getClass(), detalleentregablespedidoListDetalleentregablespedidoToAttach.getIdDetalleEntregable());
                attachedDetalleentregablespedidoList.add(detalleentregablespedidoListDetalleentregablespedidoToAttach);
            }
            pedidos.setDetalleentregablespedidoList(attachedDetalleentregablespedidoList);
            em.persist(pedidos);
            if (detalleactividades != null) {
                Pedidos oldPedidosOfDetalleactividades = detalleactividades.getPedidos();
                if (oldPedidosOfDetalleactividades != null) {
                    oldPedidosOfDetalleactividades.setDetalleactividades(null);
                    oldPedidosOfDetalleactividades = em.merge(oldPedidosOfDetalleactividades);
                }
                detalleactividades.setPedidos(pedidos);
                detalleactividades = em.merge(detalleactividades);
            }
            if (clientes != null) {
                clientes.getPedidosList().add(pedidos);
                clientes = em.merge(clientes);
            }
            if (paquetes != null) {
                paquetes.getPedidosList().add(pedidos);
                paquetes = em.merge(paquetes);
            }
            for (Detalleservicio detalleservicioListDetalleservicio : pedidos.getDetalleservicioList()) {
                Pedidos oldPedidosOfDetalleservicioListDetalleservicio = detalleservicioListDetalleservicio.getPedidos();
                detalleservicioListDetalleservicio.setPedidos(pedidos);
                detalleservicioListDetalleservicio = em.merge(detalleservicioListDetalleservicio);
                if (oldPedidosOfDetalleservicioListDetalleservicio != null) {
                    oldPedidosOfDetalleservicioListDetalleservicio.getDetalleservicioList().remove(detalleservicioListDetalleservicio);
                    oldPedidosOfDetalleservicioListDetalleservicio = em.merge(oldPedidosOfDetalleservicioListDetalleservicio);
                }
            }
            for (Detalleentregablespedido detalleentregablespedidoListDetalleentregablespedido : pedidos.getDetalleentregablespedidoList()) {
                Pedidos oldPedidosOfDetalleentregablespedidoListDetalleentregablespedido = detalleentregablespedidoListDetalleentregablespedido.getPedidos();
                detalleentregablespedidoListDetalleentregablespedido.setPedidos(pedidos);
                detalleentregablespedidoListDetalleentregablespedido = em.merge(detalleentregablespedidoListDetalleentregablespedido);
                if (oldPedidosOfDetalleentregablespedidoListDetalleentregablespedido != null) {
                    oldPedidosOfDetalleentregablespedidoListDetalleentregablespedido.getDetalleentregablespedidoList().remove(detalleentregablespedidoListDetalleentregablespedido);
                    oldPedidosOfDetalleentregablespedidoListDetalleentregablespedido = em.merge(oldPedidosOfDetalleentregablespedidoListDetalleentregablespedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedidos pedidos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidos persistentPedidos = em.find(Pedidos.class, pedidos.getIdpedido());
            Detalleactividades detalleactividadesOld = persistentPedidos.getDetalleactividades();
            Detalleactividades detalleactividadesNew = pedidos.getDetalleactividades();
            Clientes clientesOld = persistentPedidos.getClientes();
            Clientes clientesNew = pedidos.getClientes();
            Paquetes paquetesOld = persistentPedidos.getPaquetes();
            Paquetes paquetesNew = pedidos.getPaquetes();
            List<Detalleservicio> detalleservicioListOld = persistentPedidos.getDetalleservicioList();
            List<Detalleservicio> detalleservicioListNew = pedidos.getDetalleservicioList();
            List<Detalleentregablespedido> detalleentregablespedidoListOld = persistentPedidos.getDetalleentregablespedidoList();
            List<Detalleentregablespedido> detalleentregablespedidoListNew = pedidos.getDetalleentregablespedidoList();
            List<String> illegalOrphanMessages = null;
            if (detalleactividadesOld != null && !detalleactividadesOld.equals(detalleactividadesNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Detalleactividades " + detalleactividadesOld + " since its pedidos field is not nullable.");
            }
            for (Detalleservicio detalleservicioListOldDetalleservicio : detalleservicioListOld) {
                if (!detalleservicioListNew.contains(detalleservicioListOldDetalleservicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleservicio " + detalleservicioListOldDetalleservicio + " since its pedidos field is not nullable.");
                }
            }
            for (Detalleentregablespedido detalleentregablespedidoListOldDetalleentregablespedido : detalleentregablespedidoListOld) {
                if (!detalleentregablespedidoListNew.contains(detalleentregablespedidoListOldDetalleentregablespedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleentregablespedido " + detalleentregablespedidoListOldDetalleentregablespedido + " since its pedidos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (detalleactividadesNew != null) {
                detalleactividadesNew = em.getReference(detalleactividadesNew.getClass(), detalleactividadesNew.getDetalleactividadesPK());
                pedidos.setDetalleactividades(detalleactividadesNew);
            }
            if (clientesNew != null) {
                clientesNew = em.getReference(clientesNew.getClass(), clientesNew.getIdcliente());
                pedidos.setClientes(clientesNew);
            }
            if (paquetesNew != null) {
                paquetesNew = em.getReference(paquetesNew.getClass(), paquetesNew.getIdpaquete());
                pedidos.setPaquetes(paquetesNew);
            }
            List<Detalleservicio> attachedDetalleservicioListNew = new ArrayList<Detalleservicio>();
            for (Detalleservicio detalleservicioListNewDetalleservicioToAttach : detalleservicioListNew) {
                detalleservicioListNewDetalleservicioToAttach = em.getReference(detalleservicioListNewDetalleservicioToAttach.getClass(), detalleservicioListNewDetalleservicioToAttach.getIdDetalleServicio());
                attachedDetalleservicioListNew.add(detalleservicioListNewDetalleservicioToAttach);
            }
            detalleservicioListNew = attachedDetalleservicioListNew;
            pedidos.setDetalleservicioList(detalleservicioListNew);
            List<Detalleentregablespedido> attachedDetalleentregablespedidoListNew = new ArrayList<Detalleentregablespedido>();
            for (Detalleentregablespedido detalleentregablespedidoListNewDetalleentregablespedidoToAttach : detalleentregablespedidoListNew) {
                detalleentregablespedidoListNewDetalleentregablespedidoToAttach = em.getReference(detalleentregablespedidoListNewDetalleentregablespedidoToAttach.getClass(), detalleentregablespedidoListNewDetalleentregablespedidoToAttach.getIdDetalleEntregable());
                attachedDetalleentregablespedidoListNew.add(detalleentregablespedidoListNewDetalleentregablespedidoToAttach);
            }
            detalleentregablespedidoListNew = attachedDetalleentregablespedidoListNew;
            pedidos.setDetalleentregablespedidoList(detalleentregablespedidoListNew);
            pedidos = em.merge(pedidos);
            if (detalleactividadesNew != null && !detalleactividadesNew.equals(detalleactividadesOld)) {
                Pedidos oldPedidosOfDetalleactividades = detalleactividadesNew.getPedidos();
                if (oldPedidosOfDetalleactividades != null) {
                    oldPedidosOfDetalleactividades.setDetalleactividades(null);
                    oldPedidosOfDetalleactividades = em.merge(oldPedidosOfDetalleactividades);
                }
                detalleactividadesNew.setPedidos(pedidos);
                detalleactividadesNew = em.merge(detalleactividadesNew);
            }
            if (clientesOld != null && !clientesOld.equals(clientesNew)) {
                clientesOld.getPedidosList().remove(pedidos);
                clientesOld = em.merge(clientesOld);
            }
            if (clientesNew != null && !clientesNew.equals(clientesOld)) {
                clientesNew.getPedidosList().add(pedidos);
                clientesNew = em.merge(clientesNew);
            }
            if (paquetesOld != null && !paquetesOld.equals(paquetesNew)) {
                paquetesOld.getPedidosList().remove(pedidos);
                paquetesOld = em.merge(paquetesOld);
            }
            if (paquetesNew != null && !paquetesNew.equals(paquetesOld)) {
                paquetesNew.getPedidosList().add(pedidos);
                paquetesNew = em.merge(paquetesNew);
            }
            for (Detalleservicio detalleservicioListNewDetalleservicio : detalleservicioListNew) {
                if (!detalleservicioListOld.contains(detalleservicioListNewDetalleservicio)) {
                    Pedidos oldPedidosOfDetalleservicioListNewDetalleservicio = detalleservicioListNewDetalleservicio.getPedidos();
                    detalleservicioListNewDetalleservicio.setPedidos(pedidos);
                    detalleservicioListNewDetalleservicio = em.merge(detalleservicioListNewDetalleservicio);
                    if (oldPedidosOfDetalleservicioListNewDetalleservicio != null && !oldPedidosOfDetalleservicioListNewDetalleservicio.equals(pedidos)) {
                        oldPedidosOfDetalleservicioListNewDetalleservicio.getDetalleservicioList().remove(detalleservicioListNewDetalleservicio);
                        oldPedidosOfDetalleservicioListNewDetalleservicio = em.merge(oldPedidosOfDetalleservicioListNewDetalleservicio);
                    }
                }
            }
            for (Detalleentregablespedido detalleentregablespedidoListNewDetalleentregablespedido : detalleentregablespedidoListNew) {
                if (!detalleentregablespedidoListOld.contains(detalleentregablespedidoListNewDetalleentregablespedido)) {
                    Pedidos oldPedidosOfDetalleentregablespedidoListNewDetalleentregablespedido = detalleentregablespedidoListNewDetalleentregablespedido.getPedidos();
                    detalleentregablespedidoListNewDetalleentregablespedido.setPedidos(pedidos);
                    detalleentregablespedidoListNewDetalleentregablespedido = em.merge(detalleentregablespedidoListNewDetalleentregablespedido);
                    if (oldPedidosOfDetalleentregablespedidoListNewDetalleentregablespedido != null && !oldPedidosOfDetalleentregablespedidoListNewDetalleentregablespedido.equals(pedidos)) {
                        oldPedidosOfDetalleentregablespedidoListNewDetalleentregablespedido.getDetalleentregablespedidoList().remove(detalleentregablespedidoListNewDetalleentregablespedido);
                        oldPedidosOfDetalleentregablespedidoListNewDetalleentregablespedido = em.merge(oldPedidosOfDetalleentregablespedidoListNewDetalleentregablespedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidos.getIdpedido();
                if (findPedidos(id) == null) {
                    throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.");
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
            Pedidos pedidos;
            try {
                pedidos = em.getReference(Pedidos.class, id);
                pedidos.getIdpedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Detalleactividades detalleactividadesOrphanCheck = pedidos.getDetalleactividades();
            if (detalleactividadesOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedidos (" + pedidos + ") cannot be destroyed since the Detalleactividades " + detalleactividadesOrphanCheck + " in its detalleactividades field has a non-nullable pedidos field.");
            }
            List<Detalleservicio> detalleservicioListOrphanCheck = pedidos.getDetalleservicioList();
            for (Detalleservicio detalleservicioListOrphanCheckDetalleservicio : detalleservicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedidos (" + pedidos + ") cannot be destroyed since the Detalleservicio " + detalleservicioListOrphanCheckDetalleservicio + " in its detalleservicioList field has a non-nullable pedidos field.");
            }
            List<Detalleentregablespedido> detalleentregablespedidoListOrphanCheck = pedidos.getDetalleentregablespedidoList();
            for (Detalleentregablespedido detalleentregablespedidoListOrphanCheckDetalleentregablespedido : detalleentregablespedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedidos (" + pedidos + ") cannot be destroyed since the Detalleentregablespedido " + detalleentregablespedidoListOrphanCheckDetalleentregablespedido + " in its detalleentregablespedidoList field has a non-nullable pedidos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clientes clientes = pedidos.getClientes();
            if (clientes != null) {
                clientes.getPedidosList().remove(pedidos);
                clientes = em.merge(clientes);
            }
            Paquetes paquetes = pedidos.getPaquetes();
            if (paquetes != null) {
                paquetes.getPedidosList().remove(pedidos);
                paquetes = em.merge(paquetes);
            }
            em.remove(pedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedidos> findPedidosEntities() {
        return findPedidosEntities(true, -1, -1);
    }

    public List<Pedidos> findPedidosEntities(int maxResults, int firstResult) {
        return findPedidosEntities(false, maxResults, firstResult);
    }

    private List<Pedidos> findPedidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedidos.class));
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

    public Pedidos findPedidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedidos> rt = cq.from(Pedidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Pedidos getUltimoPedido(){
        EntityManager em = getEntityManager();

        try {
            Query q = em.createNativeQuery("SELECT * FROM bd_sage.pedidos ORDER BY idPedido DESC LIMIT 1;",Pedidos.class);
            return (Pedidos) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<Pedidos> findPedidosCliente(Integer id){
        EntityManager em = getEntityManager();

        try {
            Query q = em.createNativeQuery("SELECT * FROM bd_sage.pedidos WHERE clientes_idcliente = "+id,Pedidos.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
}
