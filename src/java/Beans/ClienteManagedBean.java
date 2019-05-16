package Beans;

import Classe.Cliente;
import DAO.ClienteDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ClienteMB")
@SessionScoped

public class ClienteManagedBean {

    public Cliente cliente = new Cliente();
    public ClienteDao dao = new ClienteDao();

    public List<Cliente> getAllClientes() throws Exception {
        List<Cliente> retorno = dao.getAll();
        return retorno;
    }
}
