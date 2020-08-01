/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20190812_dac_hibernateexample;

import java.util.List;
import model.Pessoa;
import org.hibernate.Query;
import org.hibernate.Session;
import util.NewHibernateUtil;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Pessoa p = new Pessoa();
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Query select = session.createQuery("from Pessoa");
        List<Pessoa> objetos = select.list();
        
        objetos.forEach(item ->{
            System.out.println("Id: " + item.getId() + " // Nome: " + item.getNome());
        });
        
       
    }
    
}
