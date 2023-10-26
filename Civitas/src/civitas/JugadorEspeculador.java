/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

/**
 *
 * @author Wavin
 */
public class JugadorEspeculador extends Jugador{
    protected static final int factorEspeculador = 2; 
    
    public JugadorEspeculador(Jugador otro){
        super(otro); 
    }
    
    private boolean puedoEdificarCasa(CasillaCalle propiedad){
        if (puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumCasas() < 4*factorEspeculador)
            return true; 
        return false;
    }
    
    private boolean puedoEdificarHotel(CasillaCalle propiedad){
        if (puedoGastar(propiedad.getPrecioEdificar()) && propiedad.getNumCasas() == 4 && propiedad.getNumHoteles() < 4*factorEspeculador)
            return true; 
        return false;
    }
    
    @Override
    public String toString(){
        return " JugadorEspeculador " + super.toString();
    }
    
    public boolean esEspecualador(){
        return true;
    }
}
