/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
import vo.ClientVO;


/**
 *
 * @author lucas
 */
@ManagedBean
@RequestScoped
public class ClientBean {
    
    private Long id;
    private String cpfClient;
    private String nomeClient;
    private String emailClient;
    
    public ClientBean() {
    }
    
    public ArrayList clientsListFromDB;
    
    @PostConstruct
    public void init() {
        Client c = ClientBuilder.newClient();
        Response r = c.target("http://localhost:8080/vendas/webresources/client")
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        List<ClientVO> lista = r.readEntity(
          new GenericType<List<ClientVO>>() {}      
        );
        
        clientsListFromDB = new ArrayList();
        
        for(int i=0; i < lista.size(); i++) {
            ClientVO v = new ClientVO();
            v.setId(lista.get(i).getId());
            v.setNomeCliente(lista.get(i).getNomeCliente());
            v.setCpfCliente(lista.get(i).getCpfCliente());
            v.setEmailCliente(lista.get(i).getEmailCliente());
            clientsListFromDB.add(v);
        }
        
    }
    
    public ArrayList clientList() {
        return clientsListFromDB;
    }
    
     
    public String saveClientDetails(ClientBean newClientObj) {
        ClientVO inp = new ClientVO();
        inp.setId(null);
        inp.setNomeCliente(newClientObj.getNomeClient());
        inp.setCpfCliente(newClientObj.getCpfClient());
        inp.setEmailCliente(newClientObj.getEmailClient());
        
        Client c = ClientBuilder.newClient();
        ClientVO vo = c.target("http://localhost:8080/vendas/webresources/client")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(inp), ClientVO.class);
        
        return "clientList.xhtml?faces-redirect=true";
    }
    
    public String deleteClientRecord(int clientId) {
        Client c = ClientBuilder.newClient();
        ClientVO inp = new ClientVO();
        inp.setId(Long.valueOf(clientId));
        
        ClientVO vo = c.target("http://localhost:8080/vendas/webresources/client/del")
            .request(MediaType.APPLICATION_JSON)
            .put(Entity.json(inp), ClientVO.class);
        
        return "clientList.xhtml?faces-redirect=true";
    }
     
    public String editClientRecord(Long clientId) {
        Client c = ClientBuilder.newClient();
        ClientVO vo = c.target("http://localhost:8080/vendas/webresources/client/find/"+clientId)
                .request(MediaType.APPLICATION_JSON)
                .get(ClientVO.class);
        
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        ClientBean editRecord = new ClientBean();
        editRecord.setId(vo.getId());
        editRecord.setCpfClient(vo.getCpfCliente());
        editRecord.setNomeClient(vo.getNomeCliente());
        editRecord.setEmailClient(vo.getEmailCliente());
        sessionMapObj.put("editRecordObj", editRecord);
        
        return "clientEdit.xhtml?faces-redirect=true";
    }
     
    public String updateClientDetails(ClientBean updateClientObj) {
        
        ClientVO inp = new ClientVO();
        inp.setId(updateClientObj.getId());
        inp.setNomeCliente(updateClientObj.getNomeClient());
        inp.setCpfCliente(updateClientObj.getCpfClient());
        inp.setEmailCliente(updateClientObj.getEmailClient());
        
        Client c = ClientBuilder.newClient();
        ClientVO vo = c.target("http://localhost:8080/vendas/webresources/client/edit")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(inp), ClientVO.class);
        
        return "clientList.xhtml?faces-redirect=true";
    }
    
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setCpfClient(String cpfClient) {
        this.cpfClient = cpfClient;
    }
    
    public String getCpfClient() {
        return this.cpfClient;
    }
    
    public void setNomeClient(String nomeClient) {
        this.nomeClient = nomeClient;
    }
    
    public String getNomeClient() {
        return this.nomeClient;
    }
    
    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }
    
    public String getEmailClient() {
        return this.emailClient;
    }
    
}
