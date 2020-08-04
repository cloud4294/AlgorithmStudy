import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 15591번 - MooTube (Silver) 
 * 
 * 		https://www.acmicpc.net/problem/15591
 */

public class Main15591 {

	static int N;
	static List<link>[] map;

	static class link {

		int src;
		int dst;
		int usado;

		public link(int src, int dst, int usado) {
			super();
			this.src = src;
			this.dst = dst;
			this.usado = usado;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		map = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int usado = Integer.parseInt(st.nextToken());

			map[src].add(new link(src, dst, usado));
			map[dst].add(new link(dst, src, usado));
		}

		for (int i = 0; i < q; i++) {

			st = new StringTokenizer(br.readLine());

			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			System.out.println(solve(k, v));
		}

	}

	public static int solve(int k, int v) {

		int recommend = 0;
		boolean[] visit = new boolean[N+1];
		Queue<link> queue = new LinkedList<>();

		visit[v] = true;
		for (int i = 0; i < map[v].size(); i++) {
			queue.offer(map[v].get(i));
		}

		while (!queue.isEmpty()) {

			link now = queue.poll();

			if (now.usado >= k) {
				recommend++;
			}

			if (visit[now.dst] == false) {
				visit[now.dst] = true;
				
				for (int i = 0; i < map[now.dst].size(); i++) {
					
					link next = map[now.dst].get(i);
					if(visit[next.dst] == false) {						
						int nextUsado = Math.min(now.usado, next.usado);
						queue.offer(new link(now.src, next.dst, nextUsado));
					}
				}
			}
		}

		return recommend;
	}
	

}
