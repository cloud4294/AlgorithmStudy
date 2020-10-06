import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 		Baekjoon Online Judge 1916번 - 최소비용 구하기
 * 
 * 		https://www.acmicpc.net/problem/1916
 */

public class Main1916 {

	static List<link>[] map;

	static class link implements Comparable<link> {

		int src;
		int dst;
		int cost;

		public link(int src, int dst, int cost) {
			this.src = src;
			this.dst = dst;
			this.cost = cost;
		}

		@Override
		public int compareTo(link o) {
			if (o.cost < this.cost)
				return 1;
			return -1;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		map = new ArrayList[N + 1];
		int[] visit = new int[N + 1];

		for (int i = 0; i < N + 1; i++) {
			map[i] = new ArrayList<>();
			visit[i] = 1000 * 100000 + 1;
		}

		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int src = Integer.parseInt(input[0]);
			int dst = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);
			map[src].add(new link(src, dst, cost));
		}

		String[] input = br.readLine().split(" ");
		int start = Integer.parseInt(input[0]);
		int goal = Integer.parseInt(input[1]);

		PriorityQueue<link> pq = new PriorityQueue<>();

		for (link now : map[start]) {
			pq.offer(now);
		}
		visit[start] = 0;

		while (!pq.isEmpty()) {

			link now = pq.poll();

			if (visit[now.src] + now.cost < visit[now.dst]) {
				visit[now.dst] = visit[now.src] + now.cost;
				for (link next : map[now.dst]) {
					pq.offer(next);
				}
			}

		}

		System.out.println(visit[goal]);

	}

}
