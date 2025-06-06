package org.yourcompany.Modelo;

/**
 * Clase abstracta que representa una persona con atributos comunes
 * como id, dni, nombre, email y telefono.
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */
public abstract class Personas
{
    /**
     * identificador unico del registro
     */
    protected int id;

    /**
     * dni del usuario
     */
    protected String DNI;

    /**
     * nombre completo del usuario
     */
    protected String nombre;

    /**
     * direccion de correo electronico
     */
    protected String email;

    /**
     * numero de telefono de contacto
     */
    protected String telefono;

    /**
     * Constructor vacio
     */
    public Personas()
    {

    }

    /**
     * Constructor con parametros
     * 
     * @param id identificador unico
     * @param DNI documento nacional de identidad
     * @param nombre nombre de la persona
     * @param email correo electronico
     * @param telefono numero de telefono
     */
    public Personas(int id, String DNI, String nombre, String email, String telefono)
    {
        this.id = id;
        this.DNI = DNI;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * obtiene el id del objeto
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * obtiene el dni del objeto
     *
     * @return dni
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * obtiene el nombre del objeto
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * obtiene el email del objeto
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * obtiene el telefono del objeto
     *
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * asigna un nuevo id al objeto
     *
     * @param id nuevo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * asigna un nuevo dni al objeto
     *
     * @param DNI nuevo dni
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     * asigna un nuevo nombre al objeto
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * asigna un nuevo email al objeto
     *
     * @param email nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * asigna un nuevo telefono al objeto
     *
     * @param telefono nuevo telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
