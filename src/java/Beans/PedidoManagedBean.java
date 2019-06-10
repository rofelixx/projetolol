package Beans;

import Classe.Cliente;
import Classe.Itempedido;
import Classe.Pedido;
import Classe.Roupa;
import DAO.PedidoDao;
import Enum.EnumStatus;
import Facade.FacadePedido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "PedidoMB")
@SessionScoped

public class PedidoManagedBean {

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
    FacadePedido facade = new FacadePedido();

    public Integer maiorPrazoTotal() {
        if (!pedido.getItempedidos().isEmpty()) {
            PrazoTotal = Collections.max(pedido.getItempedidos(), Comparator.comparing(s -> s.getPrazo())).getPrazo();
        }
        return PrazoTotal;
    }

    public void setPrazoTotal(Integer PrazoTotal) {
        this.PrazoTotal = PrazoTotal;
    }

    public Itempedido getItemToDelete() {
        return itemToDelete;
    }

    public void setItemToDelete(Itempedido itemToDelete) {
        this.itemToDelete = itemToDelete;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Itempedido> getItens() {
        return itens;
    }

    public void setItens(List<Itempedido> itens) {
        this.itens = itens;
    }

    public String adicionarCarrinho(Roupa roupa) {
        boolean hasMatch = pedido.getItempedidos().stream().anyMatch(f -> f.getRoupa().getId() == roupa.getId());
        if (hasMatch) {
            Itempedido atualizar = pedido.getItempedidos().stream().filter(f -> f.getRoupa().getId() == roupa.getId()).findFirst().get();
            atualizar.setQuantidade(atualizar.getQuantidade() + 1);
        } else {
            Itempedido itemPedido = new Itempedido();
            itemPedido.setPrazo(roupa.getPrazo());
            itemPedido.setPedido(pedido);
            itemPedido.setRoupa(roupa);
            itemPedido.setQuantidade(1);
            itemPedido.setValorUnitario(roupa.getPreco());
            itens.add(itemPedido);
        }
        valorTotal = valorTotal + (roupa.getPreco() * 1);
        pedido.setValorTotal(valorTotal);
        pedido.setCliente(usuarioLogado);
        pedido.setDataPedido(new Date());
        pedido.setItempedidos(itens);
        ItemsCarrinho++;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ItemsCarrinho", ItemsCarrinho);
        FacesMessages.info("Item adicionado com sucesso");
        return nav.novoPedido();
    }

    public void atualizarQuantidade(Itempedido item) {
        if (item.getQuantidade() < 0) {
            showConfirmDelete(item);
        } else {
            int index = pedido.getItempedidos().indexOf(item);
            pedido.getItempedidos().get(index).setQuantidade(item.getQuantidade());
            ItemsCarrinho = pedido.getItempedidos().stream().mapToInt(o -> o.getQuantidade()).sum();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ItemsCarrinho", ItemsCarrinho);
            calcularValorTotal();
        }
    }

    public void calcularValorTotal() {
        valorTotal = 0;
        pedido.getItempedidos().forEach((_item) -> {
            valorTotal = valorTotal + (_item.getRoupa().getPreco() * _item.getQuantidade());
        });
        pedido.setValorTotal(valorTotal);
    }

    public void excluirItem() {
        pedido.getItempedidos().remove(itemToDelete);
        calcularValorTotal();
        ItemsCarrinho = pedido.getItempedidos().stream().mapToInt(o -> o.getQuantidade()).sum();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ItemsCarrinho", ItemsCarrinho);
    }

    public void showConfirmDelete(Itempedido item) {
        setItemToDelete(item);
        PrimeFaces.current().executeScript("PF('groupDeleteConfirm').show()");
    }

    public void showConfirmConclude() {
        PrimeFaces.current().executeScript("PF('groupConcluirConfirm').show()");
    }

    public void showConfirmCancel() {
        PrimeFaces.current().executeScript("PF('groupDeleteConfirm').show()");
    }

    public void CancelPedido(Pedido pedido) {
        if (pedido.getStatus() == EnumStatus.AguardandoPagamento.getCode()) {
            boolean success = facade.cancelarPedido(pedido);
            if (success) {
                FacesMessages.info("Pedido cancelado com sucesso");
            }
        } else {
            FacesMessages.warning("O pedido não pode ser cancelado.");
        }
    }

    public String confirmarPedido() {
        boolean success = facade.confirmarPedido(pedido, PrazoTotal);
        if (success) {
            pedido = new Pedido();
            itemToDelete = new Itempedido();
            itens = new ArrayList<Itempedido>();
            PrazoTotal = 0;
            url = nav.pedidos();
            FacesMessages.info("Pedido realizado com com sucesso");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ItemsCarrinho", 0);
        }
        return url;
    }

    public List<Pedido> getAllPedidosByUser() {
        return facade.getAllPedidosByUser();
    }

    public List<Pedido> getAllPedidos() {
        return facade.getAllPedidos();
    }

    public String getStatus(int status) {
        String retorno = "";
        switch (status) {
            case 1:
                retorno = "Aguardando Pagamento";
                break;
            case 2:
                retorno = "Pagamento Confirmado";
                break;
            case 3:
                retorno = "Em Lavagem";
                break;
            case 4:
                retorno = "Lavagem Concluida";
                break;
            case 5:
                retorno = "Concluido";
                break;
            case 6:
                retorno = "Cancelado";
                break;
        }
        return retorno;
    }

    public List<Itempedido> getItemPedidoById() {
        if (pedido.getId() != null) {
            return facade.getItemPedidoById(pedido.getId());
        } else {
            return new ArrayList<Itempedido>();
        }
    }
}
