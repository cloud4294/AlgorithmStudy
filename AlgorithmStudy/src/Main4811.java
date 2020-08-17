import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 4811번 - 알약
 * 
 * 		https://www.acmicpc.net/problem/4811
 */

public class Main4811 {
	
	static long[][] drug;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		drug = new long[31][61];
		
		for (int i = 1; i <= 30; i++) {
			buildDrug(i,1);
		}

		
		while(true) {
			
			int n = sc.nextInt();
			
			if(n == 0)
				break;
			
			System.out.println(drug[n][1]);
		}
		
	}

	public static long buildDrug(int W,int H) {
		
		if(drug[W][H] != 0)
			return drug[W][H];
		
		if(W == 1)
			return drug[W][H] = 1; 
		
		if(H == 0)
			return drug[W][H] = buildDrug(W-1, H+1);
		
		return drug[W][H] = buildDrug(W-1, H+1) + buildDrug(W,H-1);
	}

}
