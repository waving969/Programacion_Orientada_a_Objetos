/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegoTexto;

import civitas.CivitasJuego;
import controladorCivitas.Controlador;
import java.util.ArrayList;
import vistaTextualCivitas.VistaTextual;

/**
 *
 * @author Wavin
 */
public class JuegoTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> jugadores = new ArrayList();
        jugadores.add(" Jonny Melabo "); 
        jugadores.add(" Aitor Menta ");
        jugadores.add(" Elena Nito "); 
        jugadores.add(" Cintia Aislante ");
        
        CivitasJuego modelo = new CivitasJuego(jugadores, false);
        VistaTextual vista = new VistaTextual(modelo);
        Controlador controlador = new Controlador(modelo, vista);
        
        controlador.juega();
    }
    
}
