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
public class Cesta implements java.io.Serializable{
    
    @Id
    private long cesta_id;
    private long codigo_produto_id;
    private long registro_vendas_id;
    private int quantidade;

    /**
     * @return the cesta_id
     */
    public long getCesta_id() {
        return cesta_id;
    }

    /**
     * @return the codigo_produto_id
     */
    public long getCodigo_produto_id() {
        return codigo_produto_id;
    }

    /**
     * @return the registro_vendas_id
     */
    public long getRegistro_vendas_id() {
        return registro_vendas_id;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
