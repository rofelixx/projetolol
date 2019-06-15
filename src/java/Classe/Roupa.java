package Classe;
// Generated May 30, 2019 4:07:37 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roupa",
        catalog = "projetolol"
)
public class Roupa implements java.io.Serializable {

    private Integer id;
    private Integer tipo;
    private String descricao;
    private String observacao;
    private double preco;
    private Integer prazo;
    private String image;

    public Roupa() {
    }

    public Roupa(Integer tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public Roupa(Integer tipo, String descricao, double preco, Integer prazo) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
        this.prazo = prazo;
    }

    public Roupa(Integer tipo, String descricao, String observacao, double preco, Integer prazo, String image) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.observacao = observacao;
        this.preco = preco;
        this.prazo = prazo;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "Tipo", nullable = false)
    public Integer getTipo() {
        return this.tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Column(name = "Descricao", nullable = false, length = 500)
    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(name = "Observacao", length = 45)
    public String getObservacao() {
        return this.observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Column(name = "Preco", nullable = false, precision = 22, scale = 0)
    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Column(name = "Prazo", nullable = false)
    public Integer getPrazo() {
        return this.prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    @Column(name = "Image", length = 500)
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
