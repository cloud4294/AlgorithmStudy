import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 12761 - 돌다리
 * 
 * 		https://www.acmicpc.net/problem/12761
 */

public class Main12761 {

	static int A, B, N, M;
	static boolean[] visit;

	// +1 , -1 , +A, -A , +B ,-B ,*A ,*B

	static class point {
		int x;
		int count;

		public point(int x, int count) {
			super();
			this.x = x;
			this.count = count;
		}
	} // 현재위치와 이동횟수를 저장할 클래스

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		visit = new boolean[100001];
		A = sc.nextInt();
		B = sc.nextInt();
		N = sc.nextInt();
		M = sc.nextInt();

		solve();
	}

	public static void solve() { // BFS 시행

		Queue<point> queue = new LinkedList<>();
		queue.offer(new point(N, 0));
		visit[N] = true;
		while (!queue.isEmpty()) {
			point now = queue.poll();
			if (now.x == M) {
				System.out.println(now.count);
				break;
			}

			if (now.x + 1 <= 100000 && visit[now.x + 1] == false) {
				visit[now.x + 1] = true;
				queue.offer(new point(now.x + 1, now.count + 1));
			}
			if (now.x - 1 >= 0 && visit[now.x - 1] == false) {
				visit[now.x - 1] = true;
				queue.offer(new point(now.x - 1, now.count + 1));
			}
			if (now.x + A <= 100000 && visit[now.x + A] == false) {
				visit[now.x + A] = true;
				queue.offer(new point(now.x + A, now.count + 1));
			}
			if (now.x - A >= 0 && visit[now.x - A] == false) {
				visit[now.x - A] = true;
				queue.offer(new point(now.x - A, now.count + 1));
			}
			if (now.x * A <= 100000 && visit[now.x * A] == false) {
				visit[now.x * A] = true;
				queue.offer(new point(now.x * A, now.count + 1));
			}
			if (now.x + B <= 100000 && visit[now.x + B] == false) {
				visit[now.x + B] = true;
				queue.offer(new point(now.x + B, now.count + 1));
			}
			if (now.x - B >= 0 && visit[now.x - B] == false) {
				visit[now.x - B] = true;
				queue.offer(new point(now.x - B, now.count + 1));
			}
			if (now.x * B <= 100000 && visit[now.x * B] == false) {
				visit[now.x * B] = true;
				queue.offer(new point(now.x * B, now.count + 1));
			}

		}

	}

}
