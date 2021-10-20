package mincoverage;

import java.util.ArrayList;
import java.util.Scanner;

public class MinCoverage {
	public static void runAlgo() {
		Scanner sc = new Scanner(System.in);
		int nbTest = sc.nextInt();
		sc.nextLine();
		int m = sc.nextInt();
		int r = 0;
		int l = 0;
		ArrayList<Tuple> tab = new ArrayList<>();
		do {
			l = sc.nextInt();
			r = sc.nextInt();
			System.out.println("l=" + l + " r=" + r);
			if(l == 0 && r == 0) break;
			else if(l < m && r > 0)
				tab.add(new Tuple(l, r));
		} while(true);
	}
}

class Tuple {
	private int l;
	private int r;

	public Tuple(int l, int r) {
		this.l = l;
		this.r = r;
	}
}
