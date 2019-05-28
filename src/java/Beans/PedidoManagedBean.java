package Beans;

import Classe.Cliente;
import Classe.Itempedido;
import Classe.Pedido;
import Classe.Roupa;
import DAO.RoupaDao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "PedidoMB")
@SessionScoped

public class PedidoManagedBean {

    public Roupa roupa = new Roupa();
    public List<Itempedido> itens = new ArrayList<Itempedido>();

    public List<Itempedido> getItens() {
        return itens;
    }

    public void setItens(List<Itempedido> itens) {
        this.itens = itens;
    }
    public RoupaDao dao = new RoupaDao();
    public NavControllerBean nav = new NavControllerBean();
    public String url = "";
    public Cliente usuarioLogado = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    public int ItemsCarrinho = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ItemsCarrinho");

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }
    
    public String adicionarCarrinho(Roupa roupa)
    {
        Itempedido itemPedido = new Itempedido();
        Pedido pedido = new Pedido();
        itemPedido.setPedido(pedido);
        itemPedido.setRoupa(roupa);
        itemPedido.setQuantidade(1);
        itemPedido.setValorUnitario(roupa.getPreco());
        pedido.setCliente(usuarioLogado);
        pedido.setDataPedido(new Date());
        pedido.setPrazo(pedido.getPrazo() + roupa.getPrazo());
        itens.add(itemPedido);
        ItemsCarrinho++;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ItemsCarrinho", ItemsCarrinho);
        return nav.novoPedido();
    }
}
