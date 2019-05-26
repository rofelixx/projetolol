package Classe;
// Generated 04/05/2019 19:26:27 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Roupa generated by hbm2java
 */
public class Roupa implements java.io.Serializable {

    private Integer id;
    private Integer tipo;
    private String descricao;
    private String observacao;
    private double preco;
    private int prazo;
    private String image;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    private Set itempedidos = new HashSet(0);

    public Roupa() {
    }

    public Roupa(Integer tipo, Double preco, int prazo) {
        this.tipo = tipo;
        this.preco = preco;
        this.prazo = prazo;
    }

    public Roupa(Integer tipo, String observacao, Double preco, int prazo, Set itempedidos) {
        this.tipo = tipo;
        this.observacao = observacao;
        this.preco = preco;
        this.prazo = prazo;
        this.itempedidos = itempedidos;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public int getPrazo() {
        return this.prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public Set getItempedidos() {
        return this.itempedidos;
    }

    public void setItempedidos(Set itempedidos) {
        this.itempedidos = itempedidos;
    }
}
