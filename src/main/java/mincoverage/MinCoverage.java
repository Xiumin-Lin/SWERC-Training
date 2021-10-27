package mincoverage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MinCoverage {

	public static void runAlgo() {
		Scanner sc = new Scanner(System.in);
		int nbTest = sc.nextInt();
		sc.nextLine();

		for(int i = 0; i < nbTest; i++) {
			runTest(sc);
		}
	}

	private static void runTest(Scanner sc) {
		int m = sc.nextInt();
		int r = 0;
		int l = 0;
		ArrayList<Tuple> tab = new ArrayList<>();
		do {
			l = sc.nextInt();
			r = sc.nextInt();
			if(l == 0 && r == 0) break;
			else if(r > 0 && l < m)
				tab.add(new Tuple(l, r));
		} while(true);
		sc.nextLine();
		tab.sort(Comparator.comparing(Tuple::getStart));

		// Start algo
		// method 1 smart but not always the minCoverage
		/*List<Tuple> res = new ArrayList<>();
		f(0, m, tab, res);
		System.out.println(res.size()); // nb segment
		for(Tuple t : res) {
			System.out.println(t);
		}*/

		// method 2 less smart (glouton) but give a minimal Coverage
		List<Tuple> res = f2(0, m, tab);
		if(res != null) {
			System.out.println(res.size()); // nb segment
			for(Tuple t : res) {
				System.out.println(t);
			}
		} else System.out.println(0);

		System.out.println();
	}

	private static boolean f(int start, int m, List<Tuple> tab, List<Tuple> res) {
		if(start >= m) return true;
		for(int i = 0; i < tab.size(); i++) {
			if(tab.get(i).getEnd() > start && tab.get(i).getStart() <= start &&
					f(tab.get(i).getEnd(), m, tab.subList(i, tab.size()), res)) {
				res.add(0, tab.get(i));
				return true;
			}
		}
		return false;
	}

	private static List<Tuple> f2(int start, int m, List<Tuple> tab) {
		if(start >= m) return new ArrayList<>();
		List<List<Tuple>> allRes = new ArrayList<>();
		for(int i = 0; i < tab.size(); i++) {
			if(tab.get(i).getEnd() > start && tab.get(i).getStart() <= start) {
				List<Tuple> res = f2(tab.get(i).getEnd(), m, tab.subList(i, tab.size()));
				if(res != null) {
					res.add(0, tab.get(i));
					allRes.add(res);
				}
			}
		}
		if(allRes.size() == 1) return allRes.get(0);
		else if(allRes.size() >= 2) {
			List<Tuple> result = allRes.get(0);
			for(List<Tuple> l : allRes.subList(1, allRes.size())) {
				if(l.size() < result.size()) result = l;
			}
			return result;
		}
		return null;
	}

}

class Tuple {
	private int start;
	private int end;

	public Tuple(int l, int r) {
		this.start = l;
		this.end = r;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return start + " " + end;
	}
}
