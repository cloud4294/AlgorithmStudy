import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 4485번 - 녹색 옷 입은 애가 젤다지?
 * 
 * 		https://www.acmicpc.net/problem/4485
 */


public class Main4485 {
	
	static int[] my = {-1,0,1,0};
	static int[] mx = {0,1,0,-1};
	static int N;
	static class point{
		int y;
		int x;
		int lope;
		public point(int y, int x, int lope) {
			super();
			this.y = y;
			this.x = x;
			this.lope = lope;
		}
	}
	
	private static boolean isRange(int ny, int nx) {
		if(ny < 0 || ny >= N || nx <0 || nx >= N)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int count = 1;
		while((N = Integer.parseInt(br.readLine())) != 0) {
			int[][] map = new int[N][N];
			int[][] visit = new int[N][N];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < N; j2++) {
					map[j][j2] = Integer.parseInt(st.nextToken());
					visit[j][j2] = Integer.MAX_VALUE;
				}
			} // 입력값 초기화, 방문 배열 초기화 
			
			Queue<point> queue = new LinkedList<>();
			queue.offer(new point(0,0,map[0][0])); // 시작 지점을 큐에넣음  
			visit[0][0] = map[0][0];
			
			while(!queue.isEmpty()) { 
				point now = queue.poll();
				for (int i = 0; i < 4; i++) {
					int ny = now.y + my[i];
					int nx = now.x + mx[i];
					
					if(isRange(ny,nx) == false)
						continue;
					
					if(now.lope + map[ny][nx] < visit[ny][nx]) { // 현재지점의 까지 루피값과 다음지점의 루피값의 합이  방문배열이 가지고 있던 값보다 작으면 갱신
						visit[ny][nx] = now.lope + map[ny][nx];
						queue.offer(new point(ny,nx,visit[ny][nx]));
					} 
				}
				
			}
			
			System.out.println("Problem " +count + ": "+visit[N-1][N-1]);
			count++;
			
		}
		
	}



	
}
