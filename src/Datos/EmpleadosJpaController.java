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
import Entidades.Detalleactividades;
import Entidades.Empleados;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ferra
 */
public class EmpleadosJpaController implements Serializable {

    public EmpleadosJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SAGEPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleados empleados) throws PreexistingEntityException, Exception {
        if (empleados.getDetalleactividadesList() == null) {
            empleados.setDetalleactividadesList(new ArrayList<Detalleactividades>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalleactividades> attachedDetalleactividadesList = new ArrayList<Detalleactividades>();
            for (Detalleactividades detalleactividadesListDetalleactividadesToAttach : empleados.getDetalleactividadesList()) {
                detalleactividadesListDetalleactividadesToAttach = em.getReference(detalleactividadesListDetalleactividadesToAttach.getClass(), detalleactividadesListDetalleactividadesToAttach.getDetalleactividadesPK());
                attachedDetalleactividadesList.add(detalleactividadesListDetalleactividadesToAttach);
            }
            empleados.setDetalleactividadesList(attachedDetalleactividadesList);
            em.persist(empleados);
            for (Detalleactividades detalleactividadesListDetalleactividades : empleados.getDetalleactividadesList()) {
                Empleados oldEmpleadosRFCOfDetalleactividadesListDetalleactividades = detalleactividadesListDetalleactividades.getEmpleadosRFC();
                detalleactividadesListDetalleactividades.setEmpleadosRFC(empleados);
                detalleactividadesListDetalleactividades = em.merge(detalleactividadesListDetalleactividades);
                if (oldEmpleadosRFCOfDetalleactividadesListDetalleactividades != null) {
                    oldEmpleadosRFCOfDetalleactividadesListDetalleactividades.getDetalleactividadesList().remove(detalleactividadesListDetalleactividades);
                    oldEmpleadosRFCOfDetalleactividadesListDetalleactividades = em.merge(oldEmpleadosRFCOfDetalleactividadesListDetalleactividades);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleados(empleados.getRfc()) != null) {
                throw new PreexistingEntityException("Empleados " + empleados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleados empleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados persistentEmpleados = em.find(Empleados.class, empleados.getRfc());
            List<Detalleactividades> detalleactividadesListOld = persistentEmpleados.getDetalleactividadesList();
            List<Detalleactividades> detalleactividadesListNew = empleados.getDetalleactividadesList();
            List<Detalleactividades> attachedDetalleactividadesListNew = new ArrayList<Detalleactividades>();
            for (Detalleactividades detalleactividadesListNewDetalleactividadesToAttach : detalleactividadesListNew) {
                detalleactividadesListNewDetalleactividadesToAttach = em.getReference(detalleactividadesListNewDetalleactividadesToAttach.getClass(), detalleactividadesListNewDetalleactividadesToAttach.getDetalleactividadesPK());
                attachedDetalleactividadesListNew.add(detalleactividadesListNewDetalleactividadesToAttach);
            }
            detalleactividadesListNew = attachedDetalleactividadesListNew;
            empleados.setDetalleactividadesList(detalleactividadesListNew);
            empleados = em.merge(empleados);
            for (Detalleactividades detalleactividadesListOldDetalleactividades : detalleactividadesListOld) {
                if (!detalleactividadesListNew.contains(detalleactividadesListOldDetalleactividades)) {
                    detalleactividadesListOldDetalleactividades.setEmpleadosRFC(null);
                    detalleactividadesListOldDetalleactividades = em.merge(detalleactividadesListOldDetalleactividades);
                }
            }
            for (Detalleactividades detalleactividadesListNewDetalleactividades : detalleactividadesListNew) {
                if (!detalleactividadesListOld.contains(detalleactividadesListNewDetalleactividades)) {
                    Empleados oldEmpleadosRFCOfDetalleactividadesListNewDetalleactividades = detalleactividadesListNewDetalleactividades.getEmpleadosRFC();
                    detalleactividadesListNewDetalleactividades.setEmpleadosRFC(empleados);
                    detalleactividadesListNewDetalleactividades = em.merge(detalleactividadesListNewDetalleactividades);
                    if (oldEmpleadosRFCOfDetalleactividadesListNewDetalleactividades != null && !oldEmpleadosRFCOfDetalleactividadesListNewDetalleactividades.equals(empleados)) {
                        oldEmpleadosRFCOfDetalleactividadesListNewDetalleactividades.getDetalleactividadesList().remove(detalleactividadesListNewDetalleactividades);
                        oldEmpleadosRFCOfDetalleactividadesListNewDetalleactividades = em.merge(oldEmpleadosRFCOfDetalleactividadesListNewDetalleactividades);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = empleados.getRfc();
                if (findEmpleados(id) == null) {
                    throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados empleados;
            try {
                empleados = em.getReference(Empleados.class, id);
                empleados.getRfc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
            }
            List<Detalleactividades> detalleactividadesList = empleados.getDetalleactividadesList();
            for (Detalleactividades detalleactividadesListDetalleactividades : detalleactividadesList) {
                detalleactividadesListDetalleactividades.setEmpleadosRFC(null);
                detalleactividadesListDetalleactividades = em.merge(detalleactividadesListDetalleactividades);
            }
            em.remove(empleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleados> findEmpleadosEntities() {
        return findEmpleadosEntities(true, -1, -1);
    }

    public List<Empleados> findEmpleadosEntities(int maxResults, int firstResult) {
        return findEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<Empleados> findEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleados.class));
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

    public Empleados findEmpleados(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleados> rt = cq.from(Empleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
