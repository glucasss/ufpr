/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import vo.VendasVO;

/**
 *
 * @author lucas
 */
@Entity(name="vendas")
@Table(name="vendas")
public class Vendas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")  
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    
    @Column(name="id_produto")
    private Long idProduto;
    
    @Column(name="data")
    private Date dtCompra;
    
    @Column(name="qtd_produto")
    private Integer qtdProduto;
    
    public Vendas() {}

    public Vendas(VendasVO vo) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dtUtil = null;
        
        try{
            dtUtil = format.parse(vo.getDtVenda());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        java.sql.Date dateSql = new java.sql.Date(dtUtil.getTime());
        
        this.id = vo.getId();
        this.cliente = new Cliente(vo.getClientVO());
        this.idProduto = vo.getIdProduto();
        this.dtCompra = dateSql;
        this.qtdProduto = vo.getQtdProduto();
        
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }
    
    public Long getIdProduto() {
        return this.idProduto;
    }
    
    public void setDtCompra(Date data) {
        this.dtCompra = data;
    }
    
    public Date getDtCompra() {
        return this.dtCompra;
    }
    
    public void setQtdProduto(Integer qtdProduto) {
        this.qtdProduto = qtdProduto;
    }
    
    public Integer getQtdProduto() {
        return qtdProduto;
    }
    
}
