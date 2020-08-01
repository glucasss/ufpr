/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author lucas
 */
public class ClientVO {
    
    private Long id;
    private String cpfCliente;
    private String nomeCliente;
    private String emailCliente;
    
    public ClientVO(){}
    
    public ClientVO(Long id, String cpfCliente, String nomeCliente, String emailCliente) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
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
    
}
