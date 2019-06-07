/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Classe.Cliente;
import Classe.Endereco;
import DAO.ClienteDao;
import DAO.Criptografia;
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

    public String autenticar(Cliente cliente) throws Exception {
        retorno = dao.getClienteByEmail(cliente.getEmail());
        if (retorno != null && cliente.getEmail().equals(retorno.getEmail()) && comparaSenha(cliente)) {
            cliente = retorno;
            isLogged = true;
            isCliente = retorno.getPerfil() != 1;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Logado com sucesso"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", cliente);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ItemsCarrinho", 0);
            return "home";
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
}
