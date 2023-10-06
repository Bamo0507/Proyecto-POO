//solo guarda los metodos para luego implementarlos en el verdadero main

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVreader {
    public static void main(String[] args){
        String archivoCSV = "Compradores.CSV";
//lector
        ArrayList<Comprador> compradores = new ArrayList<Comprador>();


        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String encabezado = br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                
                // Extraer los valores del CSV
                String nombre = valores[1];
                String correo = valores[2];
                String contrasena = valores[3];
                String fechaNacimiento = valores[4];
                String ubicacionDeseada = valores[5];
                String rentaCompra = valores[6];
                String presupuesto = valores[7];
                String tipoViviendaDeseado = valores[8];
                int cantBanosDeseados = Integer.parseInt(valores[9]);
                String compartirU = valores[10];
                String cuartoCompartido = valores[11];
                String serviciosIncluidos = valores[12];
                

                compradores.add(new Comprador(nombre, correo, contrasena, fechaNacimiento, 
                ubicacionDeseada, rentaCompra, presupuesto, tipoViviendaDeseado, cantBanosDeseados,
                 compartirU, cuartoCompartido, serviciosIncluidos));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //comprobar que los datos se hayan migrado al arraylist
        for (int i = 0; i < compradores.size(); i++) {
            System.out.println(compradores.get(i).toString());
        }



        //letor csv
        try (FileWriter writer = new FileWriter(archivoCSV)) {
            
            writer.write("id_curso,id_sede,nombre_curso,horario,duracion,dias,cantidad_estudiantes,salon_asignado,edificio\n");

            for (int i = 0; i <compradores.size(); i++) {
            writer.write(compradores.get(i).getNombre()+","+
            compradores.get(i).getCorreo() + "," +
            compradores.get(i).getContrasena() + "," +
            compradores.get(i).getFechaNacimiento() + "," +
            compradores.get(i).getUbicacionDeseada() + "," +
            compradores.get(i).getRentaCompra() + "," +
            compradores.get(i).getPresupuesto() + "," +
            compradores.get(i).getTipoViviendaDeseado() + "," +
            compradores.get(i).getCantBanosDeseados() + "," +
            compradores.get(i).getCompartirU() + "," +
            compradores.get(i).getCuartoCompartido() + "," +
            compradores.get(i).getServiciosIncluidos()+"\n");
            

            
        } 
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Datos sobrescritos con éxito en " + archivoCSV); 
        }
}


    }

