package flower;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FloweryTrails {
    public static final int MIN_P = 2;
    public static final int MAX_P = 10000;
    public static final int MIN_T = 1;
    public static final int MAX_T = 250000;
    public static final int MIN_L = 1;
    public static final int MAX_L = 1000;

    public static void runAlgo() {
        // "Flowery Trails" - Southwestern Europe Regional Contest (SWERC) 2014
//        Scanner sc = new Scanner(System.in);
        File f = new File("D:/xiumi/Dev/Java-workspace/SWERC/src/main/resources/flowerTrails.txt");
        try (Scanner sc = new Scanner(f)) {
            int p = sc.nextInt(); // nb point of interest
            int t = sc.nextInt(); // nb trails
            if(p < MIN_P || p > MAX_P || t < MIN_T || t > MAX_T)
                throw new IllegalArgumentException("P or T is invalid !");
            // init
            Longueur[][] matAdj = new Longueur[p][p];
            for(int i = 0; i < p; i++) {
                for(int j = 0; j < p; j++) {
                    matAdj[i][j] = null;
                }
            }
            // complete matAdj
            int p1;
            int p2;
            int l;
            for(int i = 0; i < t; i++) {
                p1 = sc.nextInt();
                p2 = sc.nextInt();
                l = sc.nextInt();
                if(l < MIN_L || l > MAX_L) throw new Exception("L is invalid !");
                else if(matAdj[p1][p2] == null) {
                    Longueur length = new Longueur(l);
                    matAdj[p1][p2] = length;
                    matAdj[p2][p1] = length;
                } else if(l < matAdj[p1][p2].l) {
                    matAdj[p1][p2].l = l;
                } else if(l == matAdj[p1][p2].l) {
                    matAdj[p1][p2].nbL++;
                }
            }
            //
            showMat(matAdj); // debug
            int oneSide = bfs(matAdj, 0);
            System.out.println("One Side = " + oneSide);
            System.out.println("Both Side = " + oneSide * 2);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static int bfs(Longueur[][] matAdj, int sommet) {
        boolean[] marques = new boolean[matAdj.length];
        Longueur[] longueurChemin = new Longueur[matAdj.length];
        for(int i = 0; i < matAdj.length; i++) {
            longueurChemin[i] = new Longueur(); // longueur -1 = inconnu par defaut
            marques[i] = false;
        }
        marques[0] = true;
        longueurChemin[sommet] = new Longueur(0);
        List<Integer> fifo = new ArrayList<>();
        fifo.add(sommet);
        int first = 0;
        do {
            first = fifo.get(0);
            System.out.println(first);
            for(int j = 0; j < matAdj.length; j++) {
                System.out.println("\t j=" + j);
                if(matAdj[first][j] != null && !marques[j] && longueurChemin[j].l == Longueur.INCONNU) {
                    fifo.add(j);
                    longueurChemin[j].l = longueurChemin[first].l + matAdj[first][j].l;
                    marques[j] = true;
                    System.out.println("A");
                    System.out.println(Arrays.toString(longueurChemin));
                } else if(matAdj[first][j] != null
                        && longueurChemin[j].l != Longueur.INCONNU
                        && longueurChemin[j].l > longueurChemin[first].l + matAdj[first][j].l) {
                    longueurChemin[j].l = longueurChemin[first].l + matAdj[first][j].l;
                    System.out.println("B");
                    System.out.println(Arrays.toString(longueurChemin));
                } else if(matAdj[first][j] != null
                        && longueurChemin[j].l != Longueur.INCONNU
                        && longueurChemin[j].l == longueurChemin[first].l + matAdj[first][j].l) {
                    longueurChemin[j].nbL++;
                    System.out.println("C");
                }
            }
            fifo.remove(0);
            System.out.println(fifo.isEmpty());
        } while (first != matAdj.length - 1 && !fifo.isEmpty());
        return longueurChemin[matAdj.length - 1].getLength();
    }

    public static void showMat(Longueur[][] mat) {
        for(Longueur[] tabL : mat) {
            for(Longueur len : tabL) {
                if(len == null) System.out.print("null  ");
                else System.out.print(len.l * len.nbL + "   ");
            }
            System.out.println();
        }
    }
}

class Longueur {
    public static final int INCONNU = -1;
    int l;
    int nbL;

    // s'il y a 1 chemin avec 2 longueurs, garder le plus court,
    // si les 2 sont identiques, doublé la distance
    public Longueur() {
        this.l = INCONNU;
        this.nbL = 1;
    }

    public Longueur(int l) {
        this.l = l;
        this.nbL = 1;
    }

    @Override
    public String toString() {
        return "(" + l + "," + nbL + ")";
    }

    public int getLength() {
        return l * nbL;
    }
}
