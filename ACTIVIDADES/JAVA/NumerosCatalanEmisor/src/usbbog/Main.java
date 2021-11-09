package usbbog;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    private static void num_catalan(int x) {
        long[] num_catalan = new long[x + 1];
        int i = 1;
        num_catalan[0] = 1;
        while (x != 0) {
            num_catalan[i] = ((factorial(2 * i)) / ((factorial(i)) * (factorial(i + 1))));
            x--;
            i++;
        }
        escribir(num_catalan);
    }

    public static long factorial(long n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        num_catalan(Integer.parseInt(args[0]));
    }

    public static void escribir(long[] num_catalan) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("C:/Users/user/Documents/Catalan Memory/catalan.txt");
            pw = new PrintWriter(fichero);
            pw.println("Numeros de catalan del 0 al " + (num_catalan.length - 1));
            pw.println("   (2n)!   ");
            pw.println("Cn=--------");
            pw.println("  (n+1)!n! ");
            for (int i = 0; i < num_catalan.length; i++) {
                pw.println(" - " + i + " = " + num_catalan[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}