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
@Table(name = "Produto")
public class Produto implements java.io.Serializable{
    
    @Id
    private long codigo_produto_id;
    private long categoria_produto_id;
    private String nome;
    private String unidade_medida;
    private double preco;
    private int quantidade;
     
    public long getCodigo_produto_id(){
        return this.codigo_produto_id;
    }
    
    public void setCodigo_produto_id(long novoCodigo_produto_id){
        this.codigo_produto_id = novoCodigo_produto_id;
    }
    
    public long getCategoria_produto_id(){
        return this.categoria_produto_id;
    }
    
    public void setCategoria_produto_id(long novaCategoria_produto_id){
        this.categoria_produto_id = novaCategoria_produto_id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String novoNome){
        this.nome = novoNome;
    }
    
    public String getUnidade_medida(){
        return this.unidade_medida;
    }
    
    public void setUnidade_medida(String novaUnidade_medida){
        this.unidade_medida = novaUnidade_medida;
    }
    
    public double getPreco(){
        return this.preco;
    }
    
    public void setPreco(double novoPreco){
        this.preco = novoPreco;
    }
    
    public int getQuantidade(){
        return this.quantidade;
    }
    
    public void setQuantidade(int novaQuantidade){
        this.quantidade = novaQuantidade;
    }
}
