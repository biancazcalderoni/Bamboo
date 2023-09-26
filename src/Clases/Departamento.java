package Clases;

public class Departamento extends Propiedad{
    
    int piso;
    String letNro;
    static int cant_hipo_depto;
    static float m2_cub_totales_depto;
    
    /*
    int nro_catastral;
    String direccion;
    double tasacion;
    int cant_ambientes;
    float m2_cubiertos;
    float m2_totales;
    */

    public Departamento() {
        nro_catastral = 0;
        direccion = null;
        tasacion = 0;
        cant_ambientes = 0;
        m2_cubiertos = 0;
        m2_totales = 0;
        piso = 0;
        letNro = null;
    }
    
    
    
    public Departamento(int nro_catastral, String direccion, 
            double tasacion, int cant_ambientes, float m2_cubiertos, 
            float m2_totales, int piso, String letNro)   {
        
        Departamento.setCant_hipo_depto(Departamento.getCant_hipo_depto() + 1);
        Departamento.setM2_cub_totales_depto(Departamento.getM2_cub_totales_depto() + m2_cubiertos);
        this.nro_catastral = nro_catastral;
        this.direccion = direccion;
        this.tasacion = tasacion;
        this.cant_ambientes = cant_ambientes;
        this.m2_cubiertos = m2_cubiertos;
        this.m2_totales = m2_totales;
        this.piso = piso;
        this.letNro = letNro;
    }

    public static int getCant_hipo_depto() {
        return cant_hipo_depto;
    }

    public static void setCant_hipo_depto(int cant_hipo_depto) {
        Departamento.cant_hipo_depto = cant_hipo_depto;
    }

    public static float getM2_cub_totales_depto() {
        return m2_cub_totales_depto;
    }

    public static void setM2_cub_totales_depto(float m2_cub_totales_depto) {
        Departamento.m2_cub_totales_depto = m2_cub_totales_depto;
    } 
    
    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getLetNro() {
        return letNro;
    }

    public void setLetNro(String letNro) {
        this.letNro = letNro;
    }
    
    public static float prom_depto_hipo()   {
        return m2_cub_totales_depto/cant_hipo_depto;
    }
    
    @Override
    public String toString()    {
        return "Departamento\n"+ nro_catastral +"\n"+ direccion +"\n"+ tasacion +"\n"+ cant_ambientes +"\n"+ m2_cubiertos +"\n"+ m2_totales +"\n"+ piso +"\n"+ letNro;
    }
    
}

