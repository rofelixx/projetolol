/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Guigo
 */
public class MyConnection {
    private Connection connection;
            
     public MyConnection() throws ClassNotFoundException, SQLException
     {
         Class.forName("com.mysql.jdbc.Driver");
         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetolol", "mths", "tads@1352");
         System.out.println("Conectado com sucesso");
     }
     
    public static void main(String[] args){
        try {
            new MyConnection();
        } catch (Exception e) {
                     System.out.println("Não conectado" + e.getMessage());
        }
}
}
