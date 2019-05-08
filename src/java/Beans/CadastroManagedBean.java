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

@ManagedBean(name = "CadastroMB")
@SessionScoped

public class CadastroManagedBean {

    public Cliente cliente = new Cliente();
    public Cliente retorno = new Cliente();
    public Endereco endereco = new Endereco();
    public ClienteDao dao = new ClienteDao();
    public boolean isLogged = false;
    public String confirmarSenha = "";

        public String cadastrar() {
        String url = "";
        retorno = dao.getClienteByEmail(cliente.getEmail());
        if (retorno != null) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage("Email já cadastrado!"));

            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getFlash().setKeepMessages(true);
        } else {
            dao.addNewCliente(cliente, endereco);
            url = "login";
        }
        return url;
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
