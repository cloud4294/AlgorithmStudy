import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 2751번 - 수 정렬하기 2
 * 
 * 		https://www.acmicpc.net/problem/2751
 */

public class Main2751 {

	static int[] num;
	static int[] temp;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		num = new int[N];
		temp = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		mergesort(0, N - 1);

		for (int i = 0; i < N; i++) {
			System.out.println(num[i]);
		}

	}

	public static void mergesort(int start, int end) { // 병합 정렬

		if (start < end) {
			int mid = (start + end) / 2;
			mergesort(start, mid);
			mergesort(mid + 1, end);
			merge(start, end, mid);
		}

	}

	public static void merge(int start, int end, int mid) {

		int i = start;
		int k = mid + 1;
		int index = start;
		while (i <= mid && k <= end) {

			if (num[i] <= num[k]) {
				temp[index++] = num[i++];
			} else {
				temp[index++] = num[k++];

			}
		}
		while (i <= mid)
			temp[index++] = num[i++];

		while (k <= end)
			temp[index++] = num[k++];

		for (int j = start; j <= end; j++) {
			num[j] = temp[j];
		}

	}

}
