import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 		Baekjoon Online Judge 5719번 - 거의 최단 경로
 * 
 * 		https://www.acmicpc.net/problem/5719
 */
public class Main5719 {

	static int N, M, S, D;
	static int INF = Integer.MAX_VALUE;
	static List<bridge>[] bridges;
	static List<Integer>[] log;
	static int[] visit;

	static class bridge implements Comparable<bridge> {
		int src;
		int dst;
		int length;
		

		public bridge(int src, int dst, int length) {
			super();
			this.src = src;
			this.dst = dst;
			this.length = length;
		}

		@Override
		public int compareTo(bridge o) {
			if (length < o.length)
				return -1;
			return 1;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		bridges = new ArrayList[501];
		log = new ArrayList[501];
		visit = new int[501];
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0)
				return;
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				visit[i] = INF;
			}			
			for (int i = 0; i < N; i++) {
				bridges[i] = new ArrayList<>();
			}
			for (int i = 0; i < N; i++) {
				log[i] = new ArrayList<>();
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				bridges[s].add(new bridge(s, d, l));
			}

			
			dijkstra();
			
			removeShortCut();
			
			dijkstra();
			
			System.out.println(visit[D] == INF ? -1 : visit[D]);
		}

	}

	public static void removeShortCut() { // 최단경로 삭제
		
	
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(D);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int i = 0; i < log[now].size(); i++) {
				int next = log[now].get(i);
				
				for (int j = 0; j < bridges[next].size(); j++) {
					bridge find = bridges[next].get(j);
					if(find.dst == now) {
						bridges[next].remove(find); // 경로제거
						break;
					}
				}
				queue.offer(next);
			}
		}
		
	}

	public static void dijkstra() { // 다익스트라, 최단경로 찾음

		for (int i = 0; i < N; i++) {
			visit[i] = INF;
		}
		visit[S] = 0;
			
		PriorityQueue<bridge> pq = new PriorityQueue<>();
		for (int i = 0; i < bridges[S].size(); i++) {
			pq.offer(bridges[S].get(i));
		}

		while (!pq.isEmpty()) {
			bridge now = pq.poll();
			
			if(visit[now.src] + now.length > visit[now.dst])
				continue;
			else if (visit[now.src] + now.length < visit[now.dst]) {
				visit[now.dst] = visit[now.src] + now.length;
				
				log[now.dst].clear(); 
				log[now.dst].add(now.src); // 이전 지점 갱신	
				for (int i = 0; i < bridges[now.dst].size(); i++) {
					pq.offer(bridges[now.dst].get(i));
				}
				
			}	
			else if (visit[now.src] + now.length == visit[now.dst]) {
				log[now.dst].add(now.src);// 이전 지점 추가				
				for (int i = 0; i < bridges[now.dst].size(); i++) {
					pq.offer(bridges[now.dst].get(i));
				}
				
			}

		}
		
	}

}
