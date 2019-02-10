import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 3197번 - 백조의 호수
 * 
 * 		https://www.acmicpc.net/problem/3197
 */

public class Main3197 {

	static int[][] melt;
	static char[][] map;
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };
	static int upper = 0;
	static int R, C ,res;
	static node start = null;
	static node end = null;

	static class node {
		int y;
		int x;
		int day;

		public node(int y, int x, int day) {
			super();
			this.y = y;
			this.x = x;
			this.day = day;
		}
	} // 좌표, 녹는 날짜를 저장하는 클래스 
	
	public static boolean isRange(int i,int j) {
		if(i < 0 || i >= R || j < 0 || j >= C)
			return false;
		else 
			return true;
		
	} // 범위확인 메소드 

	public static void makeMelt() { // 빙하가 녹는 시점을 배열로만드는 매소드 
	
		boolean[][] visit = new boolean[R][C];
		Queue<node> queue = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == '.') {
					visit[i][j]  = true;
					queue.offer(new node(i,j,0));
					
				}
			}
		} // 물인 부분을 모두 큐에 넣음 
		
		while(!queue.isEmpty()) { // 물에서 인접한 4방향을 탐색하여 빙하이면 녹는날짜를 +1 하여 다시 큐에 넣음 
			node now = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = now.y + my[i];
				int nx = now.x + mx[i];
				
				if(isRange(ny,nx) == false)
					continue;
	
				if(visit[ny][nx] == false) {
					
					melt[ny][nx] = now.day + 1;
					visit[ny][nx] = true;
					queue.offer(new node(ny,nx,now.day + 1));
					
					if(melt[ny][nx] > upper)
						upper = melt[ny][nx];
					
				}
			}
			
			
			
		}
		

	}

	public static void solve(int low,int upper) { // 이분탐색후 BFS를 시행

		if(low > upper)
			return;
		
		
		int mid = (low + upper) / 2;


		boolean[][] visit = new boolean[R][C];
		Queue<node> queue = new LinkedList<>();
		
		queue.offer(start);
		visit[start.y][start.x] = true;

		boolean pass = false;
		while (!queue.isEmpty()) {
			node now = queue.poll();

			if (now.y == end.y && now.x == end.x) {
				pass = true;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int ny = now.y + my[i];
				int nx = now.x + mx[i];

				if (isRange(ny,nx) == false)
					continue;

				if (melt[ny][nx] - mid <= 0 && visit[ny][nx] == false) {
					visit[ny][nx] = true;
					queue.offer(new node(ny, nx, 0));
				}
			}

		}  // BFS 시행 
		
		if(pass == true) { // 도착점 까지 갈수 있으면 
			if(mid < res)
				res = mid; // 결과값을 더 작은 것으로 갱신
			solve(low,mid-1); 
		}
		else 
			solve(mid+1,upper);
			

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		R = Integer.parseInt(size[0]);
		C = Integer.parseInt(size[1]);

		res = Integer.MAX_VALUE;
		map = new char[R][C];
		melt = new int[R][C];

		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'L') {
					map[i][j] = '.';
					if (start == null) {
						start = new node(i, j, 0);
					} else {
						end = new node(i, j, 0);
					}
				}

			}
		} // 입력값 초기화 

		makeMelt();
		solve(0,upper);
		
		System.out.println(res);
	}

}
