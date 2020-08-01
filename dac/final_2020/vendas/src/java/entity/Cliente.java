/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import vo.ClientVO;
/**
 *
 * @author lucas
 */
@Entity(name="clientes")
@Table(name="clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="cpf_cliente")
    private String cpfCliente;
    
    @Column(name="nome_cliente")
    private String nomeCliente;
    
    @Column(name="email_cliente")
    private String emailCliente;
    
    public Cliente() {
        
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
    
    public String getCpfCliente() {
        return this.cpfCliente;
    }
    
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    public String getNomeCliente() {
        return this.nomeCliente;
    }
    
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    
    public String getEmailCliente() {
        return this.emailCliente;
    }
    
    public Cliente(ClientVO vo) {
        this.id = vo.getId();
        this.cpfCliente = vo.getCpfCliente();
        this.nomeCliente = vo.getNomeCliente();
        this.emailCliente = vo.getEmailCliente();
    }
    
}