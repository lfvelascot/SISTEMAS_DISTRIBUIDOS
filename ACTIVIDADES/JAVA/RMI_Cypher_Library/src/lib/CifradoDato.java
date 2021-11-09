package lib;

abstract class cifradoD {

    String P1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",
            P2 = "Aa0Bb1Cc2Dd3Ee4Ff5Gg6Hh7Ii8Jj9KkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    int L = P1.length();
    int X = P2.length();

    public abstract String getCifrado(String cadenac);

    public abstract String getDescifrado(String cadenac);

}

public class CifradoDato extends cifradoD {

    @Override
    public String getCifrado(String cadena) {
        String cc = "";
        int l = cadena.length();
        for (int i = 0; i < l; i++) {
            char x = cadena.charAt(i);
            cc += getCaracter(x, l, i, 'C');
        }
        return cc;
    }

    private char getCaracter(char c, int l, int p, char m) {
        if (P1.indexOf(c) == -1) {
            return c;
        } else {
            int pp = 0;
            switch (m) {
                case 'C':
                    pp = P1.indexOf(c) + l + p;
                    if (pp - L >= 0) {
                        pp -= L;
                    }
                    return P2.charAt(pp);
                case 'D':
                    pp = P2.indexOf(c) - l - p;
                    if (pp < 0) {
                        pp += L;
                    }
                    return P1.charAt(pp);
                default:
                    return c;
            }
        }
    }

    @Override
    public String getDescifrado(String cadenac) {
        String cc = "";
        for (int i = 0; i < cadenac.length(); i++) {
            cc += getCaracter(cadenac.charAt(i), cadenac.length(), i, 'D');
        }
        return cc;
    }
}
