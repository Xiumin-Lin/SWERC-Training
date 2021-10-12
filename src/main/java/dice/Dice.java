package dice;

import java.util.Scanner;

public class Dice {
    private static final int FACE_MIN = 4;
    private static final int FACE_MAX = 20;

    private Dice() {}

    /**
     * <b>"Dice Cup"</b><br>
     * Displays the most probable sum of two dice whose number of faces is to be defined.
     * If there are several sums of the same probability, they are displayed in increasing order.
     * @throws Exception if invalid input for nb of faces
     */
    public static void runAlgo() throws Exception {
        Scanner sc = new Scanner(System.in);
        int nbFace1 = sc.nextInt();
        int nbFace2 = sc.nextInt();
        assertFaceLimit(nbFace1);
        assertFaceLimit(nbFace2);
        int smallerFace = Math.min(nbFace1, nbFace2);
        for(int i = 0; i <= Math.abs(nbFace1 - nbFace2); i++) {
            System.out.println(smallerFace + 1 + i);
        }
    }

    public static void assertFaceLimit(int nbFace) throws Exception {
        if(!(nbFace >= FACE_MIN && nbFace <= FACE_MAX)) throw new Exception("Invalid face number !");
    }
}
