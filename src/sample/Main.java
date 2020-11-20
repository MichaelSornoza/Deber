package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * DEBERA CREAR LA CARPETA DONDE DESEE GUARDAR LA DB (txt) La aplicacion omite
 * tildes a proposito para evitar inconvenientes con compatibilidad y se vuelva
 * ilegible La aplicacion utiliza el package java.io y java.util.Scanner
 *
 **/

public class Main {
    static List<Estudiante> estudianteList = new ArrayList<Estudiante>();

    static Scanner sc = new Scanner(System.in);
    static Boolean exist = false;

    static final String URL_FICHERO = "C:/datos/db.txt";

    public static void main(String[] args) {

        File fichero = new File(URL_FICHERO);
        exist = fichero.exists();

        menu();
    }

    public static void menu() {
        int seleccion = 0;

        System.out.println("Bienvenido a tu administrador de notas!!!");
        System.out.println("Selecciona lo que desees realizar");
        System.out.println(
                "1. Leer las notas registradas \n" + "2. Escribir Notas \n" + "3. Modificar Notas \n" + "4. Salir");

        seleccion = sc.nextInt();

        do {

            switch (seleccion) {
                case 1:
                    leer();
                    menu();
                    break;
                case 2:
                    String dni, nombre, apellido, nota1, nota2;

                    System.out.println("Ingrese numero de cedula del estudiante");
                    dni = sc.next();

                    sc.nextLine();
                    System.out.println("Ingrese el nombre del estudiante");
                    nombre = sc.next();

                    sc.nextLine();
                    System.out.println("Ingrese el apellido del estudiante");
                    apellido = sc.next();

                    sc.nextLine();
                    System.out.println("Ingrese la nota 1 del estudiante");
                    nota1 = sc.next();

                    sc.nextLine();
                    System.out.println("Ingrese la nota 2 del estudiante");
                    nota2 = sc.next();

                    Estudiante estudiante = new Estudiante(dni, nombre, apellido, nota1, nota2);

                    escribir(estudiante);

                    menu();
                    break;
                case 3:
                    addToList();
                    estudianteList.forEach((student) -> System.out.println(student.dni));
                    menu();
                    break;
                default:
                    System.exit(200);
            }

        } while (seleccion != 4);

    }

    public static void leer() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(URL_FICHERO);

            exist = archivo.exists();

            if (exist) {
                Boolean contenido = false;
                String linea;

                fr = new FileReader(archivo);
                br = new BufferedReader(fr);

                while ((linea = br.readLine()) != null) {
                    contenido = true;

                    String[] parts = linea.split("/");

                    for (String part : parts)
                        System.out.println(part);
                }

                if (!contenido)
                    System.out.println("No hay registros");

            } else {
                System.out.println("No hay registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void escribir(Estudiante estudiante) {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {

            fichero = new FileWriter(URL_FICHERO, exist);
            exist = true;
            pw = new PrintWriter(fichero);
            pw.write(estudiante.dni + "/" + estudiante.nombre + "/" + estudiante.apellido + "/" + estudiante.nota1 + "/"
                    + estudiante.nota2 + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public static void addToList() {

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {

            archivo = new File(URL_FICHERO);

            exist = archivo.exists();

            if (exist) {
                Boolean contenido = false;
                String linea;

                Estudiante estudiante = null;

                fr = new FileReader(archivo);
                br = new BufferedReader(fr);

                while ((linea = br.readLine()) != null) {

                    String dni, nombre, apellido, nota1, nota2;

                    contenido = true;

                    String[] parts = linea.split("/");

                    dni = parts[0];
                    nombre = parts[1];
                    apellido = parts[2];
                    nota1 = parts[3];
                    nota2 = parts[4];

                    estudianteList.add(new Estudiante(dni, nombre, apellido, nota1, nota2));
                }

                if (!contenido)
                    System.out.println("No hay registros");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class Estudiante {

        public String dni, nombre, apellido, nota1, nota2;

        public Estudiante(String dni, String nombre, String apellido, String nota1, String nota2) {
            this.dni = dni;
            this.nombre = nombre;
            this.apellido = apellido;
            this.nota1 = nota1;
            this.nota2 = nota2;
        }

    }
}
