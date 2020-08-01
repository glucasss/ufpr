/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import entity.Produto;

/**
 *
 * @author lucas
 */
public class ProdutoVO implements Comparable<ProdutoVO>{
    
    private Long id;
    private String nome;
    private String dsc;
    private Integer qtdEstoque;
    
    public ProdutoVO(){}
    
    public ProdutoVO(Long id, String nome, String dsc, Integer qtdEstoque) {
        this.id = id;
        this.nome = nome;
        this.dsc = dsc;
        this.qtdEstoque = qtdEstoque;
    }
    
    public ProdutoVO(Produto p) {
        this.id = p.getIdProduto();
        this.nome = p.getNomeProduto();
        this.dsc = p.getDesc();
        this.qtdEstoque = p.getQtdEstoque();
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

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }
    
    @Override
    public int compareTo(ProdutoVO o) {
        return this.getId().compareTo(o.getId());
    }
}
