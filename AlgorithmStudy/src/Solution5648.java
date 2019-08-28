import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * 		SW Expert Academy 5648 -  [모의 SW 역량테스트] 원자 소멸 시뮬레이션
 * 
 * 		https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRFInKex8DFAUo
 */

public class Solution5648 {

	static int N;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] visit;
	static List<atom> atoms;
	static int result;

	static boolean isRange(int y, int x) { // 범위 확인 메소드 
		if (y < 0 || y > 4000 || x < 0 || x > 4000)
			return false;
		return true;

	}

	static class atom {

		int x;
		int y;
		int dir;
		int k;

		public atom(int x, int y, int dir, int k) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.k = k;
		}

	}

	public static void solve() {

		for (int count = 0; count < 4002; count++) {

			for (int i = 0; i < atoms.size(); i++) {
				atom now = atoms.get(i);

				if (visit[now.y][now.x] == -1) { // 현재위치에서 충돌이 있었던 경우 
					visit[now.y][now.x] = 0;
					result += now.k;
					atoms.remove(now);
					i--;
					continue;
				}
				visit[now.y][now.x] = 0;
				int nx = now.x + dc[now.dir];
				int ny = now.y + dr[now.dir];

				if (isRange(ny, nx) == false) { // 범위를 벗어난 경우 
					atoms.remove(now);
					i--;
					continue;
				}

				if (visit[ny][nx] != 0) { // 다음위치에 원자와 충돌하는 경우 
					result += now.k;
					visit[ny][nx] = -1;
					atoms.remove(now);
					i--;
				} else { // 충돌하지 않는 경우
					visit[ny][nx] = now.k;
					now.x = nx;
					now.y = ny;
				}

			}
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		atoms = new ArrayList<>();	
		visit = new int[4001][4001]; // 0.5초 확인을 위해 배열을 두배로 늘림 , 재정의 하지 않아도 메소드 종료시 초기화 
		
		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(br.readLine());

			result = 0;
			for (int j = 1; j <= N; j++) {
				String[] input = br.readLine().split(" ");

				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				int dir = Integer.parseInt(input[2]);
				int k = Integer.parseInt(input[3]);

				x = x * 2 + 2000;
				y = y * 2 + 2000;

				if (dir == 0)
					dir = 2;
				else if (dir == 1)
					dir = 0;
				else if (dir == 2)
					dir = 3;
				else if (dir == 3)
					dir = 1;
				visit[y][x] = k;
				atoms.add(new atom(x, y, dir, k));
			}
			solve();
			bw.write("#" + i + " " + result + "\n");
			bw.flush();
		}
		bw.close();

	}

}
