package network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Network {
	public static void runAlgo() {
		Scanner sc = new Scanner(System.in);
		int dataSetCpt = 1;
		while(true) {
			int cpt = 0;
			HashMap<String, Integer> registre = new HashMap<>();
			int p = sc.nextInt();
			int r = sc.nextInt();
			if(p == 0 && r == 0) { // fin du programme
				break;
			} else if(p < 2 || p > 50 || r < 1) {
				System.out.println("Invalid Number for P or R");
				break;
			}
			sc.nextLine();
			System.out.print("Network " + dataSetCpt++ + " : ");
			// cree et init matrice d'adjacence
			int[][] matAdj = new int[p][p];
			for(int i = 0; i < p; i++) {
				for(int j = 0; j < p; j++) {
					matAdj[i][j] = 0;
				}
			}
			// recup les personne et leurs relations
			String str = sc.nextLine();
			String[] names = str.split(" ");
			if(names.length % 2 != 0) {
				System.out.println("Relation incomplet !");
				break;
			}
			// enregistre les personnes (sans doublons)
			for(String n : names) {
				if(!n.trim().equals("") && !registre.containsKey(n)) {
					registre.put(n.trim(), cpt);
					cpt++;
				}
			}
			// check que nb personne enregistré == nb personne indiqué par p
			if(cpt != p) {
				System.out.println("Error nb personne");
				break;
			}

			for(int i = 0; i < names.length; i += 2) {
				int a = registre.get(names[i]);
				int b = registre.get(names[i + 1]);
				matAdj[a][b] = 1;
				matAdj[b][a] = 1;
			}

			int maxDistance = 0;
			boolean isDeco = false;
			for(int s : registre.values()) {
				int d = getDistance(matAdj, s);
				if(d < 0) {
					isDeco = true;
					break;
				}
				maxDistance = Math.max(d, maxDistance);
			}
			if(isDeco) System.out.println("DISCONNECTED\n");
			else System.out.println(maxDistance + "\n");
		}
		sc.close();
	}

	public static int getDistance(int[][] matAdj, int sommet) {
		// cree & init une liste de sommet à 0
		int[] marques = new int[matAdj.length];
		int[] longueurChemin = new int[matAdj.length];
		marques[sommet] = 1;
		longueurChemin[sommet] = 0;
		List<Integer> fifo = new ArrayList<>();
		fifo.add(sommet);

		do {
			int first = fifo.get(0);
			for(int i = 0; i < matAdj.length; i++) {
				if(matAdj[first][i] == 1 && marques[i] != 1) {
					marques[i] = 1;
					fifo.add(i);
					longueurChemin[i] = longueurChemin[first] + 1;
				}
			}
			fifo.remove(0);
		} while(!fifo.isEmpty());
		for(int i : marques) if(i == 0) return -1;
		return longueurChemin[longueurChemin.length - 1];
	}

	public static void showMatrice(int[][] mat) { // DEBUG
		for(int[] ints : mat) {
			for(int j = 0; j < mat.length; j++) {
				System.out.print(ints[j]);
			}
			System.out.println();
		}
	}
}
