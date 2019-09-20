import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1976 - 여행 가자
 * 
 * 		https://www.acmicpc.net/problem/1976
 */

public class Main1976 {

	static int[] set;
	
	private static int find(int i) {
		if(set[i] == i)
			return i;
		else
			return set[i] = find(set[i]);
	}
	
	public static void union(int i, int j) {
		
		int src = find(i);
		int dst = find(j);
		
		if(src != dst) {
			set[src] = dst;
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		set = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			set[i] = i;
		}

		for (int i = 1; i <= N; i++) { // 유니온 파인드
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					union(i,j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int result = -1;
		boolean flag = true;
		for (int i = 0; i < M; i++) { // 같은 집합인지 확인
			int now = Integer.parseInt(st.nextToken());
			if(result == -1) {
				result = find(now);				
			}else if(find(now) != result) {
				flag = false;
				break;
			}
			
		}
		
		if(flag == true)
			System.out.println("YES");
		else
			System.out.println("NO");
		
	}


	

}
