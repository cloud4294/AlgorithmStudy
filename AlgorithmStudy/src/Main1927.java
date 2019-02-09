import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * 		Baekjoon Online Judge 1927번 - 최소 힙
 * 
 * 		https://www.acmicpc.net/problem/1927
 */

public class Main1927 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(br.readLine());
			
			if(now == 0) {
				if(pq.isEmpty()) {
					System.out.println(0);
				}else {
					System.out.println(pq.poll());
				}
				
			}else {
				pq.offer(now);
			}
		}
		
		

	}

}
