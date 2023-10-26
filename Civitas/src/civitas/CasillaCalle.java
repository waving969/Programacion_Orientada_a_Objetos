/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author Wavin
 */
public class CasillaCalle extends Casilla{
    //private String titulo;
    private float precioCompra; 
    private float precioEdificar; 
    private float precioBaseAlquiler; 
    private int numCasas; 
    private int numHoteles; 
    private static final float FACTORALQUILERCALLE = 1.0f; 
    private static final float FACTORALQUILERCASA = 1.0f;
    private static final float FACTORALQUILERHOTEL = 4.0f; 
    private Diario diario = Diario.getInstance();
    private Jugador propietario;
    private MazoSorpresas mazo;
    
    CasillaCalle(String titulo, float precioCompra, float precioEdificar, float precioBaseAlquiler){
        super(titulo);
        this.precioCompra = precioCompra; 
        this.precioEdificar = precioEdificar;
        this.precioBaseAlquiler = precioBaseAlquiler;
    }


    public float getPrecioCompra() {
        return precioCompra;
    }

    public float getPrecioEdificar() {
        return precioEdificar;
    }

    public float getPrecioBaseAlquiler() {
        return precioBaseAlquiler;
    }

    public int getNumCasas() {
        return numCasas;
    }

    public int getNumHoteles() {
        return numHoteles;
    }

    public Diario getDiario() {
        return diario;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public MazoSorpresas getMazo() {
        return mazo;
    }
    
    boolean comprar(Jugador jugador){
        if(!tienePropietario()){
            propietario = jugador; 
            propietario.paga(precioCompra); 
            return true;
        }
        return false;
    }
    
    boolean construirHotel(Jugador jugador){
        boolean devolver; 
        
        devolver = propietario.paga(this.getPrecioEdificar()); 
        this.numHoteles++; 
        
        return devolver;
    }
    
    boolean construirCasa(Jugador jugador){
        boolean devolver; 
        devolver = propietario.paga(this.getPrecioEdificar()); 
        this.numCasas++; 
        
        return devolver;
    }
    
    boolean derruirCasas(int n, Jugador jugador){
        boolean derruye = false; 
        if(this.esEsteElPropietario(jugador) && this.getNumCasas() >=n ){
            this.numCasas = this.numCasas - n; 
            derruye = true; 
        }
        
        return derruye;
    }
    
    
    public float getPrecioAlquilerCompleto(){
        float precioAlquilerCompleto; 
        precioAlquilerCompleto = this.getPrecioBaseAlquiler() * (FACTORALQUILERCASA + this.getNumCasas() + (this.getNumHoteles()*FACTORALQUILERHOTEL));
        
        return precioAlquilerCompleto;
    }
    
    public int cantidadCasasHoteles(){
        int cantidad = this.numCasas + this.numHoteles; 
        
        return cantidad;
    }
    
    public boolean esEsteElPropietario(Jugador jugador){
        boolean esElPropietario = false; 
        if(propietario.getNombre() == jugador.getNombre()){
            esElPropietario = true; 
        }
        
        return esElPropietario;
    }
    
    public boolean tienePropietario(){
        return this.propietario != null;
    }
    
    public void tramitarAlquiler(Jugador jugador){
        if(this.tienePropietario() == true && this.esEsteElPropietario(jugador) == false){
            jugador.pagaAlquiler(this.getPrecioAlquilerCompleto()); 
            propietario.recibe(this.getPrecioAlquilerCompleto());
        }
    }
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> jugadores){
        Jugador jugador = jugadores.get(actual);
        if(jugadorCorrecto(actual, jugadores) == true){
            super.informe(actual, jugadores);
            if(this.tienePropietario() == false){
                jugador.puedeComprarCasilla();
            }else{
                tramitarAlquiler(jugador);
            }
        }
    }
    
    @Override
    public String toString(){
        return " Casilla " + super.getNombre() + " ";
    }
    
    
    
    
}
