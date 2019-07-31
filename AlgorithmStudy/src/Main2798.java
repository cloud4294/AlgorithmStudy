import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 2798 - 블랙잭
 * 
 * 		https://www.acmicpc.net/problem/2798
 */

public class Main2798 {

	static int N,M;
	static int[] card;
	static boolean[] use;
	static int dis;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);
		card = new int[N];
		use = new boolean[N];
		dis = Integer.MAX_VALUE;
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(input[i]);
		}
		
		solve(0,0,0);
		System.out.println(M - dis);
		
		
	}

	public static void solve(int index,int sum,int count) { // 브루트포스
		
		if(sum > M)
			return;
		
		if( count == 3) {
			if(Math.abs(sum - M) < dis)
				dis = Math.abs(sum - M);		
			return;
		}
		
		for (int i = index; i < N; i++) {
			if(use[i] == false) {
				use[i] = true;
				solve(i+1,sum+card[i],count+1);
				use[i] = false;
			}
		}
	}

}
