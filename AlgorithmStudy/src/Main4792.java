import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 4792번 - 레드 블루 스패닝 트리
 * 
 * 		https://www.acmicpc.net/problem/4792
 */

public class Main4792 {

	static int n, m, k;
	static List<edge> map;
	static int[] link;

	static class edge implements Comparable<edge> {
		int src;
		int dst;
		int weight;

		public edge(int src, int dst, int weight) {
			super();
			this.src = src;
			this.dst = dst;
			this.weight = weight;
		}

		@Override
		public int compareTo(edge o) {
			return weight - o.weight;
		}
		
	}

	public static int find(int i) {

		if (link[i] == i)
			return i;
		else
			return link[i] = find(link[i]);

	}

	public static int union(int color) {

		int count = 0;

		for (int i = 0; i < map.size(); i++) {
			edge now = map.get(i);

			int src = find(now.src);
			int dst = find(now.dst);

			if (src != dst) {

				link[src] = dst;

				if (now.weight == color)
					count++;
			}

		}

		
		return count;

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = null;

		map = new ArrayList<>();
		while (true) {

			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			if (n == 0 && m == 0 && k == 0)
				return;

			map.clear();
			link = new int[n + 1];

			for (int i = 1; i <= n; i++) {
				link[i] = i;
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				String color = st.nextToken();
				int weight = 0;
				if (color.equals("B"))
					weight = 1;
				else if (color.equals("R"))
					weight = 2;
				int src = Integer.parseInt(st.nextToken());
				int dst = Integer.parseInt(st.nextToken());

				map.add(new edge(src, dst, weight));
			}

			Collections.sort(map);
			
			int max = union(1);  // 파랑간선을 최대로 선택할 경우 크루스칼
			
			for (int i = 0; i < map.size(); i++) {
				edge now = map.get(i);
				if(now.weight == 1)
					now.weight = 2;
				else if(now.weight == 2)
					now.weight = 1;
			}
			for (int i = 1; i <= n; i++) {
				link[i] = i;
			}
			
			Collections.sort(map);
			
			int min = union(2); // 파랑간선을 최소로 선택할 경우 크루스칼
			
			if(min <= k && k <= max)
				System.out.println(1);
			else 
				System.out.println(0);
		}

	}

}
