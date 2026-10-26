/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto_hawkins;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author reigv
 */
public class PROYECTO_HAWKINS {

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        final float MAX_ENERGIA = 200.0f;
        final float LIMITE_RIESGO = 150.0f;
        String operadorActual = "";
        double sensorMedido = 0;
        int codigoRiesgo = 0;
        float porcentajeEstado = 0f;
        boolean portalAbierto = false;
        int opcionMenu=1;
        System.out.print("Usuario: ");
        operadorActual = scr.nextLine();
        //scr.nextLine();
        System.out.println("Bienvenido " + operadorActual);
        menu(scr,opcionMenu);
        System.out.print("Introduzca la opción deseada: ");
        opcionMenu = scr.nextInt();
        while(opcionMenu != 0){

            switch (opcionMenu) {
                case 1:
                    //sensor medido tendrá el valor que se introduzca
                    sensorMedido = leerLecturaSegura(scr, "Introduce lectura µT: ");
                    //con ese valor de sensor medido se evalua el riesgo y se almacena en codigoRiesgo
                    codigoRiesgo = evaluarNivelRiesgo(sensorMedido);
                    //porcentaje               
                    porcentajeEstado = calcularPorcentajeTruncado(sensorMedido, MAX_ENERGIA);
                    //Creo que esto es básicamente para saber si el portal esta abierto
                    portalAbierto = ejecutarProtocoloAccion(codigoRiesgo);
                    System.out.println("---------------------------");
                    System.out.print("\nDatos del sensor: " + sensorMedido +"\nNivel de Riesgo: " + codigoRiesgo +"\nPorcentaje Actual:" + porcentajeEstado + "\nPortal Abierto: " + portalAbierto + "\n");
                    System.out.println("---------------------------");
                    break;
                case 2:
                    break;
                case 0:
                    break;
                default:
                    break;
            }
            menu(scr,opcionMenu);
            System.out.print("Introduzca la opción deseada: ");
            opcionMenu = scr.nextInt();
        }
    }
    public static void menu(Scanner scr, int opcionMenu)
{
    System.out.print("--PROYECTO--HAWKINGS--\n------------------------\n1. Inspeccionar Sensor Individual\n------------------------\n2. Iniciar Muestreo Continuo\n------------------------\n0. Salir de la consola\n-------------------------------\n");
    //puesto en un mismo print por petición del ejercicio
    
}



//Sí, efectivamente, es una comprobación que devuelve aquello introducido por el scanner
public static Double leerLecturaSegura(Scanner scr, String msg){
    double lcr = 0;
    try{
        System.out.print(msg);
        lcr = scr.nextDouble();
    } catch (InputMismatchException e){
        System.out.println("Error: Unidad no válida, por favor introduzca un número");
    }
    return lcr;
}




//que hará este proceso?
public static int evaluarNivelRiesgo(double microteslas){
    //Lo del texto con ternario es un poco ida de olla, pero bueno
    String riesgo = microteslas <= 50.0 ? "Seguro":
                    microteslas > 150.0 ? "Crítico":
                   "Inestable";
    //Para aclarar un poco, primero la condición, el valor que obtiene si cumple la condición y ya lo demás son las condiciones
    
    
    int nv_riesgo = 0;
    if(microteslas <=50){
        nv_riesgo = 0;
    }
    else if(microteslas > 50.0 && microteslas<150.0){
        nv_riesgo =1;
    }
    else if(microteslas > 150.0){
        nv_riesgo = 2;
    }
    return nv_riesgo;
}
public static int calcularPorcentajeTruncado(double lct,double mx_eng){
    double porcentaje = ((lct/mx_eng)*100);
    int tr_porcentaje = (int) porcentaje;
    return tr_porcentaje;
}

public static boolean ejecutarProtocoloAccion(int nv){
    switch (nv) {
        case 0:
            System.out.println("Modo estable");
            return false;
        case 1:
            System.out.println("Anomalía detectada");
            return false;
        case 2:
            System.out.println("Alerta máxima");
            return true;
        default:
            System.out.println("Código no válido");
            return false;
    }
}

}
