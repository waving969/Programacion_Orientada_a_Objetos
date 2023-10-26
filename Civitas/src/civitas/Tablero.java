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
public class Tablero {
    private ArrayList<Casilla> casillas; 
    private boolean porSalida; 
    
    Tablero(){
        casillas = new ArrayList<Casilla>();
        Casilla salida = new Casilla( "Salida");
        
        casillas.add(salida);
        
        porSalida = false;
    }
    
    //Metodos privados
    private boolean correcto(int numCasilla){
        boolean devolver = false; 
        if(numCasilla <= casillas.size())
            devolver = true; 
        
        return devolver;
    }
    
    //Metodos con visibilidad de paquete
    boolean computarPasoPorSalida(){
        /*porSalida = false; 
        
        return porSalida;*/
        
        if (porSalida){
            porSalida = false; 
            return true; 
        }
        
        return false;
    }
    
    void añadeCasilla(Casilla casilla){
        casillas.add(casilla);
    }
    
    
    public Casilla getCasilla(int numCasilla){
        if(this.correcto(numCasilla))
            return casillas.get(numCasilla);
        else
            return null;
    }
    
    public ArrayList<Casilla> getCasillas(){
        return casillas;
    }
    
    int nuevaPosicion(int actual, int tirada){
        int nuevaPos = actual+tirada; 
        
        if(nuevaPos >= casillas.size()){
            porSalida = true; 
            nuevaPos = nuevaPos%casillas.size();
        }
        
        return nuevaPos;
    }
}
