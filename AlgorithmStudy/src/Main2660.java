import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 2660번 - 회장뽑기
 * 
 * 		https://www.acmicpc.net/problem/2660
 */

public class Main2660 {

	static int n;
	static List<Integer>[] conn;
	static int[] point;
	
	static class user {

		int index;
		int count;

		public user(int index, int count) {
			super();
			this.index = index;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		conn = new List[n + 1];
		point = new int[n + 1];
		int min = Integer.MAX_VALUE;
		int count = 0;
		
		for (int i = 1; i <= n; i++) {
			conn[i] = new ArrayList<>();
		}
		
		while (true) {
			String[] input = br.readLine().split(" ");
			int src = Integer.parseInt(input[0]);
			int dst = Integer.parseInt(input[1]);

			if (src == -1 && dst == -1)
				break;

			conn[src].add(dst);
			conn[dst].add(src);

		}

		for (int i = 1; i <= n; i++) {
			
			int now = find(i);
			if(now < min) {
				count = 1;
				min = now;
			}else if(now == min)
				count++;
		}
		
		System.out.println(min +" "+ count);
		for (int i = 1; i <= n; i++) {
			if(point[i] == min)
				System.out.print(i +" ");
		}
		System.out.println();
	}

	public static int find(int i) {
			
		boolean[] visit = new boolean[n+1];
		visit[i] = true;
		Queue<user> queue = new LinkedList<>();
		
		queue.offer(new user(i, 0));
		
		while(!queue.isEmpty()) {
			user now = queue.poll();
			if(now.count > point[i])
				point[i] = now.count;
			
			for (int j = 0; j < conn[now.index].size(); j++) {
				int next = conn[now.index].get(j);
				
				if(visit[next] == false) {
					visit[next] = true;
					queue.offer(new user(next, now.count+1));
				}
			}
		}
		
		return point[i];
	}

}
