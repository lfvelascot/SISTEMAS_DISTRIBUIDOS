package usbbog;

/**
 *
 * @author user
 */
public class Main {

    public static void main(String[] args) {
        AesUtil cc = new AesUtil();
        String cod = "9407";
        String coded = cc.encrypt("banco-app-2020-2", cod);
        String decode = cc.decrypt("banco-app-2020-2", coded);
        System.out.println(" - CODE = " + coded + " len = "+coded.length());
        System.out.println(" - DECODED = " + decode+ " len = "+decode.length());
    }
}
