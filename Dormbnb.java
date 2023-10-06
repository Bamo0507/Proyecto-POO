import java.util.ArrayList;
import java.util.Scanner;

public class Dormbnb{
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