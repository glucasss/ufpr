/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import entity.Vendas;
import java.text.SimpleDateFormat;

/**
 *
 * @author lucas
 */
public class VendasVO {
    
    private Long id;
    private ClientVO clientVO;
    private Long idProduto;
    private String dtVenda;
    private Integer qtdProduto;
    
    public VendasVO(){}

    public Long getId() {
        return id;
    }

    public ClientVO getClientVO() {
        return clientVO;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getDtVenda() {
        return dtVenda;
    }

    public Integer getQtdProduto() {
        return qtdProduto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientVO(ClientVO clientVO) {
        this.clientVO = clientVO;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public void setDtVenda(String dtVenda) {
        this.dtVenda = dtVenda;
    }

    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }
    
    public VendasVO(Vendas vendas) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        
        this.id = vendas.getId();
        this.clientVO = new ClientVO(vendas.getCliente());
        this.idProduto = vendas.getIdProduto();
        this.dtVenda = fmt.format(vendas.getDtCompra());
        this.qtdProduto = vendas.getQtdProduto();
    }
}
