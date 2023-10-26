package com.iob;

import java.sql.*;
public class ej4ResultSet {

        public static void main(String[] args) {
        // TODO code application logic here

            String jdbcUrl = "jdbc:postgresql://localhost/pedidos";
            String sql = "select * from categorias";

        try(Connection conn = DriverManager.getConnection(jdbcUrl,"root","root");
            Statement stmt = conn.createStatement(); //Por defecto sólo lectura y movimiendo hacia delante
            ResultSet rs = stmt.executeQuery(sql);
        ) {

            //Abrir la conexión con la Base de Datos
            System.out.println("Conexión establecida con la Base de datos...");

            int id ; String descr;
            while(rs.next() )
            { 
            	//id = rs.getInt(1);	// leo la primera columna (1) de la fila actual, que es int (getInt)
            	 id = rs.getInt("categoriaid");	//, equivalente a la línea anterior
            	//descr = rs.getString(2);
            	descr = rs.getString("nombrecat");	// equivalente a la líne anterior
            	System.out.println("Identificador: " + id + ", descripción: " + descr);
            }
        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        }
        }//end try
}
