/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Cliente;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author lucas
 */
@Singleton
public class ClientDAO {
    
    @PersistenceContext(name = "vendasPU")
    private EntityManager entityManager;
    
    @Lock(LockType.READ)
    public List<Cliente> findAll() {
        Query query = entityManager.createQuery("select c from clientes c", Cliente.class);
        List<Cliente> clientes = query.getResultList();
        return clientes;
    }
    
    @Lock(LockType.READ)
    public Cliente find(Long id) {
        Query query = entityManager.createQuery("select c from clientes c where c.id = :id", Cliente.class);
        query.setParameter("id", id);
        Cliente cliente = (Cliente)query.getSingleResult();
        return cliente;
    }   
    
    
    public Long save(Cliente c) {
        entityManager.persist(c);
        return c.getId();
    }
    
    public void deleteClient(Long id) {
        Cliente entity = entityManager.find(Cliente.class, id);
        entityManager.remove(entity);
    }
    
    public Cliente atualizar(Cliente cliente) {
        return this.getEntityManager().merge(cliente);
    }
        
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
