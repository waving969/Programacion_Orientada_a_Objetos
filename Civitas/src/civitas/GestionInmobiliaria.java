/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

/**
 *
 * @author Wavin
 */
public class GestionInmobiliaria {
    private int propiedad; 
    private OperacionInmobiliaria operacion;

    public GestionInmobiliaria(int propiedad, OperacionInmobiliaria operacion) {
        this.propiedad = propiedad;
        this.operacion = operacion;
    }

    public int getPropiedad() {
        return propiedad;
    }

    public OperacionInmobiliaria getOperacion() {
        return operacion;
    }
    
    
}
