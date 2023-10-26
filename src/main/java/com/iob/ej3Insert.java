package com.iob;

import java.sql.*;

public class ej3Insert {
    
    public static void main(String[] args) {
        // TODO code application logic here

        //Abrir la conexión con la Base de Datos
        System.out.println("Conectando con la Base de datos...");
        String jdbcUrl = "jdbc:postgresql://localhost:5432/pedidos";
        try (Connection conexion = DriverManager.getConnection(jdbcUrl,"root","root");
             Statement stmt = conexion.createStatement();
        )
        {
            System.out.println("Conexión establecida con la Base de datos...");

            String sql = "CREATE TABLE CATEGORIAS2(CATEGORIAID int NOT NULL,NOMBRECAT char(50) NOT NULL, CONSTRAINT PK_CATEGORIAS2 PRIMARY KEY(CATEGORIAID) )";
          
            stmt.executeUpdate(sql);

            //Añadimos datos a la tabla vendedores
            sql = "INSERT INTO categorias2 VALUES (1,'nuevacategoria')";
            stmt.executeUpdate(sql);
            
            System.out.println("Registro añadido!");
            
        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        }
        }
    
}
