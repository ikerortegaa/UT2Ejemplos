package com.iob;/*
 * Main.java
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 */
public class ej12Function {

     public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultado = null;

        try {
            //Registrando el Driver
            /*String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            
            System.out.println("Driver "+driver+" Registrado correctamente");*/
            
            //Abrir la conexión con la Base de Datos
            System.out.println("Conectando con la Base de datos...");
            String jdbcUrl = "jdbc:postgresql://172.22.0.2:5432/pedidos";
            conn = DriverManager.getConnection(jdbcUrl,"root","root");
            System.out.println("Conexión establecida con la Base de datos...");
            
            String sentenciaSql = "select public.cuentaproductos()";

              pstmt = conn.prepareStatement(sentenciaSql);
              resultado = pstmt.executeQuery();
              resultado.next();
 
              if (resultado.wasNull())
                System.out.println("No hay productos");
              else {
                int cont = resultado.getInt(1);
                System.out.println("El total de productos es " + cont);
              }
            }catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally {
            try {
                if(pstmt!=null)
                    pstmt.close();
                if(conn!=null)
                    conn.close();
                System.out.println("Cerrando conexión con la BD");
            } catch(SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try  
    }
    
}
