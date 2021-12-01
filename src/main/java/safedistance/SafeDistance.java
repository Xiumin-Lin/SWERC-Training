package safedistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SafeDistance {
    public static void main(String[] args) throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("src/main/resources/safeDistance.txt"));
        double x = sc.nextDouble();
        double y = sc.nextDouble();
        int n = sc.nextInt();
        double[][] persons = new double[n][2];
        final int ABS = 0; // abscisse
        final int ORD = 1; // ordonnee
        for(int i = 0; i < n; i++) {
            persons[i][ABS] = sc.nextDouble();
            persons[i][ORD] = sc.nextDouble();
            System.out.println(i + " : (" + persons[i][0] + "," + persons[i][1] + ")"); // DEBUG
        }

        final int P = 4;
        int[][] m = new int[P + n][P + n]; // matrice d'adjacence

        double r = 2.25; // the rayon / 2 de la largeur du rectangle
        System.out.println("r=" + r);

        updateMat(persons, r, m, x, y);

        showMat(m); // DEBUG
        sc.close();
    }

    private static void updateMat(double[][] persons, double r, int[][] m, double x, double y) {
        final int ABS = 0; // abscisse
        final int ORD = 1; // ordonnee
        int n = persons.length;
        for(int i = 0; i < n; i++) {
            for(int j = 1 + i; j < n; j++) {
                if(r >= getR(persons[i][ABS], persons[i][ORD], persons[j][ABS], persons[j][ORD])) {
                    m[i][j] = 1;
                    m[j][i] = 1;
                }
            }
            if(persons[i][ABS] - r <= 0) { // OUEST
                m[i][n] = 1;
                m[n][i] = 1;
            }
            if(persons[i][ABS] + r >= x) { // EST
                m[i][n + 2] = 3;
                m[n + 2][i] = 3;
            }
            if(persons[i][ORD] + r >= y) { // NORD
                m[i][n + 1] = 2;
                m[n + 1][i] = 2;
            }
            if(persons[i][ORD] - r <= 0) { // SUD
                m[i][n + 3] = 4;
                m[n + 3][i] = 4;
            }
        }
    }

    private static double getR(double x1, double y1, double x2, double y2) {
        return calculDist(x1, y1, x2, y2) / 2;
    }

    private static double calculDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    private static void showMat(int[][] matAdj) {
        for(int[] ints : matAdj) {
            for(int i : ints) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}
