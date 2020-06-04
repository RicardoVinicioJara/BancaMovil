/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ups.proyecto.dao;

import ec.edu.ups.proyecto.emtitis.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ricardo
 */

@Stateless
public class ClienteDAO {

    @PersistenceContext(name="BancaMovilPersistenceUnit")
    private EntityManager em;

    public ClienteDAO() {
    }
    
    
    public boolean insert(Cliente cliente) throws Exception {
        boolean bandera = true;
    	try {
            System.out.println("si creo que llega aca");
            em.persist(cliente);
            bandera=true;
        } catch (Exception e) {
        	bandera=false;
            throw new Exception("Erro ingreso Cliente " + e.getMessage());
            
        }
        
        return bandera;
    }

    public void delete(Cliente cliente) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(cliente.getId()));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Cliente " +e.getMessage());
        }
    }

    public void deleteId(int id) throws Exception {
        try {
            System.out.println("borrando");
            em.remove(read(id));
        } catch (Exception e) {
            throw new Exception("oErro Eliminar Cliente " +e.getMessage());
        }
    }
    
    public void update(Cliente cliente) throws Exception {
        try {
            em.merge(cliente);
        } catch (Exception e) {
            throw new Exception("Erro actualizar Cliente " +e.getMessage());
        }
    }

    public Cliente read(int id) throws Exception {
        try {
            System.out.println("Estamos aca");
            return em.find(Cliente.class, id);
        } catch (Exception e) {
            throw new Exception("Erro leer Cliente " +e.getMessage());
        }
    }

    public List<Cliente> findAll() throws Exception {

        try {
            Query q = em.createNamedQuery("Cliente.findAll");
            List<Cliente> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Cliente " +e.getMessage());
        }

    }
    
    public List<Cliente> findAllCodigo(String codigo) throws Exception {

        try {
            Query q = em.createNamedQuery("Cliente.findAllCodigo");
            q.setParameter("codigo",  "%" + codigo + "%");
            List<Cliente> lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            throw new Exception("Erro listar Cliente " +e.getMessage());
        }

    }
    
    public Cliente findByID(String id) throws Exception {
        try {
            Query q = em.createNamedQuery("Cliente.findById");
            q.setParameter("ID", Integer.parseInt(id));
            return (Cliente) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  ID ");
        }

    }

    public Cliente findByCedula(String cedula) throws Exception {
        try {
            String jpql = "SELECT P FROM Cliente p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Cliente.class);
            q.setParameter("cedula", cedula);

            return (Cliente) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Erro buscar por  cedula");
        }

    }

    public int maxId() throws Exception {
        try {
            String jpql = "SELECT P FROM Cliente p "
                    + "WHERE cedula = :cedula";
            Query q = em.createQuery(jpql, Cliente.class);
            return (int) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Error MaxID", e.getCause());
        }
    }

}
