import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17472번 - 다리 만들기 2
 * 
 * 		https://www.acmicpc.net/problem/17472
 */

public class Main17472 {

	static int N, M, count, result, bridge;
	static int[][] map;
	static int[][] input;
	static int[][] link;
	static int[] set;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int INF = Integer.MAX_VALUE;

	static List<edge> mst;

	static class edge implements Comparable<edge> {
		int src;
		int dst;
		int dist;

		public edge(int src, int dst, int dist) {
			super();
			this.src = src;
			this.dst = dst;
			this.dist = dist;
		}

		@Override
		public int compareTo(edge o) {
			return dist - o.dist;
		}

		@Override
		public String toString() {
			return "edge [src=" + src + ", dst=" + dst + ", dist=" + dist + "]";
		}

	}

	public static boolean isRange(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M)
			return false;
		return true;
	}

	public static void Build(int i, int j) { // 섬번호 붙히기

		Queue<int[]> queue = new LinkedList<>();
		int[] start = { i, j };
		map[i][j] = count;
		queue.offer(start);
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int k = 0; k < 4; k++) {
				int nr = now[0] + dr[k];
				int nc = now[1] + dc[k];

				if (isRange(nr, nc) == false)
					continue;

				if (input[nr][nc] == 1 && map[nr][nc] == 0) {
					map[nr][nc] = count;
					int[] next = { nr, nc };
					queue.offer(next);
				}
			}
		}
	}

	public static void search(int i, int j) { // 이동가능한 섬 탐색

		for (int k = i - 1; k >= 0; k--) {

			if (map[k][j] == map[i][j])
				break;

			if (map[k][j] != map[i][j] && map[k][j] != 0) { // 다른 섬번호를 찾을때까지
				int next = Math.abs(k - i) - 1;

				if (next >= 2 && next < link[map[k][j]][map[i][j]]) { // 최소거리
					link[map[k][j]][map[i][j]] = next;
					link[map[i][j]][map[k][j]] = next;
				}
				break;
			}
		}
		for (int k = i + 1; k < N; k++) {

			if (map[k][j] == map[i][j])
				break;

			if (map[k][j] != 0) {
				int next = Math.abs(k - i) - 1;

				if (next >= 2 && next < link[map[k][j]][map[i][j]]) {
					link[map[k][j]][map[i][j]] = next;
					link[map[i][j]][map[k][j]] = next;
				}
				break;
			}
		}

		for (int k = j - 1; k >= 0; k--) {
			if (map[i][k] == map[i][j])
				break;

			if (map[i][k] != 0) {
				int next = Math.abs(k - j) - 1;

				if (next >= 2 && next < link[map[i][k]][map[i][j]]) {

					link[map[i][k]][map[i][j]] = next;
					link[map[i][j]][map[i][k]] = next;
				}
				break;
			}
		}
		for (int k = j; k < M; k++) {

			if (map[i][k] == map[i][j])
				break;

			if (map[i][k] != 0) {
				int next = Math.abs(k - j) - 1;
				if (next >= 2 && next < link[map[i][k]][map[i][j]]) {
					link[map[i][k]][map[i][j]] = next;
					link[map[i][j]][map[i][k]] = next;
				}

				break;
			}
		}

	}

	public static int find(int i) {

		if (set[i] == i)
			return i;
		else
			return set[i] = find(set[i]);

	}

	public static void union() {
		for (int i = 0; i < mst.size(); i++) {
			edge now = mst.get(i);

			int src = find(now.src);
			int dst = find(now.dst);

			if (src != dst) {
				set[src] = dst;
				result += now.dist;
				bridge++;

			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N][M];
		map = new int[N][M];
		mst = new ArrayList<>();

		result = 0;
		count = 0;
		bridge = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) { // 섬번호 부여
			for (int j = 0; j < M; j++) {
				if (input[i][j] == 1 && map[i][j] == 0) {
					count++;
					Build(i, j);
				}
			}
		}

		link = new int[count + 1][count + 1];
		set = new int[count + 1];
		for (int i = 1; i <= count; i++) {
			Arrays.fill(link[i], INF);
		}

		for (int i = 0; i < N; i++) { // 다른 섬으로 이동가능한 경로 탐색
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					search(i, j);
				}
			}
		}

		for (int i = 1; i <= count; i++) { // 간선을 리스트에 저장
			for (int j = i + 1; j <= count; j++) {
				if (link[i][j] != INF) {
					mst.add(new edge(i, j, link[i][j]));
				}
			}
			set[i] = i;
		}

		Collections.sort(mst);

		union(); // 크루스칼 알고리즘

		if (bridge != count - 1)
			result = 0;

		System.out.println(result == 0 ? -1 : result);
	}

}
