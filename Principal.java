/*
 * Clase principal donde se ejecuta el codigo,
 * Estructuras de seleccion, variables globales....
 */
package EstacionamientoAdmi;

/**
 *
 * @author pedro
 */

import java.util.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Principal {
    
    static double tarifaY = 0;
    static double cobroX = 0;
    static double tiempoX = 3600;
    static int cupoAuto = 0;
    static int cupoMoto = 0;
    static int cupoCami = 0;
    
     /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args)throws InterruptedException {
    
    //Variables
    int opcion = 0;
    int definir = 0;
    int cupoFinal;
    int aleatorio;
    int info = 0;
    int valido = 0;
    int finalizar = 0;
    int volver;
    int longuitud;
    char caracter;
    String cadena = "";
    
    //Para realizar algunos metodos importantes
    ValidacionesEtc metodo = new ValidacionesEtc();
    
    //Dar formato a un valor
    NumberFormat formato = NumberFormat.getInstance();
    formato.setMinimumIntegerDigits(3);
    
    //Leer entradas de texto o digitos
    Scanner entrada = new Scanner(System.in);
    Scanner leerCadena = new Scanner(System.in);
    
    //ArrayList
    ArrayList<Vehiculos> listaVehiculos = new ArrayList<Vehiculos>();
    Automoviles nAuto = new Automoviles();
    Motos nMoto = new Motos();
    Camiones nCami = new Camiones();
    
    //Obtener hora y fecha
    LocalTime hora = LocalTime.now();
    LocalDate fecha = LocalDate.now();
    DateTimeFormatter fH = DateTimeFormatter.ofPattern("h':'mm");
    DateTimeFormatter fF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        while (finalizar != 1){
            menuPrincipal();
            try {
                opcion = entrada.nextInt();
            } catch (Exception e) {
                Thread.sleep(500);
                System.out.println("\nSolo se aceptan valores numericos.");
                Thread.sleep(1000);
                entrada = new Scanner(System.in);
            }
        
        volver = 0;
        
        switch(opcion){
            
            case 1:
                while(volver != 1){
                   if((cobroX == 0) && (tarifaY == 0) && (cupoAuto == 0) && (cupoMoto == 0) && (cupoCami == 0)){
                       volver  = 1;
                       System.out.println("\nCupos y montos de cobro no definidos");
                   }else{
                       System.out.println("\nCupos y montos de cobro definidos correctamente");
                       volver = 2;
                       while(volver != 0){
                       menuVehiculos();
                       try{
                           opcion = entrada.nextInt();
                       }catch(Exception e){
                           Thread.sleep(500);
                           System.out.println("\nSolo se aceptan valores numericos.");
                           Thread.sleep(1000);
                           entrada = new Scanner(System.in);
                       }
                       switch(opcion){
                           
                           case 1:
                               if(cupoAuto == Automoviles.numeradorA){
                                   System.out.println("\nYa no tenemos estacionamientos disponibles...");
                               }else{
                                   cupoFinal = cupoAuto - Automoviles.numeradorA;
                                   System.out.println("\nHay " + formato.format(cupoFinal) + " estacionamientos disponibles");
                                   while(valido < 8){
                                       System.out.print("Ingrese la placa del vehiculo 'P001SAT': ");
                                       String placa = leerCadena.nextLine();
                                       cadena = placa;
                                       //Empezar Validacion
                                       char []contar = placa.toCharArray();
                                       longuitud = placa.length() - 1;
                                       if(longuitud == 6){
                                           valido = valido + 1;
                                           for(int i = 0; i <= longuitud; i ++){  // Recorrer la cadena de 0 a 6
                                               caracter = contar[i];
                                               switch(i){   // Evaluamos el caso con la posicion
                                                    case 0:
                                                        if(caracter == 'P'){        //Autos: Particular, la letra “P”
                                                            valido = valido + 1;
                                                            info = 0;
                                                            }else{
                                                            System.out.println("\nFormato no valido: la placa es particular 'P' Autos");
                                                            valido = 0;
                                                            info = info + 2;
                                                        }
                                                        break;
                                                    case 1: case 2: case 3:
                                                        metodo.Numeros(caracter);
                                                        valido = valido + metodo.enviaValido;
                                                        info = info + metodo.enviaInfo;
                                                        break;
                                                    case 4: case 5: case 6:
                                                        metodo.Alfabeticos(caracter);
                                                        valido = valido + metodo.enviaValido;
                                                        info = info + metodo.enviaInfo;
                                                        break;
                                                        //Fin switch
                                               }
                                               //Fin recorrer cadena
                                           }
                                           //Fin if
                                       }else{
                                           System.out.println("\nFormato no valido: excede la longuitud requerida");
                                           valido = 0;
                                           //Fin else
                                       }if(info > 0){
                                           System.out.println("\nDato no valido, 'Ingrese de nuevo'");
                                           valido = 0;
                                           //Fin else
                                       }
                                       //Fin While
                                   }
                                   valido = 0;
                                   listaVehiculos.add(new Automoviles(cadena));
                                   int x = cupoAuto + 1;
                                   
                                   nAuto.NumeroRandom(x);
                                   aleatorio = nAuto.arregloA[nAuto.contadorA];
                                   
                                   System.out.println("Estacionamiento: N°:" + formato.format(aleatorio));
                                   
                                   metodo.GenerarTicket("Estacionamiento N°: "+formato.format(aleatorio), 
                                           "Placa: "+cadena,"Fecha: "+fecha.format(fF),"Hora: "+hora.format(fH), "Tarifa de Q" + tarifaY + "0 por " + cobroX + "0 segundos");
                               }
                               break;
                               //Finaliza agregar automovil
                           case 2:
                               if(cupoMoto == Motos.numeradorM){
                                   System.out.println("\nYa no tenemos estacionamientos disponibles...");
                               }else{
                                   cupoFinal = cupoMoto - Motos.numeradorM;
                                   System.out.println("\nHay " + formato.format(cupoFinal) + " estacionamientos disponibles");
                                   while(valido < 8){
                                       System.out.print("Ingrese la placa del vehiculo 'M001SAT': ");
                                       String placa = leerCadena.nextLine();
                                       cadena = placa;
                                       //Empezar Validacion
                                       char []contar = placa.toCharArray();
                                       longuitud = placa.length() - 1;
                                       if(longuitud == 6){
                                           valido = valido + 1;
                                           for(int i = 0; i <= longuitud; i ++){  // Recorrer la cadena de 0 a 6
                                               caracter = contar[i];
                                               switch(i){   // Evaluamos el caso con la posicion
                                                    case 0:
                                                        if(caracter == 'M'){        //Motos: identificado por la letra “M”
                                                            valido = valido + 1;
                                                            info = 0;
                                                            }else{
                                                            System.out.println("\nFormato no valido: la placa es de motocicletas 'M'");
                                                            valido = 0;
                                                            info = info + 2;
                                                        }
                                                        break;
                                                    case 1: case 2: case 3:
                                                        metodo.Numeros(caracter);
                                                        valido = valido + metodo.enviaValido;
                                                        info = info + metodo.enviaInfo;
                                                        break;
                                                    case 4: case 5: case 6:
                                                        metodo.Alfabeticos(caracter);
                                                        valido = valido + metodo.enviaValido;
                                                        info = info + metodo.enviaInfo;
                                                        break;
                                                        //Fin switch
                                               }
                                               //Fin recorrer cadena
                                           }
                                           //Fin if
                                       }else{
                                           System.out.println("\nFormato no valido: excede la longuitud requerida");
                                           valido = 0;
                                           //Fin else
                                       }if(info > 0){
                                           System.out.println("\nDato no valido, 'Ingrese de nuevo'");
                                           valido = 0;
                                           //Fin else
                                       }
                                       //Fin While
                                   }
                                   valido = 0;
                                   listaVehiculos.add(new Motos(cadena));
                                   int x = cupoMoto + 1;
                                   
                                   nMoto.NumeroRandom(x);
                                   aleatorio = nMoto.arregloM[nMoto.contadorM];
                                   
                                   System.out.println("Estacionamiento: N°:" + formato.format(aleatorio));
                                   
                                   metodo.GenerarTicket("Estacionamiento N°: "+formato.format(aleatorio), 
                                           "Placa: "+cadena+"  'Aplica 10% de descuento'","Fecha: "+fecha.format(fF),"Hora: "+hora.format(fH), "Tarifa de Q" + tarifaY + "0 por " + cobroX + "0 segundos");
                               }
                               break;
                               //Finaliza agregar moto
                           case 3:
                               if(cupoCami == Camiones.numeradorC){
                                   System.out.println("\nYa no tenemos estacionamientos disponibles...");
                               }else{
                                   cupoFinal = cupoCami - Camiones.numeradorC;
                                   System.out.println("\nHay " + formato.format(cupoFinal) + " estacionamientos disponibles");
                                   while(valido < 8){
                                       System.out.print("Ingrese la placa del vehiculo 'C001SAT': ");
                                       String placa = leerCadena.nextLine();
                                       cadena = placa;
                                       //Empezar Validacion
                                       char []contar = placa.toCharArray();
                                       longuitud = placa.length() - 1;
                                       if(longuitud == 6){
                                           valido = valido + 1;
                                           for(int i = 0; i <= longuitud; i ++){  // Recorrer la cadena de 0 a 6
                                               caracter = contar[i];
                                               switch(i){   // Evaluamos el caso con la posicion
                                                    case 0:
                                                        if(caracter == 'C'){        //Camiones: Comercial por la letra “C”
                                                            valido = valido + 1;
                                                            info = 0;
                                                            }else{
                                                            System.out.println("\nFormato no valido: la placa es comercial 'C'");
                                                            System.out.println("Trasporte comercial, ligero u pesado....");
                                                            valido = 0;
                                                            info = info + 2;
                                                        }
                                                        break;
                                                    case 1: case 2: case 3:
                                                        metodo.Numeros(caracter);
                                                        valido = valido + metodo.enviaValido;
                                                        info = info + metodo.enviaInfo;
                                                        break;
                                                    case 4: case 5: case 6:
                                                        metodo.Alfabeticos(caracter);
                                                        valido = valido + metodo.enviaValido;
                                                        info = info + metodo.enviaInfo;
                                                        break;
                                                        //Fin switch
                                               }
                                               //Fin recorrer cadena
                                           }
                                           //Fin if
                                       }else{
                                           System.out.println("\nFormato no valido: excede la longuitud requerida");
                                           valido = 0;
                                           //Fin else
                                       }if(info > 0){
                                           System.out.println("\nDato no valido, 'Ingrese de nuevo'");
                                           valido = 0;
                                           //Fin else
                                       }
                                       //Fin While
                                   }
                                   valido = 0;
                                   listaVehiculos.add(new Camiones(cadena));
                                   int x = cupoCami + 1;
                                   
                                   nCami.NumeroRandom(x);
                                   aleatorio = nCami.arregloC[nCami.contadorC];
                                   
                                   System.out.println("Estacionamiento: N°:" + formato.format(aleatorio));
                                   
                                   metodo.GenerarTicket("Estacionamiento N°: "+formato.format(aleatorio), 
                                           "Placa: "+cadena+"  'Q5.00 de recargo por periodo'","Fecha: "+fecha.format(fF),"Hora: "+hora.format(fH), "Tarifa de Q" + tarifaY + "0 por " + cobroX + "0 segundos");
                               }
                               break;
                               //Finaliza agregar camion
                            case 4:
                                // Salir
                                volver = 0;
                                break;
                            default:
                                Thread.sleep(500);
                                System.out.println("\nOpcion no valida.");
                                Thread.sleep(500);
                                break;
                                //Finaliza Switch Case
                       }
                       //Finaliza While
                    }
                    //Finaliza If else   
                  }
                  volver = 1;
                  //Finaliza While 
                }
                break;
                //Finaliza Ingresar Vehiculos
            case 2:
                while(volver != 1){
                    if((cobroX == 0) && (tarifaY == 0) && (cupoAuto == 0) && (cupoMoto == 0) && (cupoCami == 0)){
                       volver  = 1;
                       System.out.println("\nCupos y montos de cobro no definidos");
                    }else{
                        System.out.println("\nCupos y montos de cobro definidos correctamente");
                        volver = 2;
                        while(volver != 0){
                            System.out.print("\nIngrese la placa del vehiculo a retirar: ");
                              String placa = leerCadena.nextLine();
                              cadena = placa;
                              
                              Vehiculos arrayVehiculos[] = new Vehiculos[listaVehiculos.size()];
                              
                              listaVehiculos.toArray(arrayVehiculos); 
                              
                              for(int z = 0; z < arrayVehiculos.length; z++){
                                  arrayVehiculos[z].MostrarDatos(cadena);
                              }
                              
                              LocalTime actual = LocalTime.now();
                                DateTimeFormatter formt = DateTimeFormatter.ofPattern("h':'mm");
                              
                              metodo.CalcularTiempo(tiempoX, Vehiculos.inicio, actual.format(formt));
                              volver = 0;
                              
                              char []contar = placa.toCharArray();
                                longuitud = placa.length() - 1;
                                    caracter = contar[0];
                                    
                                    int pasa = 0;
                                    
                                        if(caracter == 'P'){
                                            metodo.CalcularPago(0, 0);
                                            while(pasa!=9){
                                                System.out.print("Ingrese el NIT: ");
                                                String nit = leerCadena.nextLine();
                                                metodo.ValidarNit(nit);
                                                pasa = metodo.enviaValido;
                                                if(pasa < 9){
                                                    pasa = 0;
                                                }
                                                cadena = nit;
                                            }
                                            metodo.GenerarFactura("Nit: "+cadena, "Por "+metodo.periodosT+"0 periodos de"
                                                    + " tiempo utilizado", "Tarifa: Q"+tarifaY+"0", "Descuento: N/A"
                                                    , "Recargo: N/A", "Total: Q"+metodo.totalCancelar+"0 ");
                                        }
                                        if(caracter == 'M'){
                                            metodo.CalcularPago(0.1, 0);
                                            while(pasa!=9){
                                                System.out.print("Ingrese el NIT: ");
                                                String nit = leerCadena.nextLine();
                                                metodo.ValidarNit(nit);
                                                pasa = metodo.enviaValido;
                                                if(pasa < 9){
                                                    pasa = 0;
                                                }
                                                cadena = nit;
                                            }
                                            metodo.GenerarFactura("Nit: "+cadena, "Por "+metodo.periodosT+"0 periodos de"
                                                    + " tiempo utilizado", "Tarifa: Q"+tarifaY+"0", "Descuento: 10%, Es de: Q"
                                                    +metodo.totalDescuento+"0"
                                                    , "Recargo: N/A", "Total: Q"+metodo.totalCancelar+"0 ");
                                        }
                                        if(caracter == 'C'){
                                            metodo.CalcularPago(0, 5);
                                            while(pasa!=9){
                                                System.out.print("Ingrese el NIT: ");
                                                String nit = leerCadena.nextLine();
                                                metodo.ValidarNit(nit);
                                                pasa = metodo.enviaValido;
                                                if(pasa < 9){
                                                    pasa = 0;
                                                }
                                                cadena = nit;
                                            }
                                            metodo.GenerarFactura("Nit: "+cadena, "Por "+metodo.periodosT+"0 periodos de"
                                                    + " tiempo utilizado", "Tarifa: Q"+tarifaY+"0", "Descuento: N/A"
                                                    , "Recargo: Q5.00 por periodo, Es de: Q"+metodo.extra+"0"
                                                    , "Total: Q"+metodo.totalCancelar+"0 ");
                                        }
                        }
                    }
                    volver = 1;
                }
                volver = 0;
                break;
                //Finaliza Retirar Vehiculos
            case 3:
                while(volver != 1){
                    menuAdministracion();
                    try{
                        opcion = entrada.nextInt();
                    }catch(Exception e){
                        Thread.sleep(500);
                        System.out.println("\nSolo se aceptan valores numericos.");
                        Thread.sleep(1000);
                        entrada = new Scanner(System.in);
                        }
                    
                    volver = 0;
                    
                    switch(opcion){
                        
                        case 1:
                            volver = 2;
                            while(volver != 0){
                                menuVehiculos();
                                try{
                                    opcion = entrada.nextInt();
                                }catch(Exception e){
                                    Thread.sleep(500);
                                    System.out.println("\nSolo se aceptan valores numericos.");
                                    Thread.sleep(1000);
                                    entrada = new Scanner(System.in);
                                }
                                
                                switch(opcion){
                                    
                                    case 1:
                                        while(valido != 1){
                                            try {
                                                System.out.println("\nDefina el numero de estacionamientos disponibles para: ");    
                                                System.out.print("Automoviles: ");    
                                                definir = entrada.nextInt();
                                            } catch (Exception e) {
                                                Thread.sleep(500);
                                                System.out.println("\nSolo se aceptan valores numericos.");
                                                valido = -1;
                                                Thread.sleep(1000);
                                                entrada = new Scanner(System.in);
                                            }
                                                cupoAuto = definir;
                                                valido = valido + 1;
                                                Thread.sleep(500);
                                                //Finaliza While
                                        }
                                        Thread.sleep(500);
                                        System.out.println("\nSe definio " + cupoAuto + " estacionamientos disponibles");
                                        Thread.sleep(1000);
                                        valido = 0;
                                        break;
                                        // Finaliza cupo automoviles
                                    case 2:
                                        while(valido != 1){
                                            try {
                                                System.out.println("\nDefina el numero de estacionamientos disponibles para: ");    
                                                System.out.print("Motocicletas: ");    
                                                definir = entrada.nextInt();
                                            } catch (Exception e) {
                                                Thread.sleep(500);
                                                System.out.println("\nSolo se aceptan valores numericos.");
                                                valido = -1;
                                                Thread.sleep(1000);
                                                entrada = new Scanner(System.in);
                                            }
                                                cupoMoto = definir;
                                                valido = valido + 1;
                                                Thread.sleep(500);
                                                //Finaliza While
                                        }
                                        Thread.sleep(500);
                                        System.out.println("\nSe definio " + cupoMoto + " estacionamientos disponibles");
                                        Thread.sleep(1000);
                                        valido = 0;
                                        break;
                                        // Finaliza cupo motocicletas
                                    case 3:
                                        while(valido != 1){
                                            try {
                                                System.out.println("\nDefina el numero de estacionamientos disponibles para: ");    
                                                System.out.print("Camiones: ");    
                                                definir = entrada.nextInt();
                                            } catch (Exception e) {
                                                Thread.sleep(500);
                                                System.out.println("\nSolo se aceptan valores numericos.");
                                                valido = -1;
                                                Thread.sleep(1000);
                                                entrada = new Scanner(System.in);
                                            }
                                                cupoCami = definir;
                                                valido = valido + 1;
                                                Thread.sleep(500);
                                                //Finaliza While
                                        }
                                        Thread.sleep(500);
                                        System.out.println("\nSe definio " + cupoCami + " estacionamientos disponibles");
                                        Thread.sleep(1000);
                                        valido = 0;
                                        break;
                                        // Finaliza cupo camiones
                                    case 4:
                                        // Volver menu administrativo
                                        volver = 0;
                                        break;
                                    default:
                                        Thread.sleep(500);
                                        System.out.println("\nOpcion no valida.");
                                        Thread.sleep(500);
                                        break;
                                        //Finaliza Switch Case   
                                }
                                //Finaliza While
                            }
                            break;
                            // Finaliza definir cupo
                        case 2:
                            System.out.println("\nDEFINA SU TARIFA: ");
                                System.out.println("Por defecto se calculara por segundos...");
                                while(valido != 1){
                                    try {
                                        System.out.println("Defina el tiempo por el que se cobrara: ");    
                                        System.out.print("En segundos: ");    
                                        definir = entrada.nextInt();
                                    } catch (Exception e) {
                                        Thread.sleep(500);
                                        System.out.println("\nSolo se aceptan valores numericos.");
                                        valido = -1;
                                        Thread.sleep(1000);
                                        entrada = new Scanner(System.in);
                                    }
                                        cobroX = definir;
                                        valido = valido + 1;
                                            Thread.sleep(500);
                                            //Finaliza While
                                }
                                valido = 0;
                                while(valido != 1){
                                    try {
                                        System.out.println("\nDefina el monto que se cobrara por los " + cobroX + "0 segundos: ");    
                                        System.out.print("En quetzales: ");    
                                        definir = entrada.nextInt();
                                    } catch (Exception e) {
                                        Thread.sleep(500);
                                        System.out.println("\nSolo se aceptan valores numericos.");
                                        valido = -1;
                                        Thread.sleep(1000);
                                        entrada = new Scanner(System.in);
                                    }
                                        tarifaY = definir;
                                        valido = valido + 1;
                                            Thread.sleep(500);
                                            //Finaliza While
                                }
                                Thread.sleep(500);
                                System.out.println("\nSe a definido: ");
                                System.out.println("El cobor de Q" + tarifaY + "0 por " + cobroX + "0 segundos de utilizacion del estacionamiento..");
                                Thread.sleep(1000);
                                valido = 0;
                            break;
                            // Finaliza definir tarifa
                        case 3:
                            // Volver menu principal
                            volver = 1;
                            break;
                        default:
                            Thread.sleep(500);
                            System.out.println("\nOpcion no valida.");
                            Thread.sleep(500);
                            break;
                            //Finaliza Switch Case   
                    }
                    //Finaliza While
                }
                break;
                //Finaliza Administracion
            case 4:
                //Finalizar ejecucion del programa
                finalizar = 1;
                break;
                //Se ejecutara en caso de que la opcion no sea
                //la adecuada
            default:
                Thread.sleep(500);
                System.out.println("\nOpcion no valida.");
                Thread.sleep(500);
                break;
                //Finaliza Switch Case
        }
        //Finaliza While
      }
      //Finaliza metodo principal   
    }
    
    /**
     * Metodos de munus de seleccion
     */
    
    private static void menuPrincipal() {
        System.out.println("\nADMINISTRACION DE ESTACIONAMIENTOS: ");
            System.out.println("1 -------------- Ingresar vehiculo");
            System.out.println("2 -------------- Retitar vehiculo");
            System.out.println("3 -------------- Administracion");
            System.out.println("4 -------------- Finalizar ejecucion");
            System.out.print("Ingrese su opcion: ");
    }
    
    private static void menuAdministracion() {
        System.out.println("\nOPCIONES ADMINISTRATIVAS: ");
            System.out.println("1 ------------ Definir cupo de estacionamientos");
            System.out.println("2 ------------ Definir tarifa");
            System.out.println("3 ------------ Volver a menu principal");
            System.out.print("Ingrese su opcion: ");
    }
    
    private static void menuVehiculos() {
        System.out.println("\nSELECCIONE EL VEHICULO: ");
            System.out.println("1 ------------ Automoviles");
            System.out.println("2 ------------ Motocicletas");
            System.out.println("3 ------------ Camiones");
            System.out.println("4 ------------ Salir");
            System.out.print("Ingrese su opcion: ");
    }
    
}
