/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import Classe.Pedido;
import DAO.PedidoDao;
import DTO.TesteDTO;
import Facade.FacadePedido;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Guigo
 */
@Path("webService")
public class WebServiceResource {

    @Context
    private UriInfo context;
    private final FacadePedido facade = new FacadePedido();

    /**
     * Creates a new instance of WebServiceResource
     */
    public WebServiceResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> getJson() throws Exception {
        PedidoDao dao = new PedidoDao();
        List<Pedido> list = new ArrayList<>();
        for (Pedido p : dao.getPedidosWashingDone()) {
            list.add(new Pedido(p.getId(), p.getStatus()));
        }
        return list;
    }

    @Path("atualizarStatusPedido")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarStatusPedido(TesteDTO pedido) {
        boolean success = facade.atualizarStatusPedido(pedido);
        if (success) {
            return Response.ok().build();
        } else {
            return Response.serverError().build();
        }
    }
}
