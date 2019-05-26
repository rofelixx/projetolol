package Beans;

import Classe.Cliente;
import Classe.Roupa;
import DAO.RoupaDao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.PrimeFaces;
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
    private final String fileUploadPath = "C:\\Users\\Guigo\\Documents\\NetBeansProjects\\projetolol\\web\\images\\roupasUpload\\";

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

    public String upload() throws FileNotFoundException, IOException {
        File file = criarFile();
        roupa.setImage(file.getName());
        roupa.setTipo(roupa.getTipo() == 0 ? tipoRoupa : roupa.getTipo());
        boolean success = dao.addNewRoupa(roupa);
        if (success) {
            url = nav.gerenciarRoupas();
            FacesMessages.info("Roupa adicionada com sucesso");
        } else {
            FacesMessages.error("Erro!", "Email ou senha incorretos");
        }
        return url;
    }

    public File criarFile() {
        File file = new File(fileUploadPath, uploadedFile.getFileName());
        try {
            OutputStream out = new FileOutputStream(file);
            out.write(uploadedFile.getContents());
            out.close();
        } catch (IOException e) {
            FacesMessages.error("Erro", e.getMessage());
        }
        return file;
    }

    public List<Roupa> getAllRoupas() throws Exception {
        List<Roupa> retorno = dao.getAll();
        return retorno;
    }

    public String salvarEdicao() {
        if (uploadedFile.getFileName() != roupa.getImage() && !isUploadedFileEmpty()) {
            File file = criarFile();
            roupa.setImage(file.getName());
        }
        boolean saved = dao.editRoupa(roupa);
        if (saved) {
            url = nav.gerenciarRoupas();
            FacesMessages.info("Roupa editada com sucesso");
        } else {
            FacesMessages.error("Erro!", "Problema ao salvar a edição");
        }
        return url;
    }

    public boolean isUploadedFileEmpty() {
        return uploadedFile == null || uploadedFile.getSize() == 0;
    }

    public void showConfirmDelete() {
        PrimeFaces.current().executeScript("PF('groupDeleteConfirm').show()");
    }

    public String deleteRoupa(Roupa roupa) {
        boolean deleted = dao.deleteRoupa(roupa);
        if (deleted) {
            url = nav.gerenciarRoupas();
            FacesMessages.info("Roupa deletada com sucesso");
        } else {
            FacesMessages.error("Erro!", "Problema ao excluir registro.");
        }
        return url;
    }

    public List<Roupa> getAllByTipo(){
        List<Roupa> retorno = dao.getAllByTipo(tipoRoupa);
        return retorno;
    }
}
