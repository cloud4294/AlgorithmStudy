import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2623번 - 음악프로그램
 * 
 *		https://www.acmicpc.net/problem/2623
 */

public class Main2623 {

	static int N;
	static List<Integer>[] link;
	static int[] pre;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		link = new ArrayList[N + 1];
		pre = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			link[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = 0;
			int dst = 0;
			int length = Integer.parseInt(st.nextToken());
			for (int j = 0; j < length; j++) {
				dst = Integer.parseInt(st.nextToken());
				if (src != 0) {
					link[src].add(dst);
					pre[dst]++;
				}
				src = dst;
			}
		}

		solve();
	}

	public static void solve() { // 위상정렬

		Queue<Integer> queue = new LinkedList<>();
		List<Integer> output = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			if (pre[i] == 0)
				queue.offer(i);
		}

		while (!queue.isEmpty()) {

			int now = queue.poll();
			output.add(now);

			for (int i = 0; i < link[now].size(); i++) {
				int next = link[now].get(i);
				
				pre[next]--;
				
				if(pre[next] == 0) 
					queue.offer(next);
			}
		}
		
		if(output.size() < N)
			System.out.println(0);
		else {
			for (int i = 0; i < output.size(); i++) {
				System.out.println(output.get(i));
			}
		}
	}

}
