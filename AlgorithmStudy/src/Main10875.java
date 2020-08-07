import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 		Baekjoon Online Judge 10875번 - 뱀
 * 
 * 		https://www.acmicpc.net/problem/10875
 */

public class Main10875 {

	static int L;
	static point head;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static List<line> map;

	static class point {

		long r;
		long c;
		int dir;
		long time;

		public point(long r, long c, int dir, long time) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.time = time;
		}

	}

	static class line {

		long r1;
		long c1;
		long r2;
		long c2;

		public line(long r1, long c1, long r2, long c2) {
			super();
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		L = Integer.parseInt(br.readLine());

		int n = Integer.parseInt(br.readLine());
		map = new ArrayList<>();
		head = new point(0, 0, 1, 0);

		boolean flag = true;
		for (int i = 0; i < n; i++) {
			String[] com = br.readLine().split(" ");
			long t = Long.parseLong(com[0]);
			String dir = com[1];

			if (flag == false)
				continue;

			if (solve(t, dir) == false)
				flag = false;
		}
		if (flag == true) {
			solve(2 * L + 1, "");
		}
		System.out.println(head.time);
	}

	public static boolean solve(long t, String dir) {

		long time = Long.MAX_VALUE;

		long nr = head.r + t * dr[head.dir];
		long nc = head.c + t * dc[head.dir];

		if (isRange(nr, nc) == false) {  // 벽에 부딪 히는 경우
			long length = 0;
			if (head.dir == 0) {
				length = Math.abs(-(L+1) - head.r);
			} else if (head.dir == 1) {
				length = Math.abs((L+1) - head.c);
			} else if (head.dir == 2) {
				length = Math.abs((L+1) - head.r);
			} else if (head.dir == 3) {
				length = Math.abs(-(L+1) - head.c);
			}
			time = Math.min(time, length);
		}

		map.add(new line(head.r, head.c, nr, nc)); // 경로를 선으로 저장

		for (int i = 0; i < map.size() - 2; i++) { // 뱀의 몸에 부딪히는 경우 
			if (isCollision(map.get(i), map.get(map.size() - 1)) || isCollision(map.get(map.size() - 1), map.get(i))) {
				long length = 0;
				if (map.get(i).r1 == map.get(i).r2 && map.get(map.size() - 1).c1 == map.get(map.size() - 1).c2) {
					long r = map.get(i).r1;
					length = Math.abs(head.r - r);
				} else if (map.get(i).c1 == map.get(i).c2 && map.get(map.size() - 1).r1 == map.get(map.size() - 1).r2) {
					long c = map.get(i).c1;
					length = Math.abs(head.c - c);
				} else if (map.get(i).r1 == map.get(i).r2) {
					length = Math.min(Math.abs(head.c - map.get(i).c1), Math.abs(head.c - map.get(i).c2));
				} else if (map.get(i).c1 == map.get(i).c2) {
					length = Math.min(Math.abs(head.r - map.get(i).r1), Math.abs(head.r - map.get(i).r2));
				}
				time = Math.min(time, length);
			}
		}

		if (time != Long.MAX_VALUE) { // 부딫히지 않는 경우 
			head.time += time;
			return false;
		}

		head.r = nr;
		head.c = nc;
		head.time += t;

		if (dir.equals("L")) {
			head.dir = (head.dir + 3) % 4;
		} else if (dir.equals("R")) {
			head.dir = (head.dir + 1) % 4;
		}

		return true;
	}

	public static boolean isCollision(line line1, line line2) { // 두 선이 접점이 있는 지 확인

		if (line1.r1 == line1.r2 && line2.c1 == line2.c2) {
			if (((line2.c1 >= line1.c1 && line2.c1 <= line1.c2) || (line2.c1 >= line1.c2 && line2.c1 <= line1.c1))
					&& ((line1.r1 >= line2.r1 && line1.r1 <= line2.r2)
							|| (line1.r1 >= line2.r2 && line1.r1 <= line2.r1)))
				return true;
		} else if (line1.r1 == line1.r2 && line2.r1 == line2.r2 && line1.r1 == line2.r2) {
			if ((line1.c1 >= line2.c1 && line1.c1 <= line2.c1) || (line1.c2 >= line2.c1 && line1.c2 <= line2.c2))
				return true;
		} else if (line1.c1 == line1.c2 && line2.c1 == line2.c2 && line1.c1 == line2.c2) {
			if ((line1.r1 >= line2.r1 && line1.r1 <= line2.r1) || (line1.r2 >= line2.r1 && line1.r2 <= line2.r2))
				return true;
		}

		return false;
	}

	public static boolean isRange(long nr, long nc) { // 뱀이 벗어 났는지 확인 
		if (nr < (-1) * L || nr > L || nc < (-1) * L || nc > L)
			return false;
		return true;
	}

}
