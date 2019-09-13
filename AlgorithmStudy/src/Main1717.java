import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1717번 - 집합의 표현
 * 
 * 		https://www.acmicpc.net/problem/1717
 */


public class Main1717 {

	static int[] set;
	
	public static int find(int i) {
		
		if(set[i] == i)
			return i;
		else 
			return set[i] = find(set[i]);
	}
	
	public static void union(int src, int dst) { // 유니온 파인드
		
		int a = find(src);
		int b = find(dst);
		
		if(a != b) {
			set[a] = b;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		set = new int[n+1];
		for (int i = 0; i <= n; i++) {
			set[i] = i;
		}
		
		for (int i = 0; i < m; i++) { 
			st = new StringTokenizer(br.readLine());
			
			int com = Integer.parseInt(st.nextToken());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			if(com == 0) {
				union(src,dst);
			}else if(com == 1) {
				int a = find(src);
				int b = find(dst);
				if(a == b)
					System.out.println("YES");
				else 
					System.out.println("NO");
			}
		}

	}

	

}
