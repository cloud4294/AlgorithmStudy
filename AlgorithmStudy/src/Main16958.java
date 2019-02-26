import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 16958번 - 텔레포트
 * 
 * 		https://www.acmicpc.net/problem/16958
 */

public class Main16958 {

	static class node {
		int x;
		int y;

		public node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		node[] map = new node[N + 1];
		boolean[] tp = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (s == 1) {
				tp[i] = true;
			}
			map[i] = new node(x, y);
		} // 입력값 초기화, 텔레포트가 가능한지점을 tp배열에 저장 
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int dist = Math.abs(map[src].x - map[dst].x) + Math.abs(map[src].y - map[dst].y); // 텔레포트를 이용하지 않았을경우 기본 거리

			int d1 = Integer.MAX_VALUE;
			int d2 = Integer.MAX_VALUE;

			for (int j = 1; j <= N; j++) {
				if (tp[j] == true) {

					int shortcut = Math.abs(map[src].x - map[j].x) + Math.abs(map[src].y - map[j].y);
					d1 = Math.min(d1, shortcut);
					shortcut = Math.abs(map[dst].x - map[j].x) + Math.abs(map[dst].y - map[j].y);
					d2 = Math.min(d2, shortcut);
				}
			} // 시작점과 도착점에서 가장 가까운 텔레포트지점과의 거리를 구함 

			if (tp[src] == true) { // 시작 지점에 텔레포트가 가능할 경우
				dist = Math.min(dist, T + d2);
			} else if (tp[dst] == true) { // 도착 지점이 텔레포트가 가능할 경우
				dist = Math.min(dist, T + d1);
			} else if (tp[src] == true && tp[dst] == true) { // 시작 지점, 도착 지점 모두 텔레포트가 가능할 경우
				dist = Math.min(dist, T);
			} else { // 시작 지점 , 도착지점 모두 텔레포트가 불가능할 경우 
				dist = Math.min(dist, T + d1 + d2);
			}

			System.out.println(dist);
		}

	}

}
