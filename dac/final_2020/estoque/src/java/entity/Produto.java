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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import vo.ProdutoVO;

/**
 *
 * @author lucas
 */

@Entity(name="produtos")
@Table(name="produtos")
@SequenceGenerator(name="seq", sequenceName="produtos_id_seq")
public class Produto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name="id")
    private Long idProduto;
    
    @Column(name="nome")
    private String nomeProduto;
    
    @Column(name="dsc")
    private String desc;
    
    @Column(name="qtdEstoque")
    private Integer qtdEstoque;
    
    public Produto(){}

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
    
    public Produto(ProdutoVO vo) {
        this.idProduto = vo.getId();
        this.desc = vo.getDsc();
        this.nomeProduto = vo.getNome();
        this.qtdEstoque = vo.getQtdEstoque();
    }
    
}
