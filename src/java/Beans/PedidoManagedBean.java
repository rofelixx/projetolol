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

    public void showConfirmCancel(Pedido pedido) {
        setPedido(pedido);
        PrimeFaces.current().executeScript("PF('groupDeleteConfirm').show()");
    }

    public void CancelPedido() {
        if (pedido.getStatus() == EnumStatus.AguardandoPagamento.getCode()) {
            boolean success = facade.cancelarPedido(pedido);
            if (success) {
                FacesMessages.info("Pedido cancelado com sucesso");
            }
        } else if (pedido.getStatus() == EnumStatus.Cancelado.getCode()) {
            FacesMessages.warning("O pedido já está cancelado.");
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
            case 7:
                retorno = "Aguardando Coleta";
                break;
            case 8:
                retorno = "Em Entrega";
                break;
            case 9:
                retorno = "Entregue";
                break;
            case 10:
                retorno = "Não Entregue";
                break;
            case 11:
                retorno = "Entrega Cancelada";
                break;
        }
        return retorno;
    }

    public void showConfirmPayment(Pedido pedido) {
        setPedido(pedido);
        PrimeFaces.current().executeScript("PF('confirmPayment').show()");
    }

    public void ConfirmPayment() {
        if (pedido.getStatus() == EnumStatus.AguardandoPagamento.getCode()) {
            boolean success = facade.ConfirmPayment(pedido);
            if (success) {
                FacesMessages.info("Pagamento confirmado com sucesso");
            }
        } else if (pedido.getStatus() == EnumStatus.PagamentoConfirmado.getCode()) {
            FacesMessages.info("O Pagamento já foi confirmado");
        } else {
            FacesMessages.warning("O pagamento não pode ser confirmado nesse status");
        }
    }

    public void showConfirmWashing(Pedido pedido) {
        setPedido(pedido);
        PrimeFaces.current().executeScript("PF('confirmWashing').show()");
    }

    public void ConfirmWashing() {
        if (pedido.getStatus() == EnumStatus.PagamentoConfirmado.getCode()) {
            boolean success = facade.ConfirmWashing(pedido);
            if (success) {
                FacesMessages.info("O pedido em lavagem foi confirmado com sucesso");
            }
        } else if (pedido.getStatus() == EnumStatus.EmLavagem.getCode()) {
            FacesMessages.warning("O pedido já está em lavagem");
        } else {
            FacesMessages.warning("A lavagem não pode ser confirmada nesse status");
        }
    }

    public void showConfirmWashingDone(Pedido pedido) {
        setPedido(pedido);
        PrimeFaces.current().executeScript("PF('confirmWashingDone').show()");
    }

    public void ConfirmWashingDone() {
        if (pedido.getStatus() == EnumStatus.EmLavagem.getCode()) {
            boolean success = facade.ConfirmWashingDone(pedido);
            if (success) {
                FacesMessages.info("A lavagem foi concluida com sucesso");
            }
            if (sendPedidoToDelivery()) {
                FacesMessages.info("O pedido está aguardando coleta");
            } else {
                FacesMessages.error("Erro ao salvar pedido no sistema de delivery");
            }
        } else if (pedido.getStatus() == EnumStatus.LavagemConcluida.getCode()) {
            FacesMessages.warning("A lavagem já foi concluida");
        } else {
            FacesMessages.warning("A lavagem não pode ser confirmada nesse status");
        }
    }

    public boolean sendPedidoToDelivery() {
        return facade.sendPedidosToDelivery(pedido);
    }
}
