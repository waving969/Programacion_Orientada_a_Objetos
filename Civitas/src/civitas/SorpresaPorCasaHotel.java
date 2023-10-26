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
public class SorpresaPorCasaHotel extends Sorpresa{
    private String texto; 
    private int valor; 
   

    public SorpresaPorCasaHotel(String texto, int valor) {
        this.texto = texto;
        this.valor = valor;
        
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        Jugador jugador = todos.get(actual); 
        int cantidadCasasHotel = 0;
        for (CasillaCalle casilla: jugador.getPropiedades()){
            cantidadCasasHotel += casilla.cantidadCasasHoteles();
        }
        jugador.modificaSaldo(cantidadCasasHotel);
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
