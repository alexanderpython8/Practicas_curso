package org.yourcompany.Controlador;

import static org.yourcompany.Vista.Menu.Menu;

/**
 * Clase principal del sistema que permite gestionar las tablas de la base de datos
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */
public class Main 
{
    /**
     * El main que controla cuantas veces se repetira el menu hasta 
     * que el usuario lo decida
     * @param args
     */
    public static void main(String[] args) 
    {
        boolean salir = false;
        do 
        { 
            salir = Menu();
        } 
        while (!salir);
    }
}
