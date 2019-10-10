/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.LoginBean;
import bean.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class LoginDAO {
    
    Connection conn;
    
    public LoginBean findByUsernameAndPassword(String user, String password){
        
        conn = DatabaseConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        LoginBean loginBean = new LoginBean();
        try {
            ps = conn.prepareStatement(
                "select id_usuario, login_usuario from tb_usuario"
                + " where login_usuario = ? and senha_usuario = ?");
            ps.setString(1, user);
            ps.setString(2, password);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                loginBean.setLoginUsuario(rs.getString("login_usuario"));
                loginBean.setIdUsuario(rs.getInt("id_usuario"));
            }
            
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return loginBean;
        
    }
    
}
