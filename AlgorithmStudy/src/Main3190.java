import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 3190번 - 뱀
 * 
 * 		https://www.acmicpc.net/problem/3190
 */

public class Main3190 {

	static int N;
	static int[][] map;

	static int[] my = { 0, 1, 0, -1 };
	static int[] mx = { 1, 0, -1, 0 };

	static class point {
		int y;
		int x;

		public point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	} // 좌표를 저장할 클래스

	static class com {
		int time;
		String dir;

		public com(int time, String dir) {
			super();
			this.time = time;
			this.dir = dir;
		}
	} // 명령을 수행할 시간과 방향을 저장하는 클래스

	public static boolean isRange(int y, int x) {

		if (y <= 0 || y > N || x <= 0 || x > N)
			return false;
		return true;

	}// 범위 밖으로 나가는지 확인하는 메소드

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = 2;
		}// 사과 위치 초기화 
		List<com> list = new ArrayList<>();
		List<point> snake = new ArrayList<>();
		snake.add(new point(1, 1)); // 뱀의 초기위치
		map[1][1] = 1;
		int time = 0;
		int sdir = 0;
		int L = Integer.parseInt(br.readLine());

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int turntime = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			list.add(new com(turntime, dir));

		} // 명령어 리스트 초기화 
		int index = 0;
		com c = list.get(index);
		while (true) {
			time++;

			point head = snake.get(0);
			point tail = snake.get(snake.size() - 1);
			int ny = head.y + my[sdir];
			int nx = head.x + mx[sdir];

			if (isRange(ny, nx) == false) { // 다음지역이 벽이면 시간을 출력하고 종료 
				System.out.println(time);
				return;
			}

			if (map[ny][nx] == 2) { // 사과가있으면 뱀 리스트에 가장앞부분에 다음 지역 추가 
				snake.add(0, new point(ny, nx));
				map[ny][nx] = 1;
			} else if (map[ny][nx] == 0) { // 사과가 없으면 가장앞부분에 다음지역을 추가하고 꼬리부분을 삭제 

				map[tail.y][tail.x] = 0;
				snake.remove(tail);
				snake.add(0, new point(ny, nx));
				map[ny][nx] = 1;

			} else if (map[ny][nx] == 1) { // 다음지역이 뱀의 몸이면 시간을 출력하고 종료
				System.out.println(time);
				return;
			}

			if (time == c.time) { // 명령에 해당하는 시간이되면 방향을 변경하고 다음 명령을 기다림
				if (c.dir.equals("D"))
					sdir = (sdir + 1) % 4;
				else if (c.dir.equals("L"))
					sdir = (sdir + 3) % 4;

				if (index + 1 < list.size())
					c = list.get(++index);

			}

		}
	}

}
