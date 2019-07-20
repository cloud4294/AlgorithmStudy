import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 *		Baekjoon Online Judge 2979 - 트럭 주차
 * 
 * 		https://www.acmicpc.net/problem/2979
 */

public class Main2979 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] time = new int[101];
		int result = 0;
		
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			
			for (int j = src; j < dst; j++) 
				time[j]++;
		}
		
		for (int i = 1; i <= 100; i++) {
			if(time[i] == 1)
				result += A;
			else if(time[i] == 2)
				result += B*2;
			else if(time[i] == 3)
				result += C*3;
		}
		
		System.out.println(result);

	}

}
