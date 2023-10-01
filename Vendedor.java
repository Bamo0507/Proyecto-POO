public class Vendedor extends Usuario{
    //Atributos propios de un Vendedor
    private String ubicacionOfrecida;
    private String tipoViviendaOfrecida;
    private float costoVivienda;
    private int baniosVivienda;
    private String serviciosOfrecidos;
    private String compraVenta;
    private int cantPersonasCuarto;
    private String uCompartida;

    //Métodos propios de un Vendedor
    

    //Setters
    public void setUbicacionOfrecida(String ubicacionOfrecida) {
        this.ubicacionOfrecida = ubicacionOfrecida;
    }
    public Vendedor(String nombre, String correo, String contrasena, String fechaNacimiento, String ubicacionOfrecida,
            String tipoViviendaOfrecida, float costoVivienda, int baniosVivienda, String serviciosOfrecidos,
            String compraVenta, int cantPersonasCuarto, String uCompartida) {
        super(nombre, correo, contrasena, fechaNacimiento);
        this.ubicacionOfrecida = ubicacionOfrecida;
        this.tipoViviendaOfrecida = tipoViviendaOfrecida;
        this.costoVivienda = costoVivienda;
        this.baniosVivienda = baniosVivienda;
        this.serviciosOfrecidos = serviciosOfrecidos;
        this.compraVenta = compraVenta;
        this.cantPersonasCuarto = cantPersonasCuarto;
        this.uCompartida = uCompartida;
    }
    public void setTipoViviendaOfrecida(String tipoViviendaOfrecida) {
        this.tipoViviendaOfrecida = tipoViviendaOfrecida;
    }
    public void setCostoVivienda(float costoVivienda) {
        this.costoVivienda = costoVivienda;
    }
    public void setBaniosVivienda(int baniosVivienda) {
        this.baniosVivienda = baniosVivienda;
    }
    public void setServiciosOfrecidos(String serviciosOfrecidos) {
        this.serviciosOfrecidos = serviciosOfrecidos;
    }
    public void setCompraVenta(String compraVenta) {
        this.compraVenta = compraVenta;
    }
    public void setCantPersonasCuarto(int cantPersonasCuarto) {
        this.cantPersonasCuarto = cantPersonasCuarto;
    }
    public void setuCompartida(String uCompartida) {
        this.uCompartida = uCompartida;
    }
    
    //Getters
    public String getUbicacionOfrecida() {
        return ubicacionOfrecida;
    }
    public String getTipoViviendaOfrecida() {
        return tipoViviendaOfrecida;
    }
    public float getCostoVivienda() {
        return costoVivienda;
    }
    public int getBaniosVivienda() {
        return baniosVivienda;
    }
    public String getServiciosOfrecidos() {
        return serviciosOfrecidos;
    }
    public String getCompraVenta() {
        return compraVenta;
    }
    public int getCantPersonasCuarto() {
        return cantPersonasCuarto;
    }
    public String getuCompartida() {
        return uCompartida;
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
