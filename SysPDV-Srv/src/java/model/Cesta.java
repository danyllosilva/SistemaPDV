/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author zk
 */

@Entity
@Table(name = "Cesta")
public class Cesta {
    
    @Id
    private long cesta_id;
    private long codigo_produto_id;
    private long registro_vendas_id;
    private int quantidade;
}
