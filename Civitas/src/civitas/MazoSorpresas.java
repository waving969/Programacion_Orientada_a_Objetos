/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Wavin
 */
public class MazoSorpresas {
    private ArrayList<Sorpresa> sorpresas; 
    private boolean barajada;
    private int usadas; 
    private boolean debug; 
    
    private Sorpresa ultimaSorpresa;
    
   Diario diario =Diario.getInstance();
    
    private void init(){
        sorpresas = new ArrayList<Sorpresa>(); 
        barajada = false; 
        usadas = 0; 
    }

    public MazoSorpresas() {
        init(); 
        debug = false;
    }
    
    public MazoSorpresas(boolean _debug){
        init(); 
        debug = _debug;
        
        if (this.debug == true)
            diario.ocurreEvento("MazoSorpresas DebugMode ON");
    }
    
    
    //Metodos con visibilidad de paquete
    void alMazo(Sorpresa s){
        if (barajada == false)
            sorpresas.add(s);
    }
    
    Sorpresa siguiente(){
        if (debug == false){
            if(barajada == false || usadas == sorpresas.size()){
                Collections.shuffle(sorpresas);
                barajada = true; 
                usadas = 0; 
            }
        }
        
        usadas++; 
        ultimaSorpresa = sorpresas.get(0); 
        sorpresas.remove(0);
        sorpresas.add(ultimaSorpresa);
        
        return ultimaSorpresa;
    }
    
    
    
    
    
}
