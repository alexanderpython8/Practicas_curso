package org.yourcompany.Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Clase para gestionar la tabla Inquilinos
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */

public class Inquilino extends Personas implements ITablas
{
    /**
     * 1 si tiene mascota el inquilino, 0 si no
     */
    private int mascota;

    /**
     * Constructor vacio
     */
    public Inquilino() {}

    /**
     * Constructor con datos del Inquilino
     * @param id id del propietario
     * @param DNI dni del propietario
     * @param nombre nombre del propietario
     * @param email email del propietario
     * @param telefono telefono del propietario
     * @param mascota 1 si tiene, 0 si no
     */
    public Inquilino(int mascota, int id, String DNI, String nombre, String email, String telefono) {
        super(id, DNI, nombre, email, telefono);
        this.mascota = mascota;
    }

    /**
     * Inserta un nuevo Inquilino en la base de datos
     */

    @Override
    public void insertar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();

            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_insert_inquilino(?, ?, ?, ?, ?, ?) }");

            cs.setString(1, this.DNI);
            cs.setString(2, this.nombre);
            cs.setString(3, this.email);
            cs.setString(4, this.telefono);
            cs.setInt(5, this.mascota);

            cs.registerOutParameter(6, Types.INTEGER);

            cs.execute();

            int idInsertado = cs.getInt(6);

            if (idInsertado == -1) 
            {
                System.out.println("No se pudo insertar el inquilino");
            } 
            else 
            {
                System.out.println("Inquilino insertado con id: " + idInsertado);
            }

            cs.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Consulta los Inquilino de la base de datos
     */

    @Override
    public void consultar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();

            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_select_inquilino(?, ?, ?, ?, ?, ?, ?) }");

            cs.setInt(1, this.id); 

            cs.registerOutParameter(2, Types.VARCHAR); 
            cs.registerOutParameter(3, Types.VARCHAR); 
            cs.registerOutParameter(4, Types.VARCHAR); 
            cs.registerOutParameter(5, Types.VARCHAR); 
            cs.registerOutParameter(6, Types.INTEGER);
            cs.registerOutParameter(7, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(7);

            if (hecho == -1) 
            {
                System.out.println("Error en el procedimiento");
            } 
            else if (hecho == 0) 
            {
                System.out.println("No existe un inquilino con ese id");
            } 
            else 
            {
                System.out.println("DNI: " + cs.getString(2));
                System.out.println("Nombre: " + cs.getString(3));
                System.out.println("Email: " + cs.getString(4));
                System.out.println("Teléfono: " + cs.getString(5));
                System.out.println("Mascota: " + cs.getInt(6));
            }

            cs.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Elimina los Inquilinos en la base de datos
     */

    @Override
    public void borrar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();

            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_delete_inquilino(?, ?) }");

            cs.setInt(1, this.id);

            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(2);

            if (hecho == -1) 
            {
                System.out.println("Error en el procedimiento");
            } 
            else if (hecho == 0) 
            {
                System.out.println("Inquilino no encontrado.");
            } 
            else 
            {
               System.out.println("Inquilino eliminado con existo");
            }

            cs.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Modifica un Inquilino en la base de datos
     * 
     * @param modificacion campo a modificar (dni, nombre, email, telefono, mascota) 
     * @param dato String para el dato, para agregar a la modificacion
     */

    @Override
    public void modificar(String modificacion, String dato) 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();

            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_update_inquilino(?, ?, ?, ?) }");

            cs.setInt(1, this.id); 
            cs.setString(2, modificacion); 
            cs.setString(3, dato);

            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(4);

            if (hecho == -1) 
            {
                System.out.println("Error en el procedimiento");
            } 
            else if (hecho == 0)
            {
                System.out.println("No existe un inquilino con ese nombre");
            } 
            else 
            {
                System.out.println("Inquilino actualizado con exito");
            }

            cs.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve el valor de mascota
     * @return 1 si tiene, 0 si no
     */
    public int getMascota() {
        return mascota;
    }

    /**
     * Establece el valor de mascota
     * @param mascota 1 si tiene, 0 si no
     */
    public void setMascota(int mascota) {
        this.mascota = mascota;
    }

}
