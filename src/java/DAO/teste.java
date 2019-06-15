/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classe.Pedido;
import DTO.TesteDTO;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Guigo
 */
public class teste {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        PedidoDao dao = new PedidoDao();
        List<TesteDTO> list = new ArrayList<>();
        for (Pedido p : dao.getPedidosWashingDone()) {
            list.add(new TesteDTO(p.getId(), p.getStatus()));
        }
        Gson gson = new Gson();
        String json = gson.toJson(list);
        Response resp = client
                .target("http://localhost:8080/DeliveryTads/webresources/api/send")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(list));
        System.out.print(resp.getStatus());
    }
}
