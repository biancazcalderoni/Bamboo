package Interfaces;
import Clases.Casa;
import static Clases.Casa.prom_casa_hipo;
import Clases.Departamento;
import static Clases.Departamento.prom_depto_hipo;
import Clases.Propiedad;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.*;


public class Principal extends javax.swing.JFrame {
    
    JDialogConstr construccion = new JDialogConstr(this, true);
    JDialogPropExiste propExiste = new JDialogPropExiste(this, true);
    JDialogGuardadoExitoso guardado = new JDialogGuardadoExitoso(this, true);
    JDialogPropNoExiste propNoExiste = new JDialogPropNoExiste(this, true);
    JDialogEliminado eliminado = new JDialogEliminado(this, true);
    JDialogModificarExistoso modificado = new JDialogModificarExistoso(this, true);
    
    Set <Propiedad> propiedades = new HashSet<Propiedad>();
        
    DefaultTableModel dtm;
    
    static File f = new File("Propiedades.txt");
    JDialogCasillasVacias casillasVacias = new JDialogCasillasVacias(this, true);
    FileWriter fichero = null;
    PrintWriter pw = null;
    FileReader r = null;
    BufferedReader br = null;
    
    /*
     Casa c1 = new Casa(1,"",0,0,0,0,0);
     Casa c2 = new Casa(2,"",0,0,0,0,0);
     Casa c3 = new Casa(3,"",0,0,0,0,0);
     Departamento d1 = new Departamento(4,"",0,0,0,0,0,0);
     Departamento d2 = new Departamento(5,"",0,0,0,0,0,0);
    */
    
    
    public Principal() throws IOException {
        initComponents();
        CreateColumns();
        setResizable(false);
        setTitle("Sistema de Hipotecas - Banco Bamboo");
                
        if(!f.exists()){
            f.createNewFile();
        }

        /*
        // ESCRIBIR EN ARCHIVO EJEMPLO
        try
        {
            fichero = new FileWriter(f);
            pw = new PrintWriter(fichero);
            
            pw.println(c1);
            pw.println(c2);
            pw.println(d1);
            pw.println(c3);
            pw.println(d2);
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();

        }
        */

        // LECTURA INICIAL DEL PROGRAMA
        
            String s;
            Casa casaAux = new Casa();
            Departamento deptoAux = new Departamento();
            int x;
            float fl;
            double d;
            
            r = new FileReader(f);
            br = new BufferedReader(r);
        
            while((s = br.readLine()) != null) {
                if("Casa".equals(s)) {
                    //Ingresa letNro catastral
                    x = Integer.parseInt(s = br.readLine());
                    casaAux.setNro_catastral(x);
                    //Ingresa direccion
                    casaAux.setDireccion(s = br.readLine());
                    //Ingresa tasacion
                    d = Double.parseDouble( s = br.readLine());
                    casaAux.setTasacion(d);
                    //Ingresa cantidad ambientes
                    x = Integer.parseInt(s = br.readLine());
                    casaAux.setCant_ambientes(x);
                    //Ingresa metros cubiertos
                    fl = Float.parseFloat(s = br.readLine());
                    casaAux.setM2_cubiertos(fl);
                    //Ingresa metros totales
                    fl = Float.parseFloat(s = br.readLine());
                    casaAux.setM2_totales(fl);
                    //Ingresa antiguedad
                    x = Integer.parseInt(s = br.readLine());
                    casaAux.setAntiguedad(x);
                    
                    Casa casaAux2 = new Casa(casaAux.getNro_catastral(),casaAux.getDireccion(),casaAux.getTasacion(), casaAux.getCant_ambientes(), casaAux.getM2_cubiertos(), casaAux.getM2_totales(), casaAux.getAntiguedad());
                                        
                    propiedades.add(casaAux2);
                    
                }else if ("Departamento".equals(s))  {
                    //Ingresa letNro catastral
                    x = Integer.parseInt(s = br.readLine());
                    deptoAux.setNro_catastral(x);
                    //Ingresa direccion
                    deptoAux.setDireccion(s = br.readLine());
                    //Ingresa tasacion
                    d = Double.parseDouble( s = br.readLine());
                    deptoAux.setTasacion(d);
                    //Ingresa cantidad ambientes
                    x = Integer.parseInt(s = br.readLine());
                    deptoAux.setCant_ambientes(x);
                    //Ingresa metros cubiertos
                    fl = Float.parseFloat(s = br.readLine());
                    deptoAux.setM2_cubiertos(fl);
                    //Ingresa metros totales
                    fl = Float.parseFloat(s = br.readLine());
                    deptoAux.setM2_totales(fl);
                    //Ingresa piso
                    x = Integer.parseInt(s = br.readLine());
                    deptoAux.setPiso(x);
                    //Ingresa letNro
                    deptoAux.setLetNro(s = br.readLine());
                    
                    Departamento deptoAux2 = new Departamento(deptoAux.getNro_catastral(),deptoAux.getDireccion(),deptoAux.getTasacion(), deptoAux.getCant_ambientes(), deptoAux.getM2_cubiertos(), deptoAux.getM2_totales(), deptoAux.getPiso(), deptoAux.getLetNro());
                                        
                    propiedades.add(deptoAux2);
                }
            }
            
            
            
            /*for (Propiedad Prop:propiedades){
                    System.out.println(Prop);
            }*/
                        
            //GUARDAR Y SALIR
            
            /*
            FileWriter fichero = null;
            PrintWriter pw = null;
            FileReader r = null;
            BufferedReader br = null;
            
            for (Propiedad Prop:propiedades){
                try {
                    fichero = new FileWriter(f);
                    pw = new PrintWriter(fichero);

                    pw.println(Prop);


                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                   // Nuevamente aprovechamos el finally para 
                   // asegurarnos que se cierra el fichero.
                   if (null != fichero)
                      fichero.close();

                }
            }
            */
    }

    public void agregarCasa(int nro_catastral, String direccion, double tasacion, int cant_ambientes, float m2_cubiertos, float m2_totales, int antiguedad){
        Casa casaAux = new Casa(nro_catastral, direccion, tasacion, cant_ambientes, m2_cubiertos, m2_totales, antiguedad);
        Iterator<Propiedad> iterator = propiedades.iterator();
        boolean exist = false;

        if(!iterator.hasNext()){
            guardado.setVisible(true);
            propiedades.add(casaAux);
        }
        
        while (iterator.hasNext()) {
            Propiedad element = iterator.next();
            if (element.getNro_catastral() == nro_catastral) {   
                exist = true;
            } 
        }
        
        if (exist)  {
            propExiste.setVisible(true);
        }   else    {
            guardado.setVisible(true);
            propiedades.add(casaAux);
        }
    }
    
    public void agregarDepto(int nro_catastral, String direccion, double tasacion, int cant_ambientes, float m2_cubiertos, float m2_totales, int piso, String letNro) {
        Departamento deptoAux = new Departamento(nro_catastral, direccion, tasacion, cant_ambientes, m2_cubiertos, m2_totales, piso, letNro);
        Iterator<Propiedad> iterator = propiedades.iterator();
        boolean exist = false;
        
        if(!iterator.hasNext()){
            guardado.setVisible(true);
            propiedades.add(deptoAux);
        }
        
        while (iterator.hasNext()) {
            Propiedad element = iterator.next();
            if (element.getNro_catastral() == nro_catastral) {
                exist = true;
            }
        }
        
        if (exist)  {
            propExiste.setVisible(true);
        }   else    {
            guardado.setVisible(true);
            propiedades.add(deptoAux);
        }
    }
    
    public void eliminarPropiedad(int nro_catastro, boolean isModificable){
        Iterator<Propiedad> iterator = propiedades.iterator();
        while (iterator.hasNext()) {
            Propiedad element = iterator.next();
            if (element.getNro_catastral() == nro_catastro && !isModificable) {
                iterator.remove();
                eliminado.setVisible(true);
            } else if (element.getNro_catastral() == nro_catastro && isModificable){
                iterator.remove();
            }
        }
    }
    
    /*public void modificarCasa(int nro_catastral, String direccion, double tasacion, int cant_ambientes, float m2_cubiertos, float m2_totales, int antiguedad){
        eliminarPropiedad(nro_catastral);
        agregarCasa(nro_catastral, direccion, tasacion, cant_ambientes, m2_cubiertos, m2_totales, antiguedad);
    }*/
    
    /*
    public void modificarDepto(int nro_catastral, String direccion, double tasacion, int cant_ambientes, float m2_cubiertos, float m2_totales, int piso, int letNro){
        eliminarPropiedad(nro_catastral);
        agregarDepto(nro_catastral, direccion, tasacion, cant_ambientes, m2_cubiertos, m2_totales, piso, letNro);
    }*/
    
    public void modificarPropiedad(int nro_catastral, String direccion, double tasacion, int cant_ambientes, float m2_cubiertos, float m2_totales, int antiguedad, int piso, String letNro){
        Iterator<Propiedad> iterator = propiedades.iterator();
        boolean isModificable = false;
        
        while (iterator.hasNext()) {
            Propiedad element = iterator.next();
            if ((element.getNro_catastral() == nro_catastral)) {
                if(element instanceof Casa && radCasa.isSelected()){
                    Casa auxiliar = (Casa) element;
                    
                    auxiliar.setDireccion(direccion);
                    auxiliar.setTasacion(tasacion);
                    auxiliar.setCant_ambientes(cant_ambientes);
                    auxiliar.setM2_cubiertos(m2_cubiertos);
                    auxiliar.setM2_totales(m2_totales);
                    auxiliar.setAntiguedad(antiguedad);
                } else if (element instanceof Departamento && radCasa.isSelected()){
                    isModificable = true;
                    eliminarPropiedad(nro_catastral, isModificable);
                    Casa casaAux = new Casa(nro_catastral, direccion, tasacion, cant_ambientes, m2_cubiertos, m2_totales, antiguedad);
                    propiedades.add(casaAux);  
                } else if (element instanceof Departamento && radDepto.isSelected()){
                    Departamento auxiliar2 = (Departamento) element;
                    
                    auxiliar2.setDireccion(direccion);
                    auxiliar2.setTasacion(tasacion);
                    auxiliar2.setCant_ambientes(cant_ambientes);
                    auxiliar2.setM2_cubiertos(m2_cubiertos);
                    auxiliar2.setM2_totales(m2_totales);
                    auxiliar2.setPiso(piso);
                    auxiliar2.setLetNro(letNro);
                } else if (element instanceof Casa && radDepto.isSelected()){
                    isModificable = true;
                    eliminarPropiedad(nro_catastral, isModificable);
                    Departamento deptoAux = new Departamento(nro_catastral, direccion, tasacion, cant_ambientes, m2_cubiertos, m2_totales, piso, letNro);
                    propiedades.add(deptoAux);

                }
            }
        }
    }
    
    /*public void modificarCasa(int nro_catastral, String direccion, double tasacion, int cant_ambientes, float m2_cubiertos, float m2_totales, int antiguedad){
        Iterator<Propiedad> iterator = propiedades.iterator();
        while (iterator.hasNext()) {
            Casa element = (Casa) iterator.next();
            if ((element.getNro_catastral() == nro_catastral)) {
                if(radCasa.isSelected()){
                    element.setDireccion(direccion);
                    element.setTasacion(tasacion);
                    element.setCant_ambientes(cant_ambientes);
                    element.setM2_cubiertos(m2_cubiertos);
                    element.setM2_totales(m2_totales);
                    element.setAntiguedad(antiguedad);
                } else {
                    eliminarPropiedad(nro_catastral);
                    agregarDepto(nro_catastral, direccion, tasacion, cant_ambientes, m2_cubiertos, m2_totales, piso, letNro);
                }
            }
        }
    }*/
    
