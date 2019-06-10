/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Classe.Cliente;
import Classe.Endereco;
import Enum.EnumStatus;
import Hibernate.HibernateUtil;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Guigo
 */
public class teste {

    static List<Cliente> lista;
    static Cliente userObj;

    /**
     * @param args the command line arguments
     */
    /*/*   public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Cliente WHERE Email = :email");
        query.setString("email", "rofelix@live.com");
        userObj = (Cliente) query.uniqueResult();
        userObj.setSenha(Criptografia.md5(userObj.getSenha()));
        session.beginTransaction();
        session.save(userObj);
        session.getTransaction().commit();
        session.close();
    }
**/
    public static void main(String[] args) {
        System.out.println("Enum is: " + (6 == EnumStatus.Cancelado.getCode()));
        
    }
}
