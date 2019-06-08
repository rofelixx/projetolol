/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classe.Pedido;
import Hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Guigo
 */
public class PedidoDao {

    public boolean addNewPedido(Pedido newPedido) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        newPedido.setStatus(1);
        session.saveOrUpdate(newPedido);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List<Pedido> getAllPedidosByUser(Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Pedido p JOIN FETCH p.cliente cliente WHERE cliente.id = :id");
        query.setInteger("id", id);
        List<Pedido> lista = query.list();
        session.close();
        return lista;
    }

    public List<Pedido> getAllPedidos() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Pedido p JOIN FETCH p.cliente cliente");
        List<Pedido> lista = query.list();
        session.close();
        return lista;
    }
}
