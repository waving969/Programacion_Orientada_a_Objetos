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
public class CasillaSorpresa extends Casilla{
    MazoSorpresas mazo; 
    Sorpresa sorpresa;
    
    CasillaSorpresa(String nombre, MazoSorpresas mazo){
        super(nombre); 
        this.mazo = mazo; 
        this.sorpresa = null;
    }

    public MazoSorpresas getMazo() {
        return mazo;
    }

    public Sorpresa getSorpresa() {
        return sorpresa;
    }
    
    
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> jugadores){
        Jugador jugador = jugadores.get(actual);
        if(jugadorCorrecto(actual, jugadores) == true){
            sorpresa = mazo.siguiente();
            super.informe(actual, jugadores);
            sorpresa.aplicarAJugador(actual, jugadores);
        }
    }
    
    @Override
    public String toString(){
        return " CasillaSorpresa " + 
                " mazo: " + mazo.toString() + 
                " Sorpresa: " + sorpresa.toString() + " ";
    }
}
