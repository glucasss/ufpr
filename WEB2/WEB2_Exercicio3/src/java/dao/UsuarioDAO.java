/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDAO {
    
    Connection conn;
    
    public Usuario findByUsernameAndPassword(String user, String password){
        
        conn = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        Usuario usuario = new Usuario();
        try {
            ps = conn.prepareStatement(
                "select login_usuario, nome_usuario, senha_usuario from "
                + "tb_usuario where login_usuario = ? and senha_usuario = ?");
            ps.setString(1, user);
            ps.setString(2, password);
            
            System.out.println(ps);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                usuario.setLogin(rs.getString("login_usuario"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setSenha(rs.getString("senha_usuario"));
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
        
    }
    
    public int save(Usuario user){
        conn = DatabaseConnection.getConnection();
        PreparedStatement ps;
        int status = 0;
        
        try{
            ps = conn.prepareStatement("insert into tb_usuario (login_usuario, senha_usuario,"
                    + " nome_usuario) values (?, ?, ?);");
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getSenha());
            ps.setString(3, user.getNome());
            
            status = ps.executeUpdate();
            
            conn.close();
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }
    
    public List<Usuario> findAll(){
        conn = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        List<Usuario> usuarios = new ArrayList<>();
        
        try{
            ps = conn.prepareStatement("select login_usuario, senha_usuario,"
                    + " nome_usuario from tb_usuario;");
            rs = ps.executeQuery();
            
            while(rs.next()){
                Usuario user = new Usuario();
                user.setLogin(rs.getString(1));
                user.setSenha(rs.getString(2));
                user.setNome(rs.getString(3));
                usuarios.add(user);
            }
            
            conn.close();
            
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuarios;
    }
    
}
