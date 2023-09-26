package Clases;

public abstract class Propiedad {
    
    int nro_catastral;
    String direccion;
    double tasacion;
    int cant_ambientes;
    float m2_cubiertos;
    float m2_totales;
    boolean esHipotecado;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + nro_catastral;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Propiedad other = (Propiedad) obj;
        if (this.nro_catastral != other.nro_catastral) {
            return false;
        }
        return true;
    }
    
    public boolean isEsHipotecado() {
        return esHipotecado;
    }

    public void setEsHipotecado(boolean esHipotecado) {
        this.esHipotecado = esHipotecado;
    }

    public int getNro_catastral() {
        return nro_catastral;
    }

    public void setNro_catastral(int nro_catastral) {
        this.nro_catastral = nro_catastral;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getTasacion() {
        return tasacion;
    }

    public void setTasacion(double tasacion) {
        this.tasacion = tasacion;
    }

    public int getCant_ambientes() {
        return cant_ambientes;
    }

    public void setCant_ambientes(int cant_ambientes) {
        this.cant_ambientes = cant_ambientes;
    }

    public float getM2_cubiertos() {
        return m2_cubiertos;
    }

    public void setM2_cubiertos(float m2_cubiertos) {
        this.m2_cubiertos = m2_cubiertos;
    }

    public float getM2_totales() {
        return m2_totales;
    }

    public void setM2_totales(float m2_totales) {
        this.m2_totales = m2_totales;
    }
    
}