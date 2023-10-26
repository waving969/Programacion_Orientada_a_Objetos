/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;
import civitas.CivitasJuego;
import java.util.ArrayList;
import controladorCivitas.Controlador;
import vistaTextualCivitas.Vista;
import vistaTextualCivitas.VistaTextual;

/**
 *
 * @author Wavin
 */
public class TestP5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        CivitasView prueba = new CivitasView();
        CapturaNombres capturar = new CapturaNombres(prueba, true);
        ArrayList<String> nombres = new ArrayList<>();
        Controlador controlador;
        
        //Vista vista = new VistaTextual(juego);
        
        //nombres = capturar.getNombres();
        nombres.add("yo"); 
        nombres.add("Tu"); 
        nombres.add("El"); 
        nombres.add("Ella");
        
        for(String n: nombres)
            System.out.println(n);
        CivitasJuego juego = new CivitasJuego(nombres, false);
        
        controlador = new Controlador(juego, prueba);
        //controlador = new Controlador(juego, vista);
        
        prueba.setCivitasJuego(juego);
        
        controlador.juegaNewVista();
        prueba.actualiza();
        prueba.pausa();
        
        
        
    }
    
}
