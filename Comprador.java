public class Comprador extends Usuario {
    //Atributos únicos de la clase comprador
    private String ubicacionDeseada;
    private String rentaCompra;
    private String presupuesto;
    private String tipoViviendaDeseado;
    private int cantBanosDeseados;
    private String compartirU;
    private String cuartoCompartido;
    private String serviciosIncluidos;
    //Métodos propios de Comprador
    public Comprador(String nombre, String correo, String contrasena, String fechaNacimiento, String ubicacionDeseada,String rentaCompra, String presupuesto, String tipoViviendaDeseado, int cantBanosDeseados, String compartirU, String cuartoCompartido, String serviciosIncluidos) {
        super(nombre, correo, contrasena, fechaNacimiento);
        this.ubicacionDeseada = ubicacionDeseada;
        this.rentaCompra = rentaCompra;
        this.presupuesto = presupuesto;
        this.tipoViviendaDeseado = tipoViviendaDeseado;
        this.cantBanosDeseados = cantBanosDeseados;
        this.compartirU = compartirU;
        this.cuartoCompartido = cuartoCompartido;
        this.serviciosIncluidos = serviciosIncluidos;
    }
    //Setters
    public void setUbicacionDeseada(String ubicacionDeseada) {
        this.ubicacionDeseada = ubicacionDeseada;
    }
    public void setRentaCompra(String rentaCompra) {
        this.rentaCompra = rentaCompra;
    }
    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }
    public void setTipoViviendaDeseado(String tipoViviendaDeseado) {
        this.tipoViviendaDeseado = tipoViviendaDeseado;
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
    public void setServiciosIncluidos(String serviciosIncluidos) {
        this.serviciosIncluidos = serviciosIncluidos;
    }
    //Getters
    public String getUbicacionDeseada() {
        return ubicacionDeseada;
    }
    public String getRentaCompra() {
        return rentaCompra;
    }
    public String getPresupuesto() {
        return presupuesto;
    }
    public String getTipoViviendaDeseado() {
        return tipoViviendaDeseado;
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
    public String getServiciosIncluidos() {
        return serviciosIncluidos;
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
