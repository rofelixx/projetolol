/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Guigo
 */
@Entity
public class TesteDTO implements Serializable {

    public TesteDTO() {
    }

    public TesteDTO(int id, int status) {
        this.id = id;
        this.status = status;
    }

    public TesteDTO(int id, int status, Date date, String endereco, String destinatario) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.endereco = endereco;
        this.destinatario = destinatario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int status;
    private Date date;
    private String endereco;
    private String destinatario;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DTO.TesteDTO[ id=" + id + " ]";
    }

}
