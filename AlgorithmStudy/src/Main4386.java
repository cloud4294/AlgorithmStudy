import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/*
 * 		Baekjoon Online Judge 4386번 - 별자리 만들기
 * 
 * 		https://www.acmicpc.net/problem/4386
 */

public class Main4386 {

	static int N;
	static node[] nodes;
	static double[][] dist;
	static double result;

	static class node {
		double x;
		double y;

		public node(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void prim() { // 프림 알고리즘 

		List<Integer> selected = new ArrayList<>(); //MST

		selected.add(0);

		for (int i = 0; i < ((N - 1) * N) / 2; i++) {
			double min = Double.MAX_VALUE;
			int next = -1;
			for (int j = 0; j < selected.size(); j++) {
				int now = selected.get(j);
				for (int k = 0; k < N; k++) {
					if (!selected.contains(k) && dist[now][k] < min) {
						min = dist[now][k];
						next = k;
					}
				}
			}

			if (next > 0) {
			
				selected.add(next);
				result += min;

			}

		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		nodes = new node[N];

		dist = new double[N][N];
		result = 0;
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			nodes[i] = new node(Double.parseDouble(input[0]), Double.parseDouble(input[1]));
		}

		for (int i = 0; i < N; i++) { // 좌표를 간선 배열로 변환
			for (int j = i; j < N; j++) {

				if (i == j) {
					dist[i][j] = Double.MAX_VALUE;
					continue;
				}

				dist[i][j] = Math.sqrt(Math.pow(nodes[i].x - nodes[j].x, 2) + Math.pow(nodes[i].y - nodes[j].y, 2));
				dist[j][i] = dist[i][j];
			}
		}

		prim();

		System.out.printf("%.2f\n",result);
	}

}
