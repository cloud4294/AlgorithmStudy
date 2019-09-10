import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1774번 - 우주신과의 교감
 * 
 * 		https://www.acmicpc.net/problem/1774
 */

public class Main1774 {

	static int N, M;
	static List<edge> map;
	static int[] connect;
	static long[][] nodes;
	

	static class edge implements Comparable<edge> {
		int src;
		int dst;
		double dist;

		public edge(int src, int dst, double dist) {
			super();
			this.src = src;
			this.dst = dst;
			this.dist = dist;
		}

		@Override
		public int compareTo(edge o) {
			if (dist > o.dist)
				return 1;
			return -1;

		}
	}

	public static int find(int i) {

		if (connect[i] == i)
			return i;
		else
			return connect[i] = find(connect[i]);

	}

	public static boolean union(int x,int y) {

		
			int src = find(x);
			int dst = find(y);

			if (src != dst) {
				connect[src] = dst;
				return true;

			}

			return false;

	}

	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new ArrayList<>();
		nodes = new long[N + 1][2];
		double result = 0;
		int cnt = 0;
		
		connect = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			nodes[i][0] = x;
			nodes[i][1] = y;
		}

	
		for (int i = 1; i < N; i++) {
			for (int j = i; j <= N; j++) {
				if (i != j) {
					long a = nodes[i][0] - nodes[j][0];
					long b = nodes[i][1] - nodes[j][1];
					double dist = Math.sqrt(a*a + b*b);
					
					map.add(new edge(i, j, dist)); 
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			connect[i] = i;
		}
		
		for (int i = 0; i < M; i++) { // 선택된 간선을미리 연결
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			
			union(src,dst);

		}

		Collections.sort(map);
		
		
		
		for (int i = 0; i < map.size(); i++) { // 크루스칼
			edge now = map.get(i);
			if(cnt == N - M - 1)
				break;
			
			if(union(now.src, now.dst)) {
				cnt++;
				result += now.dist;
			}
		}

		System.out.printf("%.2f\n", result);
	}

}
