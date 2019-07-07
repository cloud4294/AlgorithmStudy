import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * 		Baekjoon Online Judge 1697번 - 숨바꼭질
 * 
 * 		https://www.acmicpc.net/problem/1697
 */


public class Main1697 {

	public static boolean isRange(int n) {
		if (n < 0 || n > 100000)
			return false;
		return true;
	}

	static class pos {
		int n;
		int count;

		public pos(int n, int count) {
			super();
			this.n = n;
			this.count = count;
		}
	}

	static Queue<pos> queue;
	static boolean[] visit;

	public static void add(int next, int count) {
		if (isRange(next) == false)
			return;
		if (visit[next] == false) {
			visit[next] = true;
			queue.offer(new pos(next, count));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		visit = new boolean[100001];
		queue = new LinkedList<pos>();

		visit[N] = true;
		queue.offer(new pos(N, 0));
		while (!queue.isEmpty()) { // BFS 시행
			pos now = queue.poll();
			if(now.n == K) {
				System.out.println(now.count);
				break;
			}
			
			int next = now.n + 1;
			add(next, now.count + 1);
			next = now.n - 1;
			add(next, now.count + 1);
			next = now.n * 2;
			add(next, now.count + 1);

		}
	}

}
