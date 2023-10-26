/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;
import java.util.ArrayList;
/**
 *
 * @author joaquinsgi
 */
public abstract class Jugador implements Comparable<Jugador>{

    private String nombre;
    protected static final int CasasMax = 4;
    protected static final int CasasporHotel = 4;
    private int casillaActual;
    protected static final int HotelesMax = 4;
    protected static final float PasoPorSalida = 1000;
    private boolean puedeComprar;
    private float saldo;
    private static final float Saldoinicial = 7500;
    ArrayList<CasillaCalle> propiedades; 
    private Diario diario = Diario.getInstance();
    
    Jugador(String _nombre){
        this.nombre = _nombre;
        this.casillaActual = 0;
        this.puedeComprar = false;
        this.saldo = Saldoinicial; //comprobar
        this.propiedades = new ArrayList<CasillaCalle>(); 
    }
    protected Jugador(Jugador otro){
        this.nombre = otro.getNombre();
        this.casillaActual = otro.getCasillaActual();
        this.puedeComprar = otro.getPuedeComprar();
        this.saldo = otro.getSaldo();
        this.propiedades = otro.getPropiedades();
    }
    
    
    boolean comprar(CasillaCalle titulo){
        boolean result = false;
        if(puedeComprar){
            float precio = titulo.getPrecioCompra();
            if(puedoGastar(precio)){
                result = titulo.comprar(this);
                //Ambos implicitos en compar()
                //titulo.setPropietario(this); // no se si es asi
                //paga(titulo.getPrecioCompra());
                propiedades.add(titulo);
                diario.ocurreEvento("El jugador"+this+ " compra la propiedad "+titulo);
                puedeComprar = false;
            }
            else
                diario.ocurreEvento("El jugador"+this+ " no tiene saldo para comprar la propiedad "+titulo);
        }
        return result;
    }
    
    boolean construirCasa(int ip){
        boolean result = false; 
        boolean existe = existeLaPropiedad(ip);
        CasillaCalle propiedad = propiedades.get(ip);
        boolean puedoEdificar = puedoEdificarCasa(propiedad);
        
        if(existe){
            float precioEdificar = propiedad.getPrecioEdificar();
            if(puedoGastar(precioEdificar) && propiedad.getNumCasas() < getCasasMax())
                puedoEdificar = true; 
        }
        if(existe && puedoEdificar){
            result = propiedad.construirCasa(this); 
            //incrementar numCasas
            //result = true; 
        }
        
        return result;
    }
    
    boolean construirHotel(int ip){
       boolean result = false;
       if(existeLaPropiedad(ip)){
           CasillaCalle propiedad = propiedades.get(ip);
           boolean puedoEdificarHotel = puedoEdificarHotel(propiedad);
           float precio = propiedad.getPrecioEdificar();
           if(puedoGastar(precio)){
               if(propiedad.getNumHoteles() < HotelesMax){
                   if(propiedad.getNumCasas() >= CasasporHotel){
                       puedoEdificarHotel = true;
                   }
               }
           }
           if(puedoEdificarHotel){
               result = propiedad.construirHotel(this);
               float precioEdificar = propiedad.getPrecioEdificar();
               paga(precioEdificar);
               propiedad.derruirCasas(CasasporHotel, this);
               diario.ocurreEvento("El jugador "+nombre+ " construye hotel en la propiedad " + ip);
           }
       }
       return result;
    }
    
    boolean enBancarrota(){
        return saldo <= 0;
    }
    
    boolean existeLaPropiedad(int ip){ // comprobar
        boolean existe = false;
        /*if(propiedades.get(ip).tienePropietario()){
            existe = false;
        }*/
        
        for(int i=0; i < propiedades.size(); i++){
            if(propiedades.get(i).getPropietario() == this)
                existe = true;
        }
        
        return existe;
    }
    
    private final int getCasasMax(){
        return CasasMax;
    }
    
    final int getCasasPorHotel(){
        return CasasporHotel;
    }
    
    public int getCasillaActual(){
        return casillaActual;
    }
    
    private final int getHotelesMax(){
        return HotelesMax;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    private final float getPremioPorSalida(){
        return PasoPorSalida;
    }
    
    public ArrayList<CasillaCalle> getPropiedades(){
        return propiedades;
    }
    
    boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    public float getSaldo(){
        return saldo;
    }
    
    boolean modificaSaldo(float cantidad){
        saldo = saldo + cantidad;
        diario.ocurreEvento("Se modifica el saldo de "+ this.getNombre() + " " + cantidad+ 
                            " Nuevo saldo: " + this.getSaldo());
        return true;
    }
    
    boolean moverACasilla(int c){
        casillaActual = c;
        puedeComprar = false;
        diario.ocurreEvento("El jugador se mueve a casilla: " + c);
        return true;
    }
    
    boolean paga(float cantidad){
        boolean paga = modificaSaldo(cantidad * (-1));
        return paga;
    }
    
    boolean pagaAlquiler(float cantidad){
        boolean pagaAlquiler = paga(cantidad);
        return pagaAlquiler;
    }
    
    boolean pasaPorSalida(){
       recibe(PasoPorSalida);
       diario.ocurreEvento("El jugador pasa por salida y recibe premio: " + PasoPorSalida);
       return true;
    }
    
    boolean puedeComprarCasilla(){
        boolean puedeComprarCasilla = true;
        /*if(saldo >= propiedades.get(casillaActual).getPrecioCompra()){
            puedeComprarCasilla = true;
            puedeComprar = true;
        }*/
        puedeComprar = true;
        return puedeComprarCasilla;
    }
    
    private boolean puedoEdificarCasa(CasillaCalle propiedad){
        if (puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumCasas() < 4)
            return true; 
        else{
            System.err.println("No se puede construir una casa... ");
        }
        return false;
    }
    
    private boolean puedoEdificarHotel(CasillaCalle propiedad){
        if (puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumCasas() == 4 && propiedad.getNumHoteles() < 4)
            return true; 
        else{
            System.err.println("No se puede construir un hotel... ");
        }
        return false;
    }
    
    boolean puedoGastar(float precio){
        boolean puedoGastar = false;
        if(saldo < precio){
            puedoGastar = false;
        }
        else
            puedoGastar = true;
        
        return puedoGastar;
    }
    
    boolean recibe(float cantidad){
        boolean recibe = modificaSaldo(cantidad);
        return recibe;
    }
    
    boolean tieneAlgoQueGestionar(){
        boolean tiene = true;
        if(propiedades.isEmpty()){
            tiene = false;
        }
        return tiene;
    }
    
    public boolean esEspeculador(){
        return false;
    }
    
    
    @Override
    public String toString(){
        String devolver = "";
        devolver += " El jugador actual con nombre: " + nombre;
        devolver += " El jugador tiene un saldo de: " + saldo;
        return devolver;
    }
    @Override
    public int compareTo(Jugador t) {
        int compare = 0;
       if(saldo == t.saldo){
           compare= 0;
       }
       else if(saldo < t.saldo){
           compare = 1;
       }
       else if(saldo > t.saldo){
           compare = -1;
       }
       return compare;
    }
    
}
