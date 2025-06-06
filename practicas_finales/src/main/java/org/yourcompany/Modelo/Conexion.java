package org.yourcompany.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para manejar la conexion unica a la base de datos mysql
 * utilizando el patron singleton
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */
public class Conexion 
{  
    
    /**
     * instancia unica de la conexion (patron singleton)
     */
    private static Conexion conexionUnica = null;

    /**
     * conexion activa con la base de datos
     */
    private Connection conexion;

    /**
     * Constructor privado para evitar multiples instancias
     * 
     * @throws SQLException si no se pudo acceder a la base de datos
     */
    private Conexion() throws SQLException
    {
        // código que puede lanzar SQLException
        try 
        {
            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Alquilaria", "root", "");

            if(this.conexion.isClosed())
            {
                throw new SQLException("error en la conexion: la conexion esta cerrada");
            }
        } 
        catch (Exception e) 
        {
            throw new SQLException("error en la conexion: " + e.getMessage());
        }
    }

    /**
     * Obtiene la instancia unica de la clase conexion
     * 
     * @return objeto conexion
     * @throws SQLException si no se pudo acceder a la base de datos
     */
    public static Conexion getConexion() throws SQLException 
    {
        // código que puede lanzar SQLException
        if (conexionUnica == null) 
        {
            conexionUnica = new Conexion();
        }
        return conexionUnica;
    }

    /**
     * Obtiene el objeto connection asociado
     * 
     * @return objeto connection
     */
    public Connection getConnection()
    {
        return conexion;
    }

    /**
     * Cierra la conexion si esta abierta
     * 
     * @throws SQLException si no se pudo acceder a la base de datos
     */
    public void cerrarConexion() throws SQLException
    {
        // código que puede lanzar SQLException
        if (conexion != null && !conexion.isClosed()) 
        {
            conexion.close();   
        }
    }
}
