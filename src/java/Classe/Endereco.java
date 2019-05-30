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
@Table(name="endereco"
    ,catalog="projetolol"
)
public class Endereco  implements java.io.Serializable {


     private Integer id;
     private String cep;
     private String bairro;
     private String rua;
     private String numero;
     private String observacao;
     private Set<Cliente> clientes = new HashSet<Cliente>(0);

    public Endereco() {
    }

    public Endereco(String cep, String bairro, String rua, String numero, String observacao, Set<Cliente> clientes) {
       this.cep = cep;
       this.bairro = bairro;
       this.rua = rua;
       this.numero = numero;
       this.observacao = observacao;
       this.clientes = clientes;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="Id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="Cep", length=45)
    public String getCep() {
        return this.cep;
    }
    
    public void setCep(String cep) {
        this.cep = cep;
    }

    
    @Column(name="Bairro", length=45)
    public String getBairro() {
        return this.bairro;
    }
    
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    
    @Column(name="Rua", length=45)
    public String getRua() {
        return this.rua;
    }
    
    public void setRua(String rua) {
        this.rua = rua;
    }

    
    @Column(name="Numero", length=45)
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }

    
    @Column(name="Observacao", length=45)
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="endereco")
    public Set<Cliente> getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }
}


