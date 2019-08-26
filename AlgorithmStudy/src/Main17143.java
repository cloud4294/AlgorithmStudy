import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17143 - 낚시왕
 * 
 * 		https://www.acmicpc.net/problem/17143
 */

public class Main17143 {

	static int R, C, M;
	static shark[] sharks;
	static int[][] map;
	static int result;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class shark {
		int r;
		int c;
		int s;
		int d;
		int z;

		public shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;

		}

		

	}

	public static void solve(int hunter) {

		if (hunter > C)
			return;

		for (int i = 1; i <= R; i++) { // 같은 열에 상어가 있으면 잡음 
			if (map[i][hunter] != 0) {
				result += sharks[map[i][hunter]].z;
				sharks[map[i][hunter]] = null;
				map[i][hunter] = 0;
				break;
			}
		}

		int[][] next = new int[R + 1][C + 1];
		for (int i = 1; i <= M; i++) { // 상어 이동 

			if (sharks[i] == null)
				continue;
			shark now = sharks[i];

			int nr = now.r + dr[now.d] * now.s;
			int nc = now.c + dc[now.d] * now.s;
			
			while (nr < 1 || nr > R) {
				now.d = (now.d + 2) % 4;
				if (nr < 1) {
					nr = (nr - 2) * (-1);
				} else if (nr > R) {
					nr = nr - R;
					nr = R - nr ;
				}
			}

			while (nc < 1 || nc > C) {
				now.d = (now.d + 2) % 4;
				if (nc < 1) {
					nc = (nc - 2) * (-1);
				} else if (nc > C) {
					nc = nc - C;
					nc = C - nc ;
				}
			}
			

			if (next[nr][nc] != 0) { // 이동한 위치에 상어가 있으면 
				shark target = sharks[next[nr][nc]];

				if (target.z > now.z) { // 상대상어가 더 클때 
					sharks[i] = null;
				} else { // 작을때 
					sharks[next[nr][nc]] = null;
					sharks[i].r = nr;
					sharks[i].c = nc;
					next[nr][nc] = i;
				}
			} else { 
				sharks[i].r = nr;
				sharks[i].c = nc;
				next[nr][nc] = i;
			}
		}
		
		
		for (int i = 0; i <= R; i++) {
			map[i] = Arrays.copyOf(next[i], C + 1);
		}


		solve(hunter + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sharks = new shark[M+1];
		result = 0;
		map = new int[R + 1][C + 1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			if (d == 1)
				d = 0;
			else if (d == 2)
				d = 2;
			else if (d == 3)
				d = 1;
			else if (d == 4)
				d = 3;

			sharks[i] = new shark(r, c, s, d, z);
			map[r][c] = i;
			
		}

	
		solve(1);

		System.out.println(result);
	}

}
