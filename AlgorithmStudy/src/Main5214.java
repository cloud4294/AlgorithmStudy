import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 5214 - 환승
 * 
 * 		https://www.acmicpc.net/problem/5214
 */

public class Main5214 {

	static int N, M, K;
	static List<Integer>[] map;
	static int result;

	public static void BFS() {
		boolean[] visit = new boolean[N + M + 1];
		visit[1] = true;
		Queue<int[]> queue = new LinkedList<>();
		int[] start = { 1, 0 };
		queue.offer(start);

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			if (now[0] == N) {
				result = Math.min(result, (now[1]/2)+1);
				break;
			}
			for (int i = 0; i < map[now[0]].size(); i++) {
				int next = map[now[0]].get(i);

				if (visit[next] == false) {
					visit[next] = true;
					int[] n = { next, now[1] + 1 };
					queue.offer(n);
				}
			}

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = Integer.MAX_VALUE;
		map = new ArrayList[N + M + 1];
		for (int i = 1; i <= N + M; i++) {
			map[i] = new ArrayList<Integer>();
		}
		for (int i = 1; i <= M; i++) { // 튜브도 정점으로 추가
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < K; j++) {
				int dst = Integer.parseInt(st.nextToken());
				map[i + N].add(dst);
				map[dst].add(i + N);
			}

		}

		BFS();
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

}
