
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 1516번 - 게임 개발
 * 
 * 		https://www.acmicpc.net/problem/1516
 */

public class Main1516 {

	static int N;
	static int[] time;
	static int[] pre;
	static List<Integer>[] link;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		time = new int[N+1];
		pre = new int[N+1];
		link = new ArrayList[N+1];
	
		for (int i = 1; i <= N; i++) {
			link[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N; i++) {
			String[] input = br.readLine().split(" ");
			time[i] = Integer.parseInt(input[0]);
			
			int count = 0;
			for (int j = 1; j < input.length; j++) {
				int src = Integer.parseInt(input[j]);
				if(src == -1)
					break;
				link[src].add(i);
				count++;
			}
			
			pre[i] = count;
			
		}
		
		int[] result = solve();
		
		for (int i = 1; i < result.length; i++) {
			bw.write(result[i]+ "\n");
		}
		bw.flush();
		bw.close();
		
	}

	public static int[] solve() { // 위상정렬, 다이나믹 프로그래밍
		
		Queue<Integer> queue = new LinkedList<>();
		int[] dp = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			if(pre[i] == 0) {
				queue.offer(i);
				dp[i] = time[i];
			}
		}
		
		while(!queue.isEmpty()) {
			
			int now = queue.poll();
			
			for (int i = 0; i < link[now].size(); i++) {
				
				int next = link[now].get(i);
				
				pre[next]--;
				dp[next] = Math.max(dp[next], dp[now] + time[next]);

				if(pre[next] == 0) {			
					queue.offer(next);
				}
			}
			
		}
		
		return dp;
	}

}
