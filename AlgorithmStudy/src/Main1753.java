import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1753 - 최단경로
 * 
 * 		https://www.acmicpc.net/problem/1753
 */


public class Main1753 {
	
	static class vertex implements Comparable<vertex>{
		
		int dst;
		int weight;
		
		public vertex( int dst, int weight) {
			super();
			
			this.dst = dst;
			this.weight = weight;
		}

		@Override
		public int compareTo(vertex o) {
			
			if(this.weight < o.weight)
				return -1;
			else 
				return 1;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		int[] sp = new int[V+1];
		List<vertex>[] list = new ArrayList[V+1]; 
		for (int i = 1; i <= V; i++) {
			sp[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<>();
		}
	
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[src].add(new vertex(dst,weight));
		} // 각 정점에 대해서 연결되는 간선을 리스트로 연결 
		
		
		PriorityQueue<vertex> pq = new PriorityQueue<vertex>();
		pq.offer(new vertex(K, 0));
		sp[K] = 0;
		while(!pq.isEmpty()) {
			vertex now = pq.poll();
			
			for (int i = 0; i < list[now.dst].size(); i++) {
				vertex next = list[now.dst].get(i); // 현재지점에서 연결된 다음 지점
				
				if(sp[next.dst] > sp[now.dst] + next.weight) { // 다익스트라 
					sp[next.dst] = sp[now.dst] + next.weight;
					pq.offer(new vertex(next.dst, sp[next.dst]));
				}
			}
		} // 우선순위 큐로 간선의 길이가 짧은 것부터 연결
		
		for (int i = 1; i <= V; i++) {
			 
			bw.write(sp[i] == Integer.MAX_VALUE ? "INF\n" : Integer.toString(sp[i])+"\n");
			bw.flush();
		}
		
		bw.close();
		
		

	}

}
