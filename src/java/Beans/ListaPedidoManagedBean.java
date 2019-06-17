package Beans;

import Classe.Pedido;
import Facade.FacadePedido;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@ViewScoped
@Named("ListaPedidoMB")
public class ListaPedidoManagedBean implements Serializable {
    private FacadePedido facade = new FacadePedido();

    public List<Pedido> getAllPedidosByUser() {
        return facade.getAllPedidosByUser();
    }

    public List<Pedido> getAllPedidos() {
        return facade.getAllPedidos();
    }
}
