package Beans;

import Classe.Cliente;
import DAO.ClienteDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.PrimeFaces;

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
            FacesMessages.error("Erro!", "Problema ao salvar a edição");
        }
        return url;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void confirmDelete(Cliente cliente) {
        setCliente(cliente);
    }

    public void showConfirmDelete() {
        PrimeFaces.current().executeScript("PF('groupDeleteConfirm').show()");
    }

    public String deleteCliente(Cliente cliente) {
        boolean deleted = dao.deleteCliente(cliente);
        if (deleted) {
            url = nav.gerenciarClientes();
            FacesMessages.info("Cliente deletado com sucesso");
        } else {
            FacesMessages.error("Erro!", "Problema ao excluir registro.");
        }
        return url;
    }

    public String setAdmCliente(Cliente cliente, int perfil) {
        boolean updatePerfil = dao.setAdmCliente(cliente, perfil);
        if (updatePerfil) {
            url = nav.gerenciarClientes();
            if (perfil == 1) {
                FacesMessages.info("Usuário agora é um Administrador.");
            } else {
                FacesMessages.info("Usuário agora é normal.");
            }
        } else {
            FacesMessages.error("Erro!", "Problema ao atualizar o registro.");
        }
        return url;
    }
}
