/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classe.Cliente;
import Classe.Endereco;
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
public class ClienteDao {

    public List<Cliente> lista;
    public Cliente cliente;

    public Cliente getClienteByEmail(String Email) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Cliente WHERE Email = :email");
        query.setString("email", Email);
        cliente = (Cliente) query.uniqueResult();
        session.close();
        return cliente;
    }

    public List<Cliente> getAll() throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        String strSql = "from Cliente";
        Query query = session.createQuery(strSql);
        lista = query.list();
        return query.list();
    }

    public boolean addNewCliente(Cliente cliente, Endereco endereco) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        cliente.setSenha(Criptografia.md5(cliente.getSenha()));
        cliente.setEndereco(endereco);
        session.save(cliente);
        session.getTransaction().commit();
        FacesMessage fm = new FacesMessage("Cliente salvo com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, fm);
        session.close();
        return true;
    }
}
