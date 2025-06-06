package org.yourcompany.Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Clase para gestionar la tabla Tipo Vivienda
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */
public class Tipo_vivienda implements ITablas 
{
    /**
     * codigo unico de la vivienda
     */
    private int codigo_vivienda;

    /**
     * tipo de vivienda (por ejemplo: piso, casa, estudio)
     */
    private String tipo;

    /**
     * Constructor vacio
     */
    public Tipo_vivienda() {}

    /**
     * Constructor con todos los parametros
     * 
     * @param codigo_vivienda codigo de la vivienda
     * @param tipo tipo de la vivienda
     */
    public Tipo_vivienda(int codigo_vivienda, String tipo)
    {
        this.codigo_vivienda = codigo_vivienda;
        this.tipo = tipo;
    }

    /**
     * Inserta un nuevo tipo de vivienda en la base de datos despues de insertarse la
     * Vivienda del que pertenece
     */
    @Override
    public void insertar()
    {
        // código que puede lanzar SQLException
        try
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_insert_tipo(?, ?, ?) }");

            cs.setInt(1, this.codigo_vivienda);
            cs.setString(2, this.tipo);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(3);

            if (hecho == -1)
            {
                System.out.println("error en el procedimiento");
            }
            else if (hecho == 0)
            {
                System.out.println("no se pudo insertar el tipo");
            }
            else
            {
                System.out.println("tipo insertado con exito");
            }

            cs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Consulta un tipo de vivienda en la base de datos despues de consultarse la
     * Vivienda del que pertenece
     */
    @Override
    public void consultar()
    {
        // código que puede lanzar SQLException
        try
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_select_tipo(?, ?, ?) }");

            cs.setInt(1, this.codigo_vivienda);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(3);

            if (hecho == -1)
            {
                System.out.println("error en el procedimiento");
            }
            else if (hecho == 0)
            {
                System.out.println("no existe un tipo de vivienda con ese codigo");
            }
            else
            {
                System.out.println("tipo de vivienda: " + cs.getString(2));
            }

            cs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Borra un tipo de vivienda de la base de datos despues de eliminarse la
     * Vivienda del que pertenece
     */
    @Override
    public void borrar()
    {
        // código que puede lanzar SQLException
        try
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_delete_tipo(?, ?) }");

            cs.setInt(1, this.codigo_vivienda);
            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(2);

            if (hecho == -1)
            {
                System.out.println("error en el procedimiento");
            }
            else if (hecho == 0)
            {
                System.out.println("no se encontro el tipo para borrar");
            }
            else
            {
                System.out.println("tipo eliminado con exito");
            }

            cs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Modifica el tipo de vivienda en la base de datos
     * 
     * @param modificacion nombre del campo a modificar no se usa ya que solo se puede modificar un parametro
     * @param dato nuevo valor para el tipo
     */
    @Override
    public void modificar(String modificacion, String dato)
    {
        // código que puede lanzar SQLException
        try
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_update_tipo(?, ?, ?) }");

            cs.setInt(1, this.codigo_vivienda);
            cs.setString(2, dato);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(3);

            if (hecho == -1)
            {
                System.out.println("error en el procedimiento");
            }
            else if (hecho == 0)
            {
                System.out.println("no se encontro el tipo para modificar");
            }
            else
            {
                System.out.println("tipo modificado con exito");
            }

            cs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * obtiene el codigo de vivienda
     * 
     * @return codigo de la vivienda
     */
    public int getCodigo_vivienda() {
        return codigo_vivienda;
    }

    /**
     * obtiene el tipo de vivienda
     * 
     * @return tipo de la vivienda
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * establece el codigo de vivienda
     * 
     * @param codigo_vivienda nuevo codigo
     */
    public void setCodigo_vivienda(int codigo_vivienda) {
        this.codigo_vivienda = codigo_vivienda;
    }

    /**
     * establece el tipo vivienda
     * 
     * @param tipo nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}