package Beans;

import Classe.Cliente;
import Classe.Roupa;
import DAO.RoupaDao;
import java.io.File;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "RoupaMB")
@SessionScoped

public class RoupaManagedBean {

    public Roupa roupa = new Roupa();
    public RoupaDao dao = new RoupaDao();
    public NavControllerBean nav = new NavControllerBean();
    public String url = "";
    public Cliente usuarioLogado = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    public UploadedFile uploadedFile;
    private StreamedContent chart;
    private int tipoRoupa; 

    public int getTipoRoupa() {
        return tipoRoupa;
    }

    public void setTipoRoupa(int tipoRoupa) {
        this.tipoRoupa = tipoRoupa;
    }
    
    public StreamedContent getChart() {
        return chart;
    }

    public void setChart(StreamedContent chart) {
        this.chart = chart;
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

    public String upload() {
        File file = new File("C:\\Users\\ALM4CT\\Documents\\NetBeansProjects\\projetolol\\web\\images\\roupasUpload\\", uploadedFile.getFileName());
        roupa.setImage(file.getAbsolutePath());
        roupa.setTipo(tipoRoupa);
        boolean success = dao.addNewRoupa(roupa);
        if (success) {
            url = nav.gerenciarRoupas();
            FacesMessages.info("Roupa adicionada com sucesso");
        } else {
            FacesMessages.error("Erro!", "Email ou senha incorretos");
        }
        return url;
    }
}
