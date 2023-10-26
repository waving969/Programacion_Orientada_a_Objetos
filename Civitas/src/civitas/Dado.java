/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;
import java.util.Random;
/**
 *
 * @author joaquinsgi
 */
public class Dado {
    private Random random;
    private int ultimoResultado;
    private boolean debug;
    static final private Dado instance = new Dado();
    
    Dado(){
        ultimoResultado = 0;
        debug = false;
        random  = new Random();
    }
    
    static public Dado getInstance() {
        return instance;
    }
    
    int tirar(){
        int devolver;
        if(debug == false){
            devolver = random.nextInt((6 - 1) + 1) + 1; // comprobar
        }
        else
            devolver = 1;
        ultimoResultado = devolver;
        return devolver;
    }
    
    int quienEmpieza(int n){
        int devolver;
        devolver = random.nextInt(n);
        return devolver;
    }
    
    void setDebug(boolean d){
        Diario evento= Diario.getInstance();
        if(d == true){
            debug=d;
            evento.ocurreEvento("Se ha activado el debug");
        }else{
            debug=d;
            evento.ocurreEvento("Se ha desactivado el debug");
        }
    }
    
    int getUltimoResultado(){
        return ultimoResultado;
    }
}
