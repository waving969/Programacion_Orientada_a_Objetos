/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
/**
 *
 * @author Wavin
 */
public abstract class Sorpresa {
    private Diario diario = Diario.getInstance();
    
    
    Sorpresa(){
    }
    
    void informe(int actual, ArrayList<Jugador> todos){
       diario.ocurreEvento(" Aplicando sorpresa a jugador:  " + todos.get(actual));
    }
    
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);
    
    
    boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        if(todos.get(actual)==null){
            return true;
        }else{
            return false;
        }
    }
}
