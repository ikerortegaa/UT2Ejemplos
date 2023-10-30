package com.iob;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;
import java.util.Scanner;

public class ej5ResultSet {

         public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Scanner ent = new Scanner(System.in);
        
        try {
            //Registrando el Driver
            /*String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            System.out.println("Driver "+driver+" Registrado correctamente");*/
            
            //Abrir la conexión con la Base de Datos
            System.out.println("Conectando con la Base de datos...");
            String jdbcUrl = "jdbc:postgresql://localhost:5432/pedidos";
            conn = DriverManager.getConnection(jdbcUrl,"root","root");
            System.out.println("Conexión establecida con la Base de datos...");
            
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            //Ejecutamos la SELECT sobre la tabla articulos
            String sql = "select * from clientes";
            
            rs = stmt.executeQuery(sql);
            rs.afterLast();
            System.out.println("Cursor antes de la primera fila = "+ rs.isBeforeFirst());
            int id;
            String cedula,nomcia,nomcontacto,direcli;
            
            //while (rs.next()) {
            while (rs.previous()) {
                //Obtenemos la información por el nombre de la columna
                
                id = rs.getInt("clienteid");
                if(id==5)
                {
                    ent.nextLine();
                }
                cedula = rs.getString("cedula_ruc");
                nomcia = rs.getString("nombrecia");
                
                //Obtenemos la información por el indice de la columna
                nomcontacto = rs.getString(4);
                direcli = rs.getString(5);
                

                //Mostramos la información
                System.out.print("Numero de Fila=" + rs.getRow());
                System.out.print(", id: " + id);
                System.out.print(", cédula: " + cedula);
                System.out.print(", nombre compañía: " + nomcia);
                System.out.print(", persona de contacto: " + nomcontacto);
                System.out.println(", dirección: " + direcli);
            }
            System.out.println("Cursor despues de la ultima fila= " +rs.isAfterLast());            
        } catch(SQLException se) {
            //Errores de JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Errores de Class.forName
            e.printStackTrace();
        } finally
        {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }
    }
    
}
