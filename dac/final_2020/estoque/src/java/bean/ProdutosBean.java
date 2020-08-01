/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import vo.ProdutoVO;
import javax.ws.rs.client.Entity;
import vo.QtdProdVO;
import vo.ReportVO;

/**
 *
 * @author lucas
 */
@ManagedBean
@RequestScoped
public class ProdutosBean {
    
    private Long id;   
    private String nome;
    private String desc;
    private Integer qtdEstoque;
    
    public ProdutosBean(){}
    
    public List<ProdutoVO> produtosListFromDB;
    public List<ReportVO> reportListFromDB;
    
    @PostConstruct
    public void init() {
        Client c = ClientBuilder.newClient();
        Response r = c.target("http://localhost:33977/estoque/webresources/produto")
                .request(MediaType.APPLICATION_JSON)
                .get();
        
        List<ProdutoVO> lista = r.readEntity(
                new GenericType<List<ProdutoVO>>(){}
        );
        
        produtosListFromDB = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++) {
            ProdutoVO v = new ProdutoVO();
            v.setId(lista.get(i).getId());
            v.setNome(lista.get(i).getNome());
            v.setQtdEstoque(lista.get(i).getQtdEstoque());
            produtosListFromDB.add(v);
        }
    }
    
    public List<ProdutoVO> produtosList() {
        Collections.sort(produtosListFromDB);
        return produtosListFromDB;
    }
    
    public List<ReportVO> reportList() {
        return reportListFromDB;
    }
    
    public void getReport() {
        reportListFromDB = new ArrayList<>();
        
        produtosListFromDB.forEach(item -> {
            ReportVO rVo = new ReportVO();
            rVo.setIdProduto(item.getId());
            rVo.setNomeProduto(item.getNome());
            rVo.setQtdEstoque(item.getQtdEstoque());
            rVo.setQtdVendida(getQtdVendida(item.getId()));
            reportListFromDB.add(rVo);
        });
        
        Collections.sort(reportListFromDB, Collections.reverseOrder());
        
    }
    
    public Integer getQtdVendida(Long idProduto) {
        Client c = ClientBuilder.newClient();
        
        QtdProdVO retorno = c.target("http://localhost:8080/vendas/webresources/vendas/qtdvendas/"+idProduto)
                .request(MediaType.APPLICATION_JSON)
                .get(QtdProdVO.class);
        
        return retorno.getQtd();
    }
    
    public String saveProdutoDetails(ProdutosBean newProdObj) {
        ProdutoVO inp = new ProdutoVO();
        inp.setDsc(newProdObj.getDesc());
        inp.setNome(newProdObj.getNome());
        inp.setQtdEstoque(newProdObj.getQtdEstoque());
        
        Client c = ClientBuilder.newClient();
        ProdutoVO vo = c.target("http://localhost:33977/estoque/webresources/produto")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(inp), ProdutoVO.class);
        
        return "produtosList.xhtml?faces-redirect=true";
    }
    
    public String editProdutoRecord(Long produtoId) {
        Client c = ClientBuilder.newClient();
        ProdutoVO vo = c.target("http://localhost:33977/estoque/webresources/produto/find/"+produtoId)
                .request(MediaType.APPLICATION_JSON)
                .get(ProdutoVO.class);
        
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        ProdutosBean editRecord = new ProdutosBean();
        editRecord.setId(vo.getId());
        editRecord.setNome(vo.getNome());
        editRecord.setDesc(vo.getDsc());
        editRecord.setQtdEstoque(vo.getQtdEstoque());
        sessionMapObj.put("editRecordObj", editRecord);
        
        return "produtosEdit.xhtml?faces-redirect=true";
    }
    
    public String updateProdutoDetails(ProdutosBean updateProdObj) {
        
        ProdutoVO inp = new ProdutoVO();
        inp.setId(updateProdObj.getId());
        inp.setNome(updateProdObj.getNome());
        inp.setDsc(updateProdObj.getDesc());
        inp.setQtdEstoque(updateProdObj.getQtdEstoque());
        
        Client c = ClientBuilder.newClient();
        ProdutoVO vo = c.target("http://localhost:33977/estoque/webresources/produto/edit")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(inp), ProdutoVO.class);
                
        return "produtosList.xhtml?faces-redirect=true";
    }
    
    public String deleteProdutoRecord(int produtoId) {
        Client c = ClientBuilder.newClient();
        ProdutoVO inp = new ProdutoVO();
        inp.setId(Long.valueOf(produtoId));
        
        ProdutoVO vo = c.target("http://localhost:33977/estoque/webresources/produto/del")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(inp), ProdutoVO.class);
        
        return "produtosList.xhtml?faces-redirect=true";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    
    
    
}
