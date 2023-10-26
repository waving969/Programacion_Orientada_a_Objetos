/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author joaqu
 */
public class SorpresaConvertirme extends Sorpresa{
    private String texto; 
    private int valor; 

    public SorpresaConvertirme(String texto, int valor) {
        this.texto = texto;
        this.valor = valor;
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        todos.get(actual).modificaSaldo(valor);
        super.informe(actual, todos);
    }
}
