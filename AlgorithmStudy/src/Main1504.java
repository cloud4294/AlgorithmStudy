import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1504 - 특정한 최단 경로
 * 
 * 		https://www.acmicpc.net/problem/1504
 */

public class Main1504 {

	static List<link>[] graph;
	static int N;

	static class link {
		int dst;
		int weight;

		public link(int dst, int weight) {
			super();
			this.dst = dst;
			this.weight = weight;
		}

	}// 각 정점의 연결된 간선과 가중치를 저장하는 클래스

	public static int solve(int src, int dst) {

		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		Queue<Integer> queue = new LinkedList<>();
		dist[src] = 0;
		queue.offer(src);
		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int i = 0; i < graph[now].size(); i++) {
				link next = graph[now].get(i);
				if (dist[now] + next.weight < dist[next.dst]) {
					dist[next.dst] = dist[now] + next.weight;
					queue.offer(next.dst);
				}
			}

		} // 다익스트라를 시행하여 목적지까지 최단거리를 찾음 

		if(dist[dst] == Integer.MAX_VALUE) // 목적지에 도달할 수 있는 간선이 없으면 
			return -1;
		return dist[dst];

	}

	public static int check(int a, int b, int c) {

		if (a == -1 || b == -1 || c == -1)
			return -1;
		else
			return a + b + c;

	} // 세 경로 의 합중 연결이 안되는 경로가 있으면 -1 리턴 없으면 합 리턴

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		} // 그래프 초기화 

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[src].add(new link(dst, weight));
			graph[dst].add(new link(src,weight));
		} // 입력값 초기화 

		st = new StringTokenizer(br.readLine());
		int d1 = Integer.parseInt(st.nextToken()); // 첫번째 경유 정점
		int d2 = Integer.parseInt(st.nextToken()); // 두번재 경유 정점 

		int res1 = check(solve(1, d1), solve(d1, d2), solve(d2, N)); // 시작점 - 첫 번재 경유 정점 - 두 번째 경유 정점 - 도착점
		int res2 = check(solve(1, d2), solve(d2, d1), solve(d1, N)); // 시작점 - 두 번째 경유 정점 - 첫 번째 경유 정점 - 도착점 

		System.out.println(Math.min(res1, res2) == -1 ? Math.max(res1, res2) : Math.min(res1, res2)); // 두 경로중 작은 것 출력, 두 경로중 -1이 있으면 나머지 출력

	}

}
