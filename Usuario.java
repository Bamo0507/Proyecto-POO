public abstract class Usuario {
    //Atributos a utilizar en todas las clases hijas
    protected String nombre;
    protected String correo;
    protected String contrasena;
    protected String fechaNacimiento;

    //Métodos a utilizar en todas las clases hijas
    protected Usuario(String nombre, String correo, String contrasena, String fechaNacimiento){
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
    }

    //Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }    
    
}
