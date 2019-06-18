/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Beans.NavControllerBean;
import Classe.Cliente;
import DAO.ClienteDao;
import java.util.List;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author ALM4CT
 */
public class FacadeCliente {

    private ClienteDao dao = new ClienteDao();
    private String url = "";
    private NavControllerBean nav = new NavControllerBean();

    public List<Cliente> getAllClientes() throws Exception {
        List<Cliente> retorno = dao.getAll();
        return retorno;
    }

    public String salvarEdicao(Cliente cliente) {
        Cliente retorno = dao.getClienteByEmail(cliente.getEmail());
        if (retorno != null && cliente.getEmail().equals(retorno.getEmail())) {
            FacesMessages.error("Erro!", "Email já existente.");
        } else {
            boolean saved = dao.editCliente(cliente);
            if (saved) {
                url = nav.gerenciarClientes();
                FacesMessages.info("Cliente editado com sucesso");
            } else {
                FacesMessages.error("Erro!", "Problema ao salvar a edição");
            }
        }
        return url;
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
