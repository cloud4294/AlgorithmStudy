import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1865번 - 웜홀
 * 
 * 		https://www.acmicpc.net/problem/1865
 */


public class Main1865 {

	static int n, m, w;
	static List<edge> map;
	static int[] dist;
	static int INF = Integer.MAX_VALUE;

	static class edge {
		int S;
		int E;
		int T;

		public edge(int s, int e, int t) {
			super();
			S = s;
			E = e;
			T = t;
		}
	}

	public static boolean BellmanFord() { // 벨만포드 알고리즘

		for (int i = 0; i < n; i++) {
			boolean flag = true;
			for (int j = 0; j < map.size(); j++) {

				edge now = map.get(j);
				if (dist[now.S] == INF)
					continue;
				
				if (dist[now.S] + now.T < dist[now.E]) {
					dist[now.E] = dist[now.S] + now.T;
					flag = false;
				}
			}

			if (flag == true)
				return true;

		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		map = new ArrayList<>();
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map.clear();
			dist = new int[n + 1];
			Arrays.fill(dist, INF);
			dist[1] = 0;
			
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				map.add(new edge(S, E, T));
				map.add(new edge(E, S, T));
			}
			for (int j = 0; j < w; j++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				map.add(new edge(S, E, -T));
			}
			if(BellmanFord())
				System.out.println("NO");
			else 
				System.out.println("YES");
		}
	}

}
