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
public class Casilla {
   
    private String nombre; 
    
    Casilla (String nombre){
        this.nombre = nombre; 
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
    void informe(int actual, ArrayList<Jugador> todos){
        Diario diario = Diario.getInstance(); 
        diario.ocurreEvento("El jugador " + todos.get(actual) +
                            " a caido en la casilla " + toString());
    }
    boolean jugadorCorrecto(int actual, ArrayList<Jugador> jugadores){
        if(jugadores.get(actual) != null){
            return true; 
        }else{
            return false;
        }
    }
    
    void recibeJugador(int actual, ArrayList<Jugador> jugadores){
        informe (actual, jugadores);
    }
    
    @Override
    public String toString(){
        return " Casilla " + this.nombre + " ";
    }
}
