import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * 		Baekjoon Online Judge 11279번 - 최대 힙
 * 
 * 		https://www.acmicpc.net/problem/11279
 */

public class Main11279 {
	
	
	static class num implements Comparable<num>{
		int i;

		public num(int i) {
			super();
			this.i = i;
		}

		@Override
		public int compareTo(num o) {
			
			return Integer.compare(o.i, this.i);
			
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<num> pq = new PriorityQueue<num>();
		
		for (int i = 0; i < N; i++) {
			num now = new num(Integer.parseInt(br.readLine()));
			
			if(now.i == 0) {
				if(pq.isEmpty()) {
					System.out.println(0);
				}else {
					System.out.println(pq.poll().i);
				}
				
			}else {
				pq.offer(now);
			}
		}
		
		

	}

}
