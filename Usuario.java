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
    protected abstract void setNombre(String nombre);
    protected abstract void setCorreo(String correo);
    protected abstract void setContrasena(String contrasena);
    protected abstract void setFechaNacimiento(String fechaNacimiento);

    //Getters
    protected abstract String getNombre();
    protected abstract String getCorreo();
    protected abstract String getFechaNacimiento();
    protected abstract String getContrasena();    
}
