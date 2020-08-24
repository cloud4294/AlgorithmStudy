import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2887번 - 행성 터널
 * 
 * 		https://www.acmicpc.net/problem/2887
 */

public class Main2887 {

	static int n;
	static int[] parent;

	static class point implements Comparable<point> {
		int index;
		int pos;

		public point(int index, int pos) {
			super();
			this.index = index;
			this.pos = pos;
		}

		@Override
		public int compareTo(point o) {
			if (o.pos <= this.pos)
				return 1;
			return -1;
		}

	}

	static class link implements Comparable<link> {

		int src;
		int dst;
		long length;

		public link(int src, int dst, long length) {
			super();
			this.src = src;
			this.dst = dst;
			this.length = length;
		}

		@Override
		public int compareTo(link o) {
			if (o.length <= this.length)
				return 1;
			return -1;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		StringTokenizer st = null;

		List<point>[] points = new ArrayList[3];

		for (int i = 0; i < 3; i++) {
			points[i] = new ArrayList<>();
		}

		parent = new int[n];
		List<link> links = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			points[0].add(new point(i, x));
			points[1].add(new point(i, y));
			points[2].add(new point(i, z));
			parent[i] = i;
		}

		for (int i = 0; i < 3; i++) { // x,y,z 기준 가장 가까운점 리스트에 추가 
			Collections.sort(points[i]);
			for (int j = 0; j < n - 1; j++) {
				links.add(new link(points[i].get(j).index, points[i].get(j + 1).index,
						Math.abs(points[i].get(j).pos - points[i].get(j + 1).pos)));

			}
		}

		System.out.println(MST(links));

	}

	public static long MST(List<link> links) { // 크루스칼 알고리즘

		long result = 0;
		int count = 0;

		Collections.sort(links);

		for (int i = 0; i < links.size(); i++) {
			link now = links.get(i);

			if (union(now.src, now.dst) == true) {
				count++;
				result += now.length;
			}

			if (count == n - 1)
				return result;

		}

		return result;
	}

	public static boolean union(int index, int index2) { // 유니온 파인드 

		int p = find(index);
		int q = find(index2);
		if (p != q) {
			parent[p] = q;
			return true;
		}

		return false;
	}

	public static int find(int index) {

		if (index == parent[index])
			return index;
		else
			return parent[index] = find(parent[index]);

	}

}
