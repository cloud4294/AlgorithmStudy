import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17286번 - 유미
 * 
 * 		https://www.acmicpc.net/problem/17286
 */

public class Main17286 {

	static List<Integer> list;

	static point[] position;

	static double min; 
	
	static class point {
		int x;
		int y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void solve() {

		if (list.size() == 4) {
			double length = 0;
			for (int i = 0; i < 3; i++) { // 거리를 합산, 현재 최소거리 합을 갱신 
				int src = list.get(i);
				int dst = list.get(i + 1);

				length += Math.sqrt(Math.pow(position[src].y - position[dst].y, 2)
						+ Math.pow(position[src].x - position[dst].x, 2));
				
				
			}
			min = Math.min(length, min);

			return;
		}

		for (int j = 1; j <= 3; j++) { // 순열 조합으로 가능한 모든경로 탐색 
			if (!list.contains(j)) {
				list.add(Integer.valueOf(j));
				solve();
				list.remove(Integer.valueOf(j));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		position = new point[4];
		list = new ArrayList<Integer>();
		min = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			position[i] = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		list.add(0);
		solve();

		System.out.println((int)min);
	}

}
