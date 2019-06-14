package Beans;

import Classe.Cliente;
import Classe.Endereco;
import Facade.FacadeCadastro;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "CadastroMB")
@SessionScoped

public class CadastroManagedBean {

    public Cliente cliente = new Cliente();
    public Endereco endereco = new Endereco();
    public FacadeCadastro facade = new FacadeCadastro();

    public String cadastrar() {
        return facade.Cadastrar(cliente, endereco);
    }

    public String signUp() {
        return "cadastro";
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void reset() {
        this.cliente = new Cliente();
        this.endereco = new Endereco();
    }
}
