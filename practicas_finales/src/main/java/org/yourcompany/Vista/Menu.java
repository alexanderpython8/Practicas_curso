package org.yourcompany.Vista;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.yourcompany.Modelo.Contrata;
import org.yourcompany.Modelo.Inquilino;
import org.yourcompany.Modelo.Personas;
import org.yourcompany.Modelo.Propietario;
import org.yourcompany.Modelo.Tipo_vivienda;
import org.yourcompany.Modelo.Vivienda;

/**
 * Clase principal del sistema que permite gestionar las tablas de la base de datos
 * 
 * @author Alejandro Guaman Zuñiga
 * @version 1.0
 * @since 2025-06-04
 */
public class Menu 
{
    /**
     * Se pide al usuario lo que desea insertar, mostrar, eliminar o 
     * modificar de las tablas
     * @return un true para salir del sistema y un false para continuar en el sistema
     */
    public static boolean Menu() 
    {
        Scanner rd = new Scanner(System.in);

        int opcion = 0, id = 0, mascota = 0, codigo = 0, id_propietario = 0, id_inquilino = 0, codigo_vivienda = 0;
        double precio_mensual = 0, precio = 0;
        String DNI = "", nombre = "", email = "", telefono = "", modificacion = "", dato = "", superficie = "", descripcion = "", direccion = "", tipo = "", 
            fecha_inicio = "", fecha_fin = "";
        Personas personas;
        Vivienda viviendas;
        Tipo_vivienda tipo_vivienda;
        Contrata contratos;

        // Seleccion de las tablas
        while (true) 
        {
            try 
            {
                System.out.println();
                System.out.println("--------- Tablas ----------");
                System.out.println("1. Propietario");
                System.out.println("2. Inquilino");
                System.out.println("3. Vivienda");
                System.out.println("4. Contrata");
                System.out.println("5. Salir del programa");
                System.out.println("---------------------------");
                System.out.print("Que tabla deseas elegir? ");
                opcion = rd.nextInt();
                rd.nextLine();

                if (opcion < 1 || opcion > 5) 
                {
                    throw new IllegalArgumentException("La opcion seleccionada no esta en el menu");
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un numero entre 1 y 5");
                rd.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Se ha introducido un caracter erroneo");
            }
        }
        switch (opcion) 
        {
            case 1:
                /**
                 * Seccion para gestionar la tabla Propietario
                 */
                while (true) 
                {
                    try 
                    {
                        System.out.println();
                        System.out.println("---- Consultas propietario ----");
                        System.out.println("1. Insertar");
                        System.out.println("2. Consultar");
                        System.out.println("3. Modificar");
                        System.out.println("4. Borrar");
                        System.out.println("5. Volver al menu principal");
                        System.out.println("-------------------------------");
                        System.out.print("Que consulta deseas elegir: ");

                        opcion = rd.nextInt();
                        rd.nextLine();

                        if (opcion < 1 || opcion > 5) 
                        {
                            throw new IllegalArgumentException("La opcion seleccionada no esta en el menu");
                        }

                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Debes introducir un numero entre 1 y 5");
                        rd.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Se ha introducido un caracter erroneo");
                    }
                }

                System.out.println();

                switch (opcion) 
                {
                    case 1:
                        /**
                         * Inserta un nuevo propietario
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Insertar DNI: ");
                                DNI = rd.nextLine();

                                System.out.print("Insertar nombre: ");
                                nombre = rd.nextLine();

                                System.out.print("Insertar email: ");
                                email = rd.nextLine();

                                System.out.print("Insertar telefono: ");
                                telefono = rd.nextLine();

                                personas = new Propietario(id, DNI, nombre, email, telefono);

                                ((Propietario) personas).insertar();

                                break;
                            } catch (Exception e) {
                                System.out.println("Se ha introducido un caracter erroneo");
                            }
                        }

                        break;
                    case 2:
                        /**
                         * Consulta un propietario
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Introduce el id del propietario que quieras consultar: ");
                                id = rd.nextInt();
                                rd.nextLine();

                                personas = new Propietario();

                                personas.setId(id);

                                ((Propietario) personas).consultar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Se ha introducido un caracter erroneo");
                            }
                        }
                        break;
                    case 3:
                        /**
                         * Modifica un propietario
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Introduce el id del propietario que quieras modificar: ");
                                id = rd.nextInt();
                                rd.nextLine();

                                System.out.println("Que valor quieres modificar");
                                System.out.print("(DNI, nombre, email, telefono): ");
                                modificacion = rd.nextLine();

                                modificacion = modificacion.toLowerCase();

                                System.out.print("Introduce el nuevo valor: ");
                                dato = rd.nextLine();

                                personas = new Propietario();

                                personas.setId(id);

                                ((Propietario) personas).modificar(modificacion, dato);

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Se ha introducido un caracter erroneo");
                            }
                        }
                        break;
                    case 4:
                        /**
                         * Elimina un propietario
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Introduce el id del propietario que quieras eliminar: ");
                                id = rd.nextInt();
                                rd.nextLine();

                                personas = new Propietario();

                                personas.setId(id);

                                ((Propietario) personas).borrar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Se ha introducido un caracter erroneo");
                            }
                        }
                        break;
                    case 5:
                        // Vuelve al menu principal
                        
                        System.out.println();
                        break;
                    default:
                        throw new AssertionError();
                }

                break;
            case 2:
                /**
                 * Seccion para gestionar la tabla Inquilino
                 */
                while (true) 
                {
                    try 
                    {
                        System.out.println();
                        System.out.println("---- Consultas inquilino ----");
                        System.out.println("1. Insertar");
                        System.out.println("2. Consultar");
                        System.out.println("3. Modificar");
                        System.out.println("4. Borrar");
                        System.out.println("5. Volver al menu principal");
                        System.out.println("-------------------------------");
                        System.out.print("Que consulta deseas elegir: ");

                        opcion = rd.nextInt();
                        rd.nextLine();

                        if (opcion < 1 || opcion > 5) 
                        {
                            throw new IllegalArgumentException("La opcion seleccionada no esta en el menu");
                        }

                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Debes introducir un numero entre 1 y 5");
                        rd.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Se ha introducido un caracter erroneo");
                    }
                }

                System.out.println();

                switch (opcion) {
                    case 1:
                        /**
                         * Inserta un nuevo Inquilino
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Insertar DNI: ");
                                DNI = rd.nextLine();

                                System.out.print("Insertar nombre: ");
                                nombre = rd.nextLine();

                                System.out.print("Insertar email: ");
                                email = rd.nextLine();

                                System.out.print("Insertar telefono: ");
                                telefono = rd.nextLine();

                                System.out.print("Tiene mascotas (1 = true / 0 = false): ");
                                mascota = rd.nextInt();
                                rd.nextLine();

                                if (mascota != 1 && mascota != 0) 
                                {
                                    throw new IllegalArgumentException(
                                            "El valor de 'mascota' solo puede ser 1 (true) o 0 (false)");
                                }

                                personas = new Inquilino(mascota, id, DNI, nombre, email, telefono);

                                ((Inquilino) personas).insertar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Se ha introducido un caracter erroneo");
                            }
                        }
                        break;
                    case 2:
                        /**
                         * Consula un Inquilino
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Introduce el id del inquilino que quieras consultar: ");
                                id = rd.nextInt();
                                rd.nextLine();

                                personas = new Inquilino();

                                personas.setId(id);

                                ((Inquilino) personas).consultar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Error por motivo no comprobado");
                            }
                        }
                        break;
                    case 3:
                        /**
                         * Modifica un Inquilino
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Introduce el id del inquilino que quieras modificar: ");
                                id = rd.nextInt();
                                rd.nextLine();

                                System.out.println("Que valor quieres modificar");
                                System.out.print("(DNI, nombre, email, telefono, mascota): ");
                                modificacion = rd.nextLine();

                                modificacion = modificacion.toLowerCase();

                                System.out.print("Introduce el nuevo valor: ");
                                dato = rd.nextLine();

                                if (modificacion.equals("mascota")) 
                                {
                                    if (!dato.equals("1") && !dato.equals("0")) 
                                    {
                                        throw new IllegalArgumentException("El valor de 'mascota' solo puede ser 1 (true) o 0 (false)");
                                    }
                                }

                                personas = new Inquilino();

                                personas.setId(id);

                                ((Inquilino) personas).modificar(modificacion, dato);

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Error por motivo no comprobado");
                            }
                        }
                        break;
                    case 4:
                        /**
                         * Elimina un Inquilino
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Introduce el id del inquilino que quieras eliminar: ");
                                id = rd.nextInt();
                                rd.nextLine();

                                personas = new Inquilino();

                                personas.setId(id);

                                ((Inquilino) personas).borrar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Error por motivo no comprobado");
                            }
                        }
                        break;
                    case 5:
                        // Vuelve al menu principal

                        System.out.println();

                        break;
                    default:
                        break;
                }
                break;
            case 3:
                /**
                 * Seccion para gestionar la tabla Vivienda
                 */
                while (true) 
                {
                    try 
                    {
                        System.out.println();
                        System.out.println("---- Consultas vivienda ----");
                        System.out.println("1. Insertar");
                        System.out.println("2. Consultar");
                        System.out.println("3. Modificar");
                        System.out.println("4. Borrar");
                        System.out.println("5. Volver al menu principal");
                        System.out.println("-------------------------------");
                        System.out.print("Que consulta deseas elegir: ");

                        opcion = rd.nextInt();
                        rd.nextLine();

                        if (opcion < 1 || opcion > 5) 
                        {
                            throw new IllegalArgumentException("La opcion seleccionada no esta en el menu");
                        }

                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Debes introducir un numero entre 1 y 5");
                        rd.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Se ha introducido un caracter erroneo");
                    }
                }

                System.out.println();

                switch (opcion) 
                {
                    case 1:
                        /**
                         * Inserta un nueva Vivienda
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Insertar codigo: ");
                                codigo = rd.nextInt();
                                rd.nextLine();

                                System.out.print("Insertar id_propietario: ");
                                id_propietario = rd.nextInt();
                                rd.nextLine();

                                System.out.print("Insertar superficie: ");
                                superficie = rd.nextLine();

                                System.out.print("Insertar descripcion: ");
                                descripcion = rd.nextLine();

                                System.out.print("Tiene mascotas (1 = true / 0 = false): ");
                                mascota = rd.nextInt();
                                rd.nextLine();

                                if (mascota != 1 && mascota != 0) 
                                {
                                    throw new IllegalArgumentException("El valor de 'mascota' solo puede ser 1 (true) o 0 (false)");
                                }

                                System.out.print("Insertar precio mensual: ");
                                precio_mensual = rd.nextDouble();
                                rd.nextLine();

                                System.out.print("Insertar direccion: ");
                                direccion = rd.nextLine();

                                System.out.print("Que tipo de vivienda sera: ");
                                tipo = rd.nextLine();

                                viviendas = new Vivienda(codigo, id_propietario, superficie, descripcion, mascota, precio_mensual, direccion);
                                tipo_vivienda = new Tipo_vivienda(codigo, tipo);

                                viviendas.insertar();
                                tipo_vivienda.insertar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Se ha introducido un caracter erroneo");
                            }
                        }
                        break;
                    case 2:
                        /**
                         * Consulta una Vivienda
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Introduce el codigo de la vivienda que quieres consultar: ");
                                codigo = rd.nextInt();
                                rd.nextLine();

                                viviendas = new Vivienda();
                                tipo_vivienda = new Tipo_vivienda();

                                viviendas.setCodigo(codigo);
                                tipo_vivienda.setCodigo_vivienda(codigo);

                                viviendas.consultar();
                                tipo_vivienda.consultar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Error por motivo no comprobado");
                            }
                        }

                        break;
                    case 3:
                        /**
                         * Modifica una Vivienda
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Introduce el codigo de la vivienda que quieres modificar: ");
                                codigo = rd.nextInt();
                                rd.nextLine();

                                System.out.println("Que valor quieres modificar");
                                System.out.print("(superficie, descripcion, mascotas, precio_mensual, direccion, tipo): ");
                                modificacion = rd.nextLine();

                                modificacion = modificacion.toLowerCase();

                                System.out.print("Introduce el nuevo valor: ");
                                dato = rd.nextLine();

                                if (modificacion.equals("tipo")) 
                                {
                                    tipo_vivienda = new Tipo_vivienda();

                                    tipo_vivienda.setCodigo_vivienda(codigo);

                                    tipo_vivienda.modificar(modificacion, dato);
                                } 
                                else 
                                {
                                    if (modificacion.equals("mascota")) 
                                    {
                                        if (!dato.equals("1") && !dato.equals("0")) 
                                        {
                                            throw new IllegalArgumentException("El valor de 'mascota' solo puede ser 1 (true) o 0 (false)");
                                        }
                                    }

                                    viviendas = new Vivienda();

                                    viviendas.setCodigo(codigo);

                                    viviendas.modificar(modificacion, dato);
                                }

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Error por motivo no comprobado");
                            }
                        }
                        break;
                    case 4:
                        /**
                         * Modifica una Vivienda
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Introduce el codigo de vivienda que quieras eliminar: ");
                                codigo = rd.nextInt();
                                rd.nextLine();

                                viviendas = new Vivienda();
                                tipo_vivienda = new Tipo_vivienda();

                                viviendas.setCodigo(codigo);
                                tipo_vivienda.setCodigo_vivienda(codigo);

                                viviendas.borrar();
                                tipo_vivienda.borrar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Error por motivo no comprobado");
                            }
                        }
                        break;
                    case 5:
                        // Vuelve al menu principal

                        System.out.println();
                        break;
                
                    default:
                        break;
                }
                break;
            case 4:
                /**
                 * Seccion para gestionar la tabla Contrato
                 */
                while (true) 
                {
                    try 
                    {
                        System.out.println();
                        System.out.println("---- Consultas contrata ----");
                        System.out.println("1. Insertar");
                        System.out.println("2. Consultar");
                        System.out.println("3. Modificar");
                        System.out.println("4. Borrar");
                        System.out.println("5. Volver al menu principal");
                        System.out.println("-------------------------------");
                        System.out.print("Que consulta deseas elegir: ");

                        opcion = rd.nextInt();
                        rd.nextLine();

                        if (opcion < 1 || opcion > 5) 
                        {
                            throw new IllegalArgumentException("La opcion seleccionada no esta en el menu");
                        }

                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Debes introducir un numero entre 1 y 5");
                        rd.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Se ha introducido un caracter erroneo");
                    }
                }

                System.out.println();

                switch (opcion) 
                {
                    case 1:
                        /**
                         * Inserta un nuevo Contrato
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.print("Insertar codigo codigo vivienda: ");
                                codigo_vivienda = rd.nextInt();
                                rd.nextLine();

                                System.out.print("Insertar codigo id inquilino: ");
                                id_inquilino = rd.nextInt();
                                rd.nextLine();

                                System.out.print("Insertar fecha inicio: ");
                                fecha_inicio = rd.nextLine();

                                System.out.print("Insertar fecha fin: ");
                                fecha_fin = rd.nextLine();

                                System.out.print("Insertar precio: ");
                                precio = rd.nextDouble();
                                rd.nextLine();

                                contratos = new Contrata(codigo_vivienda, fecha_fin, fecha_inicio, id_inquilino, precio);

                                contratos.insertar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Se ha introducido un caracter erroneo");
                            }
                        }
                        break;
                    case 2:
                        /**
                         * Consulta un Contrato
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.println("Porfavor introduce las dos claves para poder ver el contrato");
                                System.out.print("Introduce el id del inquilino: ");
                                id_inquilino = rd.nextInt();
                                rd.nextLine();

                                System.out.print("Introduce el codigo de vivienda: ");
                                codigo_vivienda = rd.nextInt();
                                rd.nextLine();

                                contratos = new Contrata();

                                contratos.setCodigo_vivienda(codigo_vivienda);
                                contratos.setId_inquilino(id_inquilino);

                                contratos.consultar();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Error por motivo no comprobado");
                            }
                        }
                        break;
                    case 3:
                        /**
                         * Modifica un Contrato
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.println("Porfavor introduce las dos claves para poder modificarlo");
                                System.out.print("Introduce el id del inquilino: ");
                                id_inquilino = rd.nextInt();
                                rd.nextLine();

                                System.out.print("Introduce el codigo de vivienda: ");
                                codigo_vivienda = rd.nextInt();
                                rd.nextLine();

                                System.out.println("Que valor quieres modificar");
                                System.out.print("(fecha_fin, fecha_inicio, precio): ");
                                modificacion = rd.nextLine();

                                modificacion = modificacion.toLowerCase();

                                System.out.print("Introduce el nuevo valor: ");
                                dato = rd.nextLine();

                                contratos = new Contrata();

                                contratos.setCodigo_vivienda(codigo_vivienda);
                                contratos.setId_inquilino(id_inquilino);

                                contratos.modificar(modificacion, dato);

                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("La fecha debe tener el formato yyyy-MM-dd (por ejemplo: 2025-05-30)");
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Error por motivo no comprobado");
                            }
                        }
                        break;
                    case 4:
                        /**
                         * Elimina un Contrato
                         */
                        while (true) 
                        {
                            try 
                            {
                                System.out.println("Porfavor introduce las dos claves para poder eliminarlo");
                                System.out.print("Introduce el id del inquilino: ");
                                id_inquilino = rd.nextInt();
                                rd.nextLine();

                                System.out.print("Introduce el codigo de vivienda: ");
                                codigo_vivienda = rd.nextInt();
                                rd.nextLine();

                                contratos = new Contrata();

                                contratos.setCodigo_vivienda(codigo_vivienda);
                                contratos.setId_inquilino(id_inquilino);

                                contratos.borrar();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Debes introducir un numero valido");
                                rd.nextLine();
                            } catch (Exception e) {
                                System.out.println("Error por motivo no comprobado");
                            }
                        }
                        break;
                    case 5:
                        // Vuelve al menu principal

                        System.out.println();

                        break;
                    default:
                        break;
                }
                break;
            case 5: 
                // Salida del sistema

                System.out.println();
                System.out.println("Saliendo del sistema...");
                return true;

            default:
                throw new AssertionError();
        }

        return false;
    }
}