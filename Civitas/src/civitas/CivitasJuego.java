/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import GUI.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;

/**
 *
 * @author Wavin
 */
public class CivitasJuego {
    private int indiceJugadorActual; 
    private ArrayList<Jugador> jugadores = new ArrayList(4); 
    private boolean debug;
    private MazoSorpresas mazo; 
    private Tablero tablero; 
    private EstadoJuego estado; 
    private GestorEstados gestorEstados;

    private Dado dado;
 
    
    
    public CivitasJuego(ArrayList<String> nombres, boolean debug){
        for(String n: nombres)
            this.jugadores.add(new Jugador(n){});
        
        this.debug = debug;
        this.mazo = new MazoSorpresas(); 
        this.tablero = new Tablero(); 
        this.gestorEstados = new GestorEstados();
        this.estado = gestorEstados.estadoInicial();
        dado = Dado.getInstance();
        indiceJugadorActual = dado.quienEmpieza(4);
        dado.setDebug(debug);
        inicializaTablero(mazo);
        inicializaMazoSorpresas();
    }
    
     void avanzaJugador(){
        Jugador jugadorActual = getJugadorActual(); 
        int posicionActual = jugadorActual.getCasillaActual();
        int tirada = dado.tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = tablero.getCasilla(posicionNueva); 
        contabilizarPasosPorSalida();
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual, jugadores);
    }
    
    public boolean comprar(){
        boolean res;
        Jugador jugadorActual = getJugadorActual(); 
        int numCasillaActual = jugadorActual.getCasillaActual();
        CasillaCalle casilla = (CasillaCalle)tablero.getCasilla(numCasillaActual);
        res = jugadorActual.comprar(casilla);
        
        return res;
    }
    
    public boolean construirCasa(int ip){
        //this
       return this.jugadores.get(indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
        //this
       return this.jugadores.get(indiceJugadorActual).construirHotel(ip);
    }
    
    private void contabilizarPasosPorSalida(){
        //this
        if (tablero.computarPasoPorSalida())
            jugadores.get(indiceJugadorActual).pasaPorSalida();
    }
    
    public boolean finalDelJuego(){
        //this
        for(Jugador j: jugadores){
            if(j.enBancarrota())
                return true;
        }
        
        return false;
    }
    
    public int getIndiceJugadorActual(){
        return indiceJugadorActual;
    }
    
    public Jugador getJugadorActual(){
        //this
        return this.jugadores.get(indiceJugadorActual);
    }
    
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    private void inicializaMazoSorpresas(){
        //this
        mazo.alMazo(new SorpresaPagarCobrar("A pagar", -100));
        mazo.alMazo(new SorpresaPagarCobrar("A cobrar", 100));
        mazo.alMazo(new SorpresaPagarCobrar( "A pagar", -200));
        mazo.alMazo(new SorpresaPagarCobrar( "A cobrar", 200));
        mazo.alMazo(new SorpresaPagarCobrar( "A pagar", -500));
        mazo.alMazo(new SorpresaPagarCobrar( "A cobrar", 500));
        mazo.alMazo(new SorpresaPagarCobrar("Cobras por cada propiedad 100", 100));
        mazo.alMazo(new SorpresaPorCasaHotel("Debes pagar por cada propiedad 100", -100));
        mazo.alMazo(new SorpresaPorCasaHotel( "Cobras por cada propiedad 100", 500));
        mazo.alMazo(new SorpresaPorCasaHotel( "Debes pagar por cada propiedad 100", -500));
    }
    
    private void inicializaTablero(MazoSorpresas mazo){
        //this
        tablero = new Tablero(); 
        float alquiler1 = 100; float edificar1 = 200; float compra1 = 500;  
        float alquiler2 = 200; float edificar2 = 200;  float compra2 = 600; 
        float alquiler3 = 300; float edificar3 = 200; float compra3 = 700; 
        float alquiler4 = 400; float edificar4 = 200; float compra4 = 800; 
        float alquiler5 = 500; float edificar5 = 200; float compra5 = 900; 
        float alquiler6 = 600; float edificar6 = 200; float compra6 = 1000;
        float alquiler7 = 700; float edificar7 = 200; float compra7 = 1100;
        
        
        //Crear 3 tipos de constructores de casilla 
        //tablero.añadeCasilla(new CasillaCalle( "Plaza Lavapies", 500, 100, 200));
        //tablero.añadeCasilla(new CasillaSorpresa( "SUERTE1", mazo  ));
        //tablero.añadeCasilla(new CasillaCalle("Glorieta Cuatro Caminos", 500, 100, 200));
        //Casilla casilla1 = new CasillaCalle("Prueba", 500, 100, 200);
        //tablero.añadeCasilla(casilla1);
        //tablero.añadeCasilla(new Casilla( "Parking"));
        //tablero.añadeCasilla(new CasillaCalle( "Glorieta de Bilbao", 500, 100, 200));
        //tablero.añadeCasilla(new CasillaSorpresa("SUERTE1", mazo));
        
        tablero.añadeCasilla(new CasillaCalle("Ronda de valencia ", compra1, edificar1, alquiler1));
        tablero.añadeCasilla(new CasillaCalle("GLorieta cuatro caminos ", compra1, edificar1, alquiler1));
        tablero.añadeCasilla(new CasillaCalle("Calle Alberto Aguilera ", compra2, edificar2, alquiler2));
        tablero.añadeCasilla(new CasillaCalle("Calle Velazquez ", compra2, edificar2, alquiler2));
        tablero.añadeCasilla(new CasillaSorpresa ("Suerte1 ", mazo));
        tablero.añadeCasilla(new CasillaCalle("Avenida de America ", compra3, edificar3, alquiler3));
        tablero.añadeCasilla(new CasillaCalle("Calle Cea Bermudez ", compra3, edificar3, alquiler3));
        tablero.añadeCasilla(new CasillaSorpresa ("Suerte2 ", mazo));
        tablero.añadeCasilla(new CasillaCalle("Avenida Reyes Catolicos ", compra4, edificar4, alquiler4));
        tablero.añadeCasilla(new Casilla("Parking")); //Parking
        tablero.añadeCasilla(new CasillaCalle("Plaza de España ", compra4, edificar4, alquiler4));
        tablero.añadeCasilla(new CasillaSorpresa ("Suerte3 ", mazo));
        tablero.añadeCasilla(new CasillaCalle("Puerta del SOl ", compra5, edificar5, alquiler5));
        tablero.añadeCasilla(new CasillaCalle("Calle Bailen ", compra5, edificar5, alquiler5));
        tablero.añadeCasilla(new CasillaCalle("Puerta del Sol ", compra6, edificar6, alquiler6));
        tablero.añadeCasilla(new CasillaCalle("Gran Via ", compra6, edificar6, alquiler6));
        tablero.añadeCasilla(new CasillaSorpresa ("Suerte4 ", mazo));
        tablero.añadeCasilla(new CasillaCalle("Paseo de la Castellana ", compra7, edificar7, alquiler7));
        tablero.añadeCasilla(new CasillaCalle("Paseo del Prado ", compra7, edificar7, alquiler7));
    }
    
    private void pasarTurno(){
        //this
        this.indiceJugadorActual = (indiceJugadorActual+1)%jugadores.size();
    }
    
    public ArrayList<Jugador> ranking(){
        //this
        ArrayList<Jugador> ranking; 
        ranking = jugadores; 
        Collections.sort(ranking);
        return ranking;
    }
    
    public OperacionJuego siguientePaso(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        OperacionJuego operacion = gestorEstados.siguienteOperacion(jugadorActual, estado);
        
        switch(operacion){
            case PASAR_TURNO:
                pasarTurno(); 
                siguientePasoCompletado(operacion);
                break;
            case AVANZAR:
                avanzaJugador();
                siguientePasoCompletado(operacion);
                break;
                
        }
        
        return operacion;
    }
    
    public void siguientePasoCompletado(OperacionJuego operacion){
        //this
        this.estado = gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);
    }
}
