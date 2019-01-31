import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 15685번 - 드래곤 커브
 * 
 * 		https://www.acmicpc.net/problem/15685
 */

public class Main15685 {

	static int[] my = { 0, -1, 0, 1 };
	static int[] mx = { 1, 0, -1, 0 };

	static boolean[][] map = new boolean[101][101];

	static List<Integer> list = new ArrayList<>();

	public static void solve(int x, int y, int d, int g) {

		if (g == -1) // -1번째 세대 까지 
			return;

		List<Integer> add = new ArrayList<>();

		map[x][y] = true;

		int ny = y;
		int nx = x;
		int nd = d;

		if (list.size() == 0) { //리스트가 비어있으면 현재 지점을 방문처리하고 방향을 리스트에 넣음 
			nx += mx[d];
			ny += my[d];
			list.add(nd);
			map[nx][ny] = true;
		} else { //리스트의 값을 역순으로 뽑아 반시계방향으로 회전하여 다음지점을 방문처리하고 방향을 추가될 리스트에 넣음 
			for (int i = list.size() - 1; i >= 0; i--) {
				nd = (list.get(i) + 1) % 4;
				nx += mx[nd];
				ny += my[nd];
				map[nx][ny] = true;
				add.add(nd);
			}

		}

		list.addAll(add); // 전체리스트에 이번세대에서 추가된 방향정보를 추가함 

		solve(nx, ny, nd, g - 1); // 마지막 지점부터 다시 드래곤 커브를 생성함 

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			solve(x, y, d, g); //드래곤 커브 생성
			list.clear(); // 전체 방향정보 리스트를 지움 
		} 

		int count = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == true && map[i + 1][j] == true && map[i][j + 1] == true && map[i + 1][j + 1] == true) {
					count++;
				}
			}
		}

		System.out.println(count);
	}

}
