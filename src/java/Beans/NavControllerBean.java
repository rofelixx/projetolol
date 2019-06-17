package Beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@Named("NavMB")
public class NavControllerBean implements Serializable {

    boolean isLogged = false;

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
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        if (viewId.contains("carrinhoDeCompra")) {
            return "";
        } else {
            return "carrinhoDeCompra.xhtml?faces-redirect=true";
        }
    }

    public String finalizarPedido() {
        return "finalizarPedido.xhtml";
    }

    public boolean isLogged() {
        return isLogged;
    }
}
