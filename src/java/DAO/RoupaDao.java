/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classe.Cliente;
import Classe.Endereco;
import Classe.Roupa;
import static DAO.teste.lista;
import Hibernate.HibernateUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Guigo
 */
public class RoupaDao {

    public List<Roupa> lista;
    public Roupa roupa;

    public boolean addNewRoupa(Roupa newRoupa) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(newRoupa);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
