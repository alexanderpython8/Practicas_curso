package org.yourcompany.Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Clase para gestionar la tabla Contrata
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */

public class Contrata implements ITablas
{
    /**
     * Enum para representar el estado del contrato
     */
    public enum Estado
    {
        ACTIVO, PENDIENTE, VENCIDO
    }

    /**
     * id del inquilino asociado al contrato
     */
    private int id_inquilino;

    /**
     * codigo de la vivienda alquilada
     */
    private int codigo_vivienda;

    /**
     * precio del contrato de alquiler
     */
    private double precio;

    /**
     * estado actual del contrato
     */
    private Estado estado;

    /**
     * fecha de inicio del contrato en formato yyyy-MM-dd
     */
    private String fecha_inicio;

    /**
     * fecha de fin del contrato en formato yyyy-MM-dd
     */
    private String fecha_fin;

    /**
     * Constructor vacio
     */
    public Contrata() {}

    /**
     * Constructor con todos los parametros
     * 
     * @param codigo_vivienda codigo de la vivienda
     * @param estado estado del contrato
     * @param fecha_fin fecha de fin en formato yyyy-MM-dd
     * @param fecha_inicio fecha de inicio en formato yyyy-MM-dd
     * @param id_inquilino id del inquilino
     * @param precio precio del contrato
     */
    public Contrata(int codigo_vivienda, Estado estado, String fecha_fin, 
    String fecha_inicio, int id_inquilino, double precio) 
    {
        this.codigo_vivienda = codigo_vivienda;
        this.estado = estado;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.id_inquilino = id_inquilino;
        this.precio = precio;
    }

    /**
     * Constructor sin estado (puede usarse para insertar sin estado)
     * 
     * @param codigo_vivienda codigo de la vivienda
     * @param fecha_fin fecha de fin en formato yyyy-MM-dd
     * @param fecha_inicio fecha de inicio en formato yyyy-MM-dd
     * @param id_inquilino id del inquilino
     * @param precio precio del contrato
     */
    public Contrata(int codigo_vivienda, String fecha_fin, 
    String fecha_inicio, int id_inquilino, double precio) 
    {
        this.codigo_vivienda = codigo_vivienda;
        this.fecha_fin = fecha_fin;
        this.fecha_inicio = fecha_inicio;
        this.id_inquilino = id_inquilino;
        this.precio = precio;
    }

    /**
     * Inserta un nuevo Contrato en la base de datos
     */
    public void insertar() 
    {
        // código que puede lanzar SQLException
        try 
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_insert_contrata(?, ?, ?, ?, ?, ?) }");

            cs.setInt(1, this.id_inquilino);
            cs.setInt(2, this.codigo_vivienda);
            cs.setDouble(3, this.precio);

