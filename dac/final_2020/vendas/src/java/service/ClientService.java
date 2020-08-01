/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.Singleton;

import dao.ClientDAO;
import entity.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import vo.ClientVO;

/**
 *
 * @author lucas
 */
@Singleton
public class ClientService {
    
    @Inject
    private ClientDAO clienteDAO;
    
    public List<ClientVO> findAll() {
        
        List<Cliente> clientes = clienteDAO.findAll();
        List<ClientVO> clientsVO = new ArrayList<>();
        
        clientes.forEach(c ->{
            ClientVO clientVO = new ClientVO(c);
            clientsVO.add(clientVO);
        });
        
        return clientsVO;
    }
    
    public Long saveClient(ClientVO vo) {
        Cliente entity = new Cliente(vo);
        return clienteDAO.save(entity);
    }
    
    public void deleteClient(Long id) {
        clienteDAO.deleteClient(id);
    }
    
    public ClientVO find(Long id) {
        Cliente entity = clienteDAO.find(id);
        ClientVO vo = new ClientVO(entity);
        return vo;
    }
    
    public ClientVO edit(ClientVO cliente) {
        Cliente entity = new Cliente(cliente);
        Cliente res = clienteDAO.atualizar(entity);
        return new ClientVO(res);
    }
    
    
}
