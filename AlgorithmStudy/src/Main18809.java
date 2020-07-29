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
 * 		Baekjoon Online Judge 18809번 - Gaaaaaaaaaarden
 * 
 * 		https://www.acmicpc.net/problem/18809
 */

public class Main18809 {

	static int N, M, G, R;

	static int[][] map;
	static List<point> soil;
	static List<Integer> selectR;
	static List<Integer> selectG;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int result;

	static class point {

		int r;
		int c;
		int time;
		int type;

		public point(int r, int c, int time, int type) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
			this.type = type;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		soil = new ArrayList<>();
		selectR = new ArrayList<>();
		selectG = new ArrayList<>();

		result = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					soil.add(new point(i, j, 0, 0));
				}
			}
		}

		DFS(0);

		System.out.println(result);

	}

	public static void DFS(int count) { // 가능한 토양 조합 순열을 DFS로 탐색

		if (selectR.size() == R && selectG.size() == G) {
			BFS();

			return;
		}

		if (count == soil.size())
			return;

		if (selectR.size() < R) {
			selectR.add(Integer.valueOf(count));
			DFS(count + 1);
			selectR.remove(Integer.valueOf(count));
		}

		if (selectG.size() < G) {
			selectG.add(Integer.valueOf(count));
			DFS(count + 1);
			selectG.remove(Integer.valueOf(count));
		}

		DFS(count + 1);

	}

	public static void BFS() { // BFS로 빨간색 배양액과 녹색 배양액이 만나는 지점의 횟수를 구함
		int sum = 0;
		int[][][] visit = new int[2][N][M];

		Queue<point> queue = new LinkedList<>();

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(visit[i][j], -1);
			}
		}

		for (int i = 0; i < selectR.size(); i++) {
			point red = soil.get(selectR.get(i));
			red.type = 0;
			queue.offer(red);
			visit[red.type][red.r][red.c] = 0;
		}

		for (int i = 0; i < selectG.size(); i++) {
			point green = soil.get(selectG.get(i));
			green.type = 1;
			queue.offer(green);
			visit[green.type][green.r][green.c] = 0;
		}

		while (!queue.isEmpty()) {
			point now = queue.poll();

			if (visit[now.type][now.r][now.c] == -2)
				continue;
			if (visit[(now.type + 1) % 2][now.r][now.c] == now.time) {
				sum++;
				visit[(now.type + 1) % 2][now.r][now.c] = -2;
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (isRange(nr, nc) == false)
					continue;
				if (map[nr][nc] != 0 && visit[now.type][nr][nc] == -1) {
					visit[now.type][nr][nc] = now.time + 1;
					queue.offer(new point(nr, nc, now.time + 1, now.type));

				}

			}
		}

		result = Math.max(result, sum);
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nc < 0 || nr >= N || nc >= M)
			return false;
		return true;
	}

}
