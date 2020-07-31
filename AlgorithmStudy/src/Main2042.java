import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2042번 - 구간 합 구하기
 * 
 * 		https://www.acmicpc.net/problem/2042
 */

public class Main2042 {

	static int N, M, K;
	static long[] num;
	static long[] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int segmentSize = (int) Math.pow(2, h + 1);

		num = new long[N + 1];
		sum = new long[segmentSize];

		for (int i = 1; i <= N; i++) {
			num[i] = Long.parseLong(br.readLine());
		}
		buildSum(1, N, 1); 

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (a == 1) {
				update(1, N, 1, b, c - num[b]);
			} else if (a == 2) {
				System.out.println(calcSum(1, N, 1, b, c));
			}
		}
	}

	public static long calcSum(int start, int end, int node, int left, int right) { // 구간합 계산

		if (start > right || end < left)
			return 0;
		if (start >= left && end <= right)
			return sum[node];

		int mid = (start + end) / 2;

		return calcSum(start, mid, 2 * node, left, right) + calcSum(mid + 1, end, 2 * node + 1, left, right);
	}

	public static void update(int start, int end, int node, int b, long diff) { // 구간합 업데이트
		if (start > b || end < b)
			return;

		sum[node] += diff;

		if (start == end) {
			num[start] += diff;
			return;
		}

		int mid = (start + end) / 2;

		update(start, mid, 2 * node, b, diff);
		update(mid + 1, end, 2 * node + 1, b, diff);

	}

	public static long buildSum(int start, int end, int node) { // 구간합 세그먼트 트리 생성

		if (start == end) {
			return sum[node] = num[start];
		}
		int mid = (start + end) / 2;
		return sum[node] = buildSum(start, mid, 2 * node) + buildSum(mid + 1, end, 2 * node + 1);
	}

}
