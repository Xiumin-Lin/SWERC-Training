package birdwatching;

import java.util.*;

public class BirdWatching {
    private static int nbNode;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        nbNode = sc.nextInt();
        int nbEdge = sc.nextInt();
        int tRef = sc.nextInt();
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for(int i = 0; i < nbEdge; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(graph.containsKey(x)) graph.get(x).add(y);
            else {
                ArrayList<Integer> tmpList = new ArrayList<>();
                tmpList.add(y);
                graph.put(x, tmpList);
            }
        }
        List<Integer> list = new ArrayList<>();

        for(Map.Entry<Integer, ArrayList<Integer>> key : graph.entrySet()) {
            if(key.getValue().contains(tRef) && !hasPathToRef(key.getKey(), tRef, graph)) {
                list.add(key.getKey());
            }
        }
        System.out.println(list.size());
        for(int i : list) {
            System.out.println(i);
        }
        sc.close();
    }

    private static boolean hasPathToRef(int treeNum, int tRef, HashMap<Integer, ArrayList<Integer>> graph) {
        boolean result = false;
        graph.get(treeNum).remove(Integer.valueOf(tRef));
        if(dfsModified(graph, treeNum, tRef)) result = true;
        graph.get(treeNum).add(tRef);
        return result;
    }

    public static boolean dfsModified(Map<Integer, ArrayList<Integer>> graph, int startRef, int tRef) {
        ArrayList<Integer> visited = new ArrayList<>();
        visited.add(startRef);
        ArrayList<Integer> lifo = new ArrayList<>();
        lifo.add(startRef);

        while(!lifo.isEmpty()) {
            int last = lifo.get(0);
            boolean hasNeighbour = false;
            for(int neighbour : graph.get(last)) {
                if(!visited.contains(neighbour)) {
                    if(neighbour == tRef) return true;

                    visited.add(neighbour);
                    lifo.add(0, neighbour);
                    hasNeighbour = true;
                    break;
                }
            }
            if(!hasNeighbour) lifo.remove(0);
        }
        return false;
    }
}
