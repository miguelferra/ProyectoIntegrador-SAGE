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
import Entidades.Pedidos;
import java.util.ArrayList;
import java.util.List;
import Entidades.Detalleentregablespaquete;
import Entidades.Paquetes;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ferra
 */
public class PaquetesJpaController implements Serializable {

    public PaquetesJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paquetes paquetes) {
        if (paquetes.getPedidosList() == null) {
            paquetes.setPedidosList(new ArrayList<Pedidos>());
        }
        if (paquetes.getDetalleentregablespaqueteList() == null) {
            paquetes.setDetalleentregablespaqueteList(new ArrayList<Detalleentregablespaquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedidos> attachedPedidosList = new ArrayList<Pedidos>();
            for (Pedidos pedidosListPedidosToAttach : paquetes.getPedidosList()) {
                pedidosListPedidosToAttach = em.getReference(pedidosListPedidosToAttach.getClass(), pedidosListPedidosToAttach.getIdpedido());
                attachedPedidosList.add(pedidosListPedidosToAttach);
            }
            paquetes.setPedidosList(attachedPedidosList);
            List<Detalleentregablespaquete> attachedDetalleentregablespaqueteList = new ArrayList<Detalleentregablespaquete>();
            for (Detalleentregablespaquete detalleentregablespaqueteListDetalleentregablespaqueteToAttach : paquetes.getDetalleentregablespaqueteList()) {
                detalleentregablespaqueteListDetalleentregablespaqueteToAttach = em.getReference(detalleentregablespaqueteListDetalleentregablespaqueteToAttach.getClass(), detalleentregablespaqueteListDetalleentregablespaqueteToAttach.getPrueba());
                attachedDetalleentregablespaqueteList.add(detalleentregablespaqueteListDetalleentregablespaqueteToAttach);
            }
            paquetes.setDetalleentregablespaqueteList(attachedDetalleentregablespaqueteList);
            em.persist(paquetes);
            for (Pedidos pedidosListPedidos : paquetes.getPedidosList()) {
                Paquetes oldPaquetesIdpaqueteOfPedidosListPedidos = pedidosListPedidos.getPaquetesIdpaquete();
                pedidosListPedidos.setPaquetesIdpaquete(paquetes);
                pedidosListPedidos = em.merge(pedidosListPedidos);
                if (oldPaquetesIdpaqueteOfPedidosListPedidos != null) {
                    oldPaquetesIdpaqueteOfPedidosListPedidos.getPedidosList().remove(pedidosListPedidos);
                    oldPaquetesIdpaqueteOfPedidosListPedidos = em.merge(oldPaquetesIdpaqueteOfPedidosListPedidos);
                }
            }
            for (Detalleentregablespaquete detalleentregablespaqueteListDetalleentregablespaquete : paquetes.getDetalleentregablespaqueteList()) {
                Paquetes oldPaquetesIdpaqueteOfDetalleentregablespaqueteListDetalleentregablespaquete = detalleentregablespaqueteListDetalleentregablespaquete.getPaquetesIdpaquete();
                detalleentregablespaqueteListDetalleentregablespaquete.setPaquetesIdpaquete(paquetes);
                detalleentregablespaqueteListDetalleentregablespaquete = em.merge(detalleentregablespaqueteListDetalleentregablespaquete);
                if (oldPaquetesIdpaqueteOfDetalleentregablespaqueteListDetalleentregablespaquete != null) {
                    oldPaquetesIdpaqueteOfDetalleentregablespaqueteListDetalleentregablespaquete.getDetalleentregablespaqueteList().remove(detalleentregablespaqueteListDetalleentregablespaquete);
                    oldPaquetesIdpaqueteOfDetalleentregablespaqueteListDetalleentregablespaquete = em.merge(oldPaquetesIdpaqueteOfDetalleentregablespaqueteListDetalleentregablespaquete);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paquetes paquetes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquetes persistentPaquetes = em.find(Paquetes.class, paquetes.getIdpaquete());
            List<Pedidos> pedidosListOld = persistentPaquetes.getPedidosList();
            List<Pedidos> pedidosListNew = paquetes.getPedidosList();
            List<Detalleentregablespaquete> detalleentregablespaqueteListOld = persistentPaquetes.getDetalleentregablespaqueteList();
            List<Detalleentregablespaquete> detalleentregablespaqueteListNew = paquetes.getDetalleentregablespaqueteList();
            List<String> illegalOrphanMessages = null;
            for (Pedidos pedidosListOldPedidos : pedidosListOld) {
                if (!pedidosListNew.contains(pedidosListOldPedidos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedidos " + pedidosListOldPedidos + " since its paquetesIdpaquete field is not nullable.");
                }
            }
            for (Detalleentregablespaquete detalleentregablespaqueteListOldDetalleentregablespaquete : detalleentregablespaqueteListOld) {
                if (!detalleentregablespaqueteListNew.contains(detalleentregablespaqueteListOldDetalleentregablespaquete)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleentregablespaquete " + detalleentregablespaqueteListOldDetalleentregablespaquete + " since its paquetesIdpaquete field is not nullable.");
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
            paquetes.setPedidosList(pedidosListNew);
            List<Detalleentregablespaquete> attachedDetalleentregablespaqueteListNew = new ArrayList<Detalleentregablespaquete>();
            for (Detalleentregablespaquete detalleentregablespaqueteListNewDetalleentregablespaqueteToAttach : detalleentregablespaqueteListNew) {
                detalleentregablespaqueteListNewDetalleentregablespaqueteToAttach = em.getReference(detalleentregablespaqueteListNewDetalleentregablespaqueteToAttach.getClass(), detalleentregablespaqueteListNewDetalleentregablespaqueteToAttach.getPrueba());
                attachedDetalleentregablespaqueteListNew.add(detalleentregablespaqueteListNewDetalleentregablespaqueteToAttach);
            }
            detalleentregablespaqueteListNew = attachedDetalleentregablespaqueteListNew;
            paquetes.setDetalleentregablespaqueteList(detalleentregablespaqueteListNew);
            paquetes = em.merge(paquetes);
            for (Pedidos pedidosListNewPedidos : pedidosListNew) {
                if (!pedidosListOld.contains(pedidosListNewPedidos)) {
                    Paquetes oldPaquetesIdpaqueteOfPedidosListNewPedidos = pedidosListNewPedidos.getPaquetesIdpaquete();
                    pedidosListNewPedidos.setPaquetesIdpaquete(paquetes);
                    pedidosListNewPedidos = em.merge(pedidosListNewPedidos);
                    if (oldPaquetesIdpaqueteOfPedidosListNewPedidos != null && !oldPaquetesIdpaqueteOfPedidosListNewPedidos.equals(paquetes)) {
                        oldPaquetesIdpaqueteOfPedidosListNewPedidos.getPedidosList().remove(pedidosListNewPedidos);
                        oldPaquetesIdpaqueteOfPedidosListNewPedidos = em.merge(oldPaquetesIdpaqueteOfPedidosListNewPedidos);
                    }
                }
            }
            for (Detalleentregablespaquete detalleentregablespaqueteListNewDetalleentregablespaquete : detalleentregablespaqueteListNew) {
                if (!detalleentregablespaqueteListOld.contains(detalleentregablespaqueteListNewDetalleentregablespaquete)) {
                    Paquetes oldPaquetesIdpaqueteOfDetalleentregablespaqueteListNewDetalleentregablespaquete = detalleentregablespaqueteListNewDetalleentregablespaquete.getPaquetesIdpaquete();
                    detalleentregablespaqueteListNewDetalleentregablespaquete.setPaquetesIdpaquete(paquetes);
                    detalleentregablespaqueteListNewDetalleentregablespaquete = em.merge(detalleentregablespaqueteListNewDetalleentregablespaquete);
                    if (oldPaquetesIdpaqueteOfDetalleentregablespaqueteListNewDetalleentregablespaquete != null && !oldPaquetesIdpaqueteOfDetalleentregablespaqueteListNewDetalleentregablespaquete.equals(paquetes)) {
                        oldPaquetesIdpaqueteOfDetalleentregablespaqueteListNewDetalleentregablespaquete.getDetalleentregablespaqueteList().remove(detalleentregablespaqueteListNewDetalleentregablespaquete);
                        oldPaquetesIdpaqueteOfDetalleentregablespaqueteListNewDetalleentregablespaquete = em.merge(oldPaquetesIdpaqueteOfDetalleentregablespaqueteListNewDetalleentregablespaquete);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paquetes.getIdpaquete();
                if (findPaquetes(id) == null) {
                    throw new NonexistentEntityException("The paquetes with id " + id + " no longer exists.");
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
            Paquetes paquetes;
            try {
                paquetes = em.getReference(Paquetes.class, id);
                paquetes.getIdpaquete();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paquetes with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pedidos> pedidosListOrphanCheck = paquetes.getPedidosList();
            for (Pedidos pedidosListOrphanCheckPedidos : pedidosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paquetes (" + paquetes + ") cannot be destroyed since the Pedidos " + pedidosListOrphanCheckPedidos + " in its pedidosList field has a non-nullable paquetesIdpaquete field.");
            }
            List<Detalleentregablespaquete> detalleentregablespaqueteListOrphanCheck = paquetes.getDetalleentregablespaqueteList();
            for (Detalleentregablespaquete detalleentregablespaqueteListOrphanCheckDetalleentregablespaquete : detalleentregablespaqueteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paquetes (" + paquetes + ") cannot be destroyed since the Detalleentregablespaquete " + detalleentregablespaqueteListOrphanCheckDetalleentregablespaquete + " in its detalleentregablespaqueteList field has a non-nullable paquetesIdpaquete field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(paquetes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paquetes> findPaquetesEntities() {
        return findPaquetesEntities(true, -1, -1);
    }

    public List<Paquetes> findPaquetesEntities(int maxResults, int firstResult) {
        return findPaquetesEntities(false, maxResults, firstResult);
    }

    private List<Paquetes> findPaquetesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paquetes.class));
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

    public Paquetes findPaquetes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paquetes.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaquetesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paquetes> rt = cq.from(Paquetes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
