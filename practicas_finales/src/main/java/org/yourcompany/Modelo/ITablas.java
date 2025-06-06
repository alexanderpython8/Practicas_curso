package org.yourcompany.Modelo;

/**
 * Interfaz que define las operaciones basicas que deben implementar
 * las clase que representan las tablas de base de datos
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */
public interface ITablas 
{   
    /**
     * Inserta un registro en la tabla correspondiente.
     */
    public void insertar();

    /**
     * Consulta un registro en la tabla correspondiente.
     */
    public void consultar();

    /**
     * Elimina un registro en la tabla correspondiente.
     */
    public void borrar();

    /**
     * Modifica un campo especifico de un registro en la tabla correspondiente
     * 
     * @param modificacion nombre del campo a modificar
     * @param dato nuevo valor que se asignara al campo
     */
    public void modificar(String modificacion, String dato);
}
