package com.iob;

import java.sql.*;

public class ej4ResultSetInverso {
    public static void main(String[] args) {

        String sql = "select * from categorias";

        try (Connection conexion = DriverManager.getConnection("jdbc:postgresql://localhost/pedidos", "root", "root");
             Statement stmt = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //Por defecto sólo lectura y movimiendo hacia delante
             ResultSet rs = stmt.executeQuery(sql);)
        {
            int id;
            String descrip;
            rs.afterLast();
            while (rs.previous())
            {
                id = rs.getInt("categoriaid");
                descrip = rs.getString("nombrecat");
                System.out.println("Identificador: " + id + ", descripción: " + descrip);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
