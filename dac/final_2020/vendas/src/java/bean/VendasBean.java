/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
import org.primefaces.PrimeFaces;
import vo.ClientVO;
import vo.ProdutoVO;
import vo.QtdProdVO;
import vo.VendasVO;

/**
 *
 * @author lucas
 */
@ManagedBean
@ViewScoped
public class VendasBean {
    
    private Long id;
    private Long idCliente;
    private String nomeCliente;
    private String dtVenda;
    private Long idProduto;
    private Integer qtdVendida;
    
    public VendasBean() {}

    public void setId(Long id) {
        this.id = id;
    }
    
    public ArrayList vendasListFromDB;
    public ArrayList produtosListFromDB;
    public ArrayList reportListFromDB;
    
    @PostConstruct
    public void init() {
        Client c = ClientBuilder.newClient();
        Response r = c.target("http://localhost:8080/vendas/webresources/vendas")
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        List<VendasVO> lista = r.readEntity(
                new GenericType<List<VendasVO>>(){}
        );
        
        vendasListFromDB = new ArrayList();
        for(int i=0; i < lista.size(); i++) {
            VendasVO v = new VendasVO();
            v.setId(lista.get(i).getId());
            v.setClientVO(lista.get(i).getClientVO());
            v.setIdProduto(lista.get(i).getIdProduto());
            v.setDtVenda(lista.get(i).getDtVenda());
            v.setQtdProduto(lista.get(i).getQtdProduto());
            
            vendasListFromDB.add(v);
        }
        
    }
    
    public ArrayList vendasList() {
        return vendasListFromDB;
    }
    
    public void getProdutos() {
        Client c = ClientBuilder.newClient();
        Response r = c.target("http://localhost:33977/estoque/webresources/produto")
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        List<ProdutoVO> lista = r.readEntity(
                new GenericType<List<ProdutoVO>>(){}
        );
        
        produtosListFromDB = new ArrayList();
        
        for(int i=0; i<lista.size(); i++) {
            ProdutoVO v = new ProdutoVO();
            v.setId(lista.get(i).getId());
            v.setNome(lista.get(i).getNome());
            v.setQtdEstoque(lista.get(i).getQtdEstoque());
            produtosListFromDB.add(v);
        }
        
    }
    
    public ArrayList produtosList() {
        return produtosListFromDB;
    }
    
    public String realizaVenda(VendasBean newVendasObj) {
        
        Client c = ClientBuilder.newClient();
        
        ClientVO cvo = c.target("http://localhost:8080/vendas/webresources/client/find/"+newVendasObj.getIdCliente())
            .request(MediaType.APPLICATION_JSON)
            .get(ClientVO.class);
        
        QtdProdVO retorno = c.target("http://localhost:33977/estoque/webresources/produto/qtdestoque/"+idProduto)
                .request(MediaType.APPLICATION_JSON)
                .get(QtdProdVO.class);
        
        int estoque = retorno.getQtd();
        
        VendasVO inp = new VendasVO();
        inp.setId(null);
        inp.setClientVO(cvo);
        inp.setIdProduto(newVendasObj.getIdProduto());
        inp.setDtVenda(newVendasObj.getDtVenda());
        inp.setQtdProduto(newVendasObj.getQtdVendida());
        
        if(newVendasObj.getQtdVendida() > estoque) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Venda não realizada", "A quantidade disponível em estoque é: 10"));
            return "";
        } else {
        
            VendasVO vo = c.target("http://localhost:8080/vendas/webresources/vendas")
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(inp), VendasVO.class);
            
            VendasVO vo2 = c.target("http://localhost:33977/estoque/webresources/produto/qtdestoque/att")
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(inp), VendasVO.class);

            return "sellList.xhtml?faces-redirect=true";
        }
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setDtVenda(String dtVenda) {
        this.dtVenda = dtVenda;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public void setQtdVendida(Integer qtdVendida) {
        this.qtdVendida = qtdVendida;
    }

    public Long getId() {
        return id;
    }
    
    public Long getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getDtVenda() {
        return dtVenda;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQtdVendida() {
        return qtdVendida;
    }
    
}
