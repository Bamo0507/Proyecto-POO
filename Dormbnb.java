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

        //hash maker
        MD5 MD5Hash = new MD5();

        // Crear un escáner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        String archivoCSV = "Usuarios.CSV";



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
                String presupuesto = valores[7];
                int cantBanosDeseados = Integer.parseInt(valores[8]);
                String compartirU = valores[9];
                String cuartoCompartido = valores[10];
                int numero = Integer.parseInt(valores[11]);

                    compradores.add(new Comprador(nombre, correo, contrasena, fechaNacimiento, 
                universidad, ubicacionDeseada, presupuesto, cantBanosDeseados, compartirU, 
                cuartoCompartido, numero));
                }
                if (type.equals("V")){
                    String ubicacionOfrecida = valores[6];
                    float costoVivienda = Float.parseFloat(valores[7]);
                    int baniosVivienda = Integer.parseInt(valores[8]);
                    int cantPersonasCuarto = Integer.parseInt(valores[9]);
                    String uCompartida = valores[10];

                    vendedores.add(new Vendedor(nombre,correo,contrasena,fechaNacimiento,ubicacionOfrecida,
                    costoVivienda, baniosVivienda, cantPersonasCuarto, uCompartida));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        try (FileWriter writer = new FileWriter(archivoCSV)) {
                    writer.write("nombre,correo,contrasena,fechaNacimiento,universidad/ubicacionOfrecida,ubicacionDeseada/costoVivienda,presupuesto/baniosVivienda,cantBanosDeseados/cantPersonasCuarto,compartirU/uCompartida,cuartoCompartido,numero\n");
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
                    compradores.get(i).getNumero()+"\n");
                } 
                for(int j = 0; j<vendedores.size(); j++){
                    writer.write("V," + vendedores.get(j).getNombre()+","+
                    vendedores.get(j).getCorreo() + "," +
                    vendedores.get(j).getContrasena() + "," +
                    vendedores.get(j).getFechaNacimiento() + "," +
                    vendedores.get(j).getUbicacionOfrecida() + "," +
                    vendedores.get(j).getCostoVivienda() + "," +
                    vendedores.get(j).getBaniosVivienda() + "," +
                    vendedores.get(j).getCantPersonasCuarto() + "," +
                    vendedores.get(j).getuCompartida() + "\n");
                    }

                }catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Datos sobrescritos con éxito en " + archivoCSV); 
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
        return fechaNacimiento;
    }

    //
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
    public void seleccionarUbicacionDeseada() {
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
                    System.out.println("Por favor, ingresa una opción válida.");
                    break;
            }
        }
    }   

    //Método para solicitar un precio
    public float solicitarPrecio(){
        Scanner sc = new Scanner(System.in);
        float precio = obtenerFloatValido(sc);
        return precio;
    }

    //Método para solicitar un presupuesto
    public float solicitarPresupuesto(){
        Scanner sc = new Scanner(System.in);
        float presupuesto = obtenerFloatValido(sc);
        return presupuesto; 
    }

    //Método para solicitar número
    public static String obtenerNumeroTelefonico() {
        Scanner sc = new Scanner(System.in);
        String numero = "";
        boolean valid = true;

        while (valid) {
            System.out.println("Por favor, ingresa tu número telefónico (8 dígitos):");
            numero = sc.nextLine();

            // Verificar si la longitud es de 8 dígitos
            if (numero.length() != 8) {
                System.out.println("El número telefónico debe contener 8 dígitos.");
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
                    valid = false; // Terminar el bucle si el número es válido
                }
            }
        }
        return numero;
    }

    //Método para solicitar información adicional
    public String obtenerInfoAdicional(){
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

public void seleccionarUniversidadDeseada() {
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
                System.out.println("Por favor, ingresa una opción válida.");
                break;
        }
    }
} 
public void seleccionarCantidadbanosDeseada() {
    Scanner scanner = new Scanner(System.in);
    int seleccionTipo = 0;
    String cantidadBanosDeseada = "";

    while (!(seleccionTipo >= 1 && seleccionTipo <= 2)) {
       
        System.out.println("1. 1 bano");
        System.out.println("2. 2 banos");
        System.out.print("Opción: ");

        switch (seleccionTipo = obtenerEnteroValido(scanner)) {
            case 1:
                cantidadBanosDeseada = "1 bano";
                break;
            case 2:
                cantidadBanosDeseada = "2 banos";
                break;
            default:
                System.out.println("Por favor, ingresa una opción válida.");
                break;
        }
    }
}    
public void establecersino() {
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
                System.out.println("Por favor, ingresa una opción válida.");
                break;
        }
    }
}   

}