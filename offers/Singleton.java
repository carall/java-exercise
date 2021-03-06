package offers;

/**
 * Created by song on 17-3-17.
 */
public class Singleton {
    private static Singleton singleton = null;

    public static void main(String[] args) {
        int i = 0;
        // multi threads test
        Runnable r = () -> {
            Singleton s = Singleton.getInstance();
            String temp = "null";
            if (s != null) {
                temp = s.toString();
            }
            System.out.println(temp + "@" + Thread.currentThread().toString());
        };
        while (i++ < 10) {
            new Thread(r).start();
        }
    }

    //getInstance() return the only one instance.
    private static synchronized Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    private Singleton() {
    }
}
