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
@Table(name = "CategoriaProduto")
public class CategoriaProduto implements java.io.Serializable{
    
    @Id
    private long categoria_produto_id;
    private String nome;
}
