import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*	
 * 		Baekjoon Online Judge 1931번 - 회의실배정
 * 
 * 		https://www.acmicpc.net/problem/1931
 */

public class Main1931 {

	static class schadule implements Comparable<schadule>{
		int src;
		int dst;
		public schadule(int src, int dst) {
			super();
			this.src = src;
			this.dst = dst;
		}
		@Override
		public int compareTo(schadule o) { // 회의 끝나는 시간 기준 오름차순 정렬, 같을시 시작시간 오름차순 정렬 
			
			if(o.dst < dst) 
				return 1;
			else if(o.dst == dst) {
				if(o.src < src)
					return 1;
				else 
					return -1;
			}
			return -1;
		}	
		

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<schadule> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			int src = Integer.parseInt(line[0]);
			int dst = Integer.parseInt(line[1]);
			
			pq.add(new schadule(src, dst));
		}
		
		int end = 0;
		int count = 0;
		while(!pq.isEmpty()) { // 그리디 
			schadule now = pq.poll();
			if(end <= now.src ) {
				count++;
				end = now.dst;
				
			}
		}
		
		System.out.println(count);
		
	}

}
