/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.Date;
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
public class Cliente {
    
    private int idCliente;
    private String cpfCliente;
    private String nomeCliente;
    private String emailCliente;
    private Date dataCliente;
    private String ruaCliente;
    private Integer numCliente;
    private String cepCliente;
    private String cidadeCliente;
    private String ufCliente;
    
}
