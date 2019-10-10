/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.DatabaseConnection;

/**
 *
 * @author lucas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesDAO {
    
    Connection conn;
    
    public List<Cliente> findAll(){
        List<Cliente> clientes = new ArrayList<>();
        
        conn = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            ps = conn.prepareStatement("select * from tb_cliente");
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setCpfCliente(rs.getString("cpf_cliente"));
                c.setNomeCliente(rs.getString("nome_cliente"));
                c.setEmailCliente(rs.getString("email_cliente"));
                c.setDataCliente(rs.getDate("data_cliente"));
                c.setRuaCliente(rs.getString("rua_cliente"));
                c.setNumCliente(rs.getInt("nr_cliente"));
                c.setCepCliente(rs.getString("cpf_cliente"));
                c.setCidadeCliente(rs.getString("cidade_cliente"));
                c.setUfCliente(rs.getString("uf_cliente"));
                clientes.add(c);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return clientes;
    }
    
    public Cliente findById(int id){
        conn = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        Cliente cliente = new Cliente();
        
        try{
            ps = conn.prepareStatement("select * from tb_cliente where id_cliente = ?");
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setCpfCliente(rs.getString("cpf_cliente"));
                cliente.setNomeCliente(rs.getString("nome_cliente"));
                cliente.setEmailCliente(rs.getString("email_cliente"));
                cliente.setDataCliente(rs.getDate("data_cliente"));
                cliente.setRuaCliente(rs.getString("rua_cliente"));
                cliente.setNumCliente(rs.getInt("nr_cliente"));
                cliente.setCepCliente(rs.getString("cep_cliente"));
                cliente.setCidadeCliente(rs.getString("cidade_cliente"));
                cliente.setUfCliente(rs.getString("uf_cliente"));
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cliente;
    }
    
    public int delete(int id){
        int status = 0;
        
        conn = DatabaseConnection.getConnection();
        PreparedStatement ps;
        
        try{
            ps = conn.prepareStatement("delete from tb_cliente where id_cliente = ?");
            ps.setInt(1, id);
            
            status = ps.executeUpdate();
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
        
    }
    
    public int update(Cliente c){
        int status = 0;
        
        conn = DatabaseConnection.getConnection();
        PreparedStatement ps;
        
        try{
            ps = conn.prepareStatement("update tb_cliente "
                    + "set cpf_cliente = ?,"
                    + "nome_cliente = ?,"
                    + "email_cliente = ?,"
                    + "data_cliente = ?,"
                    + "rua_cliente = ?,"
                    + "nr_cliente = ?,"
                    + "cep_cliente = ?,"
                    + "cidade_cliente = ?,"
                    + "uf_cliente = ? where id_cliente = ?");
            
            ps.setString(1, c.getCpfCliente());
            ps.setString(2, c.getNomeCliente());
            ps.setString(3, c.getEmailCliente());
            ps.setDate(4, new java.sql.Date(c.getDataCliente().getTime()));
            ps.setString(5, c.getRuaCliente());
            ps.setInt(6, c.getNumCliente());
            ps.setString(7, c.getCepCliente());
            ps.setString(8, c.getCidadeCliente());
            ps.setString(9, c.getUfCliente());
            ps.setInt(10, c.getIdCliente());
            
            status = ps.executeUpdate();
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }
    
    public int save(Cliente c){
        int status = 0;
        
        conn = DatabaseConnection.getConnection();
        PreparedStatement ps;
        
        try{
            ps = conn.prepareStatement(
            "insert into tb_cliente "
                    + "(cpf_cliente, nome_cliente, email_cliente, data_cliente, "
                    + "rua_cliente, nr_cliente, cep_cliente, cidade_cliente, uf_cliente) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            ps.setString(1, c.getCpfCliente());
            ps.setString(2, c.getNomeCliente());
            ps.setString(3, c.getEmailCliente());
            ps.setDate(4, new java.sql.Date(c.getDataCliente().getTime()));
            ps.setString(5, c.getRuaCliente());
            ps.setInt(6, c.getNumCliente());
            ps.setString(7, c.getCepCliente());
            ps.setString(8, c.getCidadeCliente());
            ps.setString(9, c.getUfCliente());
            
            status = ps.executeUpdate();
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }
    
}
