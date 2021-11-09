package usbbog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {

    static String path = "C:/Users/user/Documents/Catalan Memory/catalan.txt";
    static String backup = "C:/Users/user/Documents/Catalan Memory/backup.txt";
    static String exe = "java -jar C:/Users/user/Documents/NetBeansProjects/NumerosCatalanEmisor/dist/NumerosCatalanEmisor.jar ";

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int opc = -1;
        do {
            System.out.println("------------N U M E R O S -------------------\n"
                    + "--------D E  C A T A L A N -------------\n"
                    + " 1. Crear numeros de catalan\n"
                    + " 2. Leer memoria de respaldo\n"
                    + " 3. Eliminar contenido de la memoria de respaldo\n"
                    + " 0. Finalizar programa");
            opc = s.nextInt();
            switch (opc) {
                case 1:
                    System.out.println("Ingrese la cantidad de numeros de catalan que requiere (max. 11)");
                    crearNumeros(s.nextInt());
                    realizarResplado();
                    esperar(1);
                    limpiarMemoria(path);
                    esperar(1);
                    break;
                case 2:
                    System.out.println("--------------MEMORIA DE RESPALDO---------------");
                    leerArchivo(backup);
                    break;
                case 3:
                    System.out.println("--------------LIMPIEZA DE RESPALDO---------------");
                    limpiarMemoria(backup);
                    esperar(1);
                    System.out.println("--------------FINALIZADA---------------");
                    break;
                case 0:
                    limpiarMemoria(path);
                    limpiarMemoria(backup);
                    break;
                default:
                    break;
            }
        } while (opc != 0);

    }

    private static void leerArchivo(String archivo) throws IOException {
        File fichero = new File(archivo).getAbsoluteFile();
        Scanner s = null;
        try {
            s = new Scanner(fichero);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                System.out.println(linea);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Mensaje: " + ex.getMessage());
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } catch (Exception ex2) {
                System.out.println("Mensaje 2: " + ex2.getMessage());
            }
        }
    }

    private static void limpiarMemoria(String archivo) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("vacio");
        bw.close();
    }

    private static void realizarResplado() throws FileNotFoundException, IOException {
        File origen = new File(path);
        File destino = new File(backup);
        OutputStream out;
        try (InputStream in = new FileInputStream(origen)) {
            out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
        out.close();
    }

    private static void crearNumeros(int x) throws IOException {
        try {
            String p = exe + x;
            Runtime.getRuntime().exec(p);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        esperar(1);
        leerArchivo(path);
    }

    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
