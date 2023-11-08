public class Comprador extends Usuario {
    //Atributos únicos de la clase comprador
    private String ubicacionDeseada;
    private float presupuesto;
    private int cantBanosDeseados;
    private String compartirU;
    private String cuartoCompartido;
    private String numero;
    private String universidad;
    private Dorm reservado;
    
    //Métodos propios de Comprador
    public Comprador(String nombre, String correo, String contrasena, String fechaNacimiento, String universidad, String ubicacionDeseada, float presupuesto, int cantBanosDeseados, String compartirU, String cuartoCompartido, String numero) {
        super(nombre, correo, contrasena, fechaNacimiento);
        this.ubicacionDeseada = ubicacionDeseada;
        this.presupuesto = presupuesto;
        this.cantBanosDeseados = cantBanosDeseados;
        this.compartirU = compartirU;
        this.cuartoCompartido = cuartoCompartido;
        this.numero = numero;
    }
    //Setters
    public void setUbicacionDeseada(String ubicacionDeseada) {
        this.ubicacionDeseada = ubicacionDeseada;
    }
    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }
    public void setCantBanosDeseados(int cantBanosDeseados) {
        this.cantBanosDeseados = cantBanosDeseados;
    }
    public void setCompartirU(String compartirU) {
        this.compartirU = compartirU;
    }
    public void setCuartoCompartido(String cuartoCompartido) {
        this.cuartoCompartido = cuartoCompartido;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    //Getters
    public String getUbicacionDeseada() {
        return ubicacionDeseada;
    }
    public float getPresupuesto() {
        return presupuesto;
    }
    public int getCantBanosDeseados() {
        return cantBanosDeseados;
    }
    public String getCompartirU() {
        return compartirU;
    }
    public String getCuartoCompartido() {
        return cuartoCompartido;
    }
    public String getNumero() {
        return numero;
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
    public void setUniversidad(String universidad){
        this.universidad = universidad;
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
    public String getUniversidad(){
        return this.universidad;
    }

    @Override
    public String toString() {
        return nombre + " previamente definiste tus preferencias como: \n" + "Cantidad de baños deseados: " + cantBanosDeseados+"\n" + "Ubiación deseada: " + ubicacionDeseada +"\n" + "Tú presupuesto es de: " + presupuesto + "\n" + "Tu cuarto será compartido: " + cuartoCompartido + "\n" + "Convivirás con gente de otras universidades: " + compartirU;
    }
    
    
    

}
