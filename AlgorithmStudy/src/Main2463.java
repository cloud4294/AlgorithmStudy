import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 2463 - 비용
 * 
 * 		https://www.acmicpc.net/problem/2463
 */

public class Main2463 {

	static int mod = 1000000000;
	static int max = 100001;
	static long[] setSize = new long[max];
	static long total;
	static int[] parent;

	static class vertex implements Comparable<vertex> {
		int u;
		int v;
		int cost;

		public vertex(int u, int v, int cost) {
			super();
			this.u = u;
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(vertex o) {
			if (this.cost > o.cost) // 오름차순
				return -1;
			return 1;
		}

	}

	static int find(int i) {

		if (parent[i] == i)
			return i;
		else
			return parent[i] = find(parent[i]);
	} // 집합의 루트를 찾음 

	static void merge(int u, int v) {

		int pu = find(u);
		int pv = find(v);

		if (pu > pv) {
			parent[pu] = pv;
			setSize[v] += setSize[u];
			setSize[u] = 1;
		} else {
			parent[pv] = pu;
			setSize[u] += setSize[v];
			setSize[v] = 1;

		}

	} // u와 v의 집합을 합침


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		parent = new int[N + 1];

		long res = 0;
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			setSize[i] = 1;
		}

		List<vertex> list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int cost = sc.nextInt();
			list.add(new vertex(u, v, cost));
			total += cost;
		}

		Collections.sort(list); // 비용을 기준으로 내림차순 정렬 

		for (int i = 0; i < M; i++) {
			vertex now = list.get(i);

			int pu = find(now.u);
			int pv = find(now.v);
			
			if (pu != pv) { // 같은집합이아닌 경우
				System.out.println((((setSize[pu] * setSize[pv]) % mod) * total) % mod);
				res += (((setSize[pu] * setSize[pv]) % mod) * total) % mod;
				res %= mod;
				merge(pu, pv);

			} 
			total -= now.cost;

		}

		System.out.println(res);
	}

}
