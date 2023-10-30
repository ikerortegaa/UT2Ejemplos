package com.iob;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ej7UpdatePreparedStatement {

     public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        PreparedStatement pstmt = null;
        
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
            
            //Cramos el objeto PreparedStatement
            pstmt = conn.prepareStatement("update productos set preciounit=? where productoid=?");            
            
            //Añadimos los parametros del PreparedStatement
            pstmt.setDouble(1, 33.3);
            pstmt.setInt(2, 11);
            
            //Ejecutamos el prepared Statement
            pstmt.executeUpdate();
            System.out.println("Modificado el producto!");
            
        } catch(SQLException se) {
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
