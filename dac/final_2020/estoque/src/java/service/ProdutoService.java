/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ProdutoDAO;
import entity.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Inject;
import vo.ProdutoVO;
import vo.VendasVO;

/**
 *
 * @author lucas
 */
@Singleton
public class ProdutoService {
    
    @Inject
    private ProdutoDAO produtoDAO;
    
    public List<ProdutoVO> findAll() {
        
        List<Produto> produtos = produtoDAO.findAll();
        List<ProdutoVO> produtosVO = new ArrayList<>();
        
        produtos.forEach(item -> {
           ProdutoVO produtoVO = new ProdutoVO(item);
           produtosVO.add(produtoVO);
        });
        
        return produtosVO;
        
    }
    
    public Long saveProduto(ProdutoVO vo) {
        Produto entity = new Produto(vo);
        return produtoDAO.save(entity);
    }
    
    public void deleteProduto(Long id) {
        produtoDAO.deleteProduto(id);
    }
    
    public ProdutoVO find(Long id) {
        Produto entity = produtoDAO.find(id);
        ProdutoVO vo = new ProdutoVO(entity);
        return vo;
    }
    
    public ProdutoVO edit(ProdutoVO produto) {
        Produto entity = new Produto(produto);
        Produto res = produtoDAO.atualizar(entity);
        return new ProdutoVO(res);
    }
    
    public VendasVO attQtdEstoque(VendasVO vo) {
        this.produtoDAO.atualizarEstoque(vo);
        return new VendasVO();
    }
    
}
