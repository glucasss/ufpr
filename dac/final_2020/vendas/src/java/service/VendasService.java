/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.VendasDAO;
import entity.Vendas;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
import javax.inject.Inject;
import vo.VendasVO;

/**
 *
 * @author lucas
 */
@Singleton
public class VendasService {
    
    @Inject
    private VendasDAO vendasDAO;
    
    public List<VendasVO> findAll() {
        List<Vendas> vendas = vendasDAO.findAll();
        List<VendasVO> vendasVO = new ArrayList<>();
        
        vendas.forEach(item -> {
            VendasVO vendaVO = new VendasVO(item);
            vendasVO.add(vendaVO);
        });
        
        return vendasVO;
        
    }
    
    public Long saveVenda(VendasVO vo) {
        Vendas entity = new Vendas(vo);
        return vendasDAO.save(entity);
    }
    
    public Integer getQtdVendas(int id) {
        return vendasDAO.findQtdVendido(id);
    }
    
}
