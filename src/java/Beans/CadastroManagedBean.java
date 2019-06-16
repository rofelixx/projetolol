package Beans;

import Classe.Cliente;
import Classe.Endereco;
import Facade.FacadeCadastro;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@SessionScoped
@Named("CadastroMB")
public class CadastroManagedBean implements Serializable {

    private Cliente cliente = new Cliente();
    private Endereco endereco = new Endereco();
    private FacadeCadastro facade = new FacadeCadastro();

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

    public String buscarCep() {
        return facade.buscarCep(endereco);
    }
}
