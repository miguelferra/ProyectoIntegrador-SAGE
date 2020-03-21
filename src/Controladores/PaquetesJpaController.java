/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import Controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Detalleentregables;
import java.util.ArrayList;
import java.util.List;
import Entidades.Pedidos;
import Entidades.Detalleservicio;
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

    public void create(Paquetes paquetes) throws PreexistingEntityException, Exception {
        if (paquetes.getDetalleentregablesList() == null) {
            paquetes.setDetalleentregablesList(new ArrayList<Detalleentregables>());
        }
        if (paquetes.getPedidosList() == null) {
            paquetes.setPedidosList(new ArrayList<Pedidos>());
        }
        if (paquetes.getDetalleservicioList() == null) {
            paquetes.setDetalleservicioList(new ArrayList<Detalleservicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalleentregables> attachedDetalleentregablesList = new ArrayList<Detalleentregables>();
            for (Detalleentregables detalleentregablesListDetalleentregablesToAttach : paquetes.getDetalleentregablesList()) {
                detalleentregablesListDetalleentregablesToAttach = em.getReference(detalleentregablesListDetalleentregablesToAttach.getClass(), detalleentregablesListDetalleentregablesToAttach.getDetalleentregablesPK());
                attachedDetalleentregablesList.add(detalleentregablesListDetalleentregablesToAttach);
            }
            paquetes.setDetalleentregablesList(attachedDetalleentregablesList);
            List<Pedidos> attachedPedidosList = new ArrayList<Pedidos>();
            for (Pedidos pedidosListPedidosToAttach : paquetes.getPedidosList()) {
                pedidosListPedidosToAttach = em.getReference(pedidosListPedidosToAttach.getClass(), pedidosListPedidosToAttach.getIdpedido());
                attachedPedidosList.add(pedidosListPedidosToAttach);
            }
            paquetes.setPedidosList(attachedPedidosList);
            List<Detalleservicio> attachedDetalleservicioList = new ArrayList<Detalleservicio>();
            for (Detalleservicio detalleservicioListDetalleservicioToAttach : paquetes.getDetalleservicioList()) {
                detalleservicioListDetalleservicioToAttach = em.getReference(detalleservicioListDetalleservicioToAttach.getClass(), detalleservicioListDetalleservicioToAttach.getDetalleservicioPK());
                attachedDetalleservicioList.add(detalleservicioListDetalleservicioToAttach);
            }
            paquetes.setDetalleservicioList(attachedDetalleservicioList);
            em.persist(paquetes);
            for (Detalleentregables detalleentregablesListDetalleentregables : paquetes.getDetalleentregablesList()) {
                Paquetes oldPaquetesOfDetalleentregablesListDetalleentregables = detalleentregablesListDetalleentregables.getPaquetes();
                detalleentregablesListDetalleentregables.setPaquetes(paquetes);
                detalleentregablesListDetalleentregables = em.merge(detalleentregablesListDetalleentregables);
                if (oldPaquetesOfDetalleentregablesListDetalleentregables != null) {
                    oldPaquetesOfDetalleentregablesListDetalleentregables.getDetalleentregablesList().remove(detalleentregablesListDetalleentregables);
                    oldPaquetesOfDetalleentregablesListDetalleentregables = em.merge(oldPaquetesOfDetalleentregablesListDetalleentregables);
                }
            }
            for (Pedidos pedidosListPedidos : paquetes.getPedidosList()) {
                Paquetes oldPaquetesIdpaqueteOfPedidosListPedidos = pedidosListPedidos.getPaquetesIdpaquete();
                pedidosListPedidos.setPaquetesIdpaquete(paquetes);
                pedidosListPedidos = em.merge(pedidosListPedidos);
                if (oldPaquetesIdpaqueteOfPedidosListPedidos != null) {
                    oldPaquetesIdpaqueteOfPedidosListPedidos.getPedidosList().remove(pedidosListPedidos);
                    oldPaquetesIdpaqueteOfPedidosListPedidos = em.merge(oldPaquetesIdpaqueteOfPedidosListPedidos);
                }
            }
            for (Detalleservicio detalleservicioListDetalleservicio : paquetes.getDetalleservicioList()) {
                Paquetes oldPaquetesOfDetalleservicioListDetalleservicio = detalleservicioListDetalleservicio.getPaquetes();
                detalleservicioListDetalleservicio.setPaquetes(paquetes);
                detalleservicioListDetalleservicio = em.merge(detalleservicioListDetalleservicio);
                if (oldPaquetesOfDetalleservicioListDetalleservicio != null) {
                    oldPaquetesOfDetalleservicioListDetalleservicio.getDetalleservicioList().remove(detalleservicioListDetalleservicio);
                    oldPaquetesOfDetalleservicioListDetalleservicio = em.merge(oldPaquetesOfDetalleservicioListDetalleservicio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPaquetes(paquetes.getIdpaquete()) != null) {
                throw new PreexistingEntityException("Paquetes " + paquetes + " already exists.", ex);
            }
            throw ex;
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
            List<Detalleentregables> detalleentregablesListOld = persistentPaquetes.getDetalleentregablesList();
            List<Detalleentregables> detalleentregablesListNew = paquetes.getDetalleentregablesList();
            List<Pedidos> pedidosListOld = persistentPaquetes.getPedidosList();
            List<Pedidos> pedidosListNew = paquetes.getPedidosList();
            List<Detalleservicio> detalleservicioListOld = persistentPaquetes.getDetalleservicioList();
            List<Detalleservicio> detalleservicioListNew = paquetes.getDetalleservicioList();
            List<String> illegalOrphanMessages = null;
            for (Detalleentregables detalleentregablesListOldDetalleentregables : detalleentregablesListOld) {
                if (!detalleentregablesListNew.contains(detalleentregablesListOldDetalleentregables)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleentregables " + detalleentregablesListOldDetalleentregables + " since its paquetes field is not nullable.");
                }
            }
            for (Pedidos pedidosListOldPedidos : pedidosListOld) {
                if (!pedidosListNew.contains(pedidosListOldPedidos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedidos " + pedidosListOldPedidos + " since its paquetesIdpaquete field is not nullable.");
                }
            }
            for (Detalleservicio detalleservicioListOldDetalleservicio : detalleservicioListOld) {
                if (!detalleservicioListNew.contains(detalleservicioListOldDetalleservicio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleservicio " + detalleservicioListOldDetalleservicio + " since its paquetes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleentregables> attachedDetalleentregablesListNew = new ArrayList<Detalleentregables>();
            for (Detalleentregables detalleentregablesListNewDetalleentregablesToAttach : detalleentregablesListNew) {
                detalleentregablesListNewDetalleentregablesToAttach = em.getReference(detalleentregablesListNewDetalleentregablesToAttach.getClass(), detalleentregablesListNewDetalleentregablesToAttach.getDetalleentregablesPK());
                attachedDetalleentregablesListNew.add(detalleentregablesListNewDetalleentregablesToAttach);
            }
            detalleentregablesListNew = attachedDetalleentregablesListNew;
            paquetes.setDetalleentregablesList(detalleentregablesListNew);
            List<Pedidos> attachedPedidosListNew = new ArrayList<Pedidos>();
            for (Pedidos pedidosListNewPedidosToAttach : pedidosListNew) {
                pedidosListNewPedidosToAttach = em.getReference(pedidosListNewPedidosToAttach.getClass(), pedidosListNewPedidosToAttach.getIdpedido());
                attachedPedidosListNew.add(pedidosListNewPedidosToAttach);
            }
            pedidosListNew = attachedPedidosListNew;
            paquetes.setPedidosList(pedidosListNew);
            List<Detalleservicio> attachedDetalleservicioListNew = new ArrayList<Detalleservicio>();
            for (Detalleservicio detalleservicioListNewDetalleservicioToAttach : detalleservicioListNew) {
                detalleservicioListNewDetalleservicioToAttach = em.getReference(detalleservicioListNewDetalleservicioToAttach.getClass(), detalleservicioListNewDetalleservicioToAttach.getDetalleservicioPK());
                attachedDetalleservicioListNew.add(detalleservicioListNewDetalleservicioToAttach);
            }
            detalleservicioListNew = attachedDetalleservicioListNew;
            paquetes.setDetalleservicioList(detalleservicioListNew);
            paquetes = em.merge(paquetes);
            for (Detalleentregables detalleentregablesListNewDetalleentregables : detalleentregablesListNew) {
                if (!detalleentregablesListOld.contains(detalleentregablesListNewDetalleentregables)) {
                    Paquetes oldPaquetesOfDetalleentregablesListNewDetalleentregables = detalleentregablesListNewDetalleentregables.getPaquetes();
                    detalleentregablesListNewDetalleentregables.setPaquetes(paquetes);
                    detalleentregablesListNewDetalleentregables = em.merge(detalleentregablesListNewDetalleentregables);
                    if (oldPaquetesOfDetalleentregablesListNewDetalleentregables != null && !oldPaquetesOfDetalleentregablesListNewDetalleentregables.equals(paquetes)) {
                        oldPaquetesOfDetalleentregablesListNewDetalleentregables.getDetalleentregablesList().remove(detalleentregablesListNewDetalleentregables);
                        oldPaquetesOfDetalleentregablesListNewDetalleentregables = em.merge(oldPaquetesOfDetalleentregablesListNewDetalleentregables);
                    }
                }
            }
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
            for (Detalleservicio detalleservicioListNewDetalleservicio : detalleservicioListNew) {
                if (!detalleservicioListOld.contains(detalleservicioListNewDetalleservicio)) {
                    Paquetes oldPaquetesOfDetalleservicioListNewDetalleservicio = detalleservicioListNewDetalleservicio.getPaquetes();
                    detalleservicioListNewDetalleservicio.setPaquetes(paquetes);
                    detalleservicioListNewDetalleservicio = em.merge(detalleservicioListNewDetalleservicio);
                    if (oldPaquetesOfDetalleservicioListNewDetalleservicio != null && !oldPaquetesOfDetalleservicioListNewDetalleservicio.equals(paquetes)) {
                        oldPaquetesOfDetalleservicioListNewDetalleservicio.getDetalleservicioList().remove(detalleservicioListNewDetalleservicio);
                        oldPaquetesOfDetalleservicioListNewDetalleservicio = em.merge(oldPaquetesOfDetalleservicioListNewDetalleservicio);
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
            List<Detalleentregables> detalleentregablesListOrphanCheck = paquetes.getDetalleentregablesList();
            for (Detalleentregables detalleentregablesListOrphanCheckDetalleentregables : detalleentregablesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paquetes (" + paquetes + ") cannot be destroyed since the Detalleentregables " + detalleentregablesListOrphanCheckDetalleentregables + " in its detalleentregablesList field has a non-nullable paquetes field.");
            }
            List<Pedidos> pedidosListOrphanCheck = paquetes.getPedidosList();
            for (Pedidos pedidosListOrphanCheckPedidos : pedidosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paquetes (" + paquetes + ") cannot be destroyed since the Pedidos " + pedidosListOrphanCheckPedidos + " in its pedidosList field has a non-nullable paquetesIdpaquete field.");
            }
            List<Detalleservicio> detalleservicioListOrphanCheck = paquetes.getDetalleservicioList();
            for (Detalleservicio detalleservicioListOrphanCheckDetalleservicio : detalleservicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Paquetes (" + paquetes + ") cannot be destroyed since the Detalleservicio " + detalleservicioListOrphanCheckDetalleservicio + " in its detalleservicioList field has a non-nullable paquetes field.");
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
