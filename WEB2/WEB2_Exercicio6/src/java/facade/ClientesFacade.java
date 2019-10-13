/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import bean.Cliente;
import dao.ClientesDAO;
import java.util.List;

/**
 *
 * @author lucas
 */
public class ClientesFacade {
    
    ClientesDAO clientesDao = new ClientesDAO();
    
    public List<Cliente> findAll(){
        return clientesDao.findAll();
    }
    
    public Cliente getById(int idCliente){
        Cliente cliente = new Cliente();
        cliente = clientesDao.findById(idCliente);
        return cliente;
    }
    
    public void delete(int idCliente){
        clientesDao.delete(idCliente);
    }
    
    public void update(Cliente c){
        clientesDao.update(c);
    }
    
    public void save(Cliente c){
        clientesDao.save(c);
    }
}
