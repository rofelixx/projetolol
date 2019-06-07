package Beans;

import Classe.Cliente;
import Classe.Endereco;
import Facade.FacadeLogin;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "LoginMB")
@SessionScoped
public class LoginManagedBean {

    public Cliente cliente = new Cliente();
    public Endereco endereco = new Endereco();
    public String confirmarSenha = "";
    public FacadeLogin facade = new FacadeLogin();

    public String autenticar() throws Exception {
        return facade.autenticar(cliente);
    }

    public boolean isPerfilCliente() {
        return facade.isPerfilCliente();
    }

    public String logout() {
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        sessao.invalidate();
        return "index";
    }

    public String signUp() {
        return "cadastro";
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
