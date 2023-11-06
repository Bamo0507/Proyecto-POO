import java.sql.ResultSet;
import java.sql.Statement;  

public class Variables {
    public static String basededatos = "proyectopoo";
    public static String contrasena = "infobi";
    public static String servidor = "5.161.118.98:33007";
    public static String usuario = "poo";



    /**
     * Metodo para ejemplificar como seria un metodo para traer datos
     * de la base de datos.
     */
    public static void getValues(){
        ConexionBD BD;

        try {
            BD = new ConexionBD ("jdbc:mysql://"+servidor+"/"+basededatos+"?useSSL=false"
                    + "&characterEncoding=utf-8",usuario,contrasena);
            BD.getNewConnection();


            String query = "SELECT * FROM compradores";
            /**
             * "query" es lo que nosotros vamos a modificar traer/settear de la base de datos
             * en este caso es un SELECT que va a traer TODOS los datos de la tabla compradores
            */

            try {
                Statement stQuery = BD.getCurrentConnection().createStatement();
                ResultSet rsRecords = stQuery.executeQuery(query);


                while (rsRecords.next()){
                    //Dentro del while podemos hacer lo que queramos con los datos.
                }
                
            } catch (Exception e0Exception) {
                e0Exception.printStackTrace();
            }

        } catch (Exception e1Exception) {
            e1Exception.printStackTrace();
        }

    }


    /**
     * Metodo para ejemplicar como seria un metodo para settear datos
     */
    public static void setValues(String nombre, String correo, String contrasena, String fechaNacimiento, String universidad, String ubicacionDeseada, int presupuesto , int cantBaniosDeseados , String compartirU){
        ConexionBD BD;

        try {
            BD = new ConexionBD ("jdbc:mysql://"+servidor+"/"+basededatos+"?useSSL=false"
                    + "&characterEncoding=utf-8",usuario,contrasena);
            BD.getNewConnection();


            String insertQuery = "INSERT INTO  proyectopoo.comprador(comprador.nombre, comprador.correo, comprador.contrasena, comprador.fechaNacimiento, comprador.universidad, comprador.ubicacionDeseada, comprador.presupuesto, comprador.cantBaniosDeseados, comprador.compartirU)\n" +
                    "VALUES ('"+nombre+"', '"+correo+"', '"+contrasena+", '"+fechaNacimiento+"', '"+universidad+"', '"+ubicacionDeseada+"', '"+presupuesto+"', '"+cantBaniosDeseados+"', '"+compartirU+"') ";

            /** TODO
             * "query" es lo que nosotros vamos a modificar traer/settear de la base de datos
             * en este caso es un SELECT que va a traer TODOS los datos de la tabla compradores
             * 
             * TODO: EN FECHA DE NACIMIENTO SE DEBE DE COLOCAR EN EL SIGUIENTE FORMATO: "YYYY-MM-DD"
             * ES DECIR, AÑO-MES-DIA -> 1999-12-31 como ejemplo.
             * 
            */

            try {
                Statement stQueryInsert = BD.getCurrentConnection().createStatement();
                ResultSet rsRecords = stQueryInsert.executeQuery(insertQuery);


                while (rsRecords.next()){
                    //Dentro del while podemos hacer lo que queramos con los datos.
                }
                
            } catch (Exception e0Exception) {
                e0Exception.printStackTrace();
            }

        } catch (Exception e1Exception) {
            e1Exception.printStackTrace();
        }
    }
}
