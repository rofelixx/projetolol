package Classe;
// Generated 04/05/2019 19:26:27 by Hibernate Tools 4.3.1



/**
 * Itempedido generated by hbm2java
 */
public class Itempedido  implements java.io.Serializable {


     private Integer id;
     private Pedido pedido;
     private Roupa roupa;

    public Itempedido() {
    }

    public Itempedido(Pedido pedido, Roupa roupa) {
       this.pedido = pedido;
       this.roupa = roupa;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Roupa getRoupa() {
        return this.roupa;
    }
    
    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }




}


