package Classe;
// Generated May 30, 2019 4:07:37 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
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
@Table(name="cliente"
    ,catalog="projetolol"
)
public class Cliente  implements java.io.Serializable {


     private Integer id;
     private Endereco endereco;
     private String nome;
     private String email;
     private String cpf;
     private String sexo;
     private String senha;
     private Date dtNascimento;
     private int perfil;
     private Set<Pedido> pedidos = new HashSet<Pedido>(0);

    public Cliente() {
    }

	
    public Cliente(Endereco endereco, String nome, String email, String cpf, String sexo, String senha, Date dtNascimento, int perfil) {
        this.endereco = endereco;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.sexo = sexo;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.perfil = perfil;
    }
    public Cliente(Endereco endereco, String nome, String email, String cpf, String sexo, String senha, Date dtNascimento, int perfil, Set<Pedido> pedidos) {
       this.endereco = endereco;
       this.nome = nome;
       this.email = email;
       this.cpf = cpf;
       this.sexo = sexo;
       this.senha = senha;
       this.dtNascimento = dtNascimento;
       this.perfil = perfil;
       this.pedidos = pedidos;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="Id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="EnderecoId", nullable=false)
    public Endereco getEndereco() {
        return this.endereco;
    }
    
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    
    @Column(name="Nome", nullable=false, length=45)
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Column(name="Email", nullable=false, length=45)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="CPF", nullable=false, length=45)
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    @Column(name="Sexo", nullable=false, length=45)
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    
    @Column(name="Senha", nullable=false, length=45)
    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="DtNascimento", nullable=false, length=10)
    public Date getDtNascimento() {
        return this.dtNascimento;
    }
    
    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    
    @Column(name="Perfil", nullable=false)
    public int getPerfil() {
        return this.perfil;
    }
    
    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="cliente")
    public Set<Pedido> getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}


