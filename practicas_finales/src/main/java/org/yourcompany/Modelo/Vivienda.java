package org.yourcompany.Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Clase para gestionar la tabla Vivienda
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */

public class Vivienda implements ITablas
{
    /**
     * codigo unico de la vivienda
     */
    private int codigo;

    /**
     * id del propietario de la vivienda
     */
    private int id_propietario;

    /**
     * superficie de la vivienda (por ejemplo, en metros cuadrados) en formato
     * string
     */
    private String superficie;

    /**
     * descripcion detallada de la vivienda
     */
    private String descripcion;

    /**
     * indica si se permiten mascotas (1 = si, 0 = no)
     */
    private int mascota;

    /**
     * precio mensual del alquiler
     */
    private double precio_mensual;

    /**
     * direccion completa de la vivienda
     */
    private String direccion;

    /**
     * Constructor vacio
     */
    public Vivienda() {}

    /**
     * Constructor con todos los parametros
     * 
     * @param codigo codigo identificativo de la vivienda
     * @param id_propietario identificador del propietario
     * @param superficie superficie de la vivienda
     * @param descripcion descripcion de la vivienda
     * @param mascota 1 si permiten mascotas, 0 si no permiten
     * @param precio_mensual precio mensual del alquiler
     * @param direccion direccion de la vivienda
     */
    public Vivienda(int codigo, int id_propietario, String superficie, String descripcion, 
        int mascota, double precio_mensual, String direccion)
    {
        this.codigo = codigo;
        this.id_propietario = id_propietario;
        this.superficie = superficie;
        this.descripcion = descripcion;
        this.mascota = mascota;
        this.precio_mensual = precio_mensual;
        this.direccion = direccion;
    }

    /**
     * Inserta un nueva Vivienda en la base de datos
     */
    @Override
    public void insertar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_insert_vivienda(?, ?, ?, ?, ?, ?, ?, ?) }");

            cs.setInt(1, this.codigo);
            cs.setInt(2, this.id_propietario);
            cs.setString(3, this.superficie);
            cs.setString(4, this.descripcion);
            cs.setInt(5, this.mascota);
            cs.setDouble(6, this.precio_mensual);
            cs.setString(7, this.direccion);

            cs.registerOutParameter(8, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(8);

            if (hecho == -1) 
            {
                System.out.println("Error en el procedimiento");
            } 
            else if (hecho == 0) 
            {
                System.out.println("No se pudo insertar la vivienda");
            } 
            else 
            {
                System.out.println("Vivienda insertada con exito");
            }

            cs.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Consulta una Vivienda en la base de datos
     */
    @Override
    public void consultar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_select_vivienda(?, ?, ?, ?, ?, ?, ?, ?) }");

            cs.setInt(1, this.codigo);

            cs.registerOutParameter(2, Types.INTEGER);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.INTEGER);
            cs.registerOutParameter(6, Types.DECIMAL);
            cs.registerOutParameter(7, Types.VARCHAR);
            cs.registerOutParameter(8, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(8);

            if (hecho == -1) 
            {
                System.out.println("Error en el procedimiento");
            } 
            else if (hecho == 0) 
            {
                System.out.println("No existe una vivienda con ese codigo e id de propietario");
            } 
            else 
            {
                System.out.println("Id propietario: " + cs.getInt(2));
                System.out.println("Superficie: " + cs.getString(3));
                System.out.println("Descripcion: " + cs.getString(4));
                System.out.println("Mascotas: " + cs.getInt(5));
                System.out.println("Precio mensual: " + cs.getDouble(6));
                System.out.println("Direccion: " + cs.getString(7));
            }

            cs.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * Borra una Vivienda en la base de datos
     */
    @Override
    public void borrar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_delete_vivienda(?, ?) }");

            cs.setInt(1, this.codigo);

            cs.registerOutParameter(2, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(2);

            if (hecho == -1) 
            {
                System.out.println("Error en el procedimiento");
            } 
            else if (hecho == 0) 
            {
                System.out.println("No se encontro la vivienda");
            } 
            else 
            {
                System.out.println("Vivienda eliminada con exito");
            }

            cs.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * 
     * Modificar un Contrato en la base de datos
     *
     * @param modificacion campo a modificar (superficie, descripcion, mascota,
     * precio_mensual, mascota)
     * @param dato nuevo valor que se quiere asignar
     */
    @Override
    public void modificar(String modificacion, String dato) 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_update_vivienda(?, ?, ?, ?) }");

            cs.setInt(1, this.codigo);
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
                System.out.println("No se encontro la vivienda para modificar");
            } 
            else 
            {
                System.out.println("Vivienda modificada con exito");
            }

            cs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * obtiene el codigo de la vivienda
     *
     * @return codigo de la vivienda
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * obtiene el id del propietario
     *
     * @return id del propietario
     */
    public int getId_propietario() {
        return id_propietario;
    }

    /**
     * obtiene la superficie como cadena
     *
     * @return superficie en formato string
     */
    public String getSuperficie() {
        return superficie;
    }

    /**
     * obtiene la descripcion de la vivienda
     *
     * @return descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * indica si se permiten mascotas (1 si, 0 no)
     *
     * @return 1 si se permiten mascotas, 0 si no
     */
    public int isMascota() {
        return mascota;
    }

    /**
     * obtiene el precio mensual de la vivienda
     *
     * @return precio mensual
     */
    public double getPrecio_mensual() {
        return precio_mensual;
    }

    /**
     * obtiene la direccion de la vivienda
     *
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * asigna un nuevo codigo a la vivienda
     *
     * @param codigo nuevo codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * asigna un nuevo id de propietario
     *
     * @param id_propietario nuevo id
     */
    public void setId_propietario(int id_propietario) {
        this.id_propietario = id_propietario;
    }

    /**
     * asigna una nueva superficie
     *
     * @param superficie nueva superficie
     */
    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    /**
     * asigna una nueva descripcion
     *
     * @param descripcion nueva descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * establece si se permiten mascotas (1 para permitir, 0 para no)
     *
     * @param mascota 1 para permitir, 0 para no permitir
     */
    public void setMascota(int mascota) {
        this.mascota = mascota;
    }

    /**
     * asigna un nuevo precio mensual
     *
     * @param precio_mensual nuevo precio
     */
    public void setPrecio_mensual(double precio_mensual) {
        this.precio_mensual = precio_mensual;
    }

    /**
     * asigna una nueva direccion
     *
     * @param direccion nueva direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

