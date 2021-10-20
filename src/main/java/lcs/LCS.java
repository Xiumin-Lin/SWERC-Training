package lcs;

import java.util.Scanner;

public class LCS {
	public static void runAlgo() {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();

		System.out.println(f(a, b));
	}

	public static int f(String a, String b) {
		if(a.length() == 0 || b.length() == 0) return 0;
		char lastCharA = a.charAt(a.length() - 1);
		char lastCharB = b.charAt(b.length() - 1);
		String subA = a.substring(0, a.length() - 1);
		String subB = b.substring(0, b.length() - 1);
		if(lastCharA == lastCharB) {
			return Math.max(Math.max(f(subA, subB) + 1, f(subA, b)), f(a, subB));
		}
		return Math.max(f(a, subB), f(subA, b));
	}


}
