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
import net.bootsfaces.utils.FacesMessages;

@ManagedBean(name = "CadastroMB")
@SessionScoped

public class CadastroManagedBean {

    public Cliente cliente = new Cliente();
    public Cliente retorno = new Cliente();
    public Endereco endereco = new Endereco();
    public ClienteDao dao = new ClienteDao();
    public NavControllerBean nav = new NavControllerBean();
    public String url = "";
    public Cliente usuarioLogado = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    public String cadastrar() {
        retorno = dao.getClienteByEmail(cliente.getEmail());
        if (retorno != null) {
            FacesMessages.error("Email já cadastrado!");
        } else {
            boolean success = dao.addNewCliente(cliente, endereco);
            if (success) {
                FacesMessages.info("Cliente salvo com sucesso.");
                url = usuarioLogado != null && usuarioLogado.getPerfil() == 1 ? nav.gerenciarClientes() : "login";
            } else {
                FacesMessages.error("Erro ao salvar novo usuário.");
            }
        }
        return url;
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
}
