package Classe;
// Generated May 30, 2019 4:07:37 PM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="itempedido"
    ,catalog="projetolol"
)
public class Itempedido  implements java.io.Serializable {


     private Integer id;
     private Pedido pedido;
     private Roupa roupa;
     private int quantidade;
     private double valorUnitario;
     private int prazo;

    public Itempedido() {
    }

    public Itempedido(Pedido pedido, Roupa roupa, int quantidade, double valorUnitario, int prazo) {
       this.pedido = pedido;
       this.roupa = roupa;
       this.quantidade = quantidade;
       this.valorUnitario = valorUnitario;
       this.prazo = prazo;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="Id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="PedidoId", nullable=false)
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RoupaId", nullable=false)
    public Roupa getRoupa() {
        return this.roupa;
    }
    
    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }

    
    @Column(name="Quantidade", nullable=false)
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
    @Column(name="ValorUnitario", nullable=false, precision=22, scale=0)
    public double getValorUnitario() {
        return this.valorUnitario;
    }
    
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    
    @Column(name="Prazo", nullable=false)
    public int getPrazo() {
        return this.prazo;
    }
    
    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }
}


