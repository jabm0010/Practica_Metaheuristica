/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class main {

    public static String DIRECTORIO;
    public static String TRABAJO;
    public static Integer LINEAS;
    public static Random NUMERO;

    //Variables para el menu
    static Scanner scanner = new Scanner(System.in);
    static int select = -1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        NUMERO = new Random();
        NUMERO.setSeed(77646082);

        TRABAJO = System.getProperty("user.dir");

        System.out.println("Conjunto de archivos que quiere usar: ");
        Scanner reader = new Scanner(System.in);
        DIRECTORIO = reader.next();
        LINEAS = countLines(DIRECTORIO) + 1;

        rangoFrec frecuencias = new rangoFrec();
        listaTransmisores transmisores = new listaTransmisores();
        Restricciones rest = new Restricciones();

        float startTime;
        float endTime;
        float duration;
        while (select != 0) {

//            try {
            System.out.print("Elige opción:\n1.- Greedy"
                    + "\n2.- Búsqueda Local\n"
                    + "3.- Búsqueda Tabúr\n"
                    + "4.- GRASP\n"
                    + "5.- Cambiar directorio\n"
                    + "6.- Cambiar semilla\n"
                    + "0.- Salir"
                    + "\n: ");

            select = Integer.parseInt(scanner.nextLine());

            switch (select) {
                case 1:
                    Greedy greedy = new Greedy(transmisores, frecuencias, rest);
                    greedy.resultados();
                    break;
                case 2:
                    startTime = System.nanoTime();
                    BusquedaLocal busquedaLocal = new BusquedaLocal(transmisores, frecuencias, rest);
                    busquedaLocal.algoritmo();
                    endTime = System.nanoTime();
                    busquedaLocal.resultados();

                    duration = (endTime - startTime) / 1000000000;
                    System.out.println("Tiempo de ejecucion: " + duration + " segundos");
                    break;
                case 3:
                    startTime = System.nanoTime();
                    BusquedaTabu busquedaTabu = new BusquedaTabu(transmisores, frecuencias, rest);
                    busquedaTabu.algoritmo();
                    endTime = System.nanoTime();

                    busquedaTabu.resultados();
                    duration = (endTime - startTime) / 1000000000;
                    System.out.println("Tiempo de ejecucion: " + duration + " segundos");
                    break;
                case 4:
                    startTime = System.nanoTime();
                    Grasp grasp = new Grasp(transmisores, frecuencias, rest);
                    grasp.algoritmo();
                    endTime = System.nanoTime();

                    grasp.resultados();
                    duration = (endTime - startTime) / 1000000000;
                    System.out.println("Tiempo de ejecucion: " + duration + " segundos");
                    break;
                case 5:
                    System.out.println("Conjunto de archivos que quiere usar: ");

                    DIRECTORIO = reader.next();
                    LINEAS = countLines(DIRECTORIO) + 1;

                    frecuencias = new rangoFrec();
                    transmisores = new listaTransmisores();
                    rest = new Restricciones();
                    break;
                case 6:
                    System.out.println("Cambiar semilla: ");
                    int nuevaSemilla = reader.nextInt();
                    NUMERO = new Random();
                    NUMERO.setSeed(nuevaSemilla);
                    System.out.println(nuevaSemilla);
                case 0:
                    System.out.println("Fin");
                    break;
                default:
                    System.out.println("Número no reconocido");
                    break;
            }

            System.out.println("\n"); //Mostrar un salto de línea en Java

//            } catch (Exception e) {
//                System.out.println("Uoop! Error! " + e.toString());
//            }
        }
    }

    public static int countLines(String filename) throws IOException {
        String archivo = "var.txt";
        if (filename.matches("scen.*")) {
            archivo = archivo.toUpperCase();
        }
        File file = new File(main.TRABAJO + "/conjuntos/" + main.DIRECTORIO + "/" + archivo);
        Scanner lineas = new Scanner(file);
        int ultimoTransmisor = 0;
        while (lineas.hasNextLine()) {
            ultimoTransmisor = lineas.nextInt();
            String ultimaLinea = lineas.nextLine();
        }
        return ultimoTransmisor;
    }

}
