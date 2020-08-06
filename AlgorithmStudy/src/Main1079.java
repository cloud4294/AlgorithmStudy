import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1079번 - 마피아
 * 
 * 		https://www.acmicpc.net/problem/1079
 */

public class Main1079 {

	static int n, result;
	static int[] users;
	static boolean[] alive;
	static int[][] guilty;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		users = new int[n];
		alive = new boolean[n];
		result = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			users[i] = Integer.parseInt(st.nextToken());
			alive[i] = true;
		}

		guilty = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				guilty[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int me = Integer.parseInt(br.readLine());

		solve(me, n, 0);
		System.out.println(result);

	}

	public static void solve(int me, int survive, int count) {
		
		if(survive == 1) {
			result = Math.max(result, count);
			return;
		}
		if (survive % 2 == 0) { // 밤, 가능한 모든 유죄 지수 계산
			for (int i = 0; i < n; i++) {
				if (i != me && alive[i] == true) {
					alive[i] = false;
					for (int j = 0; j < n; j++) {
						users[j] += guilty[i][j];
					}
					solve(me, survive - 1, count + 1);
					alive[i] = true;
					for (int j = 0; j < n; j++) {
						users[j] -= guilty[i][j];
					}
				}
			}

		} else { // 낮, 유죄 지수가 가장 큰 유저를 제외
			
			int max = -1;
			int maxValue = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				if(alive[i] == true && users[i] > maxValue) {
					max = i;
					maxValue = users[i];
				}
			}
			if (max == me) {
				result = Math.max(result, count);
				return;
			}
			
			if(max != -1) {
				alive[max] = false;
				solve(me, survive - 1, count);
				alive[max] = true;				
			}

		}

	}

}
