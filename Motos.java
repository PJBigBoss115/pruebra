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
public class Motos extends Vehiculos {

    protected static int numeradorM = 0;
    
    //Variables para validar numero randon
    int contadorM = 0;
    int iM;
    int jM = 0;
    int cantidadM = 10;
    int arregloM[] = new int[cantidadM];
    
    public Motos(String placa) {
        super(placa);
    }

    public Motos() {
    }
    
    @Override
     public void setId() {
        numeradorM++;
        this.id = numeradorM;
    }
    
      /**
      * Devuelve un numero dentro de un parametro
      * @param rango 
      */
     
    @Override
    public void NumeroRandom(int rango){
        contadorM++;
        iM = 0;
        arregloM[0] = 0;
               while(iM != 1){ 
                   arregloM[contadorM] = (int) (Math.random() * rango);
                        for(jM = contadorM; jM > -1; jM--){
                            if(arregloM[contadorM] == arregloM[jM]){
                                arregloM[contadorM] = (int) (Math.random() * rango);
                                jM = contadorM;
                            }else{
                                iM = 1;
                            }
                        }
               }
    }
    
}
