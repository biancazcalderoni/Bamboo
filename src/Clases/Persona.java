package Clases;

public class Persona {
    String nombre;
    String apellido;
    int dni;
    long nro_telefono;
    String email;
    int cuit;
    double ingreso_mensual;
    
    public Persona(String nombre, String apellido, int dni, long nro_telefono, String email, int cuit, double ingreso_mensual)    {
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nro_telefono = nro_telefono;
        this.email = email;
        this.ingreso_mensual = ingreso_mensual;
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public long getNro_telefono() {
        return nro_telefono;
    }

    public void setNro_telefono(long nro_telefono) {
        this.nro_telefono = nro_telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCuit() {
        return cuit;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public double getIngreso_mensual() {
        return ingreso_mensual;
    }

    public void setIngreso_mensual(double ingreso_mensual) {
        this.ingreso_mensual = ingreso_mensual;
    }
      
}