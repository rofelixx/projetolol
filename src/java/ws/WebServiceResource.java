/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import Classe.Cliente;
import Classe.Endereco;
import DAO.ClienteDao;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Guigo
 */
@Path("webService")
public class WebServiceResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WebServiceResource
     */
    public WebServiceResource() {
    }

    /**
     * Retrieves representation of an instance of ws.WebServiceResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getJson() throws Exception {
        List<Cliente> clientes = new ArrayList<Cliente>();
        Cliente cliente = new Cliente();
        ClienteDao dao = new ClienteDao();
        for (Cliente clienteEntity : dao.getAll()) {
            clientes.add(new Cliente(clienteEntity.getEndereco(), clienteEntity.getNome(), clienteEntity.getEmail(), clienteEntity.getCpf(), clienteEntity.getSexo(), clienteEntity.getSenha(), clienteEntity.getDtNascimento(), clienteEntity.getPerfil()));
        }
        return clientes;
    }

    /**
     * PUT method for updating or creating an instance of WebServiceResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
