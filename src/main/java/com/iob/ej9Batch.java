package com.iob;/*
 * Main.java
 *
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

//package ejemplo10;
import java.sql.*;

public class ej9Batch {

        public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        Statement stmt = null;
        
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
            
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            
            //Añadimos sentencias SQL a modo Batch
            String sql = "insert into clientes values(11,283445667,'electricidad martinez','jose martinez','pablo iglesias, 3')";
            stmt.addBatch(sql);
            sql = "insert into clientes values(12,383445667,'fontaneria perez','jose perez','pablo iglesias, 4')";
            stmt.addBatch(sql);
            sql = "insert into clientes values(13,483445667 ,'carpinteria lopez','jose lopez','pablo iglesias, 5')";
            stmt.addBatch(sql);
            int result[] = stmt.executeBatch();
            conn.commit();

            for (int i=0; i < result.length;i++) {
                System.out.println("Sentencia [" + i + "]: resultado: "+result[i]+ " OK");
            }
            
        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally {
            try {
                conn.rollback();
                
                if(stmt!=null)
                    stmt.close();

                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try  
    }
    
}
