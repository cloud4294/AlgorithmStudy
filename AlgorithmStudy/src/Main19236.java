import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 		Baekjoon Online Judge 19236번 - 청소년 상어
 * 
 * 		https://www.acmicpc.net/problem/19236
 */

public class Main19236 {

	static class info implements Comparable<info> {
		int r;
		int c;
		int index;
		int dir;

		public info(int r, int c, int index, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.index = index;
			this.dir = dir;
		}

		@Override
		public int compareTo(info o) {

			if (o.index < this.index)
				return 1;
			return -1;

		}
	}

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int result;
	static List<info> order;
	static info shark;

	public static void moveFish(int value) {
		Collections.sort(order);

		List<info> nextOrder = new ArrayList<>();
		
		for (int i = 0; i < order.size(); i++) {
			nextOrder.add(order.get(i));			
		}

		
		for (int i = 0; i < order.size(); i++) {
			info now = order.get(i);
			int dir = now.dir;

			if (now.index <= 0 && now.dir < 0)
				continue;

			for (int j = 0; j < 8; j++) {
				int nr = now.r + dr[dir];
				int nc = now.c + dc[dir];
				if (range(nr, nc) == false || (nr == shark.r && nc == shark.c))
					dir = (dir + 1) % 8;
				else {
					for (int k = 0; k < order.size(); k++) {
						info target = order.get(k);
						
						if (nr == target.r && nc == target.c) {

							order.remove(now);
							order.add(i,new info(now.r, now.c, target.index, target.dir));
							order.remove(target);
							order.add(k,new info(target.r, target.c, now.index, dir));
							Collections.sort(order);
							break;
						}
					}
					break;
				}
			}
		}
		
		moveShark(value);
		
		order.clear();
	
		for (int i = 0; i < nextOrder.size(); i++) {
			order.add(nextOrder.get(i));			
		}
	}

	public static void moveShark(int value) {

		int nr = shark.r;
		int nc = shark.c;
		boolean flag = false;
		info nextShark = null;
		
		while (true) {
			nr = nr + dr[shark.dir];
			nc = nc + dc[shark.dir];

			if (range(nr, nc) == false)
				break;
			else {
				for (int i = 0; i < order.size(); i++) {
					info now = order.get(i);
					if(now.index <= 0 && now.dir < 0)
						continue;
					if (now.r == nr && now.c == nc) {
						nextShark = new info(shark.r,shark.c,shark.index,shark.dir);
						shark = new info(now.r, now.c, now.index, now.dir);
						info blank = new info(now.r, now.c, 0, -1);
						
						List<info> nextOrder = new ArrayList<>();
						for (int j = 0; j < order.size(); j++) {
							nextOrder.add(order.get(j));
						}
						order.remove(now);
						order.add(blank);
						Collections.sort(order);
						
						flag = true;
						moveFish(value + now.index);
						order.clear();
						for (int j = 0; j < nextOrder.size(); j++) {
							order.add(nextOrder.get(j));
						}
						shark = new info(nextShark.r,nextShark.c,nextShark.index,nextShark.dir);
						break;
					}
				}
			}
		}

		if (flag == false) {

			result = Math.max(result, value);
			return;
		}
	}

	public static boolean range(int nr, int nc) {
		if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4)
			return false;
		return true;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		order = new ArrayList<>();
		result = 0;
		shark = null;
		
		
		for (int i = 0; i < 4; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				if (i == 0 && j == 0) {
					shark = new info(i, j, Integer.parseInt(input[2 * j]), Integer.parseInt(input[2 * j + 1]) - 1);
					order.add(new info(i, j, 0, -1));
				}else 
					order.add(new info(i, j, Integer.parseInt(input[2 * j]), Integer.parseInt(input[2 * j + 1]) - 1));
			}
		}

		moveFish(shark.index);
		System.out.println(result);

	}

}
