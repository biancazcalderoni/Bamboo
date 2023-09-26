package Clases;

public class Casa extends Propiedad {
    
    int antiguedad;
    static int cant_hipo_casa;
    static float m2_cub_totales_casas;
    
    /*
    int nro_catastral;
    String direccion;
    double tasacion;
    int cant_ambientes;
    float m2_cubiertos;
    float m2_totales;
    */
    
    public Casa()   {
        nro_catastral = 0;
        direccion = null;
        tasacion = 0;
        cant_ambientes = 0;
        m2_cubiertos = 0;
        m2_totales = 0;
        antiguedad = 0;
    };
    
    public Casa(int nro_catastral, String direccion, 
            double tasacion, int cant_ambientes, float m2_cubiertos, 
            float m2_totales, int antiguedad)   {
        
        Casa.setCant_hipo_casa(Casa.getCant_hipo_casa() + 1);
        Casa.setM2_cub_totales_casas(Casa.getM2_cub_totales_casas() + m2_cubiertos);
        this.nro_catastral = nro_catastral;
        this.direccion = direccion;
        this.tasacion = tasacion;
        this.cant_ambientes = cant_ambientes;
        this.m2_cubiertos = m2_cubiertos;
        this.m2_totales = m2_totales;
        this.antiguedad = antiguedad;
        
    }

    public static int getCant_hipo_casa() {
        return cant_hipo_casa;
    }

    public static void setCant_hipo_casa(int cant_hipo_casa) {
        Casa.cant_hipo_casa = cant_hipo_casa;
    }

    public static float getM2_cub_totales_casas() {
        return m2_cub_totales_casas;
    }

    public static void setM2_cub_totales_casas(float m2_cub_totales_casas) {
        Casa.m2_cub_totales_casas = m2_cub_totales_casas;
    } 
    
    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
    
    public static float prom_casa_hipo()   {
        return m2_cub_totales_casas/cant_hipo_casa;
    }
    
    @Override
    public String toString()    {
        return "Casa\n"+ nro_catastral +"\n"+ direccion +"\n"+ tasacion +"\n"+ cant_ambientes +"\n"+ m2_cubiertos +"\n"+ m2_totales +"\n"+ antiguedad;
    }
     
}

