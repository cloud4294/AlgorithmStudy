import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2617 - 구슬 찾기
 * 
 * 		https://www.acmicpc.net/problem/2617
 */

public class Main2617 {

	static int N, M;
	static boolean[] upVisit;
	static boolean[] downVisit;

	static stone[] stones;

	static class stone {

		int index;
		List<stone> up;
		List<stone> down;

		public stone(int index) {
			super();
			this.index = index;
			this.up = new ArrayList<>();
			this.down = new ArrayList<>();
		}

	}

	public static int findUp(int i) {

		int count = 1;
		for (int j = 0; j < stones[i].up.size(); j++) {
			int next = stones[i].up.get(j).index;
			if (upVisit[next] == false) {
				upVisit[next] = true;
				count += findUp(next);
			}

		}

		return count;
	}

	public static int findDown(int i) {

		int count = 1;
		for (int j = 0; j < stones[i].down.size(); j++) {
			int next = stones[i].down.get(j).index;
			if (downVisit[next] == false) {
				downVisit[next] = true;
				count += findDown(next);
			}
		}

		return count;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());


		
		stones = new stone[N + 1];

		int result = 0;
		for (int i = 1; i <= N; i++) {
			stones[i] = new stone(i);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			stones[src].down.add(stones[dst]);
			stones[dst].up.add(stones[src]);
		}

		for (int i = 1; i <= N; i++) { // i번째 구슬 마다 DFS
			upVisit = new boolean[N + 1];
			downVisit = new boolean[N + 1];
			upVisit[i] = true;
			downVisit[i] = true;
			if (findUp(i) > (N + 1) / 2 || findDown(i) > (N + 1) / 2) {
				result++;
			}

		}

		System.out.println(result);
	}

}
