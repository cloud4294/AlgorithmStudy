import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2234번 - 성곽
 * 
 * 		https://www.acmicpc.net/problem/2234
 */

public class Main2234 {

	static int N, M ,result;
	static int[][] map;
	static int[][] visit;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { -1, 0, 1, 0 };
	static HashMap<Integer, Integer> space;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visit = new int[M][N];
		space = new HashMap<Integer, Integer>();
		result = 0;
		int max = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int index = 1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] == 0) {
					space.put(index, BFS(i, j, index));
					max = Math.max(max, space.get(index));
					index++;
				}
			}
		}


		System.out.println(index-1);
		System.out.println(max);
		System.out.println(result);
	}

	public static int BFS(int i, int j, int index) {

		int count = 0;
		visit[i][j] = index;
		Queue<point> queue = new LinkedList<>();
		Queue<Integer> link = new LinkedList<>();
		queue.add(new point(i, j));
		
		while (!queue.isEmpty()) {

			point now = queue.poll();

			count++;

			int nowValue = map[now.r][now.c];
		
			for (int k = 0; k < 4; k++) {
				int nr = now.r + dr[k];
				int nc = now.c + dc[k];

				if (nowValue % 2 == 0) {
					if (isRange(nr, nc) == false) {
						nowValue /= 2;
						continue;
					}

					if (visit[nr][nc] == 0) {
						visit[nr][nc] = index;
						queue.offer(new point(nr, nc));
					}
				}else {
					if (isRange(nr, nc) == false) {
						nowValue /= 2;
						continue;
					}

					if (visit[nr][nc] != 0 && visit[nr][nc] != index) {
						if(!link.contains(visit[nr][nc]))
							link.offer(visit[nr][nc]);
					}
				}

				nowValue /= 2;
			}

		}
		
		while(!link.isEmpty()) {
			int now = link.poll();
			result = Math.max(result, count+space.get(now));
		}

		return count;
	}

	public static boolean isRange(int nr, int nc) {
		if (nr < 0 || nr >= M || nc < 0 || nc >= N)
			return false;
		return true;
	}

}
