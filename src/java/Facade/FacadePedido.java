/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Classe.Cliente;
import Classe.Itempedido;
import Classe.Pedido;
import DAO.PedidoDao;
import DTO.TesteDTO;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ALM4CT
 */
public class FacadePedido {

    public PedidoDao dao = new PedidoDao();

    public List<Pedido> getAllPedidosByUser() {
        Cliente usuarioLogado = (Cliente)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
        List<Pedido> listPedidos = dao.getAllPedidosByUser(usuarioLogado.getId());
        return listPedidos;
    }

    public boolean confirmarPedido(Pedido pedido, int PrazoTotal) {
        pedido.setPrazo(PrazoTotal);
        return dao.addNewPedido(pedido);
    }

    public List<Pedido> getAllPedidos() {
        List<Pedido> listPedidos = dao.getAllPedidos();
        return listPedidos;
    }

    public boolean cancelarPedido(Pedido pedido) {
        return dao.cancelarPedido(pedido);
    }

    public List<Itempedido> getItemPedidoById(Integer id) {
        return dao.getItemPedidoById(id);
    }

    public boolean ConfirmPayment(Pedido pedido) {
        return dao.ConfirmPayment(pedido);
    }

    public boolean ConfirmWashing(Pedido pedido) {
        return dao.ConfirmWashing(pedido);
    }

    public boolean ConfirmWashingDone(Pedido pedido) {
        return dao.ConfirmWashingDone(pedido);
    }

    public List<Pedido> getPedidosWashingDone() {
        return dao.getPedidosWashingDone();
    }

    public boolean sendPedidosToDelivery(Pedido pedido) {
        Client client = ClientBuilder.newClient();
        String end = enderecoFormatado(pedido.getCliente().getEndereco().getRua(), pedido.getCliente().getEndereco().getNumero(),
                                       pedido.getCliente().getEndereco().getUf(), pedido.getCliente().getEndereco().getCidade());
        TesteDTO entrega = new TesteDTO(pedido.getId(), pedido.getStatus(), new Date(), end, pedido.getCliente().getNome());
        Response resp = client
                .target("http://localhost:8080/deliverytads/webresources/api/send")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(entrega));
        return resp.getStatus() == 200;
    }

    public String enderecoFormatado(String rua, String numero, String uf, String cidade) {
        return rua + ", " + numero + " - " + cidade + "/" + uf;
    }

    public boolean atualizarStatusPedido(TesteDTO pedido) {
        return dao.atualizarStatusPedido(pedido);
    }
}
