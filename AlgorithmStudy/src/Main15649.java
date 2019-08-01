import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 15649 - N과 M (1)
 * 
 * 		https://www.acmicpc.net/problem/15649
 */

public class Main15649 {

	static int N,M;
	static List<Integer> visit;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		visit = new ArrayList<>();
		
		solve(0);

	}

	public static void solve(int count) {
		
		if(count == M) {
			for(int i:visit) {
				System.out.print(i+" ");
			}
			System.out.println();
			
			return;
		}
		
		for (int j = 1; j <= N; j++) {
			if(!visit.contains(j)) {
				visit.add(Integer.valueOf(j));
				solve(count+1);
				visit.remove(Integer.valueOf(j));
			}
		}
		
	}

}
