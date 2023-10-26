/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorCivitas;

import GUI.CivitasView;
import civitas.CivitasJuego;
import civitas.OperacionInmobiliaria;
import civitas.OperacionJuego;
import vistaTextualCivitas.Vista;

/**
 *
 * @author Wavin
 */
public class Controlador {
    CivitasJuego juego; 
    Vista vista;
    CivitasView newVista;

    public Controlador(CivitasJuego juego, Vista vista) {
        this.juego = juego;
        this.vista = vista;
    }
    
    public Controlador(CivitasJuego juego, CivitasView newVista){
        this.juego = juego; 
        this.newVista = newVista;
    }
    
    public void juega(){
        boolean finalJuego = juego.finalDelJuego();
        while(!finalJuego){
            vista.actualiza();

            vista.pausa();

            OperacionJuego operacion = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(operacion);
            
            if(operacion != OperacionJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
            
            finalJuego = juego.finalDelJuego();
            
            if (!finalJuego){
                switch (operacion) {
                    case COMPRAR:
                        Respuesta respuesta = vista.comprar();
                        if(respuesta == Respuesta.SI){
                            juego.comprar();
                            juego.siguientePasoCompletado(operacion);
                        }else{
                            juego.siguientePasoCompletado(OperacionJuego.PASAR_TURNO);
                      
                        }
                        break;
                    case GESTIONAR:
        OperacionInmobiliaria operacionInmobiliaria = vista.elegirOperacion();
                        
                        switch (operacionInmobiliaria) {
                            case TERMINAR:
                                juego.siguientePasoCompletado(operacion);
                                break;
                            case CONSTRUIR_CASA:
                                int idCasa = vista.elegirPropiedad();
                                juego.construirCasa(idCasa);
                                break;
                            case CONSTRUIR_HOTEL: 
                                int idHotel = vista.elegirPropiedad(); 
                                juego.construirHotel(idHotel);
                                break;
                        }
                        break;
                }
            }else{
                juego.ranking(); 
                vista.actualiza();
            }
            
        }
    }
    
    public void juegaNewVista(){
        boolean finalJuego = juego.finalDelJuego();
        while(!finalJuego){
            newVista.actualiza();

            newVista.pausa();
            
            

            OperacionJuego operacion = juego.siguientePaso();
            newVista.mostrarSiguienteOperacion(operacion);
            
            if(operacion != OperacionJuego.PASAR_TURNO){
                newVista.mostrarEventos();
            }
          
            finalJuego = juego.finalDelJuego();
            
            if (!finalJuego){
                switch (operacion) {
                    case COMPRAR:
                        Respuesta respuesta = newVista.comprar();
                        if(respuesta == Respuesta.SI){
                            juego.comprar();
                            juego.siguientePasoCompletado(operacion);
                        }else{
                            juego.siguientePasoCompletado(OperacionJuego.PASAR_TURNO);
                      
                        }
                        break;
                    case GESTIONAR:
        OperacionInmobiliaria operacionInmobiliaria = newVista.elegirOperacion();
                        
                        switch (operacionInmobiliaria) {
                            case TERMINAR:
                                juego.siguientePasoCompletado(operacion);
                                break;
                            case CONSTRUIR_CASA:
                                int idCasa = newVista.elegirPropiedad();
                                juego.construirCasa(idCasa);
                                break;
                            case CONSTRUIR_HOTEL: 
                                int idHotel = newVista.elegirPropiedad(); 
                                juego.construirHotel(idHotel);
                                break;
                        }
                        break;
                }
            }else{
                juego.ranking(); 
                newVista.actualiza();
            } 
        }
    }
}
