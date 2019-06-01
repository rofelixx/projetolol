/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classe.Pedido;
import Hibernate.HibernateUtil;
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
}
