/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Vendas;
import java.math.BigDecimal;
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
public class VendasDAO {
    
    @PersistenceContext(name="vendasPU")
    private EntityManager entityManager;
    
    @Lock(LockType.READ)
    public List<Vendas> findAll() {
        Query query = entityManager.createQuery("select v from vendas v", Vendas.class);
        List<Vendas> vendas = query.getResultList();
        return vendas;
    }   
    
    public Long save(Vendas v) {
        entityManager.persist(v);
        return v.getId();
    }
    
    @Lock(LockType.READ)
    public Integer findQtdVendido(int id) {
        Query q = entityManager.createNativeQuery("select sum(qtd_produto) qtd_vendido from vendas where id_produto = ?");
        q.setParameter(1, id);
        BigDecimal qryResult = (BigDecimal)q.getSingleResult();
        if(qryResult == null) {
            return 0;
        } else {
            return Integer.valueOf(qryResult.toString());
        }   
    }
}
