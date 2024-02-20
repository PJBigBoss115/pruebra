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
public class Camiones extends Vehiculos {
    
    protected static int numeradorC = 0;
    
    //Variables para validar numero randon
    int contadorC = 0;
    int iC;
    int jC = 0;
    int cantidadC = 10;
    int arregloC[] = new int[cantidadC];

    public Camiones(String placa) {
        super(placa);
    }

    public Camiones() {
    }
    
    @Override
     public void setId() {
        numeradorC++;
        this.id = numeradorC;
    }
    
      /**
      * Devuelve un numero dentro de un parametro
      * @param rango 
      */
     
    @Override
    public void NumeroRandom(int rango){
        contadorC++;
        iC = 0;
        arregloC[0] = 0;
               while(iC != 1){ 
                   arregloC[contadorC] = (int) (Math.random() * rango);
                        for(jC = contadorC; jC > -1; jC--){
                            if(arregloC[contadorC] == arregloC[jC]){
                                arregloC[contadorC] = (int) (Math.random() * rango);
                                jC = contadorC;
                            }else{
                                iC = 1;
                            }
                        }
               }
    }
    
}
