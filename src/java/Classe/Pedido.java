package Classe;
// Generated May 30, 2019 4:07:37 PM by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pedido",
         catalog = "projetolol"
)
public class Pedido implements java.io.Serializable {

    private Integer id;
    private Cliente cliente;
    private int status;
    private Date dataPedido;
    private double valorTotal;
    private int prazo;

    private List<Itempedido> itempedidos = new ArrayList<Itempedido>();

    public Pedido() {
    }

    public Pedido(Cliente cliente, int status, Date dataPedido, double valorTotal) {
        this.cliente = cliente;
        this.status = status;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }

    public Pedido(Cliente cliente, int status, Date dataPedido, double valorTotal, List<Itempedido> itempedidos) {
        this.cliente = cliente;
        this.status = status;
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
        this.itempedidos = itempedidos;
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
    
    @Column(name = "Prazo", nullable = false)
    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ClienteId", nullable = false)
    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Column(name = "Status", nullable = false)
    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DataPedido", nullable = false, length = 19)
    public Date getDataPedido() {
        return this.dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    @Column(name = "ValorTotal", nullable = false, precision = 22, scale = 0)
    public double getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.ALL)
    public List<Itempedido> getItempedidos() {
        return this.itempedidos;
    }

    public void setItempedidos(List<Itempedido> itempedidos) {
        this.itempedidos = itempedidos;
    }
}
