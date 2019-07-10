import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 2096번 - 내려가기
 * 
 * 		https://www.acmicpc.net/problem/2096
 */

public class Main2096 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		

		int maxres = 0;
		int minres = Integer.MAX_VALUE;

		int[][] max = new int[3][2];
		int[][] min = new int[3][2];	

		int size = 0;
		for (int i = 0; i < N; i++) { // 입력을 받으며 다이나믹 프로그래밍 시행
			String[] line = br.readLine().split(" ");
			size = line.length;
			if (i == 0) {
				for (int j = 0; j < size; j++) {
					max[j][0] = Integer.parseInt(line[j]);
					min[j][0] = Integer.parseInt(line[j]);
				}
			} else {
				for (int j = 0; j < size; j++) {
					int now = 0;
					now = Math.max(now, max[j][(i + 1) % 2]);
					if (j + 1 >= 0 && j + 1 < size)
						now = Math.max(now, max[j + 1][(i + 1) % 2]);
					if (j - 1 >= 0 && j - 1 < size)
						now = Math.max(now, max[j - 1][(i + 1) % 2]);
					max[j][i % 2] = now + Integer.parseInt(line[j]);

					now = Integer.MAX_VALUE;
					now = Math.min(now, min[j][(i + 1) % 2]);
					if (j + 1 >= 0 && j + 1 < size)
						now = Math.min(now, min[j + 1][(i + 1) % 2]);
					if (j - 1 >= 0 && j - 1 < size)
						now = Math.min(now, min[j - 1][(i + 1) % 2]);
					min[j][i % 2] = now + Integer.parseInt(line[j]);
				}
			}
		}

		for (int i = 0; i < size; i++) {
			maxres = Math.max(maxres, max[i][(N - 1) % 2]);
			minres = Math.min(minres, min[i][(N - 1) % 2]);
		}
		System.out.println(maxres + " " + minres);

	}

}
