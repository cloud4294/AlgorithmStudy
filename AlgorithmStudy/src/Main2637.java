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
 * 		Baekjoon Online Judge 2637번 - 장난감조립
 * 
 * 		https://www.acmicpc.net/problem/2637
 */

public class Main2637 {

	static class edge { // 조립식 클래스 

		int src;
		int dst;
		int dist;

		public edge(int src, int dst, int dist) {
			super();
			this.src = src;
			this.dst = dst;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 완제품 번호
		int M = Integer.parseInt(br.readLine()); // 조립식 수 

		List<edge>[] map = new ArrayList[N + 1]; // 조립식을 인덱스 별로 나타내는 리스트
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}

		int[] count = new int[N]; // 조립식에 필요한 부품 갯수 

		StringTokenizer st = null; 

		for (int i = 0; i < M; i++) { // 조립식 초기화 
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[x].add(new edge(x, y, z));
			count[y]++;
		}

		solve(N, map, count);
	}

	public static void solve(int start, List<edge>[] map, int[] count) {

		Queue<edge> queue = new LinkedList<>();
		PriorityQueue<Integer> result = new PriorityQueue<Integer>();
		int[] value = new int[start + 1];
		for (int i = 0; i < map[start].size(); i++) {
			queue.offer(map[start].get(i));
		}

		while (!queue.isEmpty()) {

			edge now = queue.poll();

			if (value[now.src] == 0) // 이전 지점의 조립식이 없을 경우
				value[now.dst] += now.dist;
			else // 이전 지점의 조립식이 있을 경우 이전 지점의 배수만큼 더함 
				value[now.dst] += (value[now.src] * now.dist); 

			count[now.dst]--;
			
			if (count[now.dst] == 0) { // 이전 지점과 연결된 모든 간선을 처리한 후
				if(map[now.dst].size() == 0) // 다음 간선이 없을 경우 출력에 추가
					result.offer(now.dst);
				
				for (int i = 0; i < map[now.dst].size(); i++) { // 다음 간선이 있을 경우 큐에 추가 
					queue.offer(map[now.dst].get(i));
				}
			}

		}
		
		while(!result.isEmpty()) { // 결과 출력
			System.out.println(result.peek()+" "+value[result.poll()]);
		}
	}

}
