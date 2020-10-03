import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 1944번 - 복제 로봇
 * 
 * 		https://www.acmicpc.net/problem/1944
 */

public class Main1944 {

	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[] group;

	static class point {

		int r;
		int c;
		int count;

		public point(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

	}

	static class link implements Comparable<link> {

		int src;
		int dst;
		int length;

		public link(int src, int dst, int length) {
			super();
			this.src = src;
			this.dst = dst;
			this.length = length;
		}

		@Override
		public int compareTo(link o) {
			if (o.length < this.length)
				return 1;
			else 
				return -1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] info = br.readLine().split(" ");

		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);

		map = new int[N][N];
		group = new int[M+2];

		List<point> points = new ArrayList<>();

		int count = 1;
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				char now = input.charAt(j);
				if (now == '1')
					map[i][j] = -1;
				else if (now == '0')
					map[i][j] = 0;
				else if (now == 'S' || now == 'K') {
					map[i][j] = count++;
					points.add(new point(i, j, 0));
				}
			}
		}

		PriorityQueue<link> links = findLink(points);
		System.out.println(union(links)); // 크루스칼 알고리즘

	}

	public static int union(PriorityQueue<link> links) { // 유니온 
		
		for (int i = 1; i < M+2; i++) {
			group[i] = i;
		}
		
		int result = 0;
		while(!links.isEmpty()) {
			link now = links.poll();
			int src = find(now.src);
			int dst = find(now.dst);

			if (src != dst) {
				result += now.length;
				group[src] = dst;
			}
		}
		
		for (int i = 1; i < M+1; i++) {
			if(group[i] != group[i+1]) {
				result = -1;
				break;
			}
		}

		return result;
	}

	public static int find(int i) { // 파인드

		if (group[i] == i)
			return i;

		return group[i] = find(group[i]);
	}

	public static PriorityQueue<link> findLink(List<point> points) { // 간선 생성

		PriorityQueue<link> links = new PriorityQueue<>();

		for (int i = 0; i < points.size(); i++) {

			boolean[][] visit = new boolean[N][N];
			point start = points.get(i);

			visit[start.r][start.c] = true;

			Queue<point> queue = new LinkedList<>();

			queue.offer(start);
			while (!queue.isEmpty()) {

				point now = queue.poll();
				if (map[now.r][now.c] > i + 1) {
					links.add(new link(i + 1, map[now.r][now.c], now.count));
				}

				for (int j = 0; j < 4; j++) {
					int nr = now.r + dr[j];
					int nc = now.c + dc[j];

					if (isRange(nr, nc) == false)
						continue;

					if (map[nr][nc] != -1 && visit[nr][nc] == false) {
						visit[nr][nc] = true;
						queue.offer(new point(nr, nc, now.count + 1));
					}
				}
			}

		}

		return links;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			return false;
		return true;
	}

}
