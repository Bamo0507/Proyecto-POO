import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dormbnb {
    public static void main(String[] args) throws NoSuchAlgorithmException{
        System.out.println("***********************************************************");
        System.out.println("*  __        _______ _     ____ ___  __  __ _____ _ _ _   *");
        System.out.println("*  \\ \\      / / ____| |   / ___/ _ \\|  \\/  | ____| | | |  *");
        System.out.println("*   \\ \\ /\\ / /|  _| | |  | |  | | | | |\\/| |  _| | | | |  *");
        System.out.println("*    \\ V  V / | |___| |__| |__| |_| | |  | | |___|_|_|_|  *");
        System.out.println("*     \\_/\\_/  |_____|_____\\____\\___/|_|  |_|_____(_|_|_)  *");
        System.out.println("***********************************************************");

        // Crear una base de datos para almacenar cuentas de usuarios
        ArrayList<Comprador> compradores = new ArrayList<>();
        ArrayList<Vendedor> vendedores = new ArrayList<>();

        //hash maker
        MD5 MD5Hash = new MD5();

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
                String nombre = scanner.nextLine();
                System.out.println("Ingresa tu correo electrónico:");
                String correo = scanner.nextLine();
                System.out.println("Ingresa tu contraseña:");
                String contrasena = scanner.nextLine();
                System.out.println("Ingresa tu fecha de nacimiento:");
                String fechaNacimiento = scanner.nextLine();

                System.out.println("¿Eres un Vendedor o un Comprador? (V/C):");
                String tipoUsuario = scanner.nextLine();

                if (tipoUsuario.equalsIgnoreCase("V")) {
                    // Vendedor
                    System.out.println("Ingresa la ubicación que ofreces:");
                    String ubicacion = scanner.nextLine();
                    System.out.println("Ingresa el tipo de propiedad que ofreces:");
                    String tipoPropiedad = scanner.nextLine();
                    System.out.println("Ingresa el costo de la vivienda:");
                    float costoVivienda = scanner.nextFloat();
                    System.out.println("Ingresa el número de baños de la vivienda:");
                    int banosVivienda = scanner.nextInt();
                    System.out.println("Ingresa los servicios ofrecidos:");
                    String serviciosOfrecidos = scanner.nextLine();
                    System.out.println("La vivienda es para comprar o rentar:");
                    String compraVenta = scanner.nextLine();
                    System.out.println("Ingresa la cantidad de personas que caben en la vivienda:");
                    int cantPersonasCuarto = scanner.nextInt();
                    System.out.println("¿Deseas que el edificio tenga a personas de varias universidades?");
                    String desearCompartirU = scanner.nextLine();

                    // Crea un nuevo objeto Vendedor y agrégalo a la base de datos
                    Vendedor vendedor = new Vendedor(nombre, correo, contrasena, fechaNacimiento, ubicacion, tipoPropiedad, costoVivienda, banosVivienda, serviciosOfrecidos, compraVenta, cantPersonasCuarto, desearCompartirU);
                    vendedores.add(vendedor);
                    System.out.println("Cuenta de vendedor creada exitosamente!");
                } else if (tipoUsuario.equalsIgnoreCase("C")) {
                    // Comprador
                    System.out.println("¿En qué ubicación desea que esté su cuarto?");
                    String ubicacionDeseada = scanner.nextLine();
                    System.out.println("¿Deseas compartir la residencia? (sí/no):");
                    String compartirResidencia = scanner.next();
                    System.out.println("¿Cuántos baños deseas?");
                    int banosDeseados = scanner.nextInt();
                    System.out.println("¿Deseas convivir con gente de otras universidades?");
                    String compartirU = scanner.nextLine();
                    System.out.println("¿Deseas comprar o rentar la vivienda?");
                    String rentaCompra = scanner.nextLine();
                    System.out.println("¿Cuál es tu presupuesto?");
                    String presupuesto = scanner.nextLine();
                    System.out.println("¿Qué tipo de vivienda deseas?");
                    String tipoViviendaDeseado = scanner.next();
                    System.out.println("¿Qué servicios deseas que tenga la vivienda?");
                    String serviciosIncluidos = scanner.next();

                    // Crea un nuevo objeto Comprador 
                    Comprador comprador = new Comprador(nombre, correo, contrasena, fechaNacimiento, ubicacionDeseada, rentaCompra, presupuesto, tipoViviendaDeseado, banosDeseados, compartirU, compartirResidencia, serviciosIncluidos);
                    compradores.add(comprador);
                    System.out.println("Cuenta de comprador creada exitosamente!");
                }
            } else if (eleccion == 2) {
                // El usuario desea iniciar sesión
                System.out.println("Ingresa tu correo electrónico:");
                String correo = scanner.next();
                for (int i = 0; i <compradores.size(); i++) {
                    if (compradores.get(i).getCorreo() == correo) {
                        System.out.println("Ingresa tu contraseña:");
                        String contrasena = scanner.next();
                        contrasena = MD5Hash.getMd5(contrasena); 
                        if ((compradores.get(i).getContrasena()) == contrasena ) {
                            System.out.println("Contrasena correcta");
                            } else {
                            System.out.println("Contrasena Incorrecta");
                }}}
                for (int j = 0; j < vendedores.size(); j++) {
                    if (compradores.get(j).getCorreo() == correo) {
                        System.out.println("Ingresa tu contraseña:");
                        String contrasena = scanner.next();
                        contrasena = MD5Hash.getMd5(contrasena);
                        if ((compradores.get(j).getContrasena()) == contrasena) {
                            System.out.println("Contrasena correcta");
                            } else {
                            System.out.println("Contrasena Incorrecta");
                }}
            }
                
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

