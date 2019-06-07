/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Beans.NavControllerBean;
import Classe.Cliente;
import Classe.Roupa;
import DAO.RoupaDao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.faces.context.FacesContext;
import net.bootsfaces.utils.FacesMessages;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ALM4CT
 */
public class FacadeRoupa {

    public RoupaDao dao = new RoupaDao();
    public NavControllerBean nav = new NavControllerBean();
    public String url = "";
    public Cliente usuarioLogado = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
    private int tipoRoupa;
    private final String fileUploadPath = "C:\\Users\\Guigo\\Documents\\NetBeansProjects\\projetolol\\web\\images\\roupasUpload\\";

    public List<Roupa> getAllRoupas() throws Exception {
        List<Roupa> retorno = dao.getAll();
        return retorno;
    }

    public List<Roupa> getAllByTipo(int tipoRoupa) {
        List<Roupa> retorno = dao.getAllByTipo(tipoRoupa);
        return retorno;
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

    public String upload(Roupa roupa, UploadedFile uploadedFile) throws FileNotFoundException, IOException {
        File file = criarFile(uploadedFile);
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

    public File criarFile(UploadedFile uploadedFile) {
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

    public String salvarEdicao(UploadedFile uploadedFile, Roupa roupa) {
        if (uploadedFile.getFileName() != roupa.getImage() && !isUploadedFileEmpty(uploadedFile)) {
            File file = criarFile(uploadedFile);
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

    public boolean isUploadedFileEmpty(UploadedFile uploadedFile) {
        return uploadedFile == null || uploadedFile.getSize() == 0;
    }
}
