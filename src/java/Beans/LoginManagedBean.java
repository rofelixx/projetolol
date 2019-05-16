package Beans;

import Classe.Cliente;
import Classe.Endereco;
import DAO.ClienteDao;
import DAO.Criptografia;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.application.StateManager;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import net.bootsfaces.utils.FacesMessages;

@ManagedBean(name = "LoginMB")
@SessionScoped
public class LoginManagedBean {

    public Cliente cliente = new Cliente();
    public Cliente retorno = new Cliente();
    public Endereco endereco = new Endereco();
    public ClienteDao dao = new ClienteDao();
    public boolean isLogged = false;
    public String confirmarSenha = "";
    public boolean isCliente = false;

    public String autenticar() throws Exception {
        retorno = dao.getClienteByEmail(cliente.getEmail());
        if (retorno != null && cliente.getEmail().equals(retorno.getEmail()) && comparaSenha()) {
            cliente = retorno;
            isLogged = true;
            isCliente = retorno.getPerfil() != 1;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Logado com sucesso"));
            return "home";
        } else {
            FacesMessages.error("Erro!", "Email ou senha incorretos");
            return "login";
        }
    }

    public boolean isPerfilCliente() {
        return this.isCliente;
    }

    public boolean comparaSenha() {
        String senhaNew = Criptografia.md5(cliente.getSenha());
        String senha = retorno.getSenha();
        if (senha.equals(senhaNew)) {
            return true;
        } else {
            return false;
        }
    }

    public String logout() {
        HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        sessao.invalidate();
        return "index";
    }

    public String cadastrar() {
        retorno = dao.getClienteByEmail(cliente.getEmail());
        if (retorno.getEmail() != null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Email já cadastrado!"));

            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().setKeepMessages(true);
        } else {
            return "login";
        }
        return "";
    }

    public String signUp() {
        return "cadastro";
    }

    public boolean isLogged() {
        return isLogged;
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
}
