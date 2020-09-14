import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 6549번 - 히스토그램에서 가장 큰 직사각형
 * 
 * 		https://www.acmicpc.net/problem/6549
 */


public class Main6549 {

	static int N;
	static int[] hist;
	static int[] minHeight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			if (N == 0)
				return;

			hist = new int[N + 1];
			minHeight = new int[N * 4];
			for (int i = 1; i <= N; i++) {
				hist[i] = Integer.parseInt(st.nextToken());
			}

			BuildMin(1, 1, N);

			System.out.println(maxArea(1, N));
		}

	}

	public static long maxArea(int s, int e) { // 최솟값 인덱스를 기준으로 분할 정복

		int minIndex = Find(1, s, e, 1, N);	
		long result = (long)hist[minIndex] * (long)(e - s + 1);

		if (minIndex - 1 >= s) {
			result = Math.max(result, maxArea(s, minIndex - 1));
		}
		if (minIndex + 1 <= e)
			result = Math.max(result, maxArea(minIndex + 1, e));
		return result;

	}

	public static int Find(int index, int s, int e, int findS, int findE) { // 해당 구간의 최솟값을 가진 인덱스 반환
		
		if (findS >= s && findE <= e)
			return minHeight[index];
		else if (findE < s || findS > e)
			return -1;
		else {

			int mid = (findS + findE) / 2;
			
			int left = Find(index * 2, s, e, findS, mid);
			int right = Find(index * 2 + 1, s, e, mid + 1, findE);
			
			if(left == -1)
				return right;
			if(right == -1)
				return left;
			
			if(hist[left] <= hist[right])
				return left;
			else 
				return right;
		}
	}

	public static int BuildMin(int index, int s, int e) { // 최솟값의 인덱스를 가진 세그먼트 트리 생성

		if (s == e)
			return minHeight[index] = s;

		int mid = (s + e) / 2;

		int left = BuildMin(2 * index, s, mid);
		int right = BuildMin(2 * index + 1, mid + 1, e);

		if (hist[left] < hist[right])
			return minHeight[index] = left;
		else
			return minHeight[index] = right;
	}

}
