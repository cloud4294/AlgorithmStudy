import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 5014 - 스타트링크
 * 
 * 		https://www.acmicpc.net/problem/5014
 */

public class Main5014 {

	static class pos {
		int current;
		int step;

		public pos(int current, int step) {
			super();
			this.current = current;
			this.step = step;
		}
	}// 현재위치, 이동횟수 

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int F = sc.nextInt();
		int S = sc.nextInt();
		int G = sc.nextInt();
		int U = sc.nextInt();
		int D = sc.nextInt();

		boolean[] visit = new boolean[F+1];
		Queue<pos> queue = new LinkedList<>();
		visit[S] = true;
		queue.offer(new pos(S, 0));

		while (!queue.isEmpty()) { //BFS 시행

			pos now = queue.poll();
			if(now.current  == G) {
				System.out.println(now.step);
				return;
			}

			if (now.current + U <= F && visit[now.current + U] == false) {
				visit[now.current + U] = true;
				queue.offer(new pos(now.current + U, now.step + 1));
			}

			if (now.current - D > 0 && visit[now.current - D] == false) {
				visit[now.current - D] = true;
				queue.offer(new pos(now.current - D, now.step + 1));
			}
		}

		System.out.println("use the stairs");
	}

}