            try 
            {
                LocalDate fechaInicioLD = LocalDate.parse(this.fecha_inicio);
                LocalDate fechaFinLD = LocalDate.parse(this.fecha_fin);

                if (fechaFinLD.isBefore(fechaInicioLD)) 
                {
                    System.out.println("Error la fecha de fin no puede ser anterior a la fecha de inicio");
                    return;
                }

                Date fechaInicio = Date.valueOf(fechaInicioLD);
                Date fechaFin = Date.valueOf(fechaFinLD);

                cs.setDate(4, fechaInicio);
                cs.setDate(5, fechaFin);

                cs.registerOutParameter(6, Types.INTEGER);

                cs.execute();

                int hecho = cs.getInt(6);

                if (hecho == -1) 
                {
                    System.out.println("Error en el procedimiento");
                } 
                else if (hecho == 0) 
                {
                    System.out.println("No se pudo insertar el contrato");
                } 
                else 
                {
                    System.out.println("Contrato insertado con éxito");
                }

                cs.close();

            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto debe ser yyyy-MM-dd, por ejemplo: 2025-06-03");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Consulta un Contrato en la base de datos
     */
    @Override
    public void consultar()
    {
        // código que puede lanzar SQLException
        try
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_select_contrata(?, ?, ?, ?, ?, ?, ?) }");

            cs.setInt(1, this.id_inquilino);
            cs.setInt(2, this.codigo_vivienda);
            cs.registerOutParameter(3, Types.DECIMAL);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.registerOutParameter(5, Types.DATE);
            cs.registerOutParameter(6, Types.DATE);
            cs.registerOutParameter(7, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(7);

            if (hecho == -1)
            {
                System.out.println("Error en el procedimiento");
            }
            else if (hecho == 0)
            {
                System.out.println("No se encontro el contrato");
            }
            else
            {
                System.out.println("Precio: " + cs.getDouble(3));
                System.out.println("Estado: " + cs.getString(4));
                System.out.println("Fecha inicio: " + cs.getDate(5));
                System.out.println("Fecha fin: " + cs.getDate(6));
            }

            cs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Borra un Contrato en la base de datos
     */

    @Override
    public void borrar()
    {
        // código que puede lanzar SQLException
        try
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_delete_contrata(?, ?, ?) }");

            cs.setInt(1, this.id_inquilino);
            cs.setInt(2, this.codigo_vivienda);
            cs.registerOutParameter(3, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(3);

            if (hecho == -1)
            {
                System.out.println("Error en el procedimiento");
            }
            else if (hecho == 0)
            {
                System.out.println("No se encontro el contrato para borrar");
            }
            else
            {
                System.out.println("Contrato eliminado con exito");
            }

            cs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Modificacion del Contrato en la base de datos
     * 
     * @param modificacion campo a modificar (fecha_fin, fecha_inicio, precio)
     * @param dato valor nuevo en formato yyyy-MM-dd
     */
    @Override
    public void modificar(String modificacion, String dato)
    {
        // código que puede lanzar SQLException
        try
        {
            Conexion db = Conexion.getConexion();
            Connection conexion = db.getConnection();

            CallableStatement cs = conexion.prepareCall("{ call sp_update_contrata(?, ?, ?, ?, ?) }");

            cs.setInt(1, this.id_inquilino);
            cs.setInt(2, this.codigo_vivienda);
            cs.setString(3, modificacion);
            cs.setDate(4, Date.valueOf(dato));
            cs.registerOutParameter(5, Types.INTEGER);

            cs.execute();

            int hecho = cs.getInt(5);

            if (hecho == -1)
            {
                System.out.println("Error en el procedimiento");
            }
            else if (hecho == 0)
            {
                System.out.println("No se encontro el contrato para modificar");
            }
            else
            {
                System.out.println("Contrato modificado con exito");
            }

            cs.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    /** 
     * obtiene el id del inquilino
     * @return id del inquilino
     */

    public int getId_inquilino() {
        return id_inquilino;
    }

    /** 
     * obtiene el codigo de vivienda
     * @return codigo de la vivienda 
     * */
    public int getCodigo_vivienda() {
        return codigo_vivienda;
    }

    /** 
     * obtiene el precio
     * @return precio del contrato 
     * */
    public double getPrecio() {
        return precio;
    }

    /** 
     * obtiene el estado del contrato
     * @return estado del contrato 
    */
    public Estado getEstado() {
        return estado;
    }

    /**
     * obtiene la fecha de inicio del contrato
     *
     * @return fecha de inicio en formato yyyy-MM-dd
     */
    public String getFecha_inicio() {
        return fecha_inicio;
    }

    /**
     * obtiene la fecha de fin del contrato
     *
     * @return fecha de fin en formato yyyy-MM-dd
     */
    public String getFecha_fin() {
        return fecha_fin;
    }

    /**
     * asigna el id del inquilino al contrato
     *
     * @param id_inquilino establece el id del inquilino
     */
    public void setId_inquilino(int id_inquilino) {
        this.id_inquilino = id_inquilino;
    }

    /**
     * asigna el codigo de la vivienda al contrato
     *
     * @param codigo_vivienda establece el codigo de la vivienda
     */
    public void setCodigo_vivienda(int codigo_vivienda) {
        this.codigo_vivienda = codigo_vivienda;
    }

    /**
     * asigna el precio al contrato
     *
     * @param precio establece el precio del contrato
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * asigna el estado del contrato
     *
     * @param estado establece el estado del contrato
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * asigna la fecha de inicio al contrato
     *
     * @param fecha_inicio establece la fecha de inicio en formato yyyy-MM-dd
     */
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * asigna la fecha de fin al contrato
     *
     * @param fecha_fin establece la fecha de fin en formato yyyy-MM-dd
     */
    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
