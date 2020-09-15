import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 10868번 - 최솟값
 * 
 * 		https://www.acmicpc.net/problem/10868
 */

public class Main10868 {

	static int N;
	static int[] num;
	static int[] min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		num = new int[N + 1];
		min = new int[4 * N];

		int M = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		BuildMin(1, 1, N);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			int result = FindMin(1, from, to, 1, N);
			sb.append(String.valueOf(result)+ "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static int FindMin(int index, int from, int to, int left, int right) { // 구간내 최솟값 탐색

		if (left >= from && right <= to)
			return min[index];
		if (right < from || left > to)
			return Integer.MAX_VALUE;

		int mid = (left + right) / 2;
	
		return Math.min(FindMin(index * 2, from, to, left, mid), FindMin(index * 2 + 1, from, to, mid + 1, right));
	}

	public static int BuildMin(int index, int left, int right) { // 세그먼트 트리 생성

		if (left == right)
			return min[index] = num[left];

		int mid = (left + right) / 2;
		return min[index] = Math.min(BuildMin(index * 2, left, mid), BuildMin(index * 2 + 1, mid + 1, right));

	}

}
