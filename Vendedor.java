import java.util.ArrayList;

public class Vendedor extends Usuario{
    //Atributos propios de un Vendedor
    private int cantMensajesPendientes;  
    private String universidad;  
    private ArrayList<Dorm> dorms = new ArrayList<>(); 

    //Setters
    public Vendedor(String nombre, String correo, String contrasena, String fechaNacimiento) {
        super(nombre, correo, contrasena, fechaNacimiento);
    }
    public void setCantMensajesPendientes(int cantMensajesPendientes) {
        this.cantMensajesPendientes = cantMensajesPendientes;
    }
    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }
    public void addDorm(String ubicacionOfrecida, float costoVivienda, int baniosVivienda, int cantPersonasCuarto,
    String uCompartida){
        dorms.add(new Dorm(ubicacionOfrecida, costoVivienda, baniosVivienda, cantPersonasCuarto,
        uCompartida));
        
    }
    
    //Getters
    public String getUniversidad(){
        return universidad;
    }
    public ArrayList<Dorm> getDorms(){
        return dorms;
    }

    //Métodos Padre
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    public void setFechaNacimiento(String fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre(){
        return this.nombre;
    }
    public String getCorreo(){
        return this.correo;
    }
    public String getFechaNacimiento(){
        return this.fechaNacimiento;
    }
    public String getContrasena(){
        return this.contrasena;
    } 
    
}
