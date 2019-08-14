import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1325 - 효율적인 해킹
 * 
 * 		https://www.acmicpc.net/problem/1325
 */


public class Main1325 {

	static int N, M;
	static List<Integer>[] map;
	static int[] dist;
	static boolean[] visit;

	public static void solve(int i) { // DFS 

		visit[i] = true;

		for (int j : map[i]) {
			if (visit[j] == false) {
				dist[j]++;
				solve(j);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new ArrayList[N + 1];
		dist = new int[N + 1];
		int max = 0;
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			map[src].add(dst);
		}

		for (int i = 1; i <= N; i++) {
			visit = new boolean[N + 1];
			solve(i);
		}
		
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, dist[i]);
		}

		for (int i = 1; i <= N; i++) {
			if (dist[i] == max)
				bw.write(i+" ");
		}
		bw.flush();
		bw.close();

	}

}
