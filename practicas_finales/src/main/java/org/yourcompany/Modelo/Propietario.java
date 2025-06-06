package org.yourcompany.Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Clase para gestionar la tabla Propietario
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */
public class Propietario extends Personas implements ITablas {

    /**
     * constructor vacio
     */
    public Propietario() {}

    /**
     * constructor con datos del propietario
     * @param id id del propietario
     * @param DNI dni del propietario
     * @param nombre nombre del propietario
     * @param email email del propietario
     * @param telefono telefono del propietario
     */
    public Propietario(int id, String DNI, String nombre, String email, String telefono) {
        super(id, DNI, nombre, email, telefono);
    }

    /**
     * inserta un nuevo propietario en la base de datos
     */
    @Override
    public void insertar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{call sp_insert_propietario(?, ?, ?, ?, ?)}");

            cs.setString(1, this.DNI);
            cs.setString(2, this.nombre);
            cs.setString(3, this.email);
            cs.setString(4, this.telefono);
            cs.registerOutParameter(5, Types.INTEGER);

            cs.execute();

            int nuevoid = cs.getInt(5);

            if (nuevoid == -1) 
            {
                System.out.println("error al insertar el propietario");
            } 
            else 
            {
                System.out.println("el nuevo propietario tiene como id: " + nuevoid);
            }

            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * consulta los datos del propietario segun su id
     */
    @Override
    public void consultar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{call sp_select_propietario(?, ?, ?, ?, ?, ?)}");

            cs.setInt(1, this.id);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.VARCHAR);
            cs.registerOutParameter(6, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(6);

            if (hecho == 0) 
            {
                System.out.println("no existe ningun propietario con ese id");
            } 
            else if (hecho == -1) 
            {
                System.out.println("error al introducir los parametros");
            } 
            else 
            {
                System.out.println("dni: " + cs.getString(2) + " nombre: " + cs.getString(3));
                System.out.println("email: " + cs.getString(4) + " telefono: " + cs.getString(5));
                System.out.println("usuario mostrado correctamente");
            }

            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * borra el propietario segun su id
     */
    @Override
    public void borrar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{call sp_delete_propietario(?, ?)}");

            cs.setInt(1, this.id);
            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(2);

            if (hecho == -1) 
            {
                System.out.println("error al borrar el propietario");
            } 
            else 
            {
                System.out.println("propietario borrado con exito");
            }

            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * modifica un campo del propietario
     * @param modificacion campo a modificar (dni, nombre, email, telefono)
     * @param dato nuevo valor
     */
    @Override
    public void modificar(String modificacion, String dato) 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{call sp_update_propietario(?, ?, ?, ?)}");

            cs.setInt(1, this.id);
            cs.setString(2, modificacion);
            cs.setString(3, dato);
            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(4);

            if (hecho == -1) 
            {
                System.out.println("error al modificar el propietario");
            } 
            else if (hecho == 0) 
            {
                System.out.println("no se ha encontrado ningun propietario con ese id");
            } 
            else {
                System.out.println("propietario modificado con exito");
            }

            cs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
