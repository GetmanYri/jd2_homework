package getman.homework.data.util;

import java.util.Random;

public class Scanner {

    public static int scannerInt() {
        java.util.Scanner util = new java.util.Scanner(System.in);
        return util.nextInt();
    }

    public static long scannerLong() {
        java.util.Scanner util = new java.util.Scanner(System.in);
        return util.nextLong();
    }

    public static double scannerDouble() {
        java.util.Scanner util = new java.util.Scanner(System.in);
        return util.nextDouble();
    }

    public static String scannerString() {
        java.util.Scanner util = new java.util.Scanner(System.in);
        return util.nextLine();
    }

    public static String randomString() {
        Random r = new Random();
        return r.ints(97, 123)
                .limit(7)
                .mapToObj(c -> (char) c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();

    }
}


