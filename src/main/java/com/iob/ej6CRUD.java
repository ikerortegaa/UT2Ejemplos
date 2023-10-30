package com.iob;
import java.sql.*;

public class ej6CRUD {

         public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            //Registrando el Driver
            /*String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            System.out.println("Driver "+driver+" Registrado correctamente");*/
            
            //Abrir la conexion con la Base de Datos
            System.out.println("Conectando con la Base de datos...");
            String jdbcUrl = "jdbc:postgresql://172.19.0.2:5432/pedidos";
            conn = DriverManager.getConnection(jdbcUrl,"root","root");
            System.out.println("Conexion establecida con la Base de datos...");
            
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            //Ejecutamos la SELECT sobre la tabla clientes
            String sql = "select * from categorias2";
            
            rs = stmt.executeQuery(sql);
            
            System.out.println("Situamos el cursor al final");
            System.out.println("Modificamos el nombre de la categoría del último registro");
            rs.last();
            //rs.first();
            rs.updateString("nombrecat", "suprema2");
            rs.updateRow();
            
            //Creamos un nuevo registro en la tabla de categorias2
            
            System.out.println("Creamos un nuevo registro");
            rs.moveToInsertRow();
            rs.updateInt(1,4);
            rs.updateString(2,"magnífica");
            rs.insertRow();

            //Nos situamos en el primero del ResultSet
            System.out.println("Borramos el primer registro");
            rs.absolute(1);
            rs.deleteRow();

            
        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
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
