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
public class SorpresaPagarCobrar extends Sorpresa{
    private String texto; 
    private int valor; 
    

    public SorpresaPagarCobrar(String texto, int valor) {
        this.texto = texto;
        this.valor = valor;
        
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        todos.get(actual).modificaSaldo(valor);
        super.informe(actual, todos);
    }
    
    
    @Override
    public String toString(){
        //Implementar
        String devolver = "Sorpresa: " +
                "Texto = " + texto + 
                "Valor = " + valor;
        
        return devolver;
    
    }
    
}
