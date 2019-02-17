import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 14502 - 연구소
 * 
 * 		https://www.acmicpc.net/problem/14502
 */

public class Main14502 {
	static int N, M;
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };
	static int res;
	
	static class node {
		int y;
		int x;

		public node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	} // 좌표를 저장할 클래스

	public static void solve(int[][] map, int count) {
		
		
		if (count == 3) { // 벽이 세개가 되면  바이러스가 있는 지점부터 bfs 시행
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 2) {
						bfs(map,i, j);
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] == 0)
						sum++;
				}
			} // 바이러스가 퍼지지 않은 지역의 갯수를 합산 
			if(sum > res) { 
				res = sum; 
			}// 최대값을 갱신
			

		} else { // 비어있는 부분에 벽을 세움
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0) {
						int[][] next = new int[N][M];
						for (int k = 0; k < N; k++) {
							next[k] = Arrays.copyOf(map[k], M);
						}
						next[i][j] = 1;
						solve(next, count + 1);
					}
				}
			}
		}

	}

	public static void bfs(int[][] map,int i, int j) { // bfs 시행

		Queue<node> queue = new LinkedList<>();
		node start = new node(i, j);
		queue.offer(start);
			
		while (!queue.isEmpty()) { 
			node now = queue.poll();
			for (int k = 0; k < 4; k++) {
				int ny = now.y + my[k];
				int nx = now.x + mx[k];

				if (isRange(ny, nx) == false)
					continue;
				
				if(map[ny][nx] == 0) {
					map[ny][nx] = 2;
					queue.offer(new node(ny,nx));
				}
			}

		}
		
	}

	public static boolean isRange(int ny, int nx) { // 범위 체크
		if (ny < 0 || ny >= N || nx < 0 || nx >= M)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

		res = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력값 초기화

		solve(map, 0);
		System.out.println(res);

	}

}
