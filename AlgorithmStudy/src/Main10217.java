import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 10217 - KCM Travel
 * 
 * 		https://www.acmicpc.net/problem/10217
 */

public class Main10217 {

	static int N, M;
	static List<edge>[] map;
	static int[][] dist;

	static int INF = Integer.MAX_VALUE;

	static class edge implements Comparable<edge> {
		int dst;
		int cost;
		int time;

		public edge(int dst, int cost, int time) {
			super();
			this.dst = dst;
			this.cost = cost;
			this.time = time;
		}

		@Override
		public int compareTo(edge o) {
			return time - o.time;
		}

	}

	public static void Dijkstra() { // 다익스트라 + dp

		PriorityQueue<edge> pq = new PriorityQueue<>();
		pq.offer(new edge(1, 0, 0));
		dist[1][0] = 0;

		while (!pq.isEmpty()) {
			edge now = pq.poll();

			if (now.time > dist[now.dst][now.cost] || now.cost > M)
				continue;

			for (int i = 0; i < map[now.dst].size(); i++) {
				edge next = map[now.dst].get(i);

				if (now.cost + next.cost <= M) {
					if (dist[now.dst][now.cost] + next.time < dist[next.dst][now.cost + next.cost]) {
						dist[next.dst][now.cost + next.cost] = dist[now.dst][now.cost] + next.time;
						pq.offer(new edge(next.dst, now.cost + next.cost, now.time + next.time));
					}
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		map = new ArrayList[101];
		for (int i = 1; i <= 100; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			dist = new int[N + 1][M + 1];// 목적지,비용,거리 dp 
			int result = Integer.MAX_VALUE;

			for (int j = 1; j <= N; j++) {
				map[j].clear();
				Arrays.fill(dist[j], INF);
			}

			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				map[u].add(new edge(v, c, d));
			}

			Dijkstra();

			for (int j = 0; j <= M; j++) {
				if (dist[N][j] != 0) {
					result = Math.min(result, dist[N][j]);
				}
			}

			System.out.println(result == INF ? "Poor KCM" : result);
		}

	}

}
