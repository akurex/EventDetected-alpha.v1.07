/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sw2.ed.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Miguel
 */
public class DBase {

    protected Connection conn;

    public void init_mysql() {

        String className = "com.mysql.jdbc.Driver";

        String user = "root";
        String pass = "123";
        String url = "jdbc:mysql://localhost:3306/mydbmina";
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println("No pudo conectarse parametros incorrectos");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("No existe el driver");
        }
    }
}
