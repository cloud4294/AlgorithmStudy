import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2357번 - 최솟값과 최댓값
 * 
 * 		https://www.acmicpc.net/problem/2357
 */

public class Main2357 {

	static int N;
	static long[] max;
	static long[] min;
	static long[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		num = new long[N + 1];
		max = new long[N * 4];
		min = new long[N * 4];

		for (int i = 1; i <= N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}

		BuildMax(1, 1, N);
		BuildMin(1, 1, N);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(findMin(1, a, b, 1, N) + " " + findMax(1, a, b, 1, N)+"\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	public static long findMax(int index, int s, int e, int findS, int findE) { // 구간내 최대값 찾기

		if (findS <= findE && findS >= s && findE <= e)
			return max[index];
		else if(findE < s || findS > e)
			return 0;
		else {
			if (findS == findE)
				return 0;
			int mid = (findS + findE) / 2;
			return Math.max(findMax(index * 2, s, e, findS, mid), findMax(index * 2 + 1, s, e, mid + 1, findE));
		}
	}

	public static long findMin(int index, int s, int e, int findS, int findE) { // 구간내 최소값 찾기

		if (findS <= findE && findS >= s && findE <= e)
			return min[index];
		else if(findE < s || findS > e)
			return Long.MAX_VALUE;
		else {
			if (findS == findE)
				return Long.MAX_VALUE;
			int mid = (findS + findE) / 2;
			return Math.min(findMin(index * 2, s, e, findS, mid), findMin(index * 2 + 1, s, e, mid + 1, findE));
		}
	}

	public static long BuildMax(int index, int s, int e) { // 세그먼트 트리 생성

		if (s > e)
			return 0;

		if (s == e)
			return max[index] = num[s];

		int mid = (s + e) / 2;

		return max[index] = Math.max(BuildMax(index * 2, s, mid), BuildMax(index * 2 + 1, mid + 1, e));

	}

	public static long BuildMin(int index, int s, int e) { // 세그먼트 트리 생성

		if (s > e)
			return Long.MAX_VALUE;

		if (s == e)
			return min[index] = num[s];

		int mid = (s + e) / 2;

		return min[index] = Math.min(BuildMin(index * 2, s, mid), BuildMin(index * 2 + 1, mid + 1, e));

	}
}
