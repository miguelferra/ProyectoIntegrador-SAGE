/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Clientes;
import Entidades.Paquetes;
import Entidades.Detalleentregables;
import java.util.ArrayList;
import java.util.List;
import Entidades.Detalleactividades;
import Entidades.Detalleservicio;
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
        if (pedidos.getDetalleentregablesList() == null) {
            pedidos.setDetalleentregablesList(new ArrayList<Detalleentregables>());
        }
        if (pedidos.getDetalleactividadesList() == null) {
            pedidos.setDetalleactividadesList(new ArrayList<Detalleactividades>());
        }
        if (pedidos.getDetalleservicioList() == null) {
            pedidos.setDetalleservicioList(new ArrayList<Detalleservicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientesIdcliente = pedidos.getClientesIdcliente();
            if (clientesIdcliente != null) {
                clientesIdcliente = em.getReference(clientesIdcliente.getClass(), clientesIdcliente.getIdcliente());
                pedidos.setClientesIdcliente(clientesIdcliente);
            }
            Paquetes paquetesIdpaquete = pedidos.getPaquetesIdpaquete();
            if (paquetesIdpaquete != null) {
                paquetesIdpaquete = em.getReference(paquetesIdpaquete.getClass(), paquetesIdpaquete.getIdpaquete());
                pedidos.setPaquetesIdpaquete(paquetesIdpaquete);
            }
            List<Detalleentregables> attachedDetalleentregablesList = new ArrayList<Detalleentregables>();
            for (Detalleentregables detalleentregablesListDetalleentregablesToAttach : pedidos.getDetalleentregablesList()) {
                detalleentregablesListDetalleentregablesToAttach = em.getReference(detalleentregablesListDetalleentregablesToAttach.getClass(), detalleentregablesListDetalleentregablesToAttach.getDetalleentregablesPK());
                attachedDetalleentregablesList.add(detalleentregablesListDetalleentregablesToAttach);
            }
            pedidos.setDetalleentregablesList(attachedDetalleentregablesList);
            List<Detalleactividades> attachedDetalleactividadesList = new ArrayList<Detalleactividades>();
            for (Detalleactividades detalleactividadesListDetalleactividadesToAttach : pedidos.getDetalleactividadesList()) {
                detalleactividadesListDetalleactividadesToAttach = em.getReference(detalleactividadesListDetalleactividadesToAttach.getClass(), detalleactividadesListDetalleactividadesToAttach.getDetalleactividadesPK());
                attachedDetalleactividadesList.add(detalleactividadesListDetalleactividadesToAttach);
            }
            pedidos.setDetalleactividadesList(attachedDetalleactividadesList);
            List<Detalleservicio> attachedDetalleservicioList = new ArrayList<Detalleservicio>();
            for (Detalleservicio detalleservicioListDetalleservicioToAttach : pedidos.getDetalleservicioList()) {
                detalleservicioListDetalleservicioToAttach = em.getReference(detalleservicioListDetalleservicioToAttach.getClass(), detalleservicioListDetalleservicioToAttach.getDetalleservicioPK());
                attachedDetalleservicioList.add(detalleservicioListDetalleservicioToAttach);
            }
            pedidos.setDetalleservicioList(attachedDetalleservicioList);
            em.persist(pedidos);
            if (clientesIdcliente != null) {
                clientesIdcliente.getPedidosList().add(pedidos);
                clientesIdcliente = em.merge(clientesIdcliente);
            }
            if (paquetesIdpaquete != null) {
                paquetesIdpaquete.getPedidosList().add(pedidos);
                paquetesIdpaquete = em.merge(paquetesIdpaquete);
            }
            for (Detalleentregables detalleentregablesListDetalleentregables : pedidos.getDetalleentregablesList()) {
                Pedidos oldPedidosIdpedidoOfDetalleentregablesListDetalleentregables = detalleentregablesListDetalleentregables.getPedidosIdpedido();
                detalleentregablesListDetalleentregables.setPedidosIdpedido(pedidos);
                detalleentregablesListDetalleentregables = em.merge(detalleentregablesListDetalleentregables);
                if (oldPedidosIdpedidoOfDetalleentregablesListDetalleentregables != null) {
                    oldPedidosIdpedidoOfDetalleentregablesListDetalleentregables.getDetalleentregablesList().remove(detalleentregablesListDetalleentregables);
                    oldPedidosIdpedidoOfDetalleentregablesListDetalleentregables = em.merge(oldPedidosIdpedidoOfDetalleentregablesListDetalleentregables);
                }
            }
            for (Detalleactividades detalleactividadesListDetalleactividades : pedidos.getDetalleactividadesList()) {
                Pedidos oldPedidosOfDetalleactividadesListDetalleactividades = detalleactividadesListDetalleactividades.getPedidos();
                detalleactividadesListDetalleactividades.setPedidos(pedidos);
                detalleactividadesListDetalleactividades = em.merge(detalleactividadesListDetalleactividades);
                if (oldPedidosOfDetalleactividadesListDetalleactividades != null) {
                    oldPedidosOfDetalleactividadesListDetalleactividades.getDetalleactividadesList().remove(detalleactividadesListDetalleactividades);
                    oldPedidosOfDetalleactividadesListDetalleactividades = em.merge(oldPedidosOfDetalleactividadesListDetalleactividades);
                }
            }
            for (Detalleservicio detalleservicioListDetalleservicio : pedidos.getDetalleservicioList()) {
                Pedidos oldPedidosIdpedidoOfDetalleservicioListDetalleservicio = detalleservicioListDetalleservicio.getPedidosIdpedido();
                detalleservicioListDetalleservicio.setPedidosIdpedido(pedidos);
                detalleservicioListDetalleservicio = em.merge(detalleservicioListDetalleservicio);
                if (oldPedidosIdpedidoOfDetalleservicioListDetalleservicio != null) {
                    oldPedidosIdpedidoOfDetalleservicioListDetalleservicio.getDetalleservicioList().remove(detalleservicioListDetalleservicio);
                    oldPedidosIdpedidoOfDetalleservicioListDetalleservicio = em.merge(oldPedidosIdpedidoOfDetalleservicioListDetalleservicio);
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
            Clientes clientesIdclienteOld = persistentPedidos.getClientesIdcliente();
            Clientes clientesIdclienteNew = pedidos.getClientesIdcliente();
            Paquetes paquetesIdpaqueteOld = persistentPedidos.getPaquetesIdpaquete();
            Paquetes paquetesIdpaqueteNew = pedidos.getPaquetesIdpaquete();
            List<Detalleentregables> detalleentregablesListOld = persistentPedidos.getDetalleentregablesList();
            List<Detalleentregables> detalleentregablesListNew = pedidos.getDetalleentregablesList();
            List<Detalleactividades> detalleactividadesListOld = persistentPedidos.getDetalleactividadesList();
            List<Detalleactividades> detalleactividadesListNew = pedidos.getDetalleactividadesList();
            List<Detalleservicio> detalleservicioListOld = persistentPedidos.getDetalleservicioList();
            List<Detalleservicio> detalleservicioListNew = pedidos.getDetalleservicioList();
            List<String> illegalOrphanMessages = null;
            for (Detalleactividades detalleactividadesListOldDetalleactividades : detalleactividadesListOld) {
                if (!detalleactividadesListNew.contains(detalleactividadesListOldDetalleactividades)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleactividades " + detalleactividadesListOldDetalleactividades + " since its pedidos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clientesIdclienteNew != null) {
                clientesIdclienteNew = em.getReference(clientesIdclienteNew.getClass(), clientesIdclienteNew.getIdcliente());
                pedidos.setClientesIdcliente(clientesIdclienteNew);
            }
            if (paquetesIdpaqueteNew != null) {
                paquetesIdpaqueteNew = em.getReference(paquetesIdpaqueteNew.getClass(), paquetesIdpaqueteNew.getIdpaquete());
                pedidos.setPaquetesIdpaquete(paquetesIdpaqueteNew);
            }
            List<Detalleentregables> attachedDetalleentregablesListNew = new ArrayList<Detalleentregables>();
            for (Detalleentregables detalleentregablesListNewDetalleentregablesToAttach : detalleentregablesListNew) {
                detalleentregablesListNewDetalleentregablesToAttach = em.getReference(detalleentregablesListNewDetalleentregablesToAttach.getClass(), detalleentregablesListNewDetalleentregablesToAttach.getDetalleentregablesPK());
                attachedDetalleentregablesListNew.add(detalleentregablesListNewDetalleentregablesToAttach);
            }
            detalleentregablesListNew = attachedDetalleentregablesListNew;
            pedidos.setDetalleentregablesList(detalleentregablesListNew);
            List<Detalleactividades> attachedDetalleactividadesListNew = new ArrayList<Detalleactividades>();
            for (Detalleactividades detalleactividadesListNewDetalleactividadesToAttach : detalleactividadesListNew) {
                detalleactividadesListNewDetalleactividadesToAttach = em.getReference(detalleactividadesListNewDetalleactividadesToAttach.getClass(), detalleactividadesListNewDetalleactividadesToAttach.getDetalleactividadesPK());
                attachedDetalleactividadesListNew.add(detalleactividadesListNewDetalleactividadesToAttach);
            }
            detalleactividadesListNew = attachedDetalleactividadesListNew;
            pedidos.setDetalleactividadesList(detalleactividadesListNew);
            List<Detalleservicio> attachedDetalleservicioListNew = new ArrayList<Detalleservicio>();
            for (Detalleservicio detalleservicioListNewDetalleservicioToAttach : detalleservicioListNew) {
                detalleservicioListNewDetalleservicioToAttach = em.getReference(detalleservicioListNewDetalleservicioToAttach.getClass(), detalleservicioListNewDetalleservicioToAttach.getDetalleservicioPK());
                attachedDetalleservicioListNew.add(detalleservicioListNewDetalleservicioToAttach);
            }
            detalleservicioListNew = attachedDetalleservicioListNew;
            pedidos.setDetalleservicioList(detalleservicioListNew);
            pedidos = em.merge(pedidos);
            if (clientesIdclienteOld != null && !clientesIdclienteOld.equals(clientesIdclienteNew)) {
                clientesIdclienteOld.getPedidosList().remove(pedidos);
                clientesIdclienteOld = em.merge(clientesIdclienteOld);
            }
            if (clientesIdclienteNew != null && !clientesIdclienteNew.equals(clientesIdclienteOld)) {
                clientesIdclienteNew.getPedidosList().add(pedidos);
                clientesIdclienteNew = em.merge(clientesIdclienteNew);
            }
            if (paquetesIdpaqueteOld != null && !paquetesIdpaqueteOld.equals(paquetesIdpaqueteNew)) {
                paquetesIdpaqueteOld.getPedidosList().remove(pedidos);
                paquetesIdpaqueteOld = em.merge(paquetesIdpaqueteOld);
            }
            if (paquetesIdpaqueteNew != null && !paquetesIdpaqueteNew.equals(paquetesIdpaqueteOld)) {
                paquetesIdpaqueteNew.getPedidosList().add(pedidos);
                paquetesIdpaqueteNew = em.merge(paquetesIdpaqueteNew);
            }
            for (Detalleentregables detalleentregablesListOldDetalleentregables : detalleentregablesListOld) {
                if (!detalleentregablesListNew.contains(detalleentregablesListOldDetalleentregables)) {
                    detalleentregablesListOldDetalleentregables.setPedidosIdpedido(null);
                    detalleentregablesListOldDetalleentregables = em.merge(detalleentregablesListOldDetalleentregables);
                }
            }
            for (Detalleentregables detalleentregablesListNewDetalleentregables : detalleentregablesListNew) {
                if (!detalleentregablesListOld.contains(detalleentregablesListNewDetalleentregables)) {
                    Pedidos oldPedidosIdpedidoOfDetalleentregablesListNewDetalleentregables = detalleentregablesListNewDetalleentregables.getPedidosIdpedido();
                    detalleentregablesListNewDetalleentregables.setPedidosIdpedido(pedidos);
                    detalleentregablesListNewDetalleentregables = em.merge(detalleentregablesListNewDetalleentregables);
                    if (oldPedidosIdpedidoOfDetalleentregablesListNewDetalleentregables != null && !oldPedidosIdpedidoOfDetalleentregablesListNewDetalleentregables.equals(pedidos)) {
                        oldPedidosIdpedidoOfDetalleentregablesListNewDetalleentregables.getDetalleentregablesList().remove(detalleentregablesListNewDetalleentregables);
                        oldPedidosIdpedidoOfDetalleentregablesListNewDetalleentregables = em.merge(oldPedidosIdpedidoOfDetalleentregablesListNewDetalleentregables);
                    }
                }
            }
            for (Detalleactividades detalleactividadesListNewDetalleactividades : detalleactividadesListNew) {
                if (!detalleactividadesListOld.contains(detalleactividadesListNewDetalleactividades)) {
                    Pedidos oldPedidosOfDetalleactividadesListNewDetalleactividades = detalleactividadesListNewDetalleactividades.getPedidos();
                    detalleactividadesListNewDetalleactividades.setPedidos(pedidos);
                    detalleactividadesListNewDetalleactividades = em.merge(detalleactividadesListNewDetalleactividades);
                    if (oldPedidosOfDetalleactividadesListNewDetalleactividades != null && !oldPedidosOfDetalleactividadesListNewDetalleactividades.equals(pedidos)) {
                        oldPedidosOfDetalleactividadesListNewDetalleactividades.getDetalleactividadesList().remove(detalleactividadesListNewDetalleactividades);
                        oldPedidosOfDetalleactividadesListNewDetalleactividades = em.merge(oldPedidosOfDetalleactividadesListNewDetalleactividades);
                    }
                }
            }
            for (Detalleservicio detalleservicioListOldDetalleservicio : detalleservicioListOld) {
                if (!detalleservicioListNew.contains(detalleservicioListOldDetalleservicio)) {
                    detalleservicioListOldDetalleservicio.setPedidosIdpedido(null);
                    detalleservicioListOldDetalleservicio = em.merge(detalleservicioListOldDetalleservicio);
                }
            }
            for (Detalleservicio detalleservicioListNewDetalleservicio : detalleservicioListNew) {
                if (!detalleservicioListOld.contains(detalleservicioListNewDetalleservicio)) {
                    Pedidos oldPedidosIdpedidoOfDetalleservicioListNewDetalleservicio = detalleservicioListNewDetalleservicio.getPedidosIdpedido();
                    detalleservicioListNewDetalleservicio.setPedidosIdpedido(pedidos);
                    detalleservicioListNewDetalleservicio = em.merge(detalleservicioListNewDetalleservicio);
                    if (oldPedidosIdpedidoOfDetalleservicioListNewDetalleservicio != null && !oldPedidosIdpedidoOfDetalleservicioListNewDetalleservicio.equals(pedidos)) {
                        oldPedidosIdpedidoOfDetalleservicioListNewDetalleservicio.getDetalleservicioList().remove(detalleservicioListNewDetalleservicio);
                        oldPedidosIdpedidoOfDetalleservicioListNewDetalleservicio = em.merge(oldPedidosIdpedidoOfDetalleservicioListNewDetalleservicio);
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
            List<Detalleactividades> detalleactividadesListOrphanCheck = pedidos.getDetalleactividadesList();
            for (Detalleactividades detalleactividadesListOrphanCheckDetalleactividades : detalleactividadesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedidos (" + pedidos + ") cannot be destroyed since the Detalleactividades " + detalleactividadesListOrphanCheckDetalleactividades + " in its detalleactividadesList field has a non-nullable pedidos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clientes clientesIdcliente = pedidos.getClientesIdcliente();
            if (clientesIdcliente != null) {
                clientesIdcliente.getPedidosList().remove(pedidos);
                clientesIdcliente = em.merge(clientesIdcliente);
            }
            Paquetes paquetesIdpaquete = pedidos.getPaquetesIdpaquete();
            if (paquetesIdpaquete != null) {
                paquetesIdpaquete.getPedidosList().remove(pedidos);
                paquetesIdpaquete = em.merge(paquetesIdpaquete);
            }
            List<Detalleentregables> detalleentregablesList = pedidos.getDetalleentregablesList();
            for (Detalleentregables detalleentregablesListDetalleentregables : detalleentregablesList) {
                detalleentregablesListDetalleentregables.setPedidosIdpedido(null);
                detalleentregablesListDetalleentregables = em.merge(detalleentregablesListDetalleentregables);
            }
            List<Detalleservicio> detalleservicioList = pedidos.getDetalleservicioList();
            for (Detalleservicio detalleservicioListDetalleservicio : detalleservicioList) {
                detalleservicioListDetalleservicio.setPedidosIdpedido(null);
                detalleservicioListDetalleservicio = em.merge(detalleservicioListDetalleservicio);
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
    
}
