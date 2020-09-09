import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1238번 - 파티
 * 
 * 		https://www.acmicpc.net/problem/1238
 */

public class Main1238 {

	static int N,M,X;
	static List<link>[] map;
	static Queue<Integer> queue;
	static int[] length;
	static class link{
		
		int dst;
		int dist;
		
		public link(int dst, int dist) {
			super();
			this.dst = dst;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		int result = 0;
		
		map = new ArrayList[N+1];
		queue = new LinkedList<>();
		length = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			map[src].add(new link(dst, dist));
		}
		
		for (int i = 1; i <= N; i++) {
			int sum = dijkstra(i,X);
			sum += dijkstra(X,i);
			result = Math.max(result, sum);
		}
		
		System.out.println(result);
	}

	public static int dijkstra(int start, int end) {
		
		for (int i = 1; i <= N; i++) {
			length[i] = Integer.MAX_VALUE;
		}
		
		length[start] = 0;
		
		queue.clear();
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			
			int now = queue.poll();
			
			for (int i = 0; i < map[now].size(); i++) {
				
				link next = map[now].get(i);
				
				if(length[now] + next.dist < length[next.dst]) {
					length[next.dst] = length[now] + next.dist;
					queue.offer(next.dst);
				}
			}
			
		}
		
		return length[end];
	}
	

}
