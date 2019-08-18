import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 15657번 - N과 M(8)
 * 
 * 		https://www.acmicpc.net/problem/15657
 */

public class Main15657 {

	static int N, M;
	static int[] num;
	static List<Integer> output; 
	static BufferedWriter bw;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		output = new ArrayList<>();
		
		solve(0,0);
		
		bw.close();
	}

	public static void solve(int n, int count) throws IOException {
		
		if(count == M ) {
			for(int i : output) {
				bw.write(i+" ");
			}
			bw.write("\n");
			bw.flush();
			return;
		}
		
		for (int i = n; i < N; i++) {
			output.add(num[i]);
			solve(i,count+1);
			output.remove(Integer.valueOf(num[i]));
		}
		
	}

}
