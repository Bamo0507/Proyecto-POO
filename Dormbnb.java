import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.regex.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dormbnb {
    private static boolean valid = true;
    
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

        while (valid) {
            System.out.println("Elige una opción:");
            System.out.println("1. Crear una cuenta");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            int eleccion = scanner.nextInt();
            if (eleccion == 1) {
                // El usuario desea crear una cuenta
                List<Object> informacion = obtenerInformacion();

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

    //Pedir información al usuario
    public static List<Object> obtenerInformacion(){
        Scanner sc = new Scanner(System.in);
        String tipoUsuario = "";
        //En el ArrayList se devolverá toda la informacion necesaria, en el orden que se vaya solicitando la misma
        List<Object> informacion = new ArrayList<>();
        informacion.add(obtenerNombre());
        informacion.add(obtenerContrasenaSegura());
        informacion.add(obtenerCorreoValido());
        informacion.add(obtenerFechaNacimiento());
        tipoUsuario = obtenerTipoUsuario();

        switch(tipoUsuario){
            case "Comprador":
                break;

            case "Vendedor":
                break;
        }


        return informacion;
    }


    //Método para asegurarse que se ingrese un entero en los campos necesarios
    public static int obtenerEnteroValido(Scanner scanner) {
        int numero = 0;
        boolean entradaValida = false;
        System.out.println("------------------------");
        do {
            try {
                System.out.print("Por favor, ingresa un número entero: ");
                String entrada = scanner.nextLine();
                numero = Integer.parseInt(entrada);
                entradaValida = true;
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
            }
        } while (!entradaValida);

        return numero;
    }
    //Método para asegurarse que se ingrese un float en los campos necesarios
    public static float obtenerFloatValido(Scanner scanner) {
        float numero = 0.0f;  // Inicializado a 0.0f
        boolean entradaValida = false;
        System.out.println("------------------------");
        do {
            try {
                System.out.print("Por favor, ingresa un número decimal: ");
                String entrada = scanner.nextLine();
                numero = Float.parseFloat(entrada);
                entradaValida = true;
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número decimal.");
            }
        } while (!entradaValida);
    
        return numero;
    }
    
    public static String obtenerNombre(){
        Scanner sc = new Scanner(System.in);
        int decision = 0;
        String nombre = "";
        while(valid){
            while(!(decision>=1 && decision <= 2)){
                System.out.println("Por favor, ingrese su nombre de usuario: ");
                nombre = sc.nextLine();
                //Nos aseguramos que ese sea el nombre que quiere el usuario
                System.out.println("¿Seguro que quiere que ese sea su nombre de usuario?"); 
                System.out.println("1. Sí\n" + "2. No\n");
                switch(decision = obtenerEnteroValido(sc)){
                case 1:
                    valid = false;
                    System.out.println("Listo, su nombre ha sido escogido.");
                    break;
                case 2:
                    System.out.println("Vuelva a escoger...\n");
                    break;
                default:
                    System.out.println("Por favor, selecciona algo válido \n");
                    break;
                }
            }
        }
        return nombre;
    }

    //Método para solicitar una contraseña con requisitos, y que se tenga que escribir 2 veces
    public static String obtenerContrasenaSegura() {
        Scanner scanner = new Scanner(System.in);
        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$");

        while (true) {
            System.out.println("Por favor, ingrese una contraseña segura que contenga al menos una mayúscula, un número y un símbolo (@, #, $, %, ^, & o +):");
            String contrasena1 = scanner.nextLine();
            String contrasena2 = scanner.nextLine();

            if (!contrasena1.equals(contrasena2)) {
                System.out.println("Las contraseñas no coinciden. Inténtelo de nuevo.");
                continue;
            }

            if (pattern.matcher(contrasena1).matches()) {
                System.out.println("Su contraseña ha sido establecida.");
                return contrasena1;
            } else {
                System.out.println("La contraseña no cumple con los requisitos. Inténtelo de nuevo.");
            }
        }
    }

    //Encontrar un Correo existente y que sea válido
    public static String obtenerCorreoValido() {
        Scanner scanner = new Scanner(System.in);
        String correo;

        while (true) {
            System.out.print("Por favor, ingrese su correo electrónico: ");
            correo = scanner.nextLine();

            if (esCorreoValido(correo) && existeDominio(correo)) {
                return correo;
            } else {
                System.out.println("El correo electrónico no es válido o el dominio no existe. Inténtelo de nuevo.");
            }
        }
    }

    //Verifica si cumple con las normas de escritura de un correo
    public static boolean esCorreoValido(String correo) {
        // Expresión regular para validar un correo electrónico
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    //Verifica si el correo existe
    public static boolean existeDominio(String correo) {
        try {
            String[] partes = correo.split("@");
            String dominio = partes[1];
            URI uri = new URI("http://" + dominio);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
        } catch (URISyntaxException | IOException e) {
            return false;
        }
    }

    public static Date obtenerFechaNacimiento() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.print("Por favor, ingrese su fecha de nacimiento (dd/MM/yyyy): ");
            String fechaNacimientoStr = scanner.nextLine();

            try {
                fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
                fechaValida = true;
            } catch (ParseException e) {
                System.out.println("Fecha de nacimiento no válida. Inténtelo de nuevo.");
            }
        }

        return fechaNacimiento;
    }

    public static String obtenerTipoUsuario(){
        Scanner sc = new Scanner(System.in);
        int decision = 0;
        String tipo = "";
        boolean valid = true;
        while(valid){
            while(!(decision>=1 && decision <= 2)){
                System.out.println("Por favor, ingrese si usted será un comprador o un vendedor: ");
                //Nos aseguramos que ese sea el nombre que quiere el usuario
                System.out.println("1. Comprador\n" + "2. Vendedor\n");
                switch(decision = obtenerEnteroValido(sc)){
                case 1:
                    tipo = "Comprador";
                    valid = false;
                    System.out.println("Listo, es un comprador.");
                    break;
                case 2:
                    tipo = "Vendedor";
                    valid = false;
                    System.out.println("Listo, es un vendedor.");
                    break;
                default:
                    System.out.println("Por favor, selecciona algo válido \n");
                    break;
                }
            }
        }
        return tipo;
    }
}

