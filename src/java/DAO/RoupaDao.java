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

    public List<Roupa> getAll() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        String strSql = "FROM Roupa";
        Query query = session.createQuery(strSql);
        lista = query.list();
        session.close();
        return lista;
    }

    public boolean editRoupa(Roupa roupa) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(roupa);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean deleteRoupa(Roupa roupa) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(roupa);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Roupa> getAllByTipo(int tipoRoupa) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Roupa WHERE tipo = :tipo");
        query.setInteger("tipo", tipoRoupa);
        lista = query.list();
        session.close();
        return lista;
    }
}
