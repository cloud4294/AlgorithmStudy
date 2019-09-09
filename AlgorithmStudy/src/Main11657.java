import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 11657번 - 타임머신
 * 
 * 		https://www.acmicpc.net/problem/11657
 */

public class Main11657 {

	static int N, M;
	static int[] dist;
	static bus[] map;
	static int INF = Integer.MAX_VALUE;
	
	static class bus{
		int src;
		int dst;
		int time;
		
		public bus(int src, int dst, int time) {
			super();
			this.src = src;
			this.dst = dst;
			this.time = time;
		}
	}
	
	public static boolean BellmanFord() { // 벨만 포드 
		
		for (int i = 1; i <= N; i++) {
			boolean flag = true;
			for (int j = 1; j <= M; j++) {
				
				if(dist[map[j].src] == INF)  
					continue;
				
				if(dist[map[j].src] + map[j].time < dist[map[j].dst]) { // 다음위치의 값이 현재위치 값 + 가중치 보다 크면 작은것으로 갱신
					dist[map[j].dst] = dist[map[j].src] + map[j].time;
					flag = false;
				}
			}
			if(flag == true) // 더 이상 갱신 되지 않으면 종료 
				return true;

		}
		
		
		return false; // 갱신이 계속 됬다면 음수 사이클 있음 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dist = new int[N+1];
		
		dist[1] = 0;
		for (int i = 2; i <= N; i++) {
			dist[i] = INF;
		}
		map = new bus[M+1];
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			map[i] = new bus(src, dst, time);
		}
		
		if(BellmanFord() == true) {
			for (int i = 2; i <= N; i++) {
				System.out.println(dist[i] == INF ? -1 : dist[i]);
			}			
		}else 
			System.out.println(-1);
		
		
		
	}

	

}
