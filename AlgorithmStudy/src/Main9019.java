import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 9019 - DSLR
 * 
 * 		https://www.acmicpc.net/problem/9019
 */

public class Main9019 {

	static class command {
		int num;
		String com;

		public command(int num, String com) {
			super();
			this.num = num;
			this.com = com;
		}
	}

	static int src, dst;
	static String result;
	static boolean[] visit;

	public static void solve() {

		Queue<command> queue = new LinkedList<>();
		visit[src] = true;
		queue.offer(new command(src, ""));
		
		while (!queue.isEmpty()) { // BFS
			command now = queue.poll();
			if(now.num == dst) {
				System.out.println(now.com);
				break;
			}

			int next = (now.num * 2) % 10000;
			
			if (visit[next] == false) {
				visit[next] = true;
				queue.offer(new command(next, now.com + "D"));
			}

			next = now.num - 1 == -1 ? 9999 : now.num - 1;
			
			if (visit[next] == false) {
				visit[next] = true;
				queue.offer(new command(next, now.com + "S"));
			}
			
			next = (now.num % 1000) * 10 + now.num / 1000;
			
			if (visit[next] == false) {
				visit[next] = true;
				queue.offer(new command(next, now.com + "L"));
			}
			
			next = (now.num / 10) + (now.num % 10) * 1000;
			
			if (visit[next] == false) {
				visit[next] = true;
				queue.offer(new command(next, now.com + "R"));
			}


		}
		

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			String[] input = br.readLine().split(" ");

			visit = new boolean[10000];
			src = Integer.parseInt(input[0]);
			dst = Integer.parseInt(input[1]);

			solve();
		}

	}

}
