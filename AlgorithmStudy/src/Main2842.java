import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/*
 * 		Baekjoon Online Judge 2842번 - 집배원 한상덕
 * 
 * 		https://www.acmicpc.net/problem/2842
 */

public class Main2842 {

	static int N, Kcount;
	static char[][] map;
	static int[][] height;
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static List<Integer> look;

	static class point {

		int r;
		int c;

		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		height = new int[N][N];

		point po = null;

		Kcount = 0;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'P') {
					po = new point(i, j);
				}else if(map[i][j] == 'K')
					Kcount++;
			}
		}

		look = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				height[i][j] = Integer.parseInt(line[j]);
				if (!look.contains(height[i][j]))
					look.add(height[i][j]);
			}
		}

		Collections.sort(look);

		System.out.println(BFS(po));

	}

	public static int BFS(point start) { // 투 포인터 알고리즘 활용 

		int left = 0;
		int right = 0;

		int result = Integer.MAX_VALUE;

		while (left <= right && right < look.size()) {
			if (height[start.r][start.c] >= look.get(left) && height[start.r][start.c] <= look.get(right)) {
				boolean[][] visit = new boolean[N][N];
				Queue<point> queue = new LinkedList<>();

				visit[start.r][start.c] = true;

				queue.offer(start);

				int count = 0;

				while (!queue.isEmpty()) {
					point now = queue.poll();

					if (map[now.r][now.c] == 'K')
						count++;

					for (int i = 0; i < 8; i++) {
						int nr = now.r + dr[i];
						int nc = now.c + dc[i];

						if (isRange(nr, nc) == false)
							continue;

						if (visit[nr][nc] == false
								&& (height[nr][nc] >= look.get(left) && height[nr][nc] <= look.get(right))) {
							visit[nr][nc] = true;
							queue.offer(new point(nr, nc));
						}
					}

				}

				if (count == Kcount) {
					result = Math.min(result, look.get(right) - look.get(left));
					left++;
				} else
					right++;

			} else
				right++;

		}

		return result;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= N || nc < 0 || nc >= N)
			return false;
		return true;
	}

}
