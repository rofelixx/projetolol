package Facade;

import Beans.NavControllerBean;
import Classe.Cliente;
import Classe.Endereco;
import DAO.ClienteDao;
import Util.CorreiosWebService;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;

public class FacadeCadastro {

    public ClienteDao dao = new ClienteDao();
    public NavControllerBean nav = new NavControllerBean();
    public String url = "";
    public Cliente usuarioLogado = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");

    public String Cadastrar(Cliente cliente, Endereco endereco) {
        Cliente retorno = dao.getClienteByEmail(cliente.getEmail());
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

    public String buscarCep(Endereco endereco) {
        try {
            if (endereco.getCep() != null) {
                CorreiosWebService cws = new CorreiosWebService(endereco.getCep());

                if (cws.getResultado() == 1) {
                    endereco.setRua(cws.getTipoLogradouro() + " " + cws.getLogradouro());
                    endereco.setBairro(cws.getBairro());
                    endereco.setUf(cws.getEstado());
                    endereco.setCidade(cws.getCidade());
                } else {
                    endereco.setRua("");
                    endereco.setBairro("");
                    endereco.setUf("");
                    endereco.setCidade("");
                    FacesMessages.warning("Cep não encontrado!");
                }
            } else {
                endereco.setRua("");
                endereco.setBairro("");
                endereco.setUf("");
                endereco.setCidade("");
                FacesMessages.warning("Cep não é valido!");
            }
        } catch (Exception e) {
            FacesMessages.error("Servidor não está respondendo!");
        }
        return nav.signUp();
    }
}
