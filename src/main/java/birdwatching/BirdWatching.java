package birdwatching;

import util.Algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BirdWatching {
    private static final int TRUE = 1;
    private static final int FALSE = 0;

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("src/main/resources/birdWatching.txt");
        Scanner sc = new Scanner(f);
        int nbNode = sc.nextInt();
        int nbEdge = sc.nextInt();
        int tRef = sc.nextInt();
        int[][] graph = new int[nbNode][nbNode];
        for(int i = 0; i < nbEdge; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x][y] = TRUE;
        }
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < nbNode - 1; i++) {
            if(graph[i][tRef] == TRUE && !hasPathToRef(i, tRef, graph)) {
                list.add(i);
            }
        }
        System.out.println(list.size());
        for(int i : list) {
            System.out.println(i);
        }
        sc.close();
    }

    private static boolean hasPathToRef(int treeNum, int tRef, int[][] graph) {
        boolean result = false;
        graph[treeNum][tRef] = FALSE;
        List<Integer> dfs = Algo.dfs(graph, treeNum);
        if(dfs.contains(tRef)) {
            result = true;
        }
        graph[treeNum][tRef] = TRUE;
        return result;
    }
}
