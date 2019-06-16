package Beans;

import Classe.Cliente;
import Classe.Roupa;
import DAO.RoupaDao;
import Facade.FacadeRoupa;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.UploadedFile;

@ViewScoped
@Named("RoupaMB")

public class RoupaManagedBean implements Serializable {

    private Roupa roupa = new Roupa();
    private RoupaDao dao = new RoupaDao();
    private NavControllerBean nav = new NavControllerBean();
    private String url = "";
    private Cliente usuarioLogado = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
    private UploadedFile uploadedFile;
    private int tipoRoupa;
    private FacadeRoupa facade = new FacadeRoupa();

    public List<Roupa> getAllRoupas() throws Exception {
        return facade.getAllRoupas();
    }

    public List<Roupa> getAllByTipo() {
        return facade.getAllByTipo(tipoRoupa);
    }

    public String upload() throws FileNotFoundException, IOException {
        return facade.upload(roupa, uploadedFile);
    }

    public String salvarEdicao() {
        return facade.salvarEdicao(uploadedFile, roupa);
    }

    public int getTipoRoupa() {
        return tipoRoupa;
    }

    public void setTipoRoupa(int tipoRoupa) {
        this.tipoRoupa = tipoRoupa;
    }

    public Roupa getRoupa() {
        return roupa;
    }

    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void showConfirmDelete(Roupa roupa) {
        setRoupa(roupa);
        PrimeFaces.current().executeScript("PF('groupDeleteConfirm').show()");
    }

    public String deleteRoupa() {
        return facade.deleteRoupa(roupa);
    }

    public String getRoupaTipoNome(int tipo) {
        String retorno = "";
        switch (tipo) {
            case 1:
                retorno = "Camiseta";
                break;
            case 2:
                retorno = "Camisa";
                break;
            case 3:
                retorno = "Jaqueta/Casacos";
                break;
            case 4:
                retorno = "Calça";
                break;
            case 5:
                retorno = "Vestido";
                break;
        }
        return retorno;
    }
}
