package Classe;
// Generated May 30, 2019 4:07:37 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roupa"
    ,catalog="projetolol"
)
public class Roupa  implements java.io.Serializable {


     private Integer id;
     private int tipo;
     private String descricao;
     private String observacao;
     private double preco;
     private int prazo;
     private String image;
     private Set<Itempedido> itempedidos = new HashSet<Itempedido>(0);

    public Roupa() {
    }

	
    public Roupa(int tipo, String descricao, double preco, int prazo) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.preco = preco;
        this.prazo = prazo;
    }
    public Roupa(int tipo, String descricao, String observacao, double preco, int prazo, String image, Set<Itempedido> itempedidos) {
       this.tipo = tipo;
       this.descricao = descricao;
       this.observacao = observacao;
       this.preco = preco;
       this.prazo = prazo;
       this.image = image;
       this.itempedidos = itempedidos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="Id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="Tipo", nullable=false)
    public int getTipo() {
        return this.tipo;
    }
    
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    
    @Column(name="Descricao", nullable=false, length=500)
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    @Column(name="Observacao", length=45)
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    
    @Column(name="Preco", nullable=false, precision=22, scale=0)
    public double getPreco() {
        return this.preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }

    
    @Column(name="Prazo", nullable=false)
    public int getPrazo() {
        return this.prazo;
    }
    
    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    
    @Column(name="Image", length=500)
    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="roupa")
    public Set<Itempedido> getItempedidos() {
        return this.itempedidos;
    }
    
    public void setItempedidos(Set<Itempedido> itempedidos) {
        this.itempedidos = itempedidos;
    }




}


