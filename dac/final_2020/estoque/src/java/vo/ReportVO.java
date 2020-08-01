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
public class ReportVO implements Comparable<ReportVO>{

    private Long idProduto;
    private String nomeProduto;
    private Integer qtdEstoque;
    private Integer qtdVendida;
    
    public ReportVO() {}
    
    public ReportVO(Long idProduto, String nomeProduto, Integer qtdEstoque, Integer qtdVendida) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.qtdEstoque = qtdEstoque;
        this.qtdVendida = qtdVendida;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getQtdVendida() {
        return qtdVendida;
    }

    public void setQtdVendida(Integer qtdVendida) {
        this.qtdVendida = qtdVendida;
    }
    
    @Override
    public int compareTo(ReportVO o) {
        return this.getQtdVendida().compareTo(o.getQtdVendida());
    }
    
}
