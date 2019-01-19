import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 1654번 - 랜선 자르기 
 * 
 * 		https://www.acmicpc.net/problem/2580
 */

public class Main1654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int K = Integer.parseInt(input[0]);
		int N = Integer.parseInt(input[1]);
		long up = 0;
		long down = 1;
		long max = 0;
		int[] data = new int[K];

		for (int i = 0; i < K; i++) {
			data[i] = Integer.parseInt(br.readLine());
			if (data[i] > up)
				up = data[i];
		} // 데이터 초기화하면서 가장 큰 값을 up에 저장

		while (up >= down) {
			long mid = (up + down) / 2;
			long count = 0;

			for (int i = 0; i < K; i++) {
				count += data[i] / mid;
			} // mid 길이 만큼 잘라낸 갯수

			if (count >= N) { // 잘라낸 갯수가 N보다 작은 경우 up을 감소 시킴
				down = mid + 1;
				if (mid > max)
					max = mid;
			} else { // 이진 탐색 시행
				up = mid - 1;

			}

		} // 이진 탐색 시행

		System.out.println(max);
	}

}
