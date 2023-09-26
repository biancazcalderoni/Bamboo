package Clases;

public class Hipoteca {
    
    int id_hip;
    String fecha_inicio;
    double capital;
    float interes;
    String fecha_est_fin;
    String fecha_fin;
    double cuota;

    public int getId_hip() {
        return id_hip;
    }

    public void setId_hip(int id_hip) {
        this.id_hip = id_hip;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    public String getFecha_est_fin() {
        return fecha_est_fin;
    }

    public void setFecha_est_fin(String fecha_est_fin) {
        this.fecha_est_fin = fecha_est_fin;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }
    
}
