/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20190812_dac_hibernateexample;

import java.util.List;
import model.Endereco;
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
        p.setNome("lucas");
        Endereco c = new Endereco();
        c.setRua("rua teste");
        c.setNumero(100);
        
        p.setEndereco(c);
        c.setPessoa(p);
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        session.save(p);
        session.getTransaction().commit();
        
        session.close();
        
        NewHibernateUtil.getSessionFactory().close();
       
    }
    
}
