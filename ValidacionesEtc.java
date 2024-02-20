/*
 * Validaciones y otros procesos....
 */
package EstacionamientoAdmi;

/**
 *
 * @author pedro
 */

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ValidacionesEtc {
    
    //Variables para validaciones de caracteres
    int enviaValido;
    int enviaInfo;
    int longuitudX;
    int i;
    char caracterX;
    char evaluaX;
    
    protected double tiempoUtilizado;
    protected double totalCancelar;
    protected double periodosT;
    protected double totalDescuento;
    protected double extra;
    
    String unir = "";
    String datos;
    String contenidoX[] = {"","","","","",""};
    
    /**
     * Evalua un caracter el cual solo debe poseer numeros
     * @param evalua 
     */
    
    public void Numeros(char evalua){
        
        if(Character.isDigit(evalua) == true){                     // Verificamos que
                                                                   // el dato sea numerico   
            int numEvalua =  Character.getNumericValue (evalua);   // Pasamos el dato
                                                                   // char a un int para
        if(numEvalua > -1 && numEvalua < 10){                      // validar en un rango
                                                                   // del 0 al 9
             enviaValido = 1;       // Se sumara puntos para salir   
             enviaInfo = 0;                       // del while por cada validacion
                                                  // que pase 
        }else{                                   
                                                
             System.out.println("\nFormato no valido: ha ingresado numeros negativos");
             enviaValido = 0;
             enviaInfo = 1;
                                                
        }
             
        }else{
                                                
             System.out.println("\nFormato no valido: ha ingresado datos alfabeticos en la parte numerica");
             enviaValido = 0;
             enviaInfo = 1;
                                                
        }
    }
    
    /**
     * Caracter a evaluar que solo contenga Letras
     * @param evalua 
     */
    
    public void Alfabeticos(char evalua){
        
        if(Character.isDigit(evalua) == false){                                       // Verificamos que
                                                                                      // el dato no sea numerico   
        if(evalua == 'B' || evalua == 'C' || evalua == 'D' || evalua == 'F' ||
           evalua == 'G' || evalua == 'H' || evalua == 'J' || evalua == 'K' ||    
           evalua == 'L' || evalua == 'M' || evalua == 'N' || evalua == 'P' ||     
           evalua == 'Q' || evalua == 'R' || evalua == 'S' || evalua == 'T' ||
           evalua == 'V' || evalua == 'W' || evalua == 'X' || evalua == 'Y' ||
           evalua == 'Z'){                                                               // Validar una serie de
                                                                                         // letras mayusculas
                 enviaValido = 1;                                                        // sin vocales
                 enviaInfo = 0;
                                                
        }else{                                   
                                                
                 System.out.println("\nFormato no valido: ha ingrsado vocales o minusculas");
                 enviaValido = 0;
                 enviaInfo = 1;
                                                
        }
                                                
        }else{
                                                
                 System.out.println("\nFormato no valido: ha ingresado numeros en la parte alfabetica");
                 enviaValido = 0;
                 enviaInfo = 1;
                                                
        }  
    }
    
    /**
     * Evalua una cadena en un formato especifico del NIT
     * @param cadena 
     */
    
    public void ValidarNit(String cadena){
        enviaValido = 0;
        char []contar = cadena.toCharArray(); // Obtener la longuitud de la cadena
                                
        longuitudX = cadena.length() - 1;
        if(longuitudX == 8){
            for(int num = 0; num <= longuitudX; num++){
                caracterX = contar[num];
                switch(num){
                    
                    case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 8:
                        if(Character.isDigit(caracterX) == true){
                            enviaValido = enviaValido + 1;
                        }else{
                            enviaValido = 0;
                        }
                        break;
                    case 7:
                        if(caracterX == '-'){
                            enviaValido = enviaValido + 1;
                        }else{
                            if(Character.isDigit(caracterX) == true){
                                enviaValido = enviaValido + 1;
                            }else{
                                enviaValido = 0;
                            }
                        }
                        break;    
                }
            }
        }else{
            System.out.println("El nit ingresado no cumple con los digitos necesarios");
            enviaValido = 0;
        }if(enviaValido != 9){
            System.out.println("\nHa ingresado caracteres no valido...");
        }
    }
    
    /**
     * Genera la factura en pdf con los datos que le pasamos es un formato especifico...
     * @param x0 = NIT
     * @param x1 = Periodos de tiempo consumidos
     * @param x2 = Tarifa
     * @param x3 = Descuento
     * @param x4 = Recargos
     * @param x5 = Total 
     */
    
    public void GenerarFactura(String x0, String x1, String x2, String x3, String x4, String x5){
        try{
            
        int contadorX = 0;
        String escribe;
            
            PDDocument nuevoPDF = new PDDocument();
            PDPage pagina = new PDPage(PDRectangle.A6);
            nuevoPDF.addPage(pagina);
            PDPageContentStream contenido = new PDPageContentStream(nuevoPDF,pagina);
            
            contenido.beginText();
            contenido.setFont(PDType1Font.TIMES_BOLD,14);
            contenido.newLineAtOffset(10, pagina.getMediaBox().getHeight()-22);
            
            contenido.showText("             Factura");
            contenido.endText();
            
            contenido.beginText();
            contenido.setFont(PDType1Font.TIMES_ROMAN,12);
            contenido.newLineAtOffset(10, pagina.getMediaBox().getHeight()-42);
            
            contenido.showText("Por servicio de estacionamiento: ");
            contenido.endText();
            
            while(contadorX != 6){
                
                contenidoX[0] = x0;
                contenidoX[1] = x1;
                contenidoX[2] = x2;
                contenidoX[3] = x3;
                contenidoX[4] = x4;
                contenidoX[5] = x5;
                
                escribe = contenidoX[contadorX];
                contenido.beginText();
                contenido.setFont(PDType1Font.TIMES_ROMAN,12);
                contenido.newLineAtOffset(10, pagina.getMediaBox().getHeight() - (60*(contadorX + 1)));
            
                contenido.showText(escribe);
                contenido.endText();
                contadorX++;
            }
            
            contenido.close();
            
            nuevoPDF.save("C:\\Users\\pedro\\Documents\\NetBeansProjects\\Vehiculos_AdministraEsta\\Factura.pdf");
        }catch(Exception e){
            System.out.println("Error: Algo inesperado a sucedido......");
        }
    }
    
    /**
     * Genera un ticket en pdf con un formato especifico con los parametrosd que le pasamos..
     * @param x0 = Numero de estacionamiento
     * @param x1 = Placa
     * @param x2 = Fecha
     * @param x3 = HoraIngreso
     * @param x4 = Tarifa 
     */
    
    public void GenerarTicket(String x0, String x1, String x2, String x3, String x4){
        try{
            
        int contadorX = 0;
        String escribe;
        
            PDDocument nuevoPDF = new PDDocument();
            PDPage pagina = new PDPage(PDRectangle.A6);
            nuevoPDF.addPage(pagina);
            PDPageContentStream contenido = new PDPageContentStream(nuevoPDF,pagina);
            
            contenido.beginText();
            contenido.setFont(PDType1Font.TIMES_BOLD,14);
            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight()-52);
            
            contenido.showText("             Ticket");
            contenido.endText();
            
            while(contadorX != 5){
                
                contenidoX[0] = x0;
                contenidoX[1] = x1;
                contenidoX[2] = x2;
                contenidoX[3] = x3;
                contenidoX[4] = x4;
                
                escribe = contenidoX[contadorX];
                contenido.beginText();
                contenido.setFont(PDType1Font.TIMES_ROMAN,12);
                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (70*(contadorX + 1)));
            
                contenido.showText(escribe);
                contenido.endText();
                contadorX++;
            }
            
            contenido.close();
            
            nuevoPDF.save("C:\\Users\\pedro\\Documents\\NetBeansProjects\\Vehiculos_AdministraEsta\\Ticket.pdf");
        }catch(Exception e){
            System.out.println("Error: Algo inesperado a sucedido......");
        }
    }
    
    /**
     * Calculara el tiempo utilizado por el usuario...
     * @param tiempo
     * @param hora
     * @param horaEgreso 
     */
    
    public void  CalcularTiempo(double tiempo, String hora, String horaEgreso) {
        double horaUtilizada;
        int longuitud;
        char digito;
        String concatenar = ""; 
        
        char posicionX[] = hora.toCharArray();
                                
        longuitud = hora.length() - 1;
        
        for(i = 0; i <= longuitud; i ++){
        
        digito =  posicionX[i];
        
        if(Character.isDigit(digito) == true){ 
            concatenar = concatenar + String.valueOf(posicionX[i]);
        }else{
            concatenar = concatenar + ".";
        }
      }
        
        double valorX = Double.parseDouble(concatenar);
        
        int valor01 = (int) (valorX * tiempo);
        
        //Segunda Parte
        
        concatenar = ""; 
        
        char posicionY[] = horaEgreso.toCharArray();
                                
        longuitud = horaEgreso.length() - 1;
        
        for(i = 0; i <= longuitud; i ++){
        
        digito =  posicionY[i];
        
        if(Character.isDigit(digito) == true){ 
            concatenar = concatenar + String.valueOf(posicionY[i]);
        }else{
            concatenar = concatenar + ".";
        }
      }
        
        double valorY = Double.parseDouble(concatenar);
        
        int valor02 = (int) (valorY * tiempo);
        
        horaUtilizada = (double) (valor02 - valor01);
        
        tiempoUtilizado = horaUtilizada;
        
    }
    
    /**
     * Calculara el pago junto con el tiempo segun sea el caso...
     * @param descuento
     * @param recargo 
     */
    
    public void CalcularPago(double descuento, int recargo){
        
        if((descuento == 0)&&(recargo == 0)){
            periodosT = tiempoUtilizado / Principal.cobroX;
            totalCancelar = periodosT * Principal.tarifaY;
            System.out.println("\nTotal a cancelar: Q"+totalCancelar+"0 'No aplican descuentos'");
        }else{
            if(recargo == 0){
                periodosT = tiempoUtilizado / Principal.cobroX;
                totalCancelar = periodosT * Principal.tarifaY;
                
                totalDescuento = totalCancelar * descuento;
                totalCancelar = totalCancelar - totalDescuento;
                System.out.println("\nTotal a cancelar: Q"+totalCancelar+"0 'Aplica 10% de descuento'");
            }else{
                extra = ((tiempoUtilizado / Principal.cobroX) - 1) * recargo;
                
                periodosT = tiempoUtilizado / Principal.cobroX;
                totalCancelar = (periodosT * Principal.tarifaY) + extra;
                
                System.out.println("\nTotal a cancelar: Q"+totalCancelar+"0 'Recargo de Q5.00 por periodo'");
            }
        }
    }
    
}
