/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Classe.Cliente;
import DAO.ClienteDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Guigo
 */
@Named(value = "ClienteMB")
@SessionScoped
public class ClienteManagedBean {

    public ClienteDao dao = new ClienteDao();
    public List<Cliente> lista = new ArrayList<>();

    public List<Cliente> getAllClientes() throws Exception {
        for (Cliente clienteEntity : dao.getAll()) {
            lista.add(new Cliente(clienteEntity.getNome(), clienteEntity.getEmail(), clienteEntity.getCpf(), clienteEntity.getSexo(), clienteEntity.getSenha()));
        }
        return lista;
    }
}
