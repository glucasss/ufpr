/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lucas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    private String nome;
    private String login;
    private String senha;
    
}
