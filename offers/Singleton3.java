package offers;

/**
 * Created by song on 17-3-18.
 */
public class Singleton3 {
    private static final class InstanceHolder {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    private static Singleton3 getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private Singleton3() {}

    public static void main(String[] args) {
        int i = 0;
        // multi threads test
        Runnable r = () -> {
            Singleton3 s = Singleton3.getInstance();
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

}
