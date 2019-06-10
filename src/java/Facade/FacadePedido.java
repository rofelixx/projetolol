/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Beans.NavControllerBean;
import Classe.Cliente;
import Classe.Itempedido;
import Classe.Pedido;
import Classe.Roupa;
import DAO.PedidoDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author ALM4CT
 */
public class FacadePedido {

    public Roupa roupa = new Roupa();
    public NavControllerBean nav = new NavControllerBean();
    public String url = "";
    public Cliente usuarioLogado = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
    public int ItemsCarrinho = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ItemsCarrinho");
    public PedidoDao dao = new PedidoDao();
    public List<Itempedido> itens = new ArrayList<Itempedido>();
    double valorTotal = 0;
    Pedido pedido = new Pedido();
    Itempedido itemToDelete = new Itempedido();
    Integer PrazoTotal;

    public List<Pedido> getAllPedidosByUser() {
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
}
