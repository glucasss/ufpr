/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lucas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_endereco")
public class Endereco implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(updatable=true, name="end_rua", nullable=false, length=30)
    private String rua;
    
    @Column(updatable=true, name="end_numero", nullable=false)
    private Integer numero;
    
    @OneToOne(mappedBy = "endereco")
    private Pessoa pessoa;
    
}
