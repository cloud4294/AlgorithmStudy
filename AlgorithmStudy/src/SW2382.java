import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 		SW Expert Academy 2382.[모의 SW 역량테스트] -  미생물 격리
 * 
 * 		https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl
 */

public class SW2382 {

	static int N, res;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	
	
	static class mi {
		int r;
		int c;
		int cnt;
		int dir;

		public mi(int r, int c, int cnt, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}

	}

	public static void solve(List<mi> mis, int m) {

		if (m == 0) {
			int count =0 ;
			for (int i = 0; i < mis.size(); i++) {
				count += mis.get(i).cnt;
			}
			
			res = count;
			
			
			return;
		}

		int[][] count = new int[N][N];
		int[][] sum = new int[N][N];
		int[][] direction = new int[N][N];
		
		List<mi> next = new ArrayList<>();
		
		for (int i = 0; i < mis.size(); i++) {

			mi now = mis.get(i);
			
			now.r += dr[now.dir];
			now.c += dc[now.dir];
			
			// 리스트의 미생물 이동시 벽에 부딛혔을시
			if(now.r == 0 || now.r == N-1 || now.c == 0 || now.c == N-1 ) {
				now.cnt /= 2;
				now.dir = (now.dir+2) % 4;
				
				sum[now.r][now.c] = now.cnt;
				direction[now.r][now.c] = now.dir;
				continue;
			}	
			
			

			// 리스트의 미생물 이동시 미생물이 합쳐지는 경우
			if(now.cnt > count[now.r][now.c]) {
				count[now.r][now.c] = now.cnt;
				sum[now.r][now.c] += now.cnt;
				direction[now.r][now.c] = now.dir;	
			}else {
				sum[now.r][now.c] += now.cnt;
			}

		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(sum[i][j] > 0) {
					next.add(new mi(i,j,sum[i][j],direction[i][j]));
				}
			
			}
		
		}

		
		solve(next,m-1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			res = 0;
			N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			List<mi> mis = new ArrayList<>();

			for (int i = 0; i < K; i++) {
				int r = sc.nextInt();
				int c = sc.nextInt();
				int cnt = sc.nextInt();
				int dir = sc.nextInt();
				// 상: 1, 하: 2, 좌: 3, 우: 4)
				if(dir == 1) {
					dir = 0;
				}else if(dir == 2) {
					dir = 2;
				}else if(dir == 3) {
					dir = 3;
				}else if(dir == 4) {
					dir = 1;
				}
				
				mis.add(new mi(r, c, cnt, dir));
			}

			solve(mis,M);
			System.out.println("#"+t +" "+res);
		}

	}

}
