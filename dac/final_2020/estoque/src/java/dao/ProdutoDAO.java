/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Produto;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import vo.VendasVO;

/**
 *
 * @author lucas
 */
@Singleton
public class ProdutoDAO {
    
    @PersistenceContext(name="estoquePU")
    private EntityManager entityManager;
    
    @Lock(LockType.READ)
    public List<Produto> findAll() {
        Query query = entityManager.createQuery("select p from produtos p", Produto.class);
        List<Produto> produtos = query.getResultList();
        return produtos;
    }
    
    @Lock(LockType.READ)
    public Produto find(Long id) {
        Query query = entityManager.createQuery("select p from produtos p where p.id = :id", Produto.class);
        query.setParameter("id", id);
        Produto produto;
        try {
            produto = (Produto)query.getSingleResult();
        } catch(NoResultException nre) {
            return new Produto();
        }
        return produto;
    }
    
    public Long save(Produto p) {
        entityManager.persist(p);
        return p.getIdProduto();
    }
    
    public void deleteProduto(Long id) {
        Produto entity = entityManager.find(Produto.class, id);
        entityManager.remove(entity);
    }
    
    public Produto atualizar(Produto produto) {
        Produto entity = this.find(produto.getIdProduto());
        entity = produto;
        return this.getEntityManager().merge(produto);
    }
    
    public void atualizarEstoque(VendasVO vo) {
        Produto entity = this.find(vo.getIdProduto());
        Integer newEstoque = entity.getQtdEstoque() - vo.getQtdProduto();
        entity.setQtdEstoque(newEstoque);
        this.getEntityManager().merge(entity);
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    
}
