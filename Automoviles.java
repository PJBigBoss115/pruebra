/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstacionamientoAdmi;

/**
 *
 * @author pedro
 */
public class Automoviles extends Vehiculos{
    
    protected static int numeradorA = 0;
    
    //Variables para validar numero randon
    int contadorA = 0;
    int iA;
    int jA = 0;
    int cantidadA = 10;
    int arregloA[] = new int[cantidadA];

    public Automoviles(String placa) {
        super(placa);
    }

    public Automoviles() {
    }
    
       @Override
     public void setId() {
        numeradorA++;
        this.id = numeradorA;
    }
    
     /**
      * Devuelve un numero dentro de un parametro
      * @param rango 
      */
     
    @Override
    public void NumeroRandom(int rango){
        contadorA++;
        iA = 0;
        arregloA[0] = 0;
               while(iA != 1){ 
                   arregloA[contadorA] = (int) (Math.random() * rango);
                        for(jA = contadorA; jA > -1; jA--){
                            if(arregloA[contadorA] == arregloA[jA]){
                                arregloA[contadorA] = (int) (Math.random() * rango);
                                jA = contadorA;
                            }else{
                                iA = 1;
                            }
                        }
               }
    }
    
}
