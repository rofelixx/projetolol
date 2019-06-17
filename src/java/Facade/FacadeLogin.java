/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Beans.NavControllerBean;
import Classe.Cliente;
import DAO.ClienteDao;
import DAO.Criptografia;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author ALM4CT
 */
public class FacadeLogin {

    public Cliente retorno = new Cliente();
    public ClienteDao dao = new ClienteDao();
    public String confirmarSenha = "";
    public boolean isCliente = false;
    public boolean isLogged = false;
    NavControllerBean nav = new NavControllerBean();

    public String autenticar(Cliente cliente) throws Exception {
        retorno = dao.getClienteByEmail(cliente.getEmail());
        if (retorno != null && cliente.getEmail().equals(retorno.getEmail()) && comparaSenha(cliente)) {
            cliente = retorno;
            isLogged = true;
            isCliente = retorno.getPerfil() != 1;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Logado com sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", cliente);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ItemsCarrinho", 0);
            return isCliente ? nav.pedidos() : nav.gerenciarPedidos();
        } else {
            FacesMessages.error("Erro!", "Email ou senha incorretos");
            return "login";
        }
    }

    public boolean comparaSenha(Cliente cliente) {
        String senhaNew = Criptografia.md5(cliente.getSenha());
        String senha = retorno.getSenha();
        if (senha.equals(senhaNew)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPerfilCliente() {
        return this.isCliente;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect(nav.index());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
