import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1005번 - ACM Craft
 * 
 * 		https://www.acmicpc.net/problem/1005
 */

public class Main1005 {

	static int N;
	static int[] cost;
	static int[] count;
	static List<Integer>[] link;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int i = 0; i < test_case; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			cost = new int[N + 1];
			count = new int[N + 1];
			link = new ArrayList[N + 1];
			for (int j = 1; j <= N; j++) {
				link[j] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				cost[j] = Integer.parseInt(st.nextToken());
			}

			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int src = Integer.parseInt(st.nextToken());
				int dst = Integer.parseInt(st.nextToken());

				link[src].add(dst);
				count[dst]++;
			}

			int W = Integer.parseInt(br.readLine());

			System.out.println(solve(W));
			
		}

	}

	public static int solve(int target) { // 위상 정렬

		Queue<Integer> queue = new LinkedList<>();
		int[] build = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			if (count[i] == 0) {
				build[i] = cost[i];
				queue.offer(i);
			}

		}

		while (!queue.isEmpty()) {

			int now = queue.poll();

			for (int i = 0; i < link[now].size(); i++) {

				int next = link[now].get(i);
				build[next] = Math.max(build[next], build[now] + cost[next]);
				count[next]--;

				if (count[next] == 0) {
					queue.offer(next);
				}
			}
		}
		
		return build[target];
	}

}
