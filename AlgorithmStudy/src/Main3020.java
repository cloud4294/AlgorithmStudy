import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 3020 - 개똥벌레
 * 	
 * 		https://www.acmicpc.net/problem/3020
 */

public class Main3020 {

	static int N, H;
	static int[] up;
	static int[] down;
	static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] size = br.readLine().split(" ");

		N = Integer.parseInt(size[0]);
		H = Integer.parseInt(size[1]);
		down = new int[H+1]; // 석순 높이의 개수
		up = new int[H+1]; // 종유석 높이의 개수
		for (int i = 0; i < N / 2; i++) { 
			down[Integer.parseInt(br.readLine())]++;
			up[Integer.parseInt(br.readLine())]++;
		}

		solve();
	}

	public static void solve() {

		int[] upSum = new int[H+1]; // 종유석 개수 누적합 
		int[] downSum = new int[H+1]; // 석순 개수 누적합 
		int[] total = new int[H+1];
		int min = Integer.MAX_VALUE;
		int count = 0;
		
		upSum[H-1] = up[H-1];
		for (int i = H-2; i >= 0; i--) {
			upSum[i] = upSum[i+1] + up[i]; // 이전 누적합 + 현재 높이의 개수 
			
		}
		
		downSum[H-1] = down[H-1];
		for (int i = H-2; i >= 0; i--) {
			downSum[i] = downSum[i+1] + down[i];
			
		}
		
		for (int i = 1; i <= H; i++) {
			total[i] = upSum[H-i+1] + downSum[i];
			
			if(total[i] < min) {
				min = total[i];
				count = 1;
			}else if(total[i] == min) {
				count++;
			}
		}
		
		System.out.println(min +" "+ count);
		
	}
}
