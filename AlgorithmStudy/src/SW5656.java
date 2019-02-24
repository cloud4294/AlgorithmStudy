import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		SW Expert Academy 5656.[모의 SW 역량테스트] - 벽돌 깨기
 * 
 * 		https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo
 */


public class SW5656 {

	static int[][] map;
	static int N, W, H;
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };
	static int res;
	
	static class point {
		int y;
		int x;

		public point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	} // 좌표 저장 클래스

	public static boolean isRange(int y, int x) {

		if (y < 0 || y >= H || x < 0 || x >= W)
			return false;
		return true;

	} // 범위 확인 메소드

	public static void solve(int count) {

		if (count == N) { // N개의 구슬 사용했다면 남은블럭수를 갱신
			int c = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(map[i][j] != 0)
						c++;
				}
			}		
			res = Math.min(res, c);
			return;
		}

		for (int i = 0; i < W; i++) { // 백트랙킹 
			int[][] copy = new int[H][W];
			for (int j = 0; j < H; j++) {
				copy[j] = Arrays.copyOf(map[j], W);
			}
			crush(i);
			fall();
			solve(count + 1);
			for (int j = 0; j < H; j++) {
				map[j] = Arrays.copyOf(copy[j], W);
			}
		}
	}

	public static void fall() { // 떨어지는 블럭 처리 
		
		for (int i = 0; i < W; i++) {
			int[] fall = new int[H];
			int index = H - 1;
			for (int j = H-1; j >= 0; j--) {
				if(map[j][i] != 0) {
					fall[index] = map[j][i];
					index--;
				}
			}
			for (int j = 0; j < H; j++) {
				map[j][i] = fall[j];
			}
		}
		
	}

	public static void crush(int i) {

		Queue<point> queue = new LinkedList<>();
		for (int j = 0; j < H; j++) {  //구슬이 맞는 지점을 찾아 큐에 넣음 
			if (map[j][i] != 0) {
				queue.offer(new point(j, i));
				break;
			}
		}

		while (!queue.isEmpty()) { // 큐를 돌면서 연쇄적으로 파괴될수 있는 블럭을 다시 큐에 넣음 
			point now = queue.poll();
			
			for (int j = 0; j < 4; j++) {
				
				for (int j2 = 1; j2 < map[now.y][now.x]; j2++) {
					int ny = now.y + (j2 * my[j]);
					int nx = now.x + (j2 * mx[j]);
					
					if(isRange(ny, nx) == false)
						break;
					
					if(map[ny][nx] > 1)
						queue.offer(new point(ny, nx));
					else 
						map[ny][nx] = 0;
					
				}
			}
			
			map[now.y][now.x] = 0;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			res = Integer.MAX_VALUE;
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력값 초기화 

			solve(0); // 백트랙킹 메소드
			
			System.out.println("#"+t +" "+res);
		}

	}

}
