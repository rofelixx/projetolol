package Beans;

import Classe.Cliente;
import DAO.ClienteDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import net.bootsfaces.utils.FacesMessages;

@ViewScoped
@Named("ClienteMB")
public class ClienteManagedBean implements Serializable {

    private Cliente cliente = new Cliente();
    private ClienteDao dao = new ClienteDao();
    private String url = "";
    private NavControllerBean nav = new NavControllerBean();
    
    public List<Cliente> getAllClientes() throws Exception {
        List<Cliente> retorno = dao.getAll();
        return retorno;
    }

    public String salvarEdicao() {        
        boolean saved = dao.editCliente(cliente);
        if (saved) {
            url = nav.gerenciarClientes();
            FacesMessages.info("Cliente editado com sucesso");
        } else {
            FacesMessages.error("Erro!", "Email ou senha incorretos");
        }
        return url;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
