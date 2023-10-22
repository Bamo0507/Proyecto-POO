public class Vendedor extends Usuario{
    //Atributos propios de un Vendedor
    private String ubicacionOfrecida;
    private float costoVivienda;
    private int baniosVivienda;
    private int cantPersonasCuarto;
    private String uCompartida;
    private int cantMensajesPendientes;  
    private String universidad;  

    //Setters
    public void setUbicacionOfrecida(String ubicacionOfrecida) {
        this.ubicacionOfrecida = ubicacionOfrecida;
    }
    public Vendedor(String nombre, String correo, String contrasena, String fechaNacimiento, String ubicacionOfrecida,
            float costoVivienda, int baniosVivienda,
            int cantPersonasCuarto, String uCompartida) {
        super(nombre, correo, contrasena, fechaNacimiento);
        this.ubicacionOfrecida = ubicacionOfrecida;
        this.costoVivienda = costoVivienda;
        this.baniosVivienda = baniosVivienda;
        this.cantPersonasCuarto = cantPersonasCuarto;
        this.uCompartida = uCompartida;
    }
    public void setCostoVivienda(float costoVivienda) {
        this.costoVivienda = costoVivienda;
    }
    public void setBaniosVivienda(int baniosVivienda) {
        this.baniosVivienda = baniosVivienda;
    }
    public void setCantPersonasCuarto(int cantPersonasCuarto) {
        this.cantPersonasCuarto = cantPersonasCuarto;
    }
    public void setuCompartida(String uCompartida) {
        this.uCompartida = uCompartida;
    }
    public void setCantMensajesPendientes(int cantMensajesPendientes) {
        this.cantMensajesPendientes = cantMensajesPendientes;
    }
    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }
    
    //Getters
    public String getUbicacionOfrecida() {
        return ubicacionOfrecida;
    }
    public float getCostoVivienda() {
        return costoVivienda;
    }
    public int getBaniosVivienda() {
        return baniosVivienda;
    }
    public int getCantPersonasCuarto() {
        return cantPersonasCuarto;
    }
    public String getuCompartida() {
        return uCompartida;
    }
    public String getUniversidad(){
        return universidad;
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
