package util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Algo {
    private Algo() {
    }

    public static List<Integer> dfs(int[][] graph, int start) {
        boolean[] visited = new boolean[graph.length];
        visited[start] = true;
        List<Integer> resList = new ArrayList<>();
        resList.add(start);
        LinkedList<Integer> lifo = new LinkedList<>();
        lifo.addLast(start);

        while(!lifo.isEmpty()) {
            int last = lifo.getLast();
            lifo.removeLast();
            for(int i = 0; i < graph.length; i++) {
                if(graph[last][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    lifo.add(i);
                    resList.add(i);
                    break;
                }
            }
        }
        return resList;
    }
}
