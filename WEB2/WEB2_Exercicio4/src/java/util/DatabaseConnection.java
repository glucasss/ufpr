/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucas
 */
public class DatabaseConnection {
    
    private static final String dsn = "jdbc:mysql://localhost:3306/web2_ex3";
    private static final String username = "root";
    private static final String password = "root";
    
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dsn, username, password);
        }catch(ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
        
    }
    
    
}
