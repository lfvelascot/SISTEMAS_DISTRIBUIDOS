
package pack;

import java.rmi.*;

public class AdditionClient {

    public static void main(String[] args) {
        
        AdditionInterface hello;
        try {
            System.setSecurityManager(new SecurityManager());
            hello = (AdditionInterface) Naming.lookup("rmi://localhost:7777/ABC");
            int result = hello.add(9, 10);
            System.out.println("Result is :" + result);

        } catch (Exception e) {
            System.out.println("HelloClient exception: " + e);
        }
    }
}
