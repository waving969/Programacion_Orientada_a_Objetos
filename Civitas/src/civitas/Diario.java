/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;
import java.util.ArrayList;
/**
 *
 * @author joaquinsgi
 */
public class Diario {
    //Es un singleton. La propia clase almacena la referencia a la única instancia
    static final private Diario instance = new Diario();
    private ArrayList<String> eventos; // mirar
    private Diario () {
        eventos = new ArrayList<>();
    }
//Método de clase para obtener la instancia
    static public Diario getInstance() {
        return instance;
    
    }
    
    public boolean eventosPendientes(){
        return !eventos.isEmpty();
    }
    
    public String leerEvento(){
        String salida = "";
        if (!eventos.isEmpty()) {
          salida = eventos.remove(0);
        }
        return salida;
    }
    
    void ocurreEvento (String e) {
        eventos.add(e);
    }
// el resto de métodos de la clase Diario
}


