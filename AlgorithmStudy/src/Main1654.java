import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 1654�� - ���� �ڸ��� 
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
		} // ������ �ʱ�ȭ�ϸ鼭 ���� ū ���� up��  ����

		while (up >= down) {
			long mid = (up + down) / 2; 
			long count = 0;

			for (int i = 0; i < K; i++) {
				count += data[i] / mid;
			} // mid ���� ��ŭ �߶� ���� 

			if (count >= N) { // �߶� ������ N���� ũ�ų� ���� ��� down�� ���� ��Ŵ
				down = mid + 1;
				if (mid > max)
					max = mid; 
			} else { // �߶� ������ N���� ���� ��� up�� ���� ��Ŵ 
				up = mid - 1;
				
			}

		} // ���� Ž�� ���� 

		System.out.println(max);
	}

}
