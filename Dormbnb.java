import java.util.ArrayList;
import java.util.Scanner;

public class Dormbnb {
    public static void main(String[] args) {
        System.out.println("*********************");
        System.out.println("*  _        ___ _     __ __  _  _ ___ _ _ _   *");
        System.out.println("*  \\ \\      / / _| |   / _/ _ \\|  \\/  | ___| | | |  *");
        System.out.println("*   \\ \\ /\\ / /|  _| | |  | |  | | | | |\\/| |  _| | | | |  *");
        System.out.println("*    \\ V  V / | |_| |_| || || | |  | | |_|||_|  *");
        System.out.println("*     \\/\\/  |__|_\\_\\_/||  ||__(||)  *");
        System.out.println("*********************");

        // Crear una base de datos para almacenar cuentas de usuarios
        ArrayList<Usuario> baseDeDatos = new ArrayList<>();

        // Crear un escáner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Elige una opción:");
            System.out.println("1. Crear una cuenta");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            int eleccion = scanner.nextInt();

            if (eleccion == 1) {
                // El usuario desea crear una cuenta
                System.out.println("Ingresa tu nombre:");
                String nombre = scanner.next();
                System.out.println("Ingresa tu correo electrónico:");
                String correo = scanner.next();
                System.out.println("Ingresa tu contraseña:");
                String contrasena = scanner.next();
                System.out.println("Ingresa tu fecha de nacimiento:");
                String fechaNacimiento = scanner.next();

                System.out.println("¿Eres un Vendedor o un Comprador? (V/C):");
                String tipoUsuario = scanner.next();

                if (tipoUsuario.equalsIgnoreCase("V")) {
                    // Vendedor
                    System.out.println("Ingresa la ubicación que ofreces:");
                    String ubicacion = scanner.next();
                    System.out.println("Ingresa el tipo de propiedad que ofreces:");
                    String tipoPropiedad = scanner.next();
                    System.out.println("Ingresa el costo de la vivienda:");
                    float costoVivienda = scanner.nextFloat();
                    System.out.println("Ingresa el número de baños de la vivienda:");
                    int banosVivienda = scanner.nextInt();
                    System.out.println("Ingresa los servicios ofrecidos:");
                    String serviciosOfrecidos = scanner.next();
                    System.out.println("La vivienda es para comprar o rentar:");
                    String compraVenta = scanner.next();
                    System.out.println("Ingresa la cantidad de personas que caben en la vivienda:");
                    int cantPersonasCuarto = scanner.nextInt();

                    // Crea un nuevo objeto Vendedor y agrégalo a la base de datos
                    Vendedor vendedor = new Vendedor(nombre, correo, contrasena, fechaNacimiento, ubicacion, tipoPropiedad, costoVivienda, banosVivienda, serviciosOfrecidos, compraVenta, cantPersonasCuarto);
                    baseDeDatos.add(vendedor);
                    System.out.println("Cuenta de vendedor creada exitosamente!");
                } else if (tipoUsuario.equalsIgnoreCase("C")) {
                    // Comprador
                    System.out.println("¿Deseas compartir la residencia? (sí/no):");
                    String compartirResidencia = scanner.next();
                    System.out.println("¿Cuántos baños deseas?");
                    int bañosDeseados = scanner.nextInt();
                    System.out.println("¿A qué universidad asistes?");
                    String universidad = scanner.next();
                    System.out.println("¿Cuántas habitaciones debe tener la propiedad?");
                    int habitacionesDeseadas = scanner.nextInt();
                    System.out.println("¿Deseas comprar o rentar la vivienda?");
                    String rentaCompra = scanner.next();
                    System.out.println("¿Cuál es tu presupuesto?");
                    String presupuesto = scanner.next();
                    System.out.println("¿Qué tipo de vivienda deseas?");
                    String tipoViviendaDeseado = scanner.next();
                    System.out.println("¿Qué servicios deseas que tenga la vivienda?");
                    String serviciosIncluidos = scanner.next();

                    // Crea un nuevo objeto Comprador 
                    Comprador comprador = new Comprador(nombre, correo, contrasena, fechaNacimiento, compartirResidencia, bañosDeseados, universidad, habitacionesDeseadas, rentaCompra, presupuesto, tipoViviendaDeseado, serviciosIncluidos);
                    baseDeDatos.add(comprador);
                    System.out.println("Cuenta de comprador creada exitosamente!");
                }
            } else if (eleccion == 2) {
                // El usuario desea iniciar sesión
                System.out.println("Ingresa tu correo electrónico:");
                String correo = scanner.next();
                System.out.println("Ingresa tu contraseña:");
                String contrasena = scanner.next();

                // Verificar al usuario en la base de datos 
                // Si deseas agregar autenticación de usuario,

            } else if (eleccion == 3) {
                // Salir del programa
                System.out.println("¡Adiós, Gracias por utilizar Dormbnb!");
                break;
            } else {
                System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }
        }
    }
}
