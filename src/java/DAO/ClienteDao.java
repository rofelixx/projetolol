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
        String strSql = "SELECT c FROM Cliente c JOIN FETCH c.endereco endereco";
        Query query = session.createQuery(strSql);
        lista = query.list();
        session.close();
        return lista;
    }

    public boolean addNewCliente(Cliente cliente, Endereco endereco) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        cliente.setSenha(Criptografia.md5(cliente.getSenha()));
        cliente.setPerfil(2);
        cliente.setEndereco(endereco);
        session.save(cliente);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean editCliente(Cliente cliente) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Cliente validation = getClienteByEmail(cliente.getEmail());
        session.beginTransaction();
        if (!validation.getSenha().equals(cliente.getSenha())) {
            cliente.setSenha(Criptografia.md5(cliente.getSenha()));
        }
        session.saveOrUpdate(cliente);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public boolean deleteCliente(Cliente cliente) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(cliente);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setAdmCliente(Cliente cliente, int perfil) {
        Cliente clieteToUpdate = getClienteByEmail(cliente.getEmail());
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        clieteToUpdate.setPerfil(perfil);
        session.saveOrUpdate(clieteToUpdate);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
