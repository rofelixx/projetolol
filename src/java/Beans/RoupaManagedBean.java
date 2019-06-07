package Beans;

import Classe.Cliente;
import Classe.Roupa;
import DAO.RoupaDao;
import Facade.FacadeRoupa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "RoupaMB")
@SessionScoped

public class RoupaManagedBean {

    public Roupa roupa = new Roupa();
    public RoupaDao dao = new RoupaDao();
    public NavControllerBean nav = new NavControllerBean();
    public String url = "";
    public Cliente usuarioLogado = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
    public UploadedFile uploadedFile;
    private int tipoRoupa;
    FacadeRoupa facade = new FacadeRoupa();

    public String deleteRoupa(Roupa roupa) {
        return facade.deleteRoupa(roupa);
    }

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

    public void showConfirmDelete() {
        PrimeFaces.current().executeScript("PF('groupDeleteConfirm').show()");
    }
}
