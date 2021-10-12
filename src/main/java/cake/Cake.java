package cake;

import java.util.Scanner;

public class Cake {
    private static final int SIZE_MAX = 10000;
    private static final int SHATTERED_PIECES_MAX = 5000000;

    private Cake() {}

    /**
     * 13287 "Shattered Cake"
     * @throws Exception if invalid size or number of shattered pieces input
     */
    public static void runAlgo() throws Exception {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt(); // the width of the cake
        assertCakeSizeLimit(w);
        int n = sc.nextInt(); // the number of shattered pieces
        if(!(n > 0 && n <= SHATTERED_PIECES_MAX)) throw new Exception("Invalid number of shattered pieces !");

        int totalArea = 0;
        for(int i = 0; i < n; i++) {
            int wi = sc.nextInt(); // width of the piece i
            int li = sc.nextInt(); // length (or height) of the piece i
            assertCakeSizeLimit(wi);
            assertCakeSizeLimit(li);
            totalArea += wi * li;
//            System.out.println("totalArea = " + totalArea); // DEBUG
        }

        System.out.println(totalArea / w); // the length of the cake
    }

    public static void assertCakeSizeLimit(int size) throws Exception {
        if(!(size > 0 && size <= SIZE_MAX)) throw new Exception("Invalid Size !");
    }
}
