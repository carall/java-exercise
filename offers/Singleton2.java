package offers;

/**
 * Created by song on 17-3-18.
 */
public class Singleton2 {
    private static Singleton2 singleton2 = new Singleton2();
    /*
    static {
        singleton2 = new Singleton2();
    }
    */
    public static void main(String[] args) {
        int i = 0;
        // multi threads test
        Runnable r = () -> {
            Singleton2 s = Singleton2.getInstance();
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

    private Singleton2() {}

    private static Singleton2 getInstance() {
        return singleton2;
    }
}
