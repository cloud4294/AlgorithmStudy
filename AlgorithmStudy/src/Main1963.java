import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 1963번 - 소수 경로
 * 
 * 		https://www.acmicpc.net/problem/1963
 */

public class Main1963 {

	static boolean[] prime;

	static class number {
		int[] num;
		int count;

		public number(int[] num, int count) {
			super();
			this.num = num;
			this.count = count;
		}

	}

	private static void mkPrime() { // 에라스토테네스의 체, 소수배열 생성

		prime[1] = true;
		for (int i = 2; i * i < 10000; i++) {
			for (int j = i + i; j < 10000; j = j + i) {
				prime[j] = true;
			}
		}

	} 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		prime = new boolean[10000];

		mkPrime();

		for (int i = 0; i < T; i++) {
			String[] input = br.readLine().split(" ");
			String src = input[0];
			boolean[] visit = new boolean[10000];
			int[] start = new int[4];
			start[0] = Integer.parseInt(src.substring(0, 1));
			start[1] = Integer.parseInt(src.substring(1, 2));
			start[2] = Integer.parseInt(src.substring(2, 3));
			start[3] = Integer.parseInt(src.substring(3, 4));
			number startNum = new number(start, 0);

			String dst = input[1];
			int[] end = new int[4];
			end[0] = Integer.parseInt(dst.substring(0, 1));
			end[1] = Integer.parseInt(dst.substring(1, 2));
			end[2] = Integer.parseInt(dst.substring(2, 3));
			end[3] = Integer.parseInt(dst.substring(3, 4));
			number endNum = new number(end, 0);

			Queue<number> queue = new LinkedList<>();
			visit[start[0] * 1000 + start[1] * 100 + start[2] * 10 + start[3]] = true;
			queue.offer(startNum);

			int result = -1;
			while (!queue.isEmpty()) { // bfs 시행
				number now = queue.poll();
				
				if (now.num[0] == endNum.num[0] && now.num[1] == endNum.num[1] && now.num[2] == endNum.num[2]
						&& now.num[3] == endNum.num[3]) {
					result = now.count;
					break;
				}

				for (int j = 0; j < 4; j++) {
					for (int j2 = 0; j2 < 10; j2++) {
						int[] next = new int[4];
						next = Arrays.copyOf(now.num, 4);

						next[j] = j2;
						if (j == 0 && j2 == 0)
							continue;

						if (prime[next[0] * 1000 + next[1] * 100 + next[2] * 10 + next[3]] == false
								&& visit[next[0] * 1000 + next[1] * 100 + next[2] * 10 + next[3]] == false) {
							visit[next[0] * 1000 + next[1] * 100 + next[2] * 10 + next[3]] = true;
							queue.offer(new number(next, now.count + 1));
						}
					}
				}

			}

			if (result == -1)
				System.out.println("Impossible");
			else
				System.out.println(result);

		}

	}

}
