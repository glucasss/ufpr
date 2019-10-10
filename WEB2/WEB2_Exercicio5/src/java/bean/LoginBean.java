/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lucas
 */
@Data
@NoArgsConstructor
public class LoginBean {
    private String loginUsuario;
    private int idUsuario;
}
