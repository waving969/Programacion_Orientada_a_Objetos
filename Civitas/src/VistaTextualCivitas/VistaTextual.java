package vistaTextualCivitas;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import controladorCivitas.Respuesta;
import civitas.OperacionInmobiliaria;
import civitas.Jugador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class VistaTextual implements Vista {
  
    
  private static String separador = "=====================";
  
  private Scanner in;
  
  CivitasJuego juegoModel;
  
  public VistaTextual (CivitasJuego juegoModel) {
    in = new Scanner (System.in);
    this.juegoModel=juegoModel;
  }
  
  
  public void actualiza(){
        System.out.println(" El jugador actual es: " + juegoModel.getJugadorActual().getNombre() + "Esta en la casilla: " + juegoModel.getJugadorActual().getCasillaActual());
        if(juegoModel.finalDelJuego()){
            System.out.println(juegoModel.ranking());
        }
    }
           
 public  void pausa() {
    System.out.print ("\nPulsa una tecla");
    in.nextLine();
  }
 
 public Respuesta comprar(){
        int opcion = menu (juegoModel.getTablero().getCasilla(juegoModel.getJugadorActual().getCasillaActual()).toString() + "\n¿Desea comprar la calle actual?",
        new ArrayList<> (Arrays.asList("Si", "No")));
        return (Respuesta.values()[opcion]);

    }
 
 public int elegirPropiedad(){
    ArrayList<String> calles = new ArrayList<String>();
    for(Casilla casilla: juegoModel.getJugadorActual().getPropiedades()){
        calles.add(casilla.getNombre());
    }
    int iPropiedad = menu("¿Sobre que casilla desea operar?",calles);
    return iPropiedad;
    }
 
 public void mostrarSiguienteOperacion(OperacionJuego operacion) {
      switch(operacion){
          case AVANZAR:
              System.out.println("La siguiente operacion es avanzar");
              break;
          case COMPRAR:
              System.out.println("La siguiente operacion es comprar");
              break;
          case GESTIONAR:
              System.out.println("La siguiente operacion es gestionar");
              break;
          case PASAR_TURNO:
              System.out.println("La siguiente operacion es pasar turno");
              break;
          default:
              System.out.println("La siguiente operacion es salir de la carcel");
      }
  }
 
 public void mostrarEventos(){
     while(Diario.getInstance().eventosPendientes()){
         System.out.println(Diario.getInstance().leerEvento());
     }
 }
 
 public OperacionInmobiliaria elegirOperacion(){
     ArrayList<String> opciones = new ArrayList<>();
     opciones.add("CONSTRUIR_CASA"); opciones.add("CONSTRUIR_HOTEL"); opciones.add("TERMINAR");
     int res = menu("Elige una propiedad", opciones);
     
     return OperacionInmobiliaria.values()[res];
 }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

}
