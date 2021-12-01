package visibility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Visibility {
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/main/resources/visibility.txt");
        Scanner sc = new Scanner(f);
        int n = sc.nextInt();
        double[][] coord = new double[n][2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 2; j++) {
                coord[i][j] = sc.nextDouble();
            }
        }
        sc.close();

        System.out.println(Arrays.deepToString(coord)); // debug

        final int X = 0;
        final int Y = 1;
        int cpt = 0;
        for(double[] ptA : coord) {
            System.out.println(ptA[X] + " " + ptA[Y]); // debug
            for(double[] ptB : coord) {
                if((ptA[X] == ptB[X] && ptA[Y] == ptB[Y]) || ptA[Y] <= ptB[Y]) continue;

                System.out.print("\t-> " + ptB[X] + " " + ptB[Y]); // debug
                double coteAdj = Math.abs(ptB[Y] - ptA[Y]);
                double coteOpp = Math.abs(ptB[X] - ptA[X]);
                if(coteAdj != 0 && isBelow30Degrees(coteAdj, coteOpp)) {
                    System.out.print(" -> OK"); //debug
                    cpt++;
                }
                System.out.println(); // debug
            }
        }
        System.out.println(cpt);
    }

    private static final double DEGREE = 60;
    private static final double TAN_DEGREE = Math.tan(Math.toRadians(DEGREE));

    private static boolean isBelow30Degrees(double cAdj, double cOpp) {
        System.out.print(" : " + Math.abs(cOpp / cAdj) + " < " + TAN_DEGREE);
        return Math.abs(cOpp / cAdj) < TAN_DEGREE;
    }
}
