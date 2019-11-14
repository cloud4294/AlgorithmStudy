import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17825 - 주사위 윷놀이
 * 
 * 		https://www.acmicpc.net/problem/17825
 */

public class Main17825 {

	static int[] dice;
	static boolean[] visit;
	static int result;
	
	static int[][] map = { { 0, 1, 2, 3, 4, 5 },
						   { 2, 2, 3, 4, 5, 6 }, 
						   { 4, 3, 4, 5, 6, 7 }, 
						   { 6, 4, 5, 6, 7, 8 },
						   { 8, 5, 6, 7, 8, 9 }, 
						   { 10, 21, 22, 23, 29, 30 }, 
						   { 12, 7, 8, 9, 10, 11 }, 
						   { 14, 8, 9, 10, 11, 12 },
						   { 16, 9, 10, 11, 12, 13 }, 
						   { 18, 10, 11, 12, 13, 14 }, 
						   { 20, 24, 25, 29, 30, 31 },
						   { 22, 12, 13, 14, 15, 16 }, 
						   { 24, 13, 14, 15, 16, 17 }, 
						   { 26, 14, 15, 16, 17, 18 },
						   { 28, 15, 16, 17, 18, 19 }, 
						   { 30, 26, 27, 28, 29, 30 }, 
						   { 32, 17, 18, 19, 20, 40 },
						   { 34, 18, 19, 20, 40, 40 }, 
						   { 36, 19, 20, 40, 40, 40 }, 
						   { 38, 20, 40, 40, 40, 40 },
						   { 40, 40, 40, 40, 40, 40 },
						   { 13, 22, 23, 29, 30, 31 }, 
						   { 16, 23, 29, 30, 31, 20 }, 
						   { 19, 29, 30, 31, 20, 40 },
						   { 22, 25, 29, 30, 31, 20 }, 
						   { 24, 29, 30, 31, 20, 40 }, 
						   { 28, 27, 28, 29, 30, 31 },
						   { 27, 28, 29, 30, 31, 20 }, 
						   { 26, 29, 30, 31, 20, 40 }, 
						   { 25, 30, 31, 20, 40, 40 },
						   { 30, 31, 20, 40, 40, 40 }, 
						   { 35, 20, 40, 40, 40, 40 } };

	public static void solve(int step, int point, int[] horse) {
		
		if (step >= 10) {
			result = Math.max(result, point);
			return;
		}

		for (int i = 0; i < 4; i++) {

			int now = map[horse[i]][dice[step]];
			
			if(now <= 31 && visit[now] == false) {
				visit[horse[i]] = false;
				visit[now] = true;
				int temp = horse[i];
				horse[i] = now;
				solve(step + 1,point + map[now][0],horse);
				horse[i] = temp;
				visit[horse[i]] = true;
				visit[now] = false;
			}else if(now > 31) {
				visit[horse[i]] = false;
				solve(step + 1,point,horse);
				visit[horse[i]] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		dice = new int[10];
		visit = new boolean[33];
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		result = 0;
		int[] horse = { 0, 0, 0, 0 };
		solve(0, 0, horse);
		
		System.out.println(result);

	}

}
