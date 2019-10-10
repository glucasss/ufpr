/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
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
public class Professor {
    
    @Id
    @Column(name="id")
    private long id;
    
    @Column(name="nome")
    private String nome;
    
    @Column(name="dataadmissao")
    private Date dataAdmissao;
    
}
