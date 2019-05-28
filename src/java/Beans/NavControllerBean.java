package Beans;

import Classe.Cliente;
import Classe.Endereco;
import DAO.ClienteDao;
import DAO.Criptografia;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "NavMB")
@SessionScoped

public class NavControllerBean {

    public boolean isLogged = false;

    public String home() {
        return "home.xhtml";
    }

    public String login() {
        return "login.xhtml";
    }

    public String index() {
        return "index.xhtml";
    }

    public String signUp() {
        return "cadastro.xhtml";
    }

    public String novoPedido() {
        return "novoPedido.xhtml";
    }

    public String pedidos() {
        return "pedidos.xhtml";
    }

    public String gerenciarPedidos() {
        return "gerenciarPedidos.xhtml";
    }

    public String gerenciarClientes() {
        return "gerenciarClientes.xhtml";
    }

    public String gerenciarRoupas() {
        return "gerenciarRoupas.xhtml";
    }

    public String cadatroRoupas() {
        return "cadastroRoupas.xhtml";
    }

    public String carrinhoDeCompra() {
        return "carrinhoDeCompra.xhtml";
    }

    public boolean isLogged() {
        return isLogged;
    }
}
