package Beans;

import Classe.Cliente;
import DAO.ClienteDao;
import Facade.FacadeCliente;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@ViewScoped
@Named("ClienteMB")
public class ClienteManagedBean implements Serializable {

    private Cliente cliente = new Cliente();
    private ClienteDao dao = new ClienteDao();
    private String url = "";
    private NavControllerBean nav = new NavControllerBean();
    FacadeCliente facade = new FacadeCliente();

    public List<Cliente> getAllClientes() throws Exception {
        return facade.getAllClientes();
    }

    public String salvarEdicao() {
        return facade.salvarEdicao(cliente);
    }

    public String deleteCliente(Cliente cliente) {
        return facade.deleteCliente(cliente);
    }

    public String setAdmCliente(Cliente cliente, int perfil) {
        return facade.setAdmCliente(cliente, perfil);
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
}
