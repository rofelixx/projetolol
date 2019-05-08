/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Guigo
 */
@Stateless
@Named(value = "exemploBean")
public class ExemploBean {

    private String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String calcular() {
        return "teste";
    }
}
