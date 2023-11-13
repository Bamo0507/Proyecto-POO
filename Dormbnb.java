import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Properties;
import java.util.regex.*;
import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

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

        //Crear objeto mailer (utilizado para enviar correos)
        //Mailer mailer = new Mailer();

        //hash maker
        MD5 MD5Hash = new MD5();

        // Crear un escáner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        String archivoCSV = "Usuarios.CSV";
        Connection connection = null;
        boolean logIn = false;
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                
                // Extraer los valores del CSV
                String type = valores[0];
                String nombre = valores[1];
                String correo = valores[2];
                String contrasena = valores[3];
                String fechaNacimiento = valores[4];
                String universidad = valores[5];
                

                if (type.equals("C")){

                    String ubicacionDeseada = valores[6];
                float presupuesto = Float.parseFloat(valores[7]);
                int cantBanosDeseados = Integer.parseInt(valores[8]);
                String compartirU = valores[9];
                String cuartoCompartido = valores[10];
                String numero = valores[11];
                String ubicacionOfrecida = valores[12];
                float costoVivienda = Float.parseFloat(valores[13]);
                int baniosVivienda = Integer.parseInt(valores[14]);
                int cantPersonasCuarto = Integer.parseInt(valores[15]);
                String uCompartida = valores[16];
                boolean disponible = (valores[17].equals("true")) ? true : false;
                boolean reservado = (valores[18].equals("true")) ? true : false;

                Dorm dorm = new Dorm(ubicacionOfrecida,costoVivienda, baniosVivienda, cantPersonasCuarto,
                 uCompartida, disponible, reservado);

                    compradores.add(new Comprador(nombre, correo, contrasena, fechaNacimiento, universidad, 
                    ubicacionDeseada, presupuesto, cantBanosDeseados, compartirU, cuartoCompartido, numero, dorm));
                }
                if (type.equals("V")){
                    Vendedor vendedor = new Vendedor(nombre,correo,contrasena,fechaNacimiento,universidad);
                    for (int i = 6; i < valores.length; i += 7) {
                        if (i + 4 < valores.length) {
                            String ubicacionOfrecida = valores[i];
                            float costoVivienda = Float.parseFloat(valores[i + 1]);
                            int baniosVivienda = Integer.parseInt(valores[i + 2]);
                            int cantPersonasCuarto = Integer.parseInt(valores[i + 3]);
                            String uCompartida = valores[i + 4];
                            boolean disponible = (valores[i + 5].equals("true")) ? true : false;
                            boolean reservado = (valores[i + 6].equals("true")) ? true : false;
                        vendedor.addDorm(ubicacionOfrecida, costoVivienda, baniosVivienda, cantPersonasCuarto, uCompartida, disponible, reservado);
                    }else {
                        break;
                    }
                    }
                    vendedores.add(vendedor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (valid==true) {
            System.out.println("------------------------");
            System.out.println("Elige una opción:");
            System.out.println("1. Crear una cuenta");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            String eleccion = scanner.nextLine();
            System.out.println("------------------------");
            if (eleccion.equals("1")) {
                System.out.println("=================================================================");
                System.out.println("------------------ ¡Hora de generar tu cuenta! ------------------");
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                String nombre = obtenerNombre();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                String correo = obtenerCorreoValido();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                String contrasena = MD5Hash.getMd5(obtenerContrasenaSegura());
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String fechaComoString = formato.format(obtenerFechaNacimiento());
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Seleccione su universidad de interes: \n");
                String universidad = seleccionarUniversidadDeseada();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                String type = obtenerTipoUsuario();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                

                if (type.equals("Comprador")){
                System.out.println("¿En qué sitio estás interesado?\n");
                String ubicacionDeseada = seleccionarUbicacionDeseada();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("----- Hora de definir tu presupuesto!! -----");
                float presupuesto = solicitarPresupuesto();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Ingrese la cantidad de baños apropiada para usted: ");
                int cantBanosDeseados = seleccionarCantidadbanosDeseada();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Deseas un dorm multi-universitario? ");
                String compartirU = establecersino(); 
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Deseas compartir cuarto? ");
                String cuartoCompartido = establecersino();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Ingrese su numero de telefono ");
                String numero = obtenerNumeroTelefonico();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                Dorm dorm = new Dorm("", -1, -1, -1,
                "", false, false);
                System.out.println("------------- ¡Felicidades, haz creado tu usuario! --------------");
                System.out.println("=================================================================");
                    compradores.add(new Comprador(nombre, correo, contrasena, fechaComoString, 
                universidad, ubicacionDeseada, presupuesto, cantBanosDeseados, compartirU, 
                cuartoCompartido, numero, dorm));
            }
            
                if (type.equals("Vendedor")){
                    System.out.println("------------- ¡Felicidades, haz creado tu usuario! --------------");
                    System.out.println("=================================================================");
                    Vendedor vendedor = new Vendedor(nombre,correo,contrasena,fechaComoString,universidad);
                    vendedores.add(vendedor);
                
                }
                // El usuario desea crear una cuenta
                valid = true;
            } else if (eleccion.equals("2")) {
                // El usuario desea iniciar sesión
                System.out.println("Ingresa tu correo electrónico:");
                boolean validacion1 = false;
                String correo = scanner.next();
                for (int i = 0; i <compradores.size(); i++) {
                    if (compradores.get(i).getCorreo().equals(correo)) {
                        validacion1 = true;
                        System.out.println("Ingresa tu contraseña:");
                        String contrasena = scanner.next();
                        contrasena = MD5Hash.getMd5(contrasena); 
                        if ((compradores.get(i).getContrasena()).equals(contrasena) ) {
                            System.out.println("Contrasena correcta");
                            System.out.println("Apache ENTER para continuar...");
                            logIn = true;

                                while(logIn == true) {
                                    logIn = subMenuC(logIn, compradores.get(i), SubMenuC, vendedores);
                            }
                            } else {
                            System.out.println("Contrasena Incorrecta");
                            
                }}}
                if(validacion1 = false){
                    System.out.println("No se ha encontrado un usuario con ese correo electrónico :(\n");
                }
                for (int j = 0; j < vendedores.size(); j++) {
                    if (vendedores.get(j).getCorreo().equals(correo)) {
                        System.out.println("Ingresa tu contraseña:");
                        String contrasena = scanner.next();
                        contrasena = MD5Hash.getMd5(contrasena);
                        if ((vendedores.get(j).getContrasena()).equals(contrasena)) {
                            System.out.println("Contrasena correcta");

                            logIn = true;

                                while(logIn == true) {
                                    logIn = subMenuV(logIn, vendedores.get(j), SubMenuV);
                            } }else {
                            System.out.println("Contrasena Incorrecta");

                }}
            }
                
                // Verificar al usuario en la base de datos 
                // Si deseas agregar autenticación de usuario,

            } else if (eleccion.equals("3")) {
                // Salir del programa
                System.out.println("¡Adiós, Gracias por utilizar Dormbnb!");
                valid =!valid;
            } else {
                System.out.println("Por favor, siempre asegúrate de seleccionar una opción válida para interactuar con el programa.\n");
            }
            try (FileWriter writer = new FileWriter(archivoCSV)) {
            writer.write("tipo,nombre,correo,contrasena,fechaNacimiento,universidad/ubicacionOfrecida,ubicacionDeseada/costoVivienda,presupuesto/baniosVivienda,cantBanosDeseados/cantPersonasCuarto,compartirU/uCompartida,cuartoCompartido,numero\n");
            for (int i = 0; i <compradores.size(); i++) {
            writer.write("C," + compradores.get(i).getNombre()+","+
            compradores.get(i).getCorreo() + "," +
            compradores.get(i).getContrasena() + "," +
            compradores.get(i).getFechaNacimiento() + "," +
            compradores.get(i).getUniversidad() + "," +
            compradores.get(i).getUbicacionDeseada() + "," +
            compradores.get(i).getPresupuesto() + "," +
            compradores.get(i).getCantBanosDeseados() + "," +
            compradores.get(i).getCompartirU() + "," +
            compradores.get(i).getCuartoCompartido() + "," +
            compradores.get(i).getNumero()+ ","+
            compradores.get(i).getReservado().getUbicacionOfrecida() + "," +
            compradores.get(i).getReservado().getCostoVivienda() + "," +
            compradores.get(i).getReservado().getBaniosVivienda() + "," +
            compradores.get(i).getReservado().getCantPersonasCuarto() + "," +
            compradores.get(i).getReservado().getuCompartida() + "," +
            compradores.get(i).getReservado().isDisponible() + "," +
            compradores.get(i).getReservado().isReservado() + "\n");
        } 
        for (int j = 0; j < vendedores.size(); j++) {
            vendedores.get(j).toString();
            writer.write("V," + vendedores.get(j).getNombre() + "," +
                    vendedores.get(j).getCorreo() + "," +
                    vendedores.get(j).getContrasena() + "," +
                    vendedores.get(j).getFechaNacimiento() + "," + 
                    vendedores.get(j).getUniversidad() + ",");

            if(vendedores.get(j).getDorms().size() == 0){
                writer.write("\n");
            }
            if(vendedores.get(j).getDorms().size() != 0){

                for (int h = 0; h < vendedores.get(j).getDorms().size(); h++) {
                Dorm dormitorio = vendedores.get(j).getDorms().get(h);
                writer.write(dormitorio.getUbicacionOfrecida() + "," +
                        dormitorio.getCostoVivienda() + "," +
                        dormitorio.getBaniosVivienda() + "," +
                        dormitorio.getCantPersonasCuarto() + "," +
                        dormitorio.getuCompartida()+ ","+
                        dormitorio.isDisponible() + "," +
                        dormitorio.isReservado()+",");
        
                // Comprobar si es el último dormitorio en la lista
                if (h == vendedores.get(j).getDorms().size() -1) {
                    writer.write("\n");
                } 
            }
            }
            
        } 
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Datos sobrescritos con éxito en " + archivoCSV); 
        }
        }

        
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
            decision = 0;
            while(!(decision>=1 && decision <= 2)){
                System.out.println("Por favor, ingrese el nombre de usuario que desee: ");
                nombre = sc.nextLine();
                //Nos aseguramos que ese sea el nombre que quiere el usuario
                System.out.println("\n¿Seguro que quiere que ese sea su nombre de usuario?"); 
                System.out.println("1. Sí\n" + "2. No");
                switch(decision = obtenerEnteroValido(sc)){
                case 1:
                    valid = false;
                    System.out.println("Listo, su nombre ha sido escogido.");
                    System.out.println("Continuando con los siguientes pasos...\n");
                    break;
                case 2:
                    System.out.println("Vuelva a escoger...\n");
                    break;
                default:
                    System.out.println("Solamente puedes seleccionar 1 o 2...");
                    System.out.println("Vuelve a colocar un nombre de usuario.");
                    System.out.println("Por favor, selecciona una opción valida ;)\n");
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
            System.out.println("--------------------");
            System.out.println("Por favor, vuelva a ingresar la contraseña:");
            String contrasena2 = scanner.nextLine();
            System.out.println("--------------------");

            if (!contrasena1.equals(contrasena2)) {
                System.out.println("Las contraseñas no coinciden. Inténtelo de nuevo.");
                continue;
            }

            if (pattern.matcher(contrasena1).matches()) {
                System.out.println("Su contraseña ha sido establecida.");
                System.out.println("Continuemos querido usuario :D");
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
                System.out.println("Se ha establecido su correo, muchas gracias :)");
                return correo;
            } else {
                System.out.println("El correo electrónico no es válido o el dominio no existe. Inténtelo de nuevo.\n");
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
            URL url = new URL("http://" + dominio);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(false); // Desactiva las redirecciones automáticas
            connection.setRequestMethod("HEAD");
    
            int responseCode = connection.getResponseCode();
    
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Se encontró el dominio.");
                return true;
            } else if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                // Si hay una redirección, sigue la nueva ubicación
                String newLocation = connection.getHeaderField("Location");
                if (newLocation != null) {
                    HttpURLConnection newConnection = (HttpURLConnection) new URL(newLocation).openConnection();
                    newConnection.setRequestMethod("HEAD");
                    int newResponseCode = newConnection.getResponseCode();    
                    if (newResponseCode == HttpURLConnection.HTTP_OK) {
                        return true;
                    }
                }
            }
            return false;
        } catch (IOException e) {
            System.out.println("\nOh oh parece que ha ocurrido un error :(");
            return false;
        }
    }  

    //Verificar que se ingrese una fecha válida
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
        System.out.println("Muchas gracias!!");
        return fechaNacimiento;
    }

    //Se devuelve si el usuario desea ser un comprador o un vendedor
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
                    System.out.println("Listo, es un comprador.\n");
                    break;
                case 2:
                    tipo = "Vendedor";
                    valid = false;
                    System.out.println("Listo, es un vendedor.\n");
                    break;
                default:
                    System.out.println("Por favor, selecciona una opción válida, solo hay dos tipos de usuario :)\n");
                    break;
                }
            }
        }
        return tipo;
    }

    // Método para limpiar el buffer del Scanner
    public static void limpiarBuffer(Scanner scanner) {
        while (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    //Se devuelve una ubicación
    public static String seleccionarUbicacionDeseada() {
        Scanner scanner = new Scanner(System.in);
        int seleccionTipo = 0;
        String ubicacionDeseada = "";
    
        while (!(seleccionTipo >= 1 && seleccionTipo <= 25)) {
           
            System.out.println("1. Zona 1");
            System.out.println("2. Zona 2");
            System.out.println("3. Zona 3");
            System.out.println("4. Zona 4");
            System.out.println("5. Zona 5");
            System.out.println("6. Zona 6");
            System.out.println("7. Zona 7");
            System.out.println("8. Zona 8");
            System.out.println("9. Zona 9");
            System.out.println("10. Zona 10");
            System.out.println("11. Zona 11");
            System.out.println("12. Zona 12");
            System.out.println("13. Zona 13");
            System.out.println("14. Zona 14");
            System.out.println("15. Zona 15");
            System.out.println("16. Zona 16");
            System.out.println("17. Zona 17");
            System.out.println("18. Zona 18");
            System.out.println("19. Zona 19");
            System.out.println("20. Zona 21");
            System.out.println("21. Zona 24");
            System.out.println("22. Zona 25");
            System.out.println("23. San Cristobal");
            System.out.println("24. Carretera al Salvador");
            System.out.println("25. Mixco");
            System.out.print("Opción: ");
    
            switch (seleccionTipo = obtenerEnteroValido(scanner)) {
                case 1:
                    ubicacionDeseada = "Zona 1";
                    break;
                case 2:
                    ubicacionDeseada = "Zona 2";
                    break;
                case 3:
                    ubicacionDeseada = "Zona 3";
                    break;
                case 4:
                    ubicacionDeseada = "Zona 4";
                    break;
                case 5:
                    ubicacionDeseada = "Zona 5";
                    break;
                case 6:
                    ubicacionDeseada = "Zona 6";
                    break;
                case 7:
                    ubicacionDeseada = "Zona 7";
                    break;
                case 8:
                    ubicacionDeseada = "Zona 8";
                    break;
                case 9:
                    ubicacionDeseada = "Zona 9";
                    break;
                case 10:
                    ubicacionDeseada = "Zona 10";
                    break;
                case 11:
                    ubicacionDeseada = "Zona 11";
                    break;
                case 12:
                    ubicacionDeseada = "Zona 12";
                    break;
                case 13:
                    ubicacionDeseada = "Zona 13";
                    break;
                case 14:
                    ubicacionDeseada = "Zona 14";
                    break;
                case 15:
                    ubicacionDeseada = "Zona 15";
                    break;
                case 16:
                    ubicacionDeseada = "Zona 16";
                    break;
                case 17:
                    ubicacionDeseada = "Zona 17";
                    break;
                case 18:
                    ubicacionDeseada = "Zona 18";
                    break;
                case 19:
                    ubicacionDeseada = "Zona 19";
                    break;
                case 20:
                    ubicacionDeseada = "Zona 20";
                    break;
                case 21:
                    ubicacionDeseada = "Zona 21";
                    break;
                case 22:
                    ubicacionDeseada = "Zona 24";
                    break;
                case 23:
                    ubicacionDeseada = "Zona 25";
                    break;
                case 24:
                    ubicacionDeseada = "San Cristobal";
                    break;
                case 25:
                    ubicacionDeseada = "Carretera al Salvador";
                    break;
                case 26:
                    ubicacionDeseada = "Mixco";
                    break;
                default:
                    System.out.println("Por favor, ingresa una opción válida. Revisa las ubicaciones ofrecidas\n");
                    break;
            }
        }
        System.out.println("Listo, se ha definido su ubicación deseada :)");
        return ubicacionDeseada;
    }   

    //Método para solicitar un precio
    public static float solicitarPrecio(){
        Scanner sc = new Scanner(System.in);
        float precio = obtenerFloatValido(sc);
        return precio;
    }

    //Método para solicitar un presupuesto
    public static float solicitarPresupuesto(){
        Scanner sc = new Scanner(System.in);
        float presupuesto = obtenerFloatValido(sc);
        return presupuesto; 
    }

    //Método para solicitar número
    public static String obtenerNumeroTelefonico() {
        Scanner sc = new Scanner(System.in);
        String numero = "";
        boolean valid1 = true;

        while (valid1) {
            System.out.println("Por favor, ingresa tu número telefónico (8 dígitos):");
            numero = sc.nextLine();

            // Verificar si la longitud es de 8 dígitos
            if (numero.length() != 8) {
                System.out.println("El número telefónico debe contener 8 dígitos. Vuelve a intentarlo...\n");
            } else {
                // Verificar si todos los caracteres son dígitos
                boolean esNumero = true;
                for (int i = 0; i < numero.length(); i++) {
                    if (!Character.isDigit(numero.charAt(i))) {
                        esNumero = false;
                        break;
                    }
                }
                if (!esNumero) {
                    System.out.println("El número telefónico solo debe contener dígitos.");
                } else {
                    valid1 = false; // Terminar el bucle si el número es válido
                }
            }
        }
        System.out.println("Listo!! Se ha definido tú número teléfonico ;)");
        return numero;
    }

    //Método para solicitar información adicional
    public static String obtenerInfoAdicional(){
        StringBuilder sb = new StringBuilder();
        boolean valid = true;
        String seleccion = "";
        Scanner sc = new Scanner(System.in);
        while(valid){
            System.out.println("Agrega uno por uno los servicios adicionales que se prestan en tu dorm.");
            System.out.println("Si no quiere agregar solo seleccione salir.");
            System.out.println("1. Agregar servicio");
            System.out.println("2. Salir");
            switch(seleccion = sc.nextLine()){
                case "1":
                    System.out.println("Escriba el servicio: ");
                    seleccion = sc.nextLine();
                    sb.append(seleccion).append(",");
                    break;
                case "2":
                    System.out.println("Muchas gracias!!!");
                    valid = false;
                    break;
                default:
                    System.out.println("Seleccione una opción válida :D");
                    break;
            }
        }
        String informacion = sb.toString();
        return informacion;
    }

    //Método para seleccionar una Unviersidad
    public static String seleccionarUniversidadDeseada() {
        Scanner scanner = new Scanner(System.in);
        int seleccionTipo = 0;
        String universidadDeseada = "";

        while (!(seleccionTipo >= 1 && seleccionTipo <= 14)) {
        
            System.out.println("1. Universidad del Valle de Guatemala");
            System.out.println("2. Universidad Francisco Marroquín");
            System.out.println("3. Universidad Rafael Landivar");
            System.out.println("4. Universidad del Istmo");
            System.out.println("5. Universidad San Carlos");
            System.out.println("6. Universidad Mariano Galvez");
            System.out.println("7. Universidad Panamericana");
            System.out.println("8. Universidad Mesoamericana");
            System.out.println("9. Universidad Galileo");
            System.out.println("10. Universidad Rural");
            System.out.println("11. Universidad Da Vinci");
            System.out.println("12. Universidad San Pablo");
            System.out.println("13. Universidad Internaciones");
            System.out.println("14. Universidad De Occidente");
            System.out.print("Opción: ");

            switch (seleccionTipo = obtenerEnteroValido(scanner)) {
                case 1:
                    universidadDeseada = "Universidad del Valle de Guatemala";
                    break;
                case 2:
                    universidadDeseada = "Universidad Francisco Marroquín";
                    break;
                case 3:
                    universidadDeseada = "Universidad Rafael Landivar";
                    break;
                case 4:
                    universidadDeseada = "Universidad del Istmo";
                    break;
                case 5:
                    universidadDeseada = "Universidad San Carlos";
                    break;
                case 6:
                    universidadDeseada = "Universidad Mariano Galvez";
                    break;
                case 7:
                    universidadDeseada = "Universidad Panamericana";
                    break;
                case 8:
                    universidadDeseada = "Universidad Mesoamericana";
                    break;
                case 9:
                    universidadDeseada = "Universidad Galileo";
                    break;
                case 10:
                    universidadDeseada = "Universidad Rural";
                    break;
                case 11:
                    universidadDeseada = "Universidad Da Vinci";
                    break;
                case 12:
                    universidadDeseada = "Universidad San Pablo";
                    break;
                case 13:
                    universidadDeseada = "Universidad Internaciones";
                    break;
                case 14:
                    universidadDeseada = "Universidad De Occidente";
                    break;
                default:
                    System.out.println("Por favor, ingresa una opción válida. Revisa todas las universidades con las que se cuentan\n");
                    break;
            }
        }
        System.out.println("Perfecto!!! Continuemos ;)");
        return universidadDeseada;
    } 

    //Seleccionar cantidad de baños
    public static int seleccionarCantidadbanosDeseada() {
        Scanner scanner = new Scanner(System.in);
        int seleccionTipo = 0;
        int cantidadBanosDeseada = -1;

        while (!(seleccionTipo >= 1 && seleccionTipo <= 3)) {
        
            System.out.println("1. 0 baños");
            System.out.println("2. 1 baño");
            System.out.println("3. 2 baños");
            System.out.print("Opción: ");

            switch (seleccionTipo = obtenerEnteroValido(scanner)) {
                case 1:
                    cantidadBanosDeseada = 0;
                    break;
                case 2:
                    cantidadBanosDeseada = 1;
                    break;
                case 3:
                    cantidadBanosDeseada = 2;
                default:
                    System.out.println("Por favor, ingresa una opción válida. Solo se tienen dorms que tienen de 0 a 2 baños.\n");
                    break;
            }
        }
        System.out.println("Se ha establecido su cantidad de baños.");
        return cantidadBanosDeseada;
    }    

    //Devolver una respuesta afirmativa o negativa
    public static String establecersino() {
        Scanner scanner = new Scanner(System.in);
        int seleccionTipo = 0;
        String establecerSiNo = "";

        while (!(seleccionTipo >= 1 && seleccionTipo <= 2)) {
        
            System.out.println("1.  Si");
            System.out.println("2. No");
            System.out.print("Opción: ");

            switch (seleccionTipo = obtenerEnteroValido(scanner)) {
                case 1:
                establecerSiNo = "Si";
                    break;
                case 2:
                establecerSiNo = "No";
                    break;
                default:
                    System.out.println("Por favor, ingresa una opción válida. Solo hay sí o no.\n");
                    break;
            }
        }
        return establecerSiNo;
    }   


static String SubMenuV = ("Bienvenido Vendedor:\n"+
            "1. Agregar Dorm\n"+
            "2. Ver mis dorms\n"+
            "3. Salir");


    public static boolean subMenuV(boolean logIn, Vendedor vendedor, String subMenuV){
        System.out.println();
        System.out.println(subMenuV);
            Scanner scanner = new Scanner(System.in);
            String opcion = scanner.nextLine();
            if (opcion.equals("1")) {
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Ingrese su Ubicacion: ");
                String ubicacionOfrecida = seleccionarUbicacionDeseada();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Ingrese el costo de su dorm: ");
                float costoVivienda = solicitarPresupuesto();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Ingrese el numero de banios: ");
                int baniosVivienda = seleccionarCantidadbanosDeseada();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Ingrese la cantidad de personas que puede albergar: ");
                int cantPersonasCuarto = obtenerEnteroValido(scanner);
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                System.out.println("Ingrese si es un dorm multi-univeritario");
                String uCompartida = establecersino();
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                boolean disponible = true;
                boolean reservado = false;
                vendedor.addDorm(ubicacionOfrecida, costoVivienda, baniosVivienda, cantPersonasCuarto, uCompartida, disponible, reservado);

                logIn = true;
                }if(opcion.equals("2")){
                for(int i=0; i<vendedor.getDorms().size(); i++){
                    System.out.println(vendedor.getDorms().get(i).toString());
                    logIn = true;
                }
            System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
            }
            if(opcion.equals("3")) {
                logIn = false;
            }
            return logIn;
            }

static String SubMenuC = ("Bienvenido Comprador: \n"+
            "1. Ver todos los dorms disponibles\n"+
            "2. Ver Dorms recomendados\n"+
            "3. Reservar Dorm\n"+
            "4. Salir");

public static boolean subMenuC(boolean logIn, Comprador comprador, String submenuC, ArrayList<Vendedor> vendedores){
    Mailer mailer = new Mailer();
    Scanner scanner = new Scanner(System.in);
    boolean opcionValida = false;
    logIn = true;
        String opcion = scanner.nextLine();
        System.out.println(submenuC);
        if(opcion.equals("1")){
            opcionValida = true;
            for(int i = 0; i <vendedores.size(); i++){{
                for (int j = 0; j < vendedores.get(i).getDorms().size(); j++){
                    if(vendedores.get(i).getDorms().get(j).isDisponible() == true){
                        System.out.println("Coidgo: "+ i +"-" + j + " "+vendedores.get(i).getDorms().get(j).toString());
                        logIn = true;
                }
                }
            }
            }
            System.out.println("\nApache ENTER para continuar");
        }
        else if(opcion.equals("2")){
            opcionValida = true;
            System.out.println("Si no se le llega a mostrar nada, es porque de momento no contamos con dorms que se adecúen a sus necesidades.");
            for(int i = 0; i <vendedores.size(); i++){{
                for (int j = 0; j < vendedores.get(i).getDorms().size(); j++){
                    Dorm dorm = vendedores.get(i).getDorms().get(j);
                    if(dorm.isDisponible() == true  && comprador.getUbicacionDeseada().equals(dorm.getUbicacionOfrecida()) 
                    && (comprador.getPresupuesto() >= dorm.getCostoVivienda()) && 
                    (comprador.getCantBanosDeseados() == dorm.getBaniosVivienda()) && comprador.getCompartirU().equals(dorm.getuCompartida()) 
                    && (comprador.getCuartoCompartido().equals("Si")) && dorm.getCantPersonasCuarto() > 0 && dorm.isReservado() == false){//casi termiando
                        System.out.println("Coidgo: "+ i +"-" + j + " "+vendedores.get(i).getDorms().get(j).toString());
                        logIn = true;

                } if(dorm.isDisponible() == true  && comprador.getUbicacionDeseada().equals(dorm.getUbicacionOfrecida()) 
                    && (comprador.getPresupuesto() >= dorm.getCostoVivienda()) && 
                    (comprador.getCantBanosDeseados() == dorm.getBaniosVivienda()) && comprador.getCompartirU().equals(dorm.getuCompartida()) 
                    && (comprador.getCuartoCompartido().equals("No")) && dorm.getCantPersonasCuarto() == 0 && dorm.isReservado() == false){//casi termiando
                        System.out.println("Coidgo: "+ i +"-" + j + " "+vendedores.get(i).getDorms().get(j).toString());
                        logIn = true;
                    }
                    }
                }
            }
            System.out.println("\nApache ENTER para continuar");
        }
        else if(opcion.equals("3")){
            opcionValida = true;
            System.out.println("Si no se le llega a mostrar nada, es porque de momento no contamos con dorms que se adecúen a sus necesidades.");
            System.out.println("De ser este el caso, tendrá que colocar '0-0'.");
            for(int i = 0; i <vendedores.size(); i++){
                for (int j = 0; j < vendedores.get(i).getDorms().size(); j++){
                    Dorm dorm = vendedores.get(i).getDorms().get(j);
                    if(dorm.isDisponible() == true  && comprador.getUbicacionDeseada().equals(dorm.getUbicacionOfrecida()) 
                    && (comprador.getPresupuesto() >= dorm.getCostoVivienda()) && 
                    (comprador.getCantBanosDeseados() == dorm.getBaniosVivienda()) && comprador.getCompartirU().equals(dorm.getuCompartida()) 
                    && (comprador.getCuartoCompartido().equals("Si")) && dorm.getCantPersonasCuarto() > 0 && dorm.isReservado() == false){//casi termiando
                        System.out.println("Coidgo: "+ i +"-" + j + " "+ vendedores.get(i).getDorms().get(j).toString());
                        logIn = true;

                } if(dorm.isDisponible() == true  && comprador.getUbicacionDeseada().equals(dorm.getUbicacionOfrecida()) 
                    && (comprador.getPresupuesto() >= dorm.getCostoVivienda()) && 
                    (comprador.getCantBanosDeseados() == dorm.getBaniosVivienda()) && comprador.getCompartirU().equals(dorm.getuCompartida()) 
                    && (comprador.getCuartoCompartido().equals("No")) && dorm.getCantPersonasCuarto() == 0 && dorm.isReservado() == false){//casi termiando
                        System.out.println("Coidgo: "+ i +"-" + j + " "+ vendedores.get(i).getDorms().get(j).toString());
                        logIn = true;
                    }
                    }
                    
                }
                try {
                    System.out.println("Ingrese el codigo del dorm que desea reservar(#-#): ");;
                    String code = scanner.nextLine();
                    String[] partes = code.split("-");
                
                    int i = Integer.parseInt(partes[0]);
                    int j = Integer.parseInt(partes[1]);
                
                    if (i >= 0 && i < vendedores.size() && j >= 0 && j < vendedores.get(i).getDorms().size()) {
                        comprador.getReservado().setBaniosVivienda(vendedores.get(i).getDorms().get(j).getBaniosVivienda());
                        comprador.getReservado().setUbicacionOfrecida(vendedores.get(i).getDorms().get(j).getUbicacionOfrecida());
                        comprador.getReservado().setCostoVivienda(vendedores.get(i).getDorms().get(j).getCostoVivienda());
                        comprador.getReservado().setCantPersonasCuarto(vendedores.get(i).getDorms().get(j).getCantPersonasCuarto());
                        comprador.getReservado().setuCompartida(vendedores.get(i).getDorms().get(j).getuCompartida());
                        vendedores.get(i).getDorms().get(j).setDisponible(false);
                        vendedores.get(i).getDorms().get(j).setReservado(true);

                        mailer.enviarCorreo(comprador.getCorreo(), "Reservaste el Dorm: " + vendedores.get(i).getDorms().get(j).toString());
                        mailer.enviarCorreo(vendedores.get(i).getCorreo(), "Un usuario esta interesado en tu Dorm: \n" + vendedores.get(i).getDorms().get(j).toString() +"\n"+ "Su numero de telefono es: "+ comprador.getNumero());
                    } else {
                        System.out.println("Los índices están fuera de rango.");
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Ingrese un formato válido (#-#).");
                }   
                System.out.println("Por favor, apache ENTER para continuar.");             
        }
        else if(opcion.equals("4")) {
            opcionValida = true;
            logIn = false;
        }
    return logIn;
    }

}