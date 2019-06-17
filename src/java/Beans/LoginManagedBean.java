package Beans;

import Classe.Cliente;
import Classe.Endereco;
import Facade.FacadeLogin;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@SessionScoped
@Named("LoginMB")
public class LoginManagedBean implements Serializable {

    Cliente cliente = new Cliente();
    Endereco endereco = new Endereco();
    String confirmarSenha = "";
    private FacadeLogin facade = new FacadeLogin();
    NavControllerBean nav = new NavControllerBean();

    public String autenticar() throws Exception {
        return facade.autenticar(cliente);
    }

    public boolean isPerfilCliente() {
        return facade.isPerfilCliente();
    }

    public void logout() {
        facade.logout();
    }

    public String signUp() {
        return nav.signUp();
    }

    public boolean isLogged() {
        return facade.isLogged();
    }

    public String getconfirmarSenha() {
        return confirmarSenha;
    }

    public void setEndereco(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
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

    public void setUsuario(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getPerfil() {
        return this.cliente.getPerfil();
    }
}
