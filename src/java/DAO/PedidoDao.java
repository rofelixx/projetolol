/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classe.Itempedido;
import Classe.Pedido;
import Enum.EnumStatus;
import Hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Guigo
 */
public class PedidoDao {

    public boolean addNewPedido(Pedido newPedido) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            newPedido.setStatus(1);
            session.saveOrUpdate(newPedido);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Pedido> getAllPedidosByUser(Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente cliente WHERE cliente.id = :id");
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

    public boolean cancelarPedido(Pedido pedido) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            pedido.setStatus(EnumStatus.Cancelado.getCode());
            session.saveOrUpdate(pedido);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Itempedido> getItemPedidoById(Integer id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Itempedido p JOIN FETCH p.roupa roupa WHERE p.pedido.id = :Id");
        query.setInteger("Id", id);
        List<Itempedido> lista = query.list();
        session.close();
        return lista;
    }

    public boolean ConfirmPayment(Pedido pedido) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            pedido.setStatus(EnumStatus.PagamentoConfirmado.getCode());
            session.saveOrUpdate(pedido);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ConfirmWashing(Pedido pedido) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            pedido.setStatus(EnumStatus.EmLavagem.getCode());
            session.saveOrUpdate(pedido);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ConfirmWashingDone(Pedido pedido) {
        try {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            pedido.setStatus(EnumStatus.AguardandoColeta.getCode());
            session.saveOrUpdate(pedido);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Pedido> getPedidosWashingDone() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Pedido p JOIN FETCH p.cliente cliente WHERE p.status = :Status");
        query.setInteger("Status", EnumStatus.AguardandoColeta.getCode());
        List<Pedido> lista = query.list();
        session.close();
        return lista;
    }
}
