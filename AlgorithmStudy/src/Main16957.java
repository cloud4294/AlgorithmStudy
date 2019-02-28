import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/*
 * 		Baekjoon Online Judge 16957번 - 체스판 위의 공
 * 
 * 		https://www.acmicpc.net/problem/16957
 */

public class Main16957 {

	static int[] my = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] mx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[][] map;
	static int[][] count;
	static int R, C;
	static node[][] nodemap;

	static class node {

		int y;
		int x;

		public node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	} // 좌표 클래스

	public static boolean isRange(int ny, int nx) {
		if (ny < 0 || ny >= R || nx < 0 || nx >= C)
			return false;
		return true;
	} // 범위확인 메소드

	public static node solve(int i, int j) {
		if (nodemap[i][j] != null) {
			count[nodemap[i][j].y][nodemap[i][j].x]++;
			return nodemap[i][j];
		} // 방문한적이 있는 지점이면 최종적으로 도달했던 지점을 리턴 

		int min = map[i][j];
		int y = -1;
		int x = -1;
		for (int k = 0; k < 8; k++) {
			int ny = i + my[k];
			int nx = j + mx[k];

			if (isRange(ny, nx) == false)
				continue;

			if (map[ny][nx] < min) {
				min = map[ny][nx];
				y = ny;
				x = nx;
			}
		} // 현재위치에서 인접한 지점중 가장 작은 값을 가진 지점을 찾음

		if (min == map[i][j]) {
			count[i][j]++;
			return new node(i, j);
		} // 현재지점이 가장 작은 값이면 현재위치의 구슬 갯수를 증가시키고 현재지점을 리턴 

		return nodemap[i][j] = solve(y, x); // 현재지점에 저장하며 다음위치의 값을 리턴 
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		count = new int[R][C];
		nodemap = new node[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력값 초기화 

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				solve(i, j);
			}
		} // 모든지점을 체크하여 구슬이 도착한지점을 찾음 

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				bw.write(count[i][j] + " ");
			}
			bw.flush();
			bw.write("\n");
		}
		bw.close();

	}

}