    /*public void modificarDepto(int nro_catastral, String direccion, double tasacion, int cant_ambientes, float m2_cubiertos, float m2_totales, int piso, String letNro){
        Iterator<Propiedad> iterator = propiedades.iterator();
        while (iterator.hasNext()) {
            Departamento element = (Departamento) iterator.next();
            if ((element.getNro_catastral() == nro_catastral)) {
                element.setDireccion(direccion);
                element.setTasacion(tasacion);
                element.setCant_ambientes(cant_ambientes);
                element.setM2_cubiertos(m2_cubiertos);
                element.setM2_totales(m2_totales);
                element.setPiso(piso);
                element.setLetNro(letNro);
            }
        }
    }*/
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opCasDept = new javax.swing.ButtonGroup();
        header = new javax.swing.JPanel();
        guYsalir = new javax.swing.JButton();
        tituloPpal = new javax.swing.JLabel();
        jButtInicio = new javax.swing.JButton();
        labPandita = new javax.swing.JLabel();
        contenedor = new javax.swing.JPanel();
        inicio = new javax.swing.JPanel();
        jButtSimPrest = new javax.swing.JButton();
        jButtGestPers = new javax.swing.JButton();
        jButtGestPrest = new javax.swing.JButton();
        jButtGestProp = new javax.swing.JButton();
        jButtOtrasCons = new javax.swing.JButton();
        gestProp = new javax.swing.JPanel();
        labGestProp = new javax.swing.JLabel();
        jButtCargProp = new javax.swing.JButton();
        jButtElimProp = new javax.swing.JButton();
        jButtModProp = new javax.swing.JButton();
        jButtConsProp = new javax.swing.JButton();
        gestPers = new javax.swing.JPanel();
        labGestPers = new javax.swing.JLabel();
        jButtCargPers = new javax.swing.JButton();
        jButtElimPers = new javax.swing.JButton();
        jButtModPers = new javax.swing.JButton();
        jButtConsPers = new javax.swing.JButton();
        gestPrestHip = new javax.swing.JPanel();
        labGestPrestHip = new javax.swing.JLabel();
        jButtBuscPres = new javax.swing.JButton();
        jButtElimPrest = new javax.swing.JButton();
        jButtModPrest = new javax.swing.JButton();
        jButtFinPrest = new javax.swing.JButton();
        jButtSimPrestGest = new javax.swing.JButton();
        otrasConsultas = new javax.swing.JPanel();
        labConsDisp = new javax.swing.JLabel();
        jButtCons10Vig = new javax.swing.JButton();
        jButtConsMayMenMont = new javax.swing.JButton();
        jButtPromM2 = new javax.swing.JButton();
        jButtHipActiva = new javax.swing.JButton();
        jButtMasHip = new javax.swing.JButton();
        jButtCons10NoVig = new javax.swing.JButton();
        altaProp = new javax.swing.JPanel();
        opCasDep = new javax.swing.JPanel();
        panelVacio = new javax.swing.JPanel();
        panelCasa = new javax.swing.JPanel();
        antig = new javax.swing.JLabel();
        antiguedad = new javax.swing.JTextField();
        meses = new javax.swing.JLabel();
        panelDepto = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        piso = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        letraNro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nroCatastral = new javax.swing.JTextField();
        direccion = new javax.swing.JTextField();
        m2totales = new javax.swing.JTextField();
        tasacion = new javax.swing.JTextField();
        m2cubiertos = new javax.swing.JTextField();
        cantAmbientes = new javax.swing.JTextField();
        jButtConfirmarAlta = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jRadioCasa = new javax.swing.JRadioButton();
        jRadioDepto = new javax.swing.JRadioButton();
        elimPers = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButtBusDNI = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        telefono = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cuil = new javax.swing.JTextField();
        ingMens = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButtBusDNI1 = new javax.swing.JButton();
        consProp = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        buscProp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProp = new javax.swing.JTable();
        modProp = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButtModBuscar = new javax.swing.JButton();
        modNroCatast = new javax.swing.JTextField();
        modproppan = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        modMuestraProp = new javax.swing.JPanel();
        radCasa = new javax.swing.JRadioButton();
        radDepto = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        modDireccion = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        modm2tot = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        modm2cub = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        modtasacion = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        modAmb = new javax.swing.JTextField();
        modPropConfirm = new javax.swing.JButton();
        contMod = new javax.swing.JPanel();
        modCasaPan = new javax.swing.JPanel();
        modAntig = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        modDeptoPan = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        modLetNro = new javax.swing.JTextField();
        modPiso = new javax.swing.JTextField();
        elimProp = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jButtModBuscar1 = new javax.swing.JButton();
        elimNroCatast = new javax.swing.JTextField();
        elimPropPan = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        elimMuestraProp = new javax.swing.JPanel();
        radCasa1 = new javax.swing.JRadioButton();
        radDepto1 = new javax.swing.JRadioButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        elimDireccion = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        elimM2Tot = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        elimM2Cub = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        elimTasa = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        elimAmb = new javax.swing.JTextField();
        elimPropConfirm = new javax.swing.JButton();
        contElim = new javax.swing.JPanel();
        elimCasaPan = new javax.swing.JPanel();
        elimAntig = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        elimDeptoPan = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        elimLetraNro = new javax.swing.JTextField();
        elimPiso = new javax.swing.JTextField();
        aggPers = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        nombre1 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        apellido1 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        telefono1 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        email1 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        cuil1 = new javax.swing.JTextField();
        ingMens1 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jButtAggPers = new javax.swing.JButton();
        consultarPers = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButtBusDNI2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        modPers = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButtBusDNI3 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        nombre2 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        apellido2 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        telefono2 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        email2 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        cuil2 = new javax.swing.JTextField();
        ingMens2 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jButtBusDNI4 = new javax.swing.JButton();
        simPrest = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PestañasSimulador = new javax.swing.JTabbedPane();
        pestTitular = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        aggPers1 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        nombre3 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        apellido3 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        telefono3 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        email3 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        cuil3 = new javax.swing.JTextField();
        ingMens3 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jButtConfTit = new javax.swing.JButton();
        pestProp = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        elimNroCatast1 = new javax.swing.JTextField();
        jButtModBuscar2 = new javax.swing.JButton();
        radCasa2 = new javax.swing.JRadioButton();
        radDepto2 = new javax.swing.JRadioButton();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        modDireccion1 = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        modm2tot1 = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        modm2cub1 = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        modtasacion1 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        modAmb1 = new javax.swing.JTextField();
        modPropConfirm1 = new javax.swing.JButton();
        panPropCamb = new javax.swing.JPanel();
        elimDeptoPan1 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        elimLetraNro1 = new javax.swing.JTextField();
        elimPiso1 = new javax.swing.JTextField();
        elimCasaPan1 = new javax.swing.JPanel();
        elimAntig1 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        pestGarante = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        pestHipoteca = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        nombre4 = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        nombre5 = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jButtConfTit1 = new javax.swing.JButton();
        consulta = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        labCasa = new javax.swing.JLabel();
        labDepto = new javax.swing.JLabel();
        labDepto1 = new javax.swing.JLabel();
        labDepto2 = new javax.swing.JLabel();
        labDepto3 = new javax.swing.JLabel();
        labDepto4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));

        header.setBackground(new java.awt.Color(153, 255, 153));
        header.setPreferredSize(new java.awt.Dimension(400, 200));

        guYsalir.setBackground(new java.awt.Color(51, 102, 0));
        guYsalir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        guYsalir.setText("Guardar y Salir");
        guYsalir.setAlignmentY(4.0F);
        guYsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guYsalirActionPerformed(evt);
            }
        });

        tituloPpal.setFont(new java.awt.Font("Footlight MT Light", 1, 36)); // NOI18N
        tituloPpal.setForeground(new java.awt.Color(51, 102, 0));
        tituloPpal.setText("Banco Bamboo");

        jButtInicio.setBackground(new java.awt.Color(51, 102, 0));
        jButtInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtInicio.setText("Inicio");
        jButtInicio.setAlignmentY(4.0F);
        jButtInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtInicioActionPerformed(evt);
            }
        });

        labPandita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pandita.png"))); // NOI18N

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(labPandita, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tituloPpal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guYsalir)
                .addGap(17, 17, 17))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guYsalir)
                    .addComponent(jButtInicio))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tituloPpal)
                .addGap(19, 19, 19))
            .addComponent(labPandita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        contenedor.setBackground(new java.awt.Color(204, 255, 204));
        contenedor.setLayout(new java.awt.CardLayout());

        inicio.setBackground(new java.awt.Color(204, 255, 204));

        jButtSimPrest.setBackground(new java.awt.Color(36, 173, 73));
        jButtSimPrest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtSimPrest.setForeground(new java.awt.Color(0, 102, 51));
        jButtSimPrest.setText("Simular préstamo hipotecario");
        jButtSimPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtSimPrestActionPerformed(evt);
            }
        });

        jButtGestPers.setBackground(new java.awt.Color(73, 248, 121));
        jButtGestPers.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtGestPers.setForeground(new java.awt.Color(0, 102, 0));
        jButtGestPers.setText("Gestionar Personas");
        jButtGestPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtGestPersActionPerformed(evt);
            }
        });

        jButtGestPrest.setBackground(new java.awt.Color(73, 248, 121));
        jButtGestPrest.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtGestPrest.setForeground(new java.awt.Color(0, 102, 0));
        jButtGestPrest.setText("Gestionar préstamos hipotecarios");
        jButtGestPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtGestPrestActionPerformed(evt);
            }
        });

        jButtGestProp.setBackground(new java.awt.Color(73, 248, 121));
        jButtGestProp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtGestProp.setForeground(new java.awt.Color(0, 102, 0));
        jButtGestProp.setText("Gestionar Propiedades");
        jButtGestProp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtGestPropActionPerformed(evt);
            }
        });

        jButtOtrasCons.setBackground(new java.awt.Color(73, 248, 121));
        jButtOtrasCons.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtOtrasCons.setForeground(new java.awt.Color(0, 102, 0));
        jButtOtrasCons.setText("Otras consultas");
        jButtOtrasCons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtOtrasConsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inicioLayout = new javax.swing.GroupLayout(inicio);
        inicio.setLayout(inicioLayout);
        inicioLayout.setHorizontalGroup(
            inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtSimPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(inicioLayout.createSequentialGroup()
                        .addGroup(inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtGestPers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtGestProp, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtGestPrest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtOtrasCons, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        inicioLayout.setVerticalGroup(
            inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jButtSimPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtGestPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtGestPers, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtOtrasCons, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtGestProp, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
        );

        contenedor.add(inicio, "card2");

        gestProp.setBackground(new java.awt.Color(204, 255, 204));

        labGestProp.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        labGestProp.setForeground(new java.awt.Color(0, 102, 0));
        labGestProp.setText("Gestionamiento de Propiedades");

        jButtCargProp.setBackground(new java.awt.Color(73, 248, 121));
        jButtCargProp.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtCargProp.setForeground(new java.awt.Color(0, 102, 0));
        jButtCargProp.setText("Cargar una nueva propiedad");
        jButtCargProp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtCargPropActionPerformed(evt);
            }
        });

        jButtElimProp.setBackground(new java.awt.Color(73, 248, 121));
        jButtElimProp.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtElimProp.setForeground(new java.awt.Color(0, 102, 0));
        jButtElimProp.setText("Eliminar una propiedad del sistema");
        jButtElimProp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtElimPropActionPerformed(evt);
            }
        });

        jButtModProp.setBackground(new java.awt.Color(73, 248, 121));
        jButtModProp.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtModProp.setForeground(new java.awt.Color(0, 102, 0));
        jButtModProp.setText("Modificar los datos de una propiedad");
        jButtModProp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtModPropActionPerformed(evt);
            }
        });

        jButtConsProp.setBackground(new java.awt.Color(73, 248, 121));
        jButtConsProp.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtConsProp.setForeground(new java.awt.Color(0, 102, 0));
        jButtConsProp.setText("Consultar los datos de una propiedad");
        jButtConsProp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtConsPropActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gestPropLayout = new javax.swing.GroupLayout(gestProp);
        gestProp.setLayout(gestPropLayout);
        gestPropLayout.setHorizontalGroup(
            gestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestPropLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(gestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gestPropLayout.createSequentialGroup()
                        .addGroup(gestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtCargProp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtModProp, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(gestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtElimProp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtConsProp, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gestPropLayout.createSequentialGroup()
                        .addComponent(labGestProp)
                        .addGap(127, 127, 127)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        gestPropLayout.setVerticalGroup(
            gestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestPropLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(labGestProp)
                .addGap(30, 30, 30)
                .addGroup(gestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtCargProp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtElimProp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtConsProp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtModProp, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        contenedor.add(gestProp, "card3");

        gestPers.setBackground(new java.awt.Color(204, 255, 204));

        labGestPers.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        labGestPers.setForeground(new java.awt.Color(0, 102, 0));
        labGestPers.setText("Gestionamiento de Personas");

        jButtCargPers.setBackground(new java.awt.Color(73, 248, 121));
        jButtCargPers.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtCargPers.setForeground(new java.awt.Color(0, 102, 0));
        jButtCargPers.setText("Cargar una nueva persona");
        jButtCargPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtCargPersActionPerformed(evt);
            }
        });

        jButtElimPers.setBackground(new java.awt.Color(73, 248, 121));
        jButtElimPers.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtElimPers.setForeground(new java.awt.Color(0, 102, 0));
        jButtElimPers.setText("Eliminar una persona del sistema");
        jButtElimPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtElimPersActionPerformed(evt);
            }
        });

        jButtModPers.setBackground(new java.awt.Color(73, 248, 121));
        jButtModPers.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtModPers.setForeground(new java.awt.Color(0, 102, 0));
        jButtModPers.setText("Modificar los datos de una persona");
        jButtModPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtModPersActionPerformed(evt);
            }
        });

        jButtConsPers.setBackground(new java.awt.Color(73, 248, 121));
        jButtConsPers.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtConsPers.setForeground(new java.awt.Color(0, 102, 0));
        jButtConsPers.setText("Consultar los datos de una persona");
        jButtConsPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtConsPersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gestPersLayout = new javax.swing.GroupLayout(gestPers);
        gestPers.setLayout(gestPersLayout);
        gestPersLayout.setHorizontalGroup(
            gestPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gestPersLayout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(gestPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(gestPersLayout.createSequentialGroup()
                        .addGroup(gestPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtCargPers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtModPers, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(gestPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtElimPers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtConsPers, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(gestPersLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(labGestPers)))
                .addGap(86, 86, 86))
        );
        gestPersLayout.setVerticalGroup(
            gestPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestPersLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(labGestPers)
                .addGap(38, 38, 38)
                .addGroup(gestPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtCargPers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtElimPers, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gestPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtConsPers, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtModPers, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        contenedor.add(gestPers, "card4");

        gestPrestHip.setBackground(new java.awt.Color(204, 255, 204));

        labGestPrestHip.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        labGestPrestHip.setForeground(new java.awt.Color(0, 102, 0));
        labGestPrestHip.setText("Gestionamiento de préstamos hipotecarios");

        jButtBuscPres.setBackground(new java.awt.Color(73, 248, 121));
        jButtBuscPres.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtBuscPres.setForeground(new java.awt.Color(0, 102, 0));
        jButtBuscPres.setText("Buscar un préstamo hipotecario");
        jButtBuscPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtBuscPresActionPerformed(evt);
            }
        });

        jButtElimPrest.setBackground(new java.awt.Color(73, 248, 121));
        jButtElimPrest.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtElimPrest.setForeground(new java.awt.Color(0, 102, 0));
        jButtElimPrest.setText("Dar de baja un préstamo");
        jButtElimPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtElimPrestActionPerformed(evt);
            }
        });

        jButtModPrest.setBackground(new java.awt.Color(73, 248, 121));
        jButtModPrest.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtModPrest.setForeground(new java.awt.Color(0, 102, 0));
        jButtModPrest.setText("Modificar los datos de un préstamo");
        jButtModPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtModPrestActionPerformed(evt);
            }
        });

        jButtFinPrest.setBackground(new java.awt.Color(73, 248, 121));
        jButtFinPrest.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jButtFinPrest.setForeground(new java.awt.Color(0, 102, 0));
        jButtFinPrest.setText("Finalizar un préstamo hipotecario");
        jButtFinPrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtFinPrestActionPerformed(evt);
            }
        });

        jButtSimPrestGest.setBackground(new java.awt.Color(51, 255, 102));
        jButtSimPrestGest.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jButtSimPrestGest.setForeground(new java.awt.Color(0, 102, 51));
        jButtSimPrestGest.setText("Simular préstamo hipotecario");
        jButtSimPrestGest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtSimPrestGestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gestPrestHipLayout = new javax.swing.GroupLayout(gestPrestHip);
        gestPrestHip.setLayout(gestPrestHipLayout);
        gestPrestHipLayout.setHorizontalGroup(
            gestPrestHipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestPrestHipLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(gestPrestHipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gestPrestHipLayout.createSequentialGroup()
                        .addComponent(labGestPrestHip)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gestPrestHipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtSimPrestGest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(gestPrestHipLayout.createSequentialGroup()
                            .addGroup(gestPrestHipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtBuscPres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtModPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(gestPrestHipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButtElimPrest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtFinPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        gestPrestHipLayout.setVerticalGroup(
            gestPrestHipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gestPrestHipLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(labGestPrestHip)
                .addGap(18, 18, 18)
                .addComponent(jButtSimPrestGest, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gestPrestHipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtBuscPres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtElimPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(gestPrestHipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtFinPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtModPrest, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        contenedor.add(gestPrestHip, "card5");

        otrasConsultas.setBackground(new java.awt.Color(204, 255, 204));

        labConsDisp.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        labConsDisp.setForeground(new java.awt.Color(0, 102, 0));
        labConsDisp.setText("Consultas disponibles");

        jButtCons10Vig.setBackground(new java.awt.Color(73, 248, 121));
        jButtCons10Vig.setForeground(new java.awt.Color(0, 102, 0));
        jButtCons10Vig.setText("Consultar listado de las 10 personas con préstamos vigentes cuyos plazos sean los más cortos en número de cuotas");
        jButtCons10Vig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtCons10VigActionPerformed(evt);
            }
        });

        jButtConsMayMenMont.setBackground(new java.awt.Color(73, 248, 121));
        jButtConsMayMenMont.setForeground(new java.awt.Color(0, 102, 0));
        jButtConsMayMenMont.setText("Consultar el préstamo de mayor y menor monto en la historia del banco");
        jButtConsMayMenMont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtConsMayMenMontActionPerformed(evt);
            }
        });

        jButtPromM2.setBackground(new java.awt.Color(0, 204, 102));
        jButtPromM2.setForeground(new java.awt.Color(0, 102, 0));
        jButtPromM2.setText("Consultar el promedio medido en m2 cubiertos de casas y departamentos hipotecados");
        jButtPromM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtPromM2ActionPerformed(evt);
            }
        });

        jButtHipActiva.setBackground(new java.awt.Color(73, 248, 121));
        jButtHipActiva.setForeground(new java.awt.Color(0, 102, 0));
        jButtHipActiva.setText("Consultar listado de personas con hipotecas activas con todos sus datos");
        jButtHipActiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtHipActivaActionPerformed(evt);
            }
        });

        jButtMasHip.setBackground(new java.awt.Color(73, 248, 121));
        jButtMasHip.setForeground(new java.awt.Color(0, 102, 0));
        jButtMasHip.setText("Consultar cuál es el tipo de propiedad más hipotecada");
        jButtMasHip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtMasHipActionPerformed(evt);
            }
        });

        jButtCons10NoVig.setBackground(new java.awt.Color(73, 248, 121));
        jButtCons10NoVig.setForeground(new java.awt.Color(0, 102, 0));
        jButtCons10NoVig.setText("Consultar listado de las 10 personas con préstamos no vigentes cuyos plazos sean los más largos en número de cuotas");
        jButtCons10NoVig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtCons10NoVigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout otrasConsultasLayout = new javax.swing.GroupLayout(otrasConsultas);
        otrasConsultas.setLayout(otrasConsultasLayout);
        otrasConsultasLayout.setHorizontalGroup(
            otrasConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, otrasConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(otrasConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtMasHip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtCons10NoVig, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                    .addComponent(jButtCons10Vig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtConsMayMenMont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtPromM2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtHipActiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
            .addGroup(otrasConsultasLayout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(labConsDisp)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        otrasConsultasLayout.setVerticalGroup(
            otrasConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(otrasConsultasLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labConsDisp)
                .addGap(26, 26, 26)
                .addComponent(jButtMasHip, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtCons10NoVig, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtCons10Vig, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtConsMayMenMont, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtPromM2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtHipActiva, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        contenedor.add(otrasConsultas, "card6");

        altaProp.setBackground(new java.awt.Color(204, 255, 204));

        opCasDep.setBackground(new java.awt.Color(204, 255, 204));
        opCasDep.setLayout(new java.awt.CardLayout());

        panelVacio.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout panelVacioLayout = new javax.swing.GroupLayout(panelVacio);
        panelVacio.setLayout(panelVacioLayout);
        panelVacioLayout.setHorizontalGroup(
            panelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 793, Short.MAX_VALUE)
        );
        panelVacioLayout.setVerticalGroup(
            panelVacioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        opCasDep.add(panelVacio, "card4");

        panelCasa.setBackground(new java.awt.Color(204, 255, 204));

        antig.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        antig.setForeground(new java.awt.Color(51, 51, 51));
        antig.setText("Antiguedad de construcción:");

        antiguedad.setBackground(new java.awt.Color(255, 255, 255));
        antiguedad.setForeground(new java.awt.Color(51, 51, 51));
        antiguedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                antiguedadActionPerformed(evt);
            }
        });
        antiguedad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                antiguedadKeyTyped(evt);
            }
        });

        meses.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        meses.setForeground(new java.awt.Color(51, 51, 51));
        meses.setText("meses");

        javax.swing.GroupLayout panelCasaLayout = new javax.swing.GroupLayout(panelCasa);
        panelCasa.setLayout(panelCasaLayout);
        panelCasaLayout.setHorizontalGroup(
            panelCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCasaLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(antig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(antiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meses)
                .addContainerGap(341, Short.MAX_VALUE))
        );
        panelCasaLayout.setVerticalGroup(
            panelCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCasaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(antig)
                    .addGroup(panelCasaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(antiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(meses)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        opCasDep.add(panelCasa, "card2");

        panelDepto.setBackground(new java.awt.Color(204, 255, 204));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Piso:");

        piso.setBackground(new java.awt.Color(255, 255, 255));
        piso.setForeground(new java.awt.Color(51, 51, 51));
        piso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pisoKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Letra/Número:");

        letraNro.setBackground(new java.awt.Color(255, 255, 255));
        letraNro.setForeground(new java.awt.Color(51, 51, 51));
        letraNro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                letraNroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelDeptoLayout = new javax.swing.GroupLayout(panelDepto);
        panelDepto.setLayout(panelDeptoLayout);
        panelDeptoLayout.setHorizontalGroup(
            panelDeptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeptoLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(piso, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(letraNro, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        panelDeptoLayout.setVerticalGroup(
            panelDeptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeptoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDeptoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(piso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(letraNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        opCasDep.add(panelDepto, "card3");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setText("Ingrese los datos de la propiedad");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Número catastral:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Dirección:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("m2 totales:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("m2 cubiertos:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Valor de tasación:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Cantidad de ambientes:");

        nroCatastral.setBackground(new java.awt.Color(255, 255, 255));
        nroCatastral.setForeground(new java.awt.Color(51, 51, 51));
        nroCatastral.setText("Ingrese el número de catastro que aparece en la escritura...");
        nroCatastral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nroCatastralMouseClicked(evt);
            }
        });
        nroCatastral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nroCatastralActionPerformed(evt);
            }
        });
        nroCatastral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nroCatastralKeyTyped(evt);
            }
        });

        direccion.setBackground(new java.awt.Color(255, 255, 255));
        direccion.setForeground(new java.awt.Color(51, 51, 51));
        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direccionKeyTyped(evt);
            }
        });

        m2totales.setBackground(new java.awt.Color(255, 255, 255));
        m2totales.setForeground(new java.awt.Color(51, 51, 51));
        m2totales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m2totalesKeyTyped(evt);
            }
        });

        tasacion.setBackground(new java.awt.Color(255, 255, 255));
        tasacion.setForeground(new java.awt.Color(51, 51, 51));
        tasacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tasacionKeyTyped(evt);
            }
        });

        m2cubiertos.setBackground(new java.awt.Color(255, 255, 255));
        m2cubiertos.setForeground(new java.awt.Color(51, 51, 51));
        m2cubiertos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                m2cubiertosKeyTyped(evt);
            }
        });

        cantAmbientes.setBackground(new java.awt.Color(255, 255, 255));
        cantAmbientes.setForeground(new java.awt.Color(51, 51, 51));
        cantAmbientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantAmbientesKeyTyped(evt);
            }
        });

        jButtConfirmarAlta.setBackground(new java.awt.Color(73, 248, 121));
        jButtConfirmarAlta.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jButtConfirmarAlta.setForeground(new java.awt.Color(0, 102, 0));
        jButtConfirmarAlta.setText("Confirmar datos");
        jButtConfirmarAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtConfirmarAltaActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Tipo de Propiedad");

        opCasDept.add(jRadioCasa);
        jRadioCasa.setForeground(new java.awt.Color(51, 51, 51));
        jRadioCasa.setText("Casa");
        jRadioCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioCasaActionPerformed(evt);
            }
        });

        opCasDept.add(jRadioDepto);
        jRadioDepto.setForeground(new java.awt.Color(51, 51, 51));
        jRadioDepto.setText("Departamento");
        jRadioDepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioDeptoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout altaPropLayout = new javax.swing.GroupLayout(altaProp);
        altaProp.setLayout(altaPropLayout);
        altaPropLayout.setHorizontalGroup(
            altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(opCasDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(altaPropLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(altaPropLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(32, 32, 32)
                        .addComponent(jRadioCasa)))
                .addGap(45, 45, 45)
                .addComponent(jRadioDepto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, altaPropLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtConfirmarAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(310, 310, 310))
            .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(altaPropLayout.createSequentialGroup()
                    .addGap(83, 83, 83)
                    .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(altaPropLayout.createSequentialGroup()
                            .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, altaPropLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(m2totales, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(altaPropLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tasacion)))
                            .addGap(18, 18, 18)
                            .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(altaPropLayout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cantAmbientes))
                                .addGroup(altaPropLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(m2cubiertos, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, altaPropLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(direccion))
                            .addGroup(altaPropLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(30, 30, 30)
                                .addComponent(nroCatastral, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(120, Short.MAX_VALUE)))
        );
        altaPropLayout.setVerticalGroup(
            altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(altaPropLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 199, Short.MAX_VALUE)
                .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jRadioCasa)
                    .addComponent(jRadioDepto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opCasDep, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtConfirmarAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(altaPropLayout.createSequentialGroup()
                    .addGap(77, 77, 77)
                    .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(nroCatastral, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(12, 12, 12)
                    .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(m2totales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(m2cubiertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(altaPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(tasacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(cantAmbientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(201, Short.MAX_VALUE)))
        );

        contenedor.add(altaProp, "card7");

        elimPers.setBackground(new java.awt.Color(204, 255, 204));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("DNI:");

        jLabel12.setBackground(new java.awt.Color(204, 255, 204));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Eliminar persona del sistema");

        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setForeground(new java.awt.Color(102, 102, 102));
        jTextField1.setText("Ingrese el DNI...");

        jButtBusDNI.setBackground(new java.awt.Color(73, 248, 121));
        jButtBusDNI.setForeground(new java.awt.Color(51, 51, 51));
        jButtBusDNI.setText("Buscar");
        jButtBusDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtBusDNIActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Datos personales:");

        nombre.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Nombre:");

        apellido.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Apellido:");

        telefono.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Teléfono:");

        email.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Email:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Cuil/Cuit:");

        cuil.setBackground(new java.awt.Color(255, 255, 255));

        ingMens.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Ingreso mensual:");

        jButtBusDNI1.setBackground(new java.awt.Color(73, 248, 121));
        jButtBusDNI1.setForeground(new java.awt.Color(51, 51, 51));
        jButtBusDNI1.setText("Eliminar persona");
        jButtBusDNI1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtBusDNI1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout elimPersLayout = new javax.swing.GroupLayout(elimPers);
        elimPers.setLayout(elimPersLayout);
        elimPersLayout.setHorizontalGroup(
            elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimPersLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(elimPersLayout.createSequentialGroup()
                        .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(elimPersLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(elimPersLayout.createSequentialGroup()
                                .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cuil, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(telefono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 50, 50)
                        .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(elimPersLayout.createSequentialGroup()
                                .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(apellido)
                                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(elimPersLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingMens, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(elimPersLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jButtBusDNI1))
                    .addGroup(elimPersLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtBusDNI)))
                .addGap(0, 48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, elimPersLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257))
        );
        elimPersLayout.setVerticalGroup(
            elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimPersLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtBusDNI))
                .addGap(29, 29, 29)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(elimPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(ingMens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(37, 37, 37)
                .addComponent(jButtBusDNI1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        contenedor.add(elimPers, "card8");

        consProp.setBackground(new java.awt.Color(204, 255, 204));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 102, 0));
        jLabel23.setText("Buscar propiedad");

        buscProp.setBackground(new java.awt.Color(255, 255, 255));
        buscProp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscPropActionPerformed(evt);
            }
        });
        buscProp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscPropKeyReleased(evt);
            }
        });

        jTableProp.setBackground(new java.awt.Color(153, 255, 153));
        jTableProp.setForeground(new java.awt.Color(51, 51, 51));
        jTableProp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableProp);

        javax.swing.GroupLayout consPropLayout = new javax.swing.GroupLayout(consProp);
        consProp.setLayout(consPropLayout);
        consPropLayout.setHorizontalGroup(
            consPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consPropLayout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(buscProp, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
            .addGroup(consPropLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(consPropLayout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        consPropLayout.setVerticalGroup(
            consPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consPropLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscProp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(consProp, "card9");

        modProp.setBackground(new java.awt.Color(204, 255, 204));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 102, 0));
        jLabel24.setText("Modificar datos de una propiedad");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Ingrese el número catastral:");

        jButtModBuscar.setBackground(new java.awt.Color(73, 248, 121));
        jButtModBuscar.setForeground(new java.awt.Color(51, 51, 51));
        jButtModBuscar.setText("Buscar");
        jButtModBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtModBuscarActionPerformed(evt);
            }
        });

        modNroCatast.setBackground(new java.awt.Color(255, 255, 255));
        modNroCatast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modNroCatastActionPerformed(evt);
            }
        });
        modNroCatast.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modNroCatastKeyTyped(evt);
            }
        });

        modproppan.setBackground(new java.awt.Color(204, 255, 204));
        modproppan.setLayout(new java.awt.CardLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 811, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        modproppan.add(jPanel3, "card8");

        modMuestraProp.setBackground(new java.awt.Color(204, 255, 204));

        opCasDept.add(radCasa);
        radCasa.setForeground(new java.awt.Color(51, 51, 51));
        radCasa.setText("Casa");
        radCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCasaActionPerformed(evt);
            }
        });

        opCasDept.add(radDepto);
        radDepto.setForeground(new java.awt.Color(51, 51, 51));
        radDepto.setText("Departamento");
        radDepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDeptoActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Tipo de propiedad:");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 102, 0));
        jLabel27.setText("Mostrando datos de la propiedad encontrada:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 51));
        jLabel28.setText("Dirección:");

        modDireccion.setBackground(new java.awt.Color(255, 255, 255));
        modDireccion.setForeground(new java.awt.Color(102, 102, 102));
        modDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modDireccionActionPerformed(evt);
            }
        });
        modDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modDireccionKeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("m2 totales:");

        modm2tot.setBackground(new java.awt.Color(255, 255, 255));
        modm2tot.setForeground(new java.awt.Color(102, 102, 102));
        modm2tot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modm2totKeyTyped(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("m2 cubiertos:");

        modm2cub.setBackground(new java.awt.Color(255, 255, 255));
        modm2cub.setForeground(new java.awt.Color(102, 102, 102));
        modm2cub.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modm2cubKeyTyped(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setText("Valor de tasación");

        modtasacion.setBackground(new java.awt.Color(255, 255, 255));
        modtasacion.setForeground(new java.awt.Color(102, 102, 102));
        modtasacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modtasacionKeyTyped(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setText("Cantidad de ambientes:");

        modAmb.setBackground(new java.awt.Color(255, 255, 255));
        modAmb.setForeground(new java.awt.Color(102, 102, 102));
        modAmb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modAmbKeyTyped(evt);
            }
        });

        modPropConfirm.setBackground(new java.awt.Color(73, 248, 121));
        modPropConfirm.setForeground(new java.awt.Color(51, 51, 51));
        modPropConfirm.setText("Confirmar cambios");
        modPropConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modPropConfirmActionPerformed(evt);
            }
        });

        contMod.setLayout(new java.awt.CardLayout());

        modCasaPan.setBackground(new java.awt.Color(204, 255, 204));

        modAntig.setBackground(new java.awt.Color(255, 255, 255));
        modAntig.setForeground(new java.awt.Color(102, 102, 102));
        modAntig.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modAntigKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setText("Antiguedad de construcción");

        javax.swing.GroupLayout modCasaPanLayout = new javax.swing.GroupLayout(modCasaPan);
        modCasaPan.setLayout(modCasaPanLayout);
        modCasaPanLayout.setHorizontalGroup(
            modCasaPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modCasaPanLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modAntig, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(386, Short.MAX_VALUE))
        );
        modCasaPanLayout.setVerticalGroup(
            modCasaPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modCasaPanLayout.createSequentialGroup()
                .addGroup(modCasaPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modAntig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        contMod.add(modCasaPan, "card2");

        modDeptoPan.setBackground(new java.awt.Color(204, 255, 204));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 51));
        jLabel34.setText("Piso");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Letra/Número");

        modLetNro.setBackground(new java.awt.Color(255, 255, 255));
        modLetNro.setForeground(new java.awt.Color(102, 102, 102));
        modLetNro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modLetNroKeyTyped(evt);
            }
        });

        modPiso.setBackground(new java.awt.Color(255, 255, 255));
        modPiso.setForeground(new java.awt.Color(102, 102, 102));
        modPiso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                modPisoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout modDeptoPanLayout = new javax.swing.GroupLayout(modDeptoPan);
        modDeptoPan.setLayout(modDeptoPanLayout);
        modDeptoPanLayout.setHorizontalGroup(
            modDeptoPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modDeptoPanLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modPiso, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modLetNro, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        modDeptoPanLayout.setVerticalGroup(
            modDeptoPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modDeptoPanLayout.createSequentialGroup()
                .addGroup(modDeptoPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modDeptoPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(modLetNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modDeptoPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(modPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        contMod.add(modDeptoPan, "card3");

        javax.swing.GroupLayout modMuestraPropLayout = new javax.swing.GroupLayout(modMuestraProp);
        modMuestraProp.setLayout(modMuestraPropLayout);
        modMuestraPropLayout.setHorizontalGroup(
            modMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modMuestraPropLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(modMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modtasacion))
                    .addGroup(modMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modm2tot))
                    .addGroup(modMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(modMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modDireccion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(modMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(31, 31, 31)
                        .addComponent(radCasa)
                        .addGap(32, 32, 32)
                        .addComponent(radDepto))
                    .addGroup(modMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(modm2cub, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modAmb)))
                .addGap(18, 18, 18))
            .addComponent(contMod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(modMuestraPropLayout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(modPropConfirm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        modMuestraPropLayout.setVerticalGroup(
            modMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modMuestraPropLayout.createSequentialGroup()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(modDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(radCasa)
                    .addComponent(radDepto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(modm2tot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(modm2cub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(modtasacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modAmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contMod, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modPropConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        modproppan.add(modMuestraProp, "card3");

        javax.swing.GroupLayout modPropLayout = new javax.swing.GroupLayout(modProp);
        modProp.setLayout(modPropLayout);
        modPropLayout.setHorizontalGroup(
            modPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modPropLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(modPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addGroup(modPropLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modNroCatast, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButtModBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(modproppan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        modPropLayout.setVerticalGroup(
            modPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modPropLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(modNroCatast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtModBuscar))
                .addGap(18, 18, 18)
                .addComponent(modproppan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedor.add(modProp, "card10");

        elimProp.setBackground(new java.awt.Color(204, 255, 204));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 102, 0));
        jLabel36.setText("Eliminar una propiedad");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setText("Ingrese el número catastral:");

        jButtModBuscar1.setBackground(new java.awt.Color(73, 248, 121));
        jButtModBuscar1.setForeground(new java.awt.Color(51, 51, 51));
        jButtModBuscar1.setText("Buscar");
        jButtModBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtModBuscar1ActionPerformed(evt);
            }
        });

        elimNroCatast.setBackground(new java.awt.Color(255, 255, 255));

        elimPropPan.setBackground(new java.awt.Color(204, 255, 204));
        elimPropPan.setLayout(new java.awt.CardLayout());

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 793, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );

        elimPropPan.add(jPanel4, "card8");

        elimMuestraProp.setBackground(new java.awt.Color(204, 255, 204));

        opCasDept.add(radCasa1);
        radCasa1.setForeground(new java.awt.Color(51, 51, 51));
        radCasa1.setText("Casa");

        opCasDept.add(radDepto1);
        radDepto1.setForeground(new java.awt.Color(51, 51, 51));
        radDepto1.setText("Departamento");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 51));
        jLabel38.setText("Tipo de propiedad:");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 102, 0));
        jLabel39.setText("Mostrando datos de la propiedad a eliminar:");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 51, 51));
        jLabel40.setText("Dirección:");

        elimDireccion.setBackground(new java.awt.Color(255, 255, 255));
        elimDireccion.setForeground(new java.awt.Color(102, 102, 102));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setText("m2 totales:");

        elimM2Tot.setBackground(new java.awt.Color(255, 255, 255));
        elimM2Tot.setForeground(new java.awt.Color(102, 102, 102));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(51, 51, 51));
        jLabel42.setText("m2 cubiertos:");

        elimM2Cub.setBackground(new java.awt.Color(255, 255, 255));
        elimM2Cub.setForeground(new java.awt.Color(102, 102, 102));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(51, 51, 51));
        jLabel43.setText("Valor de tasación");

        elimTasa.setBackground(new java.awt.Color(255, 255, 255));
        elimTasa.setForeground(new java.awt.Color(102, 102, 102));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(51, 51, 51));
        jLabel44.setText("Cantidad de ambientes:");

        elimAmb.setBackground(new java.awt.Color(255, 255, 255));
        elimAmb.setForeground(new java.awt.Color(102, 102, 102));

        elimPropConfirm.setBackground(new java.awt.Color(73, 248, 121));
        elimPropConfirm.setForeground(new java.awt.Color(51, 51, 51));
        elimPropConfirm.setText("Eliminar Propiedad");
        elimPropConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elimPropConfirmActionPerformed(evt);
            }
        });

        contElim.setLayout(new java.awt.CardLayout());

        elimCasaPan.setBackground(new java.awt.Color(204, 255, 204));

        elimAntig.setBackground(new java.awt.Color(255, 255, 255));
        elimAntig.setForeground(new java.awt.Color(102, 102, 102));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 51, 51));
        jLabel45.setText("Antiguedad de construcción");

        javax.swing.GroupLayout elimCasaPanLayout = new javax.swing.GroupLayout(elimCasaPan);
        elimCasaPan.setLayout(elimCasaPanLayout);
        elimCasaPanLayout.setHorizontalGroup(
            elimCasaPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimCasaPanLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elimAntig, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(374, Short.MAX_VALUE))
        );
        elimCasaPanLayout.setVerticalGroup(
            elimCasaPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimCasaPanLayout.createSequentialGroup()
                .addGroup(elimCasaPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elimAntig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        contElim.add(elimCasaPan, "card2");

        elimDeptoPan.setBackground(new java.awt.Color(204, 255, 204));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(51, 51, 51));
        jLabel46.setText("Piso");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("Letra/Número");

        elimLetraNro.setBackground(new java.awt.Color(255, 255, 255));
        elimLetraNro.setForeground(new java.awt.Color(102, 102, 102));

        elimPiso.setBackground(new java.awt.Color(255, 255, 255));
        elimPiso.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout elimDeptoPanLayout = new javax.swing.GroupLayout(elimDeptoPan);
        elimDeptoPan.setLayout(elimDeptoPanLayout);
        elimDeptoPanLayout.setHorizontalGroup(
            elimDeptoPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimDeptoPanLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elimPiso, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elimLetraNro, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        elimDeptoPanLayout.setVerticalGroup(
            elimDeptoPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimDeptoPanLayout.createSequentialGroup()
                .addGroup(elimDeptoPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elimPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elimLetraNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        contElim.add(elimDeptoPan, "card3");

        javax.swing.GroupLayout elimMuestraPropLayout = new javax.swing.GroupLayout(elimMuestraProp);
        elimMuestraProp.setLayout(elimMuestraPropLayout);
        elimMuestraPropLayout.setHorizontalGroup(
            elimMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, elimMuestraPropLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(elimMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(elimMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(elimTasa))
                    .addGroup(elimMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elimM2Tot))
                    .addGroup(elimMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(elimMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elimDireccion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(elimMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(elimMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(31, 31, 31)
                        .addComponent(radCasa1)
                        .addGap(32, 32, 32)
                        .addComponent(radDepto1))
                    .addGroup(elimMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addGap(18, 18, 18)
                        .addComponent(elimM2Cub, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(elimMuestraPropLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elimAmb)))
                .addGap(18, 18, 18))
            .addComponent(contElim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(elimMuestraPropLayout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(elimPropConfirm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        elimMuestraPropLayout.setVerticalGroup(
            elimMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimMuestraPropLayout.createSequentialGroup()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(elimMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(elimDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(radCasa1)
                    .addComponent(radDepto1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(elimMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(elimM2Tot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(elimM2Cub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(elimMuestraPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(elimTasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elimAmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contElim, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elimPropConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        elimPropPan.add(elimMuestraProp, "card3");

        javax.swing.GroupLayout elimPropLayout = new javax.swing.GroupLayout(elimProp);
        elimProp.setLayout(elimPropLayout);
        elimPropLayout.setHorizontalGroup(
            elimPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimPropLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(elimPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addGroup(elimPropLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(elimNroCatast, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButtModBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(elimPropPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        elimPropLayout.setVerticalGroup(
            elimPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimPropLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(elimPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(elimNroCatast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtModBuscar1))
                .addGap(18, 18, 18)
                .addComponent(elimPropPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedor.add(elimProp, "card10");

        aggPers.setBackground(new java.awt.Color(204, 255, 204));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(51, 51, 51));
        jLabel48.setText("DNI:");

        jLabel13.setBackground(new java.awt.Color(204, 255, 204));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Ingresar nueva persona al sistema");

        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setForeground(new java.awt.Color(102, 102, 102));
        jTextField2.setText("Ingrese el DNI...");
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(102, 102, 102));
        jLabel49.setText("Datos personales:");

        nombre1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(51, 51, 51));
        jLabel50.setText("Nombre:");

        apellido1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(51, 51, 51));
        jLabel51.setText("Apellido:");

        telefono1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(51, 51, 51));
        jLabel52.setText("Teléfono:");

        email1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(51, 51, 51));
        jLabel53.setText("Email:");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(51, 51, 51));
        jLabel54.setText("Cuil/Cuit:");

        cuil1.setBackground(new java.awt.Color(255, 255, 255));

        ingMens1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(51, 51, 51));
        jLabel55.setText("Ingreso mensual:");

        jButtAggPers.setBackground(new java.awt.Color(73, 248, 121));
        jButtAggPers.setForeground(new java.awt.Color(51, 51, 51));
        jButtAggPers.setText("Agregar persona");
        jButtAggPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtAggPersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout aggPersLayout = new javax.swing.GroupLayout(aggPers);
        aggPers.setLayout(aggPersLayout);
        aggPersLayout.setHorizontalGroup(
            aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aggPersLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addGroup(aggPersLayout.createSequentialGroup()
                        .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(aggPersLayout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(aggPersLayout.createSequentialGroup()
                                .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel54))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cuil1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(telefono1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 50, 50)
                        .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(aggPersLayout.createSequentialGroup()
                                .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addComponent(jLabel53))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(apellido1)
                                    .addComponent(email1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(aggPersLayout.createSequentialGroup()
                                .addComponent(jLabel55)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingMens1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(aggPersLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jButtAggPers))
                    .addGroup(aggPersLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        aggPersLayout.setVerticalGroup(
            aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aggPersLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel49)
                .addGap(18, 18, 18)
                .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(telefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(email1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aggPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(ingMens1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54))
                .addGap(37, 37, 37)
                .addComponent(jButtAggPers, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        contenedor.add(aggPers, "card12");

        consultarPers.setBackground(new java.awt.Color(204, 255, 204));

        jLabel57.setBackground(new java.awt.Color(204, 255, 204));
        jLabel57.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(102, 102, 102));
        jLabel57.setText("Consultar personas del sistema");

        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setForeground(new java.awt.Color(102, 102, 102));

        jButtBusDNI2.setBackground(new java.awt.Color(73, 248, 121));
        jButtBusDNI2.setForeground(new java.awt.Color(51, 51, 51));
        jButtBusDNI2.setText("Buscar");
        jButtBusDNI2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtBusDNI2ActionPerformed(evt);
            }
        });

        jTable1.setBackground(new java.awt.Color(73, 248, 121));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "DNI", "Teléfono", "email", "cuil/cuit", "Ingreso Mensual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout consultarPersLayout = new javax.swing.GroupLayout(consultarPers);
        consultarPers.setLayout(consultarPersLayout);
        consultarPersLayout.setHorizontalGroup(
            consultarPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultarPersLayout.createSequentialGroup()
                .addGroup(consultarPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultarPersLayout.createSequentialGroup()
                        .addGroup(consultarPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(consultarPersLayout.createSequentialGroup()
                                .addGap(211, 211, 211)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtBusDNI2))
                            .addGroup(consultarPersLayout.createSequentialGroup()
                                .addGap(258, 258, 258)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 203, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        consultarPersLayout.setVerticalGroup(
            consultarPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultarPersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(consultarPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtBusDNI2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        contenedor.add(consultarPers, "card8");

        modPers.setBackground(new java.awt.Color(204, 255, 204));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(51, 51, 51));
        jLabel56.setText("DNI:");

        jLabel58.setBackground(new java.awt.Color(204, 255, 204));
        jLabel58.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(102, 102, 102));
        jLabel58.setText("Modificar persona del sistema");

        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setForeground(new java.awt.Color(102, 102, 102));
        jTextField4.setText("Ingrese el DNI...");
        jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField4MouseClicked(evt);
            }
        });

        jButtBusDNI3.setBackground(new java.awt.Color(73, 248, 121));
        jButtBusDNI3.setForeground(new java.awt.Color(51, 51, 51));
        jButtBusDNI3.setText("Buscar");
        jButtBusDNI3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtBusDNI3ActionPerformed(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(102, 102, 102));
        jLabel59.setText("Datos personales:");

        nombre2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(51, 51, 51));
        jLabel60.setText("Nombre:");

        apellido2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(51, 51, 51));
        jLabel61.setText("Apellido:");

        telefono2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(51, 51, 51));
        jLabel62.setText("Teléfono:");

        email2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(51, 51, 51));
        jLabel63.setText("Email:");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(51, 51, 51));
        jLabel64.setText("Cuil/Cuit:");

        cuil2.setBackground(new java.awt.Color(255, 255, 255));

        ingMens2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(51, 51, 51));
        jLabel65.setText("Ingreso mensual:");

        jButtBusDNI4.setBackground(new java.awt.Color(73, 248, 121));
        jButtBusDNI4.setForeground(new java.awt.Color(51, 51, 51));
        jButtBusDNI4.setText("Modificar persona");
        jButtBusDNI4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtBusDNI4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modPersLayout = new javax.swing.GroupLayout(modPers);
        modPers.setLayout(modPersLayout);
        modPersLayout.setHorizontalGroup(
            modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modPersLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59)
                    .addGroup(modPersLayout.createSequentialGroup()
                        .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modPersLayout.createSequentialGroup()
                                .addComponent(jLabel60)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modPersLayout.createSequentialGroup()
                                .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel62)
                                    .addComponent(jLabel64))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cuil2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(telefono2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(50, 50, 50)
                        .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(modPersLayout.createSequentialGroup()
                                .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel63))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(apellido2)
                                    .addComponent(email2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(modPersLayout.createSequentialGroup()
                                .addComponent(jLabel65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ingMens2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(modPersLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jButtBusDNI4))
                    .addGroup(modPersLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtBusDNI3)))
                .addGap(0, 48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modPersLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(257, 257, 257))
        );
        modPersLayout.setVerticalGroup(
            modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modPersLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtBusDNI3))
                .addGap(29, 29, 29)
                .addComponent(jLabel59)
                .addGap(18, 18, 18)
                .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(telefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63)
                    .addComponent(email2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modPersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(ingMens2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuil2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64))
                .addGap(37, 37, 37)
                .addComponent(jButtBusDNI4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        contenedor.add(modPers, "card8");

        simPrest.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 0));
        jLabel2.setText("Simulador de préstamo");

        PestañasSimulador.setBackground(new java.awt.Color(73, 248, 121));
        PestañasSimulador.setForeground(new java.awt.Color(51, 51, 51));

        pestTitular.setBackground(new java.awt.Color(153, 255, 153));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 51, 51));
        jLabel66.setText("Datos personales del titular");

        aggPers1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(51, 51, 51));
        jLabel67.setText("DNI:");

        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setForeground(new java.awt.Color(102, 102, 102));

        nombre3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(51, 51, 51));
        jLabel70.setText("Nombre:");

        apellido3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(51, 51, 51));
        jLabel71.setText("Apellido:");

        telefono3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(51, 51, 51));
        jLabel72.setText("Teléfono:");

        email3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(51, 51, 51));
        jLabel73.setText("Email:");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(51, 51, 51));
        jLabel74.setText("Cuil/Cuit:");

        cuil3.setBackground(new java.awt.Color(255, 255, 255));

        ingMens3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(51, 51, 51));
        jLabel75.setText("Ingreso mensual:");

        jButtConfTit.setBackground(new java.awt.Color(0, 102, 51));
        jButtConfTit.setForeground(new java.awt.Color(255, 255, 255));
        jButtConfTit.setText("Confirmar datos");
        jButtConfTit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtConfTitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout aggPers1Layout = new javax.swing.GroupLayout(aggPers1);
        aggPers1.setLayout(aggPers1Layout);
        aggPers1Layout.setHorizontalGroup(
            aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aggPers1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aggPers1Layout.createSequentialGroup()
                        .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(aggPers1Layout.createSequentialGroup()
                                .addComponent(jLabel70)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nombre3, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(aggPers1Layout.createSequentialGroup()
                                .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel72)
                                    .addComponent(jLabel74))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cuil3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(telefono3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel73))
                        .addGap(48, 48, 48)
                        .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(aggPers1Layout.createSequentialGroup()
                                    .addComponent(jLabel71)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(apellido3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(aggPers1Layout.createSequentialGroup()
                                    .addComponent(jLabel75)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ingMens3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(aggPers1Layout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(email3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, aggPers1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtConfTit)
                .addGap(326, 326, 326))
        );
        aggPers1Layout.setVerticalGroup(
            aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aggPers1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(apellido3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel72)
                        .addComponent(telefono3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(ingMens3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuil3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel74))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aggPers1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(email3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jButtConfTit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pestTitularLayout = new javax.swing.GroupLayout(pestTitular);
        pestTitular.setLayout(pestTitularLayout);
        pestTitularLayout.setHorizontalGroup(
            pestTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestTitularLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel66)
                .addContainerGap(567, Short.MAX_VALUE))
            .addGroup(pestTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pestTitularLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(aggPers1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pestTitularLayout.setVerticalGroup(
            pestTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestTitularLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel66)
                .addContainerGap(335, Short.MAX_VALUE))
            .addGroup(pestTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pestTitularLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(aggPers1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        PestañasSimulador.addTab("Titular", pestTitular);

        pestProp.setBackground(new java.awt.Color(153, 255, 153));

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(51, 51, 51));
        jLabel78.setText("Ingrese el número catastral:");

        elimNroCatast1.setBackground(new java.awt.Color(255, 255, 255));

        jButtModBuscar2.setBackground(new java.awt.Color(73, 248, 121));
        jButtModBuscar2.setForeground(new java.awt.Color(51, 51, 51));
        jButtModBuscar2.setText("Buscar");
        jButtModBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtModBuscar2ActionPerformed(evt);
            }
        });

        opCasDept.add(radCasa2);
        radCasa2.setForeground(new java.awt.Color(51, 51, 51));
        radCasa2.setText("Casa");
        radCasa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCasa2ActionPerformed(evt);
            }
        });

        opCasDept.add(radDepto2);
        radDepto2.setForeground(new java.awt.Color(51, 51, 51));
        radDepto2.setText("Departamento");
        radDepto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDepto2ActionPerformed(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(51, 51, 51));
        jLabel81.setText("Tipo de propiedad:");

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(0, 102, 0));
        jLabel82.setText("Mostrando datos de la propiedad encontrada:");

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(51, 51, 51));
        jLabel83.setText("Dirección:");

        modDireccion1.setBackground(new java.awt.Color(255, 255, 255));
        modDireccion1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(51, 51, 51));
        jLabel84.setText("m2 totales:");

        modm2tot1.setBackground(new java.awt.Color(255, 255, 255));
        modm2tot1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(51, 51, 51));
        jLabel85.setText("m2 cubiertos:");

        modm2cub1.setBackground(new java.awt.Color(255, 255, 255));
        modm2cub1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(51, 51, 51));
        jLabel86.setText("Valor de tasación");

        modtasacion1.setBackground(new java.awt.Color(255, 255, 255));
        modtasacion1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(51, 51, 51));
        jLabel87.setText("Cantidad de ambientes:");

        modAmb1.setBackground(new java.awt.Color(255, 255, 255));
        modAmb1.setForeground(new java.awt.Color(102, 102, 102));

        modPropConfirm1.setBackground(new java.awt.Color(73, 248, 121));
        modPropConfirm1.setForeground(new java.awt.Color(51, 51, 51));
        modPropConfirm1.setText("Confirmar cambios");
        modPropConfirm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modPropConfirm1ActionPerformed(evt);
            }
        });

        panPropCamb.setLayout(new java.awt.CardLayout());

        elimDeptoPan1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(51, 51, 51));
        jLabel88.setText("Piso");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(51, 51, 51));
        jLabel89.setText("Letra/Número");

        elimLetraNro1.setBackground(new java.awt.Color(255, 255, 255));
        elimLetraNro1.setForeground(new java.awt.Color(102, 102, 102));

        elimPiso1.setBackground(new java.awt.Color(255, 255, 255));
        elimPiso1.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout elimDeptoPan1Layout = new javax.swing.GroupLayout(elimDeptoPan1);
        elimDeptoPan1.setLayout(elimDeptoPan1Layout);
        elimDeptoPan1Layout.setHorizontalGroup(
            elimDeptoPan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimDeptoPan1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elimPiso1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elimLetraNro1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        elimDeptoPan1Layout.setVerticalGroup(
            elimDeptoPan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimDeptoPan1Layout.createSequentialGroup()
                .addGroup(elimDeptoPan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elimPiso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elimLetraNro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        panPropCamb.add(elimDeptoPan1, "card3");

        elimCasaPan1.setBackground(new java.awt.Color(153, 255, 153));

        elimAntig1.setBackground(new java.awt.Color(255, 255, 255));
        elimAntig1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(51, 51, 51));
        jLabel90.setText("Antiguedad de construcción");

        javax.swing.GroupLayout elimCasaPan1Layout = new javax.swing.GroupLayout(elimCasaPan1);
        elimCasaPan1.setLayout(elimCasaPan1Layout);
        elimCasaPan1Layout.setHorizontalGroup(
            elimCasaPan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimCasaPan1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elimAntig1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(374, Short.MAX_VALUE))
        );
        elimCasaPan1Layout.setVerticalGroup(
            elimCasaPan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(elimCasaPan1Layout.createSequentialGroup()
                .addGroup(elimCasaPan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elimAntig1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        panPropCamb.add(elimCasaPan1, "card2");

        javax.swing.GroupLayout pestPropLayout = new javax.swing.GroupLayout(pestProp);
        pestProp.setLayout(pestPropLayout);
        pestPropLayout.setHorizontalGroup(
            pestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestPropLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestPropLayout.createSequentialGroup()
                        .addGroup(pestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pestPropLayout.createSequentialGroup()
                                .addComponent(jLabel86)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(modtasacion1))
                            .addGroup(pestPropLayout.createSequentialGroup()
                                .addComponent(jLabel84)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modm2tot1))
                            .addGroup(pestPropLayout.createSequentialGroup()
                                .addComponent(jLabel82)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pestPropLayout.createSequentialGroup()
                                .addComponent(jLabel83)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modDireccion1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pestPropLayout.createSequentialGroup()
                                .addComponent(jLabel81)
                                .addGap(31, 31, 31)
                                .addComponent(radCasa2)
                                .addGap(32, 32, 32)
                                .addComponent(radDepto2))
                            .addGroup(pestPropLayout.createSequentialGroup()
                                .addComponent(jLabel85)
                                .addGap(18, 18, 18)
                                .addComponent(modm2cub1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pestPropLayout.createSequentialGroup()
                                .addComponent(jLabel87)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modAmb1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestPropLayout.createSequentialGroup()
                        .addComponent(modPropConfirm1)
                        .addGap(330, 330, 330))))
            .addGroup(pestPropLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(elimNroCatast1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtModBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panPropCamb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pestPropLayout.setVerticalGroup(
            pestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestPropLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(pestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(elimNroCatast1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtModBuscar2))
                .addGap(42, 42, 42)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(modDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81)
                    .addComponent(radCasa2)
                    .addComponent(radDepto2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(modm2tot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85)
                    .addComponent(modm2cub1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pestPropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(modtasacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modAmb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panPropCamb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(modPropConfirm1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        PestañasSimulador.addTab("Propiedad", pestProp);

        pestGarante.setBackground(new java.awt.Color(153, 255, 153));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 153, 0));
        jLabel69.setText("No disponible");

        javax.swing.GroupLayout pestGaranteLayout = new javax.swing.GroupLayout(pestGarante);
        pestGarante.setLayout(pestGaranteLayout);
        pestGaranteLayout.setHorizontalGroup(
            pestGaranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestGaranteLayout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(jLabel69)
                .addContainerGap(349, Short.MAX_VALUE))
        );
        pestGaranteLayout.setVerticalGroup(
            pestGaranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestGaranteLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel69)
                .addContainerGap(193, Short.MAX_VALUE))
        );

        PestañasSimulador.addTab("Garante", pestGarante);

        pestHipoteca.setBackground(new java.awt.Color(153, 255, 153));

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(51, 51, 51));
        jLabel76.setText("Capital:");

        nombre4.setBackground(new java.awt.Color(255, 255, 255));
        nombre4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre4ActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(51, 51, 51));
        jLabel77.setText("Interés mensual:");

        nombre5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(51, 51, 51));
        jLabel79.setText("Fecha de inicio:");

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(51, 51, 51));
        jLabel80.setText("Fecha estimada de fin:");

        jDateChooser2.setBackground(new java.awt.Color(204, 255, 204));

        jDateChooser3.setBackground(new java.awt.Color(204, 255, 204));

        jButtConfTit1.setBackground(new java.awt.Color(0, 102, 51));
        jButtConfTit1.setForeground(new java.awt.Color(255, 255, 255));
        jButtConfTit1.setText("Calcular cuota mensual");
        jButtConfTit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtConfTit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pestHipotecaLayout = new javax.swing.GroupLayout(pestHipoteca);
        pestHipoteca.setLayout(pestHipotecaLayout);
        pestHipotecaLayout.setHorizontalGroup(
            pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pestHipotecaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pestHipotecaLayout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombre5))
                    .addGroup(pestHipotecaLayout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombre4, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pestHipotecaLayout.createSequentialGroup()
                        .addComponent(jLabel79)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pestHipotecaLayout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pestHipotecaLayout.createSequentialGroup()
                    .addGap(338, 338, 338)
                    .addComponent(jButtConfTit1)
                    .addContainerGap(301, Short.MAX_VALUE)))
        );
        pestHipotecaLayout.setVerticalGroup(
            pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pestHipotecaLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pestHipotecaLayout.createSequentialGroup()
                        .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel79)
                                .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel76)
                                    .addComponent(nombre4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pestHipotecaLayout.createSequentialGroup()
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80)
                            .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel77)
                                .addComponent(nombre5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pestHipotecaLayout.createSequentialGroup()
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(pestHipotecaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pestHipotecaLayout.createSequentialGroup()
                    .addGap(145, 145, 145)
                    .addComponent(jButtConfTit1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(145, Short.MAX_VALUE)))
        );

        PestañasSimulador.addTab("Hipoteca", pestHipoteca);

        javax.swing.GroupLayout simPrestLayout = new javax.swing.GroupLayout(simPrest);
        simPrest.setLayout(simPrestLayout);
        simPrestLayout.setHorizontalGroup(
            simPrestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PestañasSimulador)
            .addGroup(simPrestLayout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        simPrestLayout.setVerticalGroup(
            simPrestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(simPrestLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PestañasSimulador, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        contenedor.add(simPrest, "card15");

        consulta.setBackground(new java.awt.Color(204, 255, 204));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 51, 0));
        jLabel68.setText("El promedio medido en m2 cubiertos de casas y departamentos hipotecados es:");

        labCasa.setFont(new java.awt.Font("Segoe UI", 3, 100)); // NOI18N
        labCasa.setForeground(new java.awt.Color(51, 51, 51));
        labCasa.setText("00");

        labDepto.setFont(new java.awt.Font("Segoe UI", 3, 100)); // NOI18N
        labDepto.setForeground(new java.awt.Color(51, 51, 51));
        labDepto.setText("00");

        labDepto1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        labDepto1.setForeground(new java.awt.Color(51, 51, 51));
        labDepto1.setText("m2 cubiertos");

        labDepto2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        labDepto2.setForeground(new java.awt.Color(51, 51, 51));
        labDepto2.setText("m2 cubiertos");

        labDepto3.setFont(new java.awt.Font("Segoe UI", 3, 30)); // NOI18N
        labDepto3.setForeground(new java.awt.Color(51, 51, 51));
        labDepto3.setText("en Departamentos");

        labDepto4.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        labDepto4.setForeground(new java.awt.Color(51, 51, 51));
        labDepto4.setText("en Casas");

        javax.swing.GroupLayout consultaLayout = new javax.swing.GroupLayout(consulta);
        consulta.setLayout(consultaLayout);
        consultaLayout.setHorizontalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel68)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaLayout.createSequentialGroup()
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labCasa)
                            .addComponent(labDepto1)))
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(labDepto4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaLayout.createSequentialGroup()
                        .addComponent(labDepto3)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, consultaLayout.createSequentialGroup()
                        .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labDepto)
                            .addComponent(labDepto2))
                        .addGap(140, 140, 140))))
        );
        consultaLayout.setVerticalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel68)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(labDepto1)
                        .addGap(18, 18, 18)
                        .addComponent(labCasa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labDepto4))
                    .addGroup(consultaLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(labDepto2)
                        .addGap(18, 18, 18)
                        .addComponent(labDepto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labDepto3)))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        contenedor.add(consulta, "card16");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
            .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guYsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guYsalirActionPerformed
        // TODO add your handling code here:
            FileWriter fichero = null;
            PrintWriter pw = null;
            FileReader r = null;
            BufferedReader br = null;
            
            
                try {
                    fichero = new FileWriter(f);
                    pw = new PrintWriter(fichero);
                    
                    for (Propiedad Prop:propiedades){
                        pw.println(Prop);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                   // Nuevamente aprovechamos el finally para 
                   // asegurarnos que se cierra el fichero.
                   if (null != fichero)
                      try {
                          fichero.close();
                   } catch (IOException ex) {
                       Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                   }

                }
        System.exit(0);
    }//GEN-LAST:event_guYsalirActionPerformed

    private void jButtGestPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtGestPersActionPerformed
        contenedor.removeAll();
        contenedor.add(gestPers);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtGestPersActionPerformed

    private void jButtInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtInicioActionPerformed
        contenedor.removeAll();
        contenedor.add(inicio);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtInicioActionPerformed

    private void jButtGestPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtGestPrestActionPerformed
        contenedor.removeAll();
        contenedor.add(gestPrestHip);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtGestPrestActionPerformed

    private void jButtGestPropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtGestPropActionPerformed
        contenedor.removeAll();
        contenedor.add(gestProp);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtGestPropActionPerformed

    private void jButtConfirmarAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtConfirmarAltaActionPerformed
        // ESTE ES EL ALTA DE PROPIEDADES
        
        String s = "";
        if (jRadioCasa.isSelected())    {
            if (s.equals(nroCatastral.getText()) || s.equals(direccion.getText()) || s.equals(tasacion.getText()) || s.equals(cantAmbientes.getText()) || s.equals(m2cubiertos.getText()) || s.equals(m2totales.getText()) || s.equals(antiguedad.getText()))
                casillasVacias.setVisible(true);
        } else if (jRadioDepto.isSelected())    {
            if (s.equals(nroCatastral.getText()) || s.equals(direccion.getText()) || s.equals(tasacion.getText()) || s.equals(cantAmbientes.getText()) || s.equals(m2cubiertos.getText()) || s.equals(m2totales.getText()) || s.equals(piso.getText()) || s.equals(letraNro.getText()))
                casillasVacias.setVisible(true);
        } else  {
            casillasVacias.setVisible(true);
        }
        
        if(jRadioCasa.isSelected()) {
            this.agregarCasa(Integer.parseInt(nroCatastral.getText()), direccion.getText(), Double.parseDouble(tasacion.getText()), Integer.parseInt(cantAmbientes.getText()), Float.parseFloat(m2cubiertos.getText()) , Float.parseFloat(m2totales.getText()),Integer.parseInt(antiguedad.getText()));
        } else if(jRadioDepto.isSelected()) {
            this.agregarDepto(Integer.parseInt(nroCatastral.getText()), direccion.getText(), Double.parseDouble(tasacion.getText()), Integer.parseInt(cantAmbientes.getText()), Float.parseFloat(m2cubiertos.getText()) , Float.parseFloat(m2totales.getText()), Integer.parseInt(piso.getText()), letraNro.getText());
        }
        /*
        nroCatastral.setText("");
        direccion.setText("");
        tasacion.setText("");
        cantAmbientes.setText("");
        m2cubiertos.setText("");
        m2totales.setText("");
        piso.setText("");
        letraNro.setText("");
        antiguedad.setText("");
        radCasa.setSelected(false);
        radDepto.setSelected(false);
        */
    }//GEN-LAST:event_jButtConfirmarAltaActionPerformed

    private void jButtCargPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtCargPersActionPerformed
        contenedor.removeAll();
        contenedor.add(aggPers);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtCargPersActionPerformed

    private void jButtCargPropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtCargPropActionPerformed
        contenedor.removeAll();
        contenedor.add(altaProp);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtCargPropActionPerformed

    private void jButtHipActivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtHipActivaActionPerformed
        // TODO add your handling code here:
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtHipActivaActionPerformed

    private void jButtMasHipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtMasHipActionPerformed
        // TODO add your handling code here:
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtMasHipActionPerformed

    private void jButtOtrasConsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtOtrasConsActionPerformed
        // TODO add your handling code here:
        contenedor.removeAll();
        contenedor.add(otrasConsultas);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtOtrasConsActionPerformed

    private void jButtCons10NoVigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtCons10NoVigActionPerformed
        // TODO add your handling code here:
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtCons10NoVigActionPerformed

    private void jButtCons10VigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtCons10VigActionPerformed
        // TODO add your handling code here:
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtCons10VigActionPerformed

    private void jButtConsMayMenMontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtConsMayMenMontActionPerformed
        // TODO add your handling code here:
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtConsMayMenMontActionPerformed

    private void jButtPromM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtPromM2ActionPerformed
        // HACERLO 
        // vbles son labCasa y labDepto
        contenedor.removeAll();
        contenedor.add(consulta);
        contenedor.repaint();
        contenedor.revalidate();
        
        labCasa.setText(String.valueOf(prom_casa_hipo()));
        labDepto.setText(String.valueOf(prom_depto_hipo()));
        
    }//GEN-LAST:event_jButtPromM2ActionPerformed

    private void jButtBusDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtBusDNIActionPerformed
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtBusDNIActionPerformed

    private void jRadioCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioCasaActionPerformed
        // TODO add your handling code here:
        if(jRadioCasa.isSelected()){
            opCasDep.removeAll();
            opCasDep.add(panelCasa);
            opCasDep.repaint();
            opCasDep.revalidate();
        }
    }//GEN-LAST:event_jRadioCasaActionPerformed

    private void jRadioDeptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioDeptoActionPerformed
        if(jRadioDepto.isSelected()){
            opCasDep.removeAll();
            opCasDep.add(panelDepto);
            opCasDep.repaint();
            opCasDep.revalidate();
        }
    }//GEN-LAST:event_jRadioDeptoActionPerformed

    private void jButtConsPropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtConsPropActionPerformed
        contenedor.removeAll();
        contenedor.add(consProp);
        contenedor.repaint();
        contenedor.revalidate();
        llenar();
    }//GEN-LAST:event_jButtConsPropActionPerformed

    private void jButtModBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtModBuscarActionPerformed
        // TODO add your handling code here:
        
        String s = "";
        if (s.equals(modNroCatast.getText()) || modNroCatast.getText() == null)
            casillasVacias.setVisible(true);
        
        Iterator<Propiedad> iterator = propiedades.iterator();
        boolean exist = false;
        /*
        if(!iterator.hasNext())     {
            propNoExiste.setVisible(true);
        }*/
        
        Casa auxCasa = new Casa();
        Departamento auxDepto = new Departamento();
        
        while (iterator.hasNext()) {
            Propiedad element = iterator.next();
            if (element.getNro_catastral() == Integer.parseInt(modNroCatast.getText()) && (element instanceof Casa)) {
                exist = true;
                auxCasa = (Casa)element;
                radCasa.setSelected(true);
                modDireccion.setText(auxCasa.getDireccion());
                modtasacion.setText(String.valueOf(auxCasa.getTasacion()));
                modAmb.setText(String.valueOf(auxCasa.getCant_ambientes()));
                modm2cub.setText(String.valueOf(auxCasa.getM2_cubiertos()));
                modm2tot.setText(String.valueOf(auxCasa.getM2_totales()));
                modAntig.setText(String.valueOf(auxCasa.getAntiguedad()));
                
                contMod.removeAll();
                contMod.add(modCasaPan);
                contMod.repaint();
                contMod.revalidate();
                
            } else if (element.getNro_catastral() == Integer.parseInt(modNroCatast.getText()) && (element instanceof Departamento)){
                exist = true;
                auxDepto = (Departamento)element;
                radDepto.setSelected(true);
                modDireccion.setText(auxDepto.getDireccion());
                modtasacion.setText(String.valueOf(auxDepto.getTasacion()));
                modAmb.setText(String.valueOf(auxDepto.getCant_ambientes()));
                modm2cub.setText(String.valueOf(auxDepto.getM2_cubiertos()));
                modm2tot.setText(String.valueOf(auxDepto.getM2_totales()));
                modPiso.setText(String.valueOf(auxDepto.getPiso()));
                modLetNro.setText(String.valueOf(auxDepto.getLetNro()));
                
                contMod.removeAll();
                contMod.add(modDeptoPan);
                contMod.repaint();
                contMod.revalidate();
                
            } 
        }
        
        if (!exist) {
            propNoExiste.setVisible(true);
            modproppan.removeAll();
            modproppan.add(jPanel3);
            modproppan.repaint();
            modproppan.revalidate();
            
            modNroCatast.setText("");
            
        } else  {
            modproppan.removeAll();
            modproppan.add(modMuestraProp);
            modproppan.repaint();
            modproppan.revalidate();
        }
        
            
        // buscar en la coleccion, mostrar, y dependiendo de lo que muestra en casa o depto es:
        /*
        if(radCasa.isSelected()){
            contMod.removeAll();
            contMod.add(modCasaPan);
            contMod.repaint();
            contMod.revalidate();
        } else {
            contMod.removeAll();
            contMod.add(modDeptoPan);
            contMod.repaint();
            contMod.revalidate();
        }*/
        
    }//GEN-LAST:event_jButtModBuscarActionPerformed

    private void modPropConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modPropConfirmActionPerformed
        // TODO add your handling code here:
        //Cuando confirmen tiene que tomar las variables con la nueva actualizacion y pasarlas por parametro
        // al metodo que hicimos para modificar la coleccion.
        /*if(radCasa.isSelected()){
            modificarCasa(Integer.parseInt(modNroCatast.getText()), modDireccion.getText(), Double.parseDouble(modtasacion.getText()), Integer.parseInt(modAmb.getText()), Float.parseFloat(modm2cub.getText()) , Float.parseFloat(modm2tot.getText()),Integer.parseInt(modAntig.getText()));
        } else if (radDepto.isSelected()){
            modificarDepto(Integer.parseInt(modNroCatast.getText()), modDireccion.getText(), Double.parseDouble(modtasacion.getText()), Integer.parseInt(modAmb.getText()), Float.parseFloat(modm2cub.getText()) , Float.parseFloat(modm2tot.getText()),Integer.parseInt(modPiso.getText()),modLetNro.getText());
        }*/
        
        
        modificarPropiedad(Integer.parseInt(modNroCatast.getText()), modDireccion.getText(), Double.parseDouble(modtasacion.getText()), Integer.parseInt(modAmb.getText()), Float.parseFloat(modm2cub.getText()) , Float.parseFloat(modm2tot.getText()),Integer.parseInt(modAntig.getText()),Integer.parseInt(modPiso.getText()),modLetNro.getText());
        
        modificado.setVisible(true);
        
        modNroCatast.setText("");
        modDireccion.setText("");
        modtasacion.setText("");
        modAmb.setText("");
        modm2cub.setText("");
        modm2tot.setText("");
        modPiso.setText("");
        modLetNro.setText("");
        modAntig.setText("");
        radCasa.setSelected(false);
        radDepto.setSelected(false);
        
    }//GEN-LAST:event_modPropConfirmActionPerformed

    private void jButtModBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtModBuscar1ActionPerformed
        // TODO add your handling code here:
        String s = "";
        
        if (s.equals(elimNroCatast.getText()))
            casillasVacias.setVisible(true);
        
        Iterator<Propiedad> iterator = propiedades.iterator();
        boolean exist = false;
        
        Casa auxCasa = new Casa();
        Departamento auxDepto = new Departamento();
        
        if(!iterator.hasNext()){
            propNoExiste.setVisible(true);
        }
        
        while (iterator.hasNext()) {
            Propiedad element = iterator.next();
            if (element.getNro_catastral() == Integer.parseInt(elimNroCatast.getText()) && (element instanceof Casa)) {
                exist = true;
                auxCasa = (Casa)element;
                radCasa1.setSelected(true);
                elimDireccion.setText(auxCasa.getDireccion());
                elimTasa.setText(String.valueOf(auxCasa.getTasacion()));
                elimAmb.setText(String.valueOf(auxCasa.getCant_ambientes()));
                elimM2Cub.setText(String.valueOf(auxCasa.getM2_cubiertos()));
                elimM2Tot.setText(String.valueOf(auxCasa.getM2_totales()));
                elimAntig.setText(String.valueOf(auxCasa.getAntiguedad()));
                
                contElim.removeAll();
                contElim.add(elimCasaPan);
                contElim.repaint();
                contElim.revalidate();
                
            } else if (element.getNro_catastral() == Integer.parseInt(elimNroCatast.getText()) && (element instanceof Departamento)){
                exist = true;
                auxDepto = (Departamento)element;
                radDepto1.setSelected(true);
                elimDireccion.setText(auxDepto.getDireccion());
                elimTasa.setText(String.valueOf(auxDepto.getTasacion()));
                elimAmb.setText(String.valueOf(auxDepto.getCant_ambientes()));
                elimM2Cub.setText(String.valueOf(auxDepto.getM2_cubiertos()));
                elimM2Tot.setText(String.valueOf(auxDepto.getM2_totales()));
                elimPiso.setText(String.valueOf(auxDepto.getPiso()));
                elimLetraNro.setText(String.valueOf(auxDepto.getLetNro()));
                
                contElim.removeAll();
                contElim.add(elimDeptoPan);
                contElim.repaint();
                contElim.revalidate();
                
            }
        }
        
        if (!exist) {
            propNoExiste.setVisible(true);
        }
        
            elimPropPan.removeAll();
            elimPropPan.add(elimMuestraProp);
            elimPropPan.repaint();
            elimPropPan.revalidate();
    }//GEN-LAST:event_jButtModBuscar1ActionPerformed

    private void elimPropConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elimPropConfirmActionPerformed
        // TODO add your handling code here:
        eliminarPropiedad(Integer.parseInt(elimNroCatast.getText()), false);
        elimNroCatast.setText("");
        elimDireccion.setText("");
        elimTasa.setText("");
        elimAmb.setText("");
        elimM2Cub.setText("");
        elimM2Tot.setText("");
        elimPiso.setText("");
        elimLetraNro.setText("");
        elimAntig.setText("");
        radCasa1.setSelected(false);
        radDepto1.setSelected(false);
    }//GEN-LAST:event_elimPropConfirmActionPerformed

    private void jButtElimPropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtElimPropActionPerformed
        // TODO add your handling code here:
        contenedor.removeAll();
        contenedor.add(elimProp);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtElimPropActionPerformed

    private void jButtModPropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtModPropActionPerformed
        contenedor.removeAll();
        contenedor.add(modProp);
        contenedor.repaint();
        contenedor.revalidate();
        
        modproppan.removeAll();
        modproppan.add(jPanel3);
        modproppan.repaint();
        modproppan.revalidate();
        
        modNroCatast.setText("");
        
    }//GEN-LAST:event_jButtModPropActionPerformed

    private void jButtBusDNI1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtBusDNI1ActionPerformed
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtBusDNI1ActionPerformed

    private void jButtElimPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtElimPersActionPerformed
        contenedor.removeAll();
        contenedor.add(elimPers);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtElimPersActionPerformed

    private void jButtAggPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtAggPersActionPerformed
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtAggPersActionPerformed

    private void jButtBusDNI2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtBusDNI2ActionPerformed
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtBusDNI2ActionPerformed

    private void jButtConsPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtConsPersActionPerformed
        contenedor.removeAll();
        contenedor.add(consultarPers);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtConsPersActionPerformed

    private void jButtModPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtModPersActionPerformed
        contenedor.removeAll();
        contenedor.add(modPers);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtModPersActionPerformed

    private void jButtBusDNI3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtBusDNI3ActionPerformed
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtBusDNI3ActionPerformed

    private void jButtBusDNI4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtBusDNI4ActionPerformed
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtBusDNI4ActionPerformed

    private void jButtConfTitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtConfTitActionPerformed
         construccion.setVisible(true);
    }//GEN-LAST:event_jButtConfTitActionPerformed

    private void jButtConfTit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtConfTit1ActionPerformed
         construccion.setVisible(true);
    }//GEN-LAST:event_jButtConfTit1ActionPerformed

    private void jButtModBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtModBuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtModBuscar2ActionPerformed

    private void nombre4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre4ActionPerformed

    private void modPropConfirm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modPropConfirm1ActionPerformed
         construccion.setVisible(true);
    }//GEN-LAST:event_modPropConfirm1ActionPerformed

    private void radCasa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCasa2ActionPerformed
        if(radCasa2.isSelected()){
            panPropCamb.removeAll();
            panPropCamb.add(elimCasaPan1);
            panPropCamb.repaint();
            panPropCamb.revalidate();
        }
    }//GEN-LAST:event_radCasa2ActionPerformed

    private void radDepto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDepto2ActionPerformed
        // TODO add your handling code here:
        if(radDepto2.isSelected()){
            panPropCamb.removeAll();
            panPropCamb.add(elimDeptoPan1);
            panPropCamb.repaint();
            panPropCamb.revalidate();
        }
    }//GEN-LAST:event_radDepto2ActionPerformed

    private void jButtSimPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtSimPrestActionPerformed
        contenedor.removeAll();
        contenedor.add(simPrest);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtSimPrestActionPerformed

    private void jButtSimPrestGestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtSimPrestGestActionPerformed
        contenedor.removeAll();
        contenedor.add(simPrest);
        contenedor.repaint();
        contenedor.revalidate();
    }//GEN-LAST:event_jButtSimPrestGestActionPerformed

    private void radCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCasaActionPerformed
        if(radCasa.isSelected()){
            contMod.removeAll();
            contMod.add(modCasaPan);
            contMod.repaint();
            contMod.revalidate();
        }
    }//GEN-LAST:event_radCasaActionPerformed

    private void radDeptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDeptoActionPerformed
        if(radDepto.isSelected()){
            contMod.removeAll();
            contMod.add(modDeptoPan);
            contMod.repaint();
            contMod.revalidate();
        }
    }//GEN-LAST:event_radDeptoActionPerformed

    private void nroCatastralMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nroCatastralMouseClicked
        // TODO add your handling code here:
        nroCatastral.setText("");
    }//GEN-LAST:event_nroCatastralMouseClicked

    private void jButtBuscPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtBuscPresActionPerformed
        // TODO add your handling code here:
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtBuscPresActionPerformed

    private void jButtElimPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtElimPrestActionPerformed
        // TODO add your handling code here:
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtElimPrestActionPerformed

    private void jButtModPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtModPrestActionPerformed
        // TODO add your handling code here:
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtModPrestActionPerformed

    private void jButtFinPrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtFinPrestActionPerformed
        // TODO add your handling code here:
        construccion.setVisible(true);
    }//GEN-LAST:event_jButtFinPrestActionPerformed

    private void nroCatastralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nroCatastralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nroCatastralActionPerformed

    private void jTextField4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField4MouseClicked
        // TODO add your handling code here:v
        jTextField4.setText("");
    }//GEN-LAST:event_jTextField4MouseClicked

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        // TODO add your handling code here:
        jTextField2.setText("");
    }//GEN-LAST:event_jTextField2MouseClicked

    private void CreateColumns(){
        dtm = (DefaultTableModel) jTableProp.getModel();
        dtm.addColumn("N°Cat");
        dtm.addColumn("Tipo");
        dtm.addColumn("Dirección");
        dtm.addColumn("m2 totales");
        dtm.addColumn("m2 cubiertos");
        dtm.addColumn("Valor");
    }
    
    private void llenar(){
        DefaultTableModel dtm = (DefaultTableModel) jTableProp.getModel();
        dtm.setRowCount(0);
        
        
        for(Propiedad prop: propiedades){
            System.out.println(prop);
            String[] rowData = {String.valueOf(prop.getNro_catastral()), prop.getDireccion(), String.valueOf(prop.getTasacion()), String.valueOf(prop.getCant_ambientes()), String.valueOf(prop.getM2_cubiertos()), String.valueOf(prop.getM2_totales())};
            dtm.addRow(rowData);
        }
    }
    
    private void filter(String query){
        TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(dtm);
        jTableProp.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(query));
    }
    
    private void buscPropKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscPropKeyReleased
        // TODO add your handling code here:
        String query=buscProp.getText();
        filter(query);
    }//GEN-LAST:event_buscPropKeyReleased

    
    private void buscPropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscPropActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscPropActionPerformed

    private void modNroCatastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modNroCatastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modNroCatastActionPerformed

    private void modDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modDireccionActionPerformed

    private void nroCatastralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nroCatastralKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_nroCatastralKeyTyped

    private void direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionKeyTyped

    private void m2totalesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m2totalesKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_m2totalesKeyTyped

    private void m2cubiertosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_m2cubiertosKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_m2cubiertosKeyTyped

    private void tasacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tasacionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_tasacionKeyTyped

    private void cantAmbientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantAmbientesKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_cantAmbientesKeyTyped

    private void pisoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pisoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_pisoKeyTyped

    private void letraNroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_letraNroKeyTyped
        // TODO add your handling code here:
        if (letraNro.getText().length() >= 4) evt.consume();
    }//GEN-LAST:event_letraNroKeyTyped

    private void antiguedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_antiguedadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_antiguedadActionPerformed

    private void antiguedadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_antiguedadKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_antiguedadKeyTyped

    private void modNroCatastKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modNroCatastKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_modNroCatastKeyTyped

    private void modDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modDireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_modDireccionKeyTyped

    private void modm2totKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modm2totKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_modm2totKeyTyped

    private void modm2cubKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modm2cubKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_modm2cubKeyTyped

    private void modtasacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modtasacionKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_modtasacionKeyTyped

    private void modAmbKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modAmbKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_modAmbKeyTyped

    private void modAntigKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modAntigKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_modAntigKeyTyped

    private void modLetNroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modLetNroKeyTyped
        // TODO add your handling code here:
        if (modLetNro.getText().length() >= 4) evt.consume();
    }//GEN-LAST:event_modLetNroKeyTyped

    private void modPisoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_modPisoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(Character.isLetter(c))    {
            evt.consume();
        }
    }//GEN-LAST:event_modPisoKeyTyped
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane PestañasSimulador;
    private javax.swing.JPanel aggPers;
    private javax.swing.JPanel aggPers1;
    private javax.swing.JPanel altaProp;
    private javax.swing.JLabel antig;
    private javax.swing.JTextField antiguedad;
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField apellido1;
    private javax.swing.JTextField apellido2;
    private javax.swing.JTextField apellido3;
    private javax.swing.JTextField buscProp;
    private javax.swing.JTextField cantAmbientes;
    private javax.swing.JPanel consProp;
    private javax.swing.JPanel consulta;
    private javax.swing.JPanel consultarPers;
    private javax.swing.JPanel contElim;
    private javax.swing.JPanel contMod;
    private javax.swing.JPanel contenedor;
    private javax.swing.JTextField cuil;
    private javax.swing.JTextField cuil1;
    private javax.swing.JTextField cuil2;
    private javax.swing.JTextField cuil3;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField elimAmb;
    private javax.swing.JTextField elimAntig;
    private javax.swing.JTextField elimAntig1;
    private javax.swing.JPanel elimCasaPan;
    private javax.swing.JPanel elimCasaPan1;
    private javax.swing.JPanel elimDeptoPan;
    private javax.swing.JPanel elimDeptoPan1;
    private javax.swing.JTextField elimDireccion;
    private javax.swing.JTextField elimLetraNro;
    private javax.swing.JTextField elimLetraNro1;
    private javax.swing.JTextField elimM2Cub;
    private javax.swing.JTextField elimM2Tot;
    private javax.swing.JPanel elimMuestraProp;
    private javax.swing.JTextField elimNroCatast;
    private javax.swing.JTextField elimNroCatast1;
    private javax.swing.JPanel elimPers;
    private javax.swing.JTextField elimPiso;
    private javax.swing.JTextField elimPiso1;
    private javax.swing.JPanel elimProp;
    private javax.swing.JButton elimPropConfirm;
    private javax.swing.JPanel elimPropPan;
    private javax.swing.JTextField elimTasa;
    private javax.swing.JTextField email;
    private javax.swing.JTextField email1;
    private javax.swing.JTextField email2;
    private javax.swing.JTextField email3;
    private javax.swing.JPanel gestPers;
    private javax.swing.JPanel gestPrestHip;
    private javax.swing.JPanel gestProp;
    private javax.swing.JButton guYsalir;
    private javax.swing.JPanel header;
    private javax.swing.JTextField ingMens;
    private javax.swing.JTextField ingMens1;
    private javax.swing.JTextField ingMens2;
    private javax.swing.JTextField ingMens3;
    private javax.swing.JPanel inicio;
    private javax.swing.JButton jButtAggPers;
    private javax.swing.JButton jButtBusDNI;
    private javax.swing.JButton jButtBusDNI1;
    private javax.swing.JButton jButtBusDNI2;
    private javax.swing.JButton jButtBusDNI3;
    private javax.swing.JButton jButtBusDNI4;
    private javax.swing.JButton jButtBuscPres;
    private javax.swing.JButton jButtCargPers;
    private javax.swing.JButton jButtCargProp;
    private javax.swing.JButton jButtConfTit;
    private javax.swing.JButton jButtConfTit1;
    private javax.swing.JButton jButtConfirmarAlta;
    private javax.swing.JButton jButtCons10NoVig;
    private javax.swing.JButton jButtCons10Vig;
    private javax.swing.JButton jButtConsMayMenMont;
    private javax.swing.JButton jButtConsPers;
    private javax.swing.JButton jButtConsProp;
    private javax.swing.JButton jButtElimPers;
    private javax.swing.JButton jButtElimPrest;
    private javax.swing.JButton jButtElimProp;
    private javax.swing.JButton jButtFinPrest;
    private javax.swing.JButton jButtGestPers;
    private javax.swing.JButton jButtGestPrest;
    private javax.swing.JButton jButtGestProp;
    private javax.swing.JButton jButtHipActiva;
    private javax.swing.JButton jButtInicio;
    private javax.swing.JButton jButtMasHip;
    private javax.swing.JButton jButtModBuscar;
    private javax.swing.JButton jButtModBuscar1;
    private javax.swing.JButton jButtModBuscar2;
    private javax.swing.JButton jButtModPers;
    private javax.swing.JButton jButtModPrest;
    private javax.swing.JButton jButtModProp;
    private javax.swing.JButton jButtOtrasCons;
    private javax.swing.JButton jButtPromM2;
    private javax.swing.JButton jButtSimPrest;
    private javax.swing.JButton jButtSimPrestGest;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioCasa;
    private javax.swing.JRadioButton jRadioDepto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableProp;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel labCasa;
    private javax.swing.JLabel labConsDisp;
    private javax.swing.JLabel labDepto;
    private javax.swing.JLabel labDepto1;
    private javax.swing.JLabel labDepto2;
    private javax.swing.JLabel labDepto3;
    private javax.swing.JLabel labDepto4;
    private javax.swing.JLabel labGestPers;
    private javax.swing.JLabel labGestPrestHip;
    private javax.swing.JLabel labGestProp;
    private javax.swing.JLabel labPandita;
    private javax.swing.JTextField letraNro;
    private javax.swing.JTextField m2cubiertos;
    private javax.swing.JTextField m2totales;
    private javax.swing.JLabel meses;
    private javax.swing.JTextField modAmb;
    private javax.swing.JTextField modAmb1;
    private javax.swing.JTextField modAntig;
    private javax.swing.JPanel modCasaPan;
    private javax.swing.JPanel modDeptoPan;
    private javax.swing.JTextField modDireccion;
    private javax.swing.JTextField modDireccion1;
    private javax.swing.JTextField modLetNro;
    private javax.swing.JPanel modMuestraProp;
    private javax.swing.JTextField modNroCatast;
    private javax.swing.JPanel modPers;
    private javax.swing.JTextField modPiso;
    private javax.swing.JPanel modProp;
    private javax.swing.JButton modPropConfirm;
    private javax.swing.JButton modPropConfirm1;
    private javax.swing.JTextField modm2cub;
    private javax.swing.JTextField modm2cub1;
    private javax.swing.JTextField modm2tot;
    private javax.swing.JTextField modm2tot1;
    private javax.swing.JPanel modproppan;
    private javax.swing.JTextField modtasacion;
    private javax.swing.JTextField modtasacion1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField nombre1;
    private javax.swing.JTextField nombre2;
    private javax.swing.JTextField nombre3;
    private javax.swing.JTextField nombre4;
    private javax.swing.JTextField nombre5;
    private javax.swing.JTextField nroCatastral;
    private javax.swing.JPanel opCasDep;
    private javax.swing.ButtonGroup opCasDept;
    private javax.swing.JPanel otrasConsultas;
    private javax.swing.JPanel panPropCamb;
    private javax.swing.JPanel panelCasa;
    private javax.swing.JPanel panelDepto;
    private javax.swing.JPanel panelVacio;
    private javax.swing.JPanel pestGarante;
    private javax.swing.JPanel pestHipoteca;
    private javax.swing.JPanel pestProp;
    private javax.swing.JPanel pestTitular;
    private javax.swing.JTextField piso;
    private javax.swing.JRadioButton radCasa;
    private javax.swing.JRadioButton radCasa1;
    private javax.swing.JRadioButton radCasa2;
    private javax.swing.JRadioButton radDepto;
    private javax.swing.JRadioButton radDepto1;
    private javax.swing.JRadioButton radDepto2;
    private javax.swing.JPanel simPrest;
    private javax.swing.JTextField tasacion;
    private javax.swing.JTextField telefono;
    private javax.swing.JTextField telefono1;
    private javax.swing.JTextField telefono2;
    private javax.swing.JTextField telefono3;
    private javax.swing.JLabel tituloPpal;
    // End of variables declaration//GEN-END:variables
}
