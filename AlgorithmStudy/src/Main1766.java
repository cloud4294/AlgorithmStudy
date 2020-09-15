import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1766번 - 문제집
 * 		
 * 		https://www.acmicpc.net/problem/1766
 */

public class Main1766 {

	static int N;
	static int[] count;
	static List<Integer>[] link;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(st.nextToken());
		
		link = new ArrayList[N+1];
		count = new int[N+1];
	
		for (int i = 1; i <= N; i++) {
			link[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			link[src].add(dst);
			count[dst]++;
		}
		
		solve();
		
	}

	public static void solve() { // 위상 정렬
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 1; i <= N; i++) {
			if(count[i] == 0)
				pq.offer(i);
		}
		
		while(!pq.isEmpty()) {
			
			int now = pq.poll();
			System.out.print(now+ " ");
			
			for (int i = 0; i < link[now].size(); i++) {
				int next = link[now].get(i);
				count[next] --;
				if(count[next] == 0)
					pq.offer(next);
			}
		}
		
		
	}

}
