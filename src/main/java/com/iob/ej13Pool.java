// El mismo ejemplo anterior utilizando Apache Common DBUtils para los close()
package com.iob;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ej13Pool
{
	public static void main(String args[])
	{
        Connection conexion=null; String jdbcUrl="jdbc:postgresql://localhost/pedidos"; PreparedStatement sentencia = null;
        ResultSet rs=null;
        BasicDataSource bds = new BasicDataSource();
        bds.setUrl(jdbcUrl);
        bds.setUsername("root");
        bds.setPassword("root");
        bds.setMinIdle(5);
        bds.setMaxIdle(10);
        bds.setMaxOpenPreparedStatements(100);
		String descripcionProducto = "alicates";
		float preciounit = 1.8f;
		int idProveedor=10, idCategoria = 100, existencias =10;
		// El "id" del producto que vamos a registrar aún no se conoce
		String sqlAltaProducto = "INSERT INTO productos (productoid,proveedorid,categoriaid,descripcion, preciounit,existencia) VALUES (?,?, ?,?, ?,?)";
		String sqlAltaCategoria = "INSERT INTO categorias(categoriaid, nombrecat) " + "VALUES (?, ?)";
		try {
			conexion = bds.getConnection();
			// conexion = DriverManager.getConnection(jdbcUrl,"postgres","bitnami");
			//Inicia transacción
			//conexion.setAutoCommit(false);
			sentencia = conexion.prepareStatement(sqlAltaProducto,
					PreparedStatement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1, 15);
			sentencia.setInt(2, idProveedor);
			sentencia.setInt(3, idCategoria);
			sentencia.setString(4, descripcionProducto);
			sentencia.setFloat(5, preciounit);
			sentencia.setInt(6, existencias);
			sentencia.executeUpdate();
			// Obtiene el id del producto que se acaba de registrar
			rs = sentencia.getGeneratedKeys();
			rs.next();
			int idProducto = rs.getInt(1);
			//sentencia.close();
			sentencia = conexion.prepareStatement(sqlAltaCategoria);
			idCategoria = 900;
			String nomCateg = "ferretería";
			sentencia.setInt(1, idCategoria);
			sentencia.setString(2, nomCateg);
			sentencia.executeUpdate();
			// Valida la transacción
			//conexion.commit();
            
		} catch (SQLException e) {
            /*try
            {
			    conexion.rollback();
            }*
            catch (SQLException e) { */
			e.printStackTrace();
		} finally {
                //conexion.setAutocommit(true);
 /*               if (conexion != null)
				    conexion.setAutoCommit(true);
				if (idGenerados != null)
                    idGenerados.close();
            	if (sentencia != null)		
                    sentencia.close();*/
                DbUtils.closeQuietly(rs);
                DbUtils.closeQuietly(sentencia);
                DbUtils.closeQuietly(conexion);
			} 
		}
}

