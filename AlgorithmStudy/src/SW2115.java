import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		SW Expert Academy 2115번 - [모의 SW 역량테스트] 벌꿀채취
 * 
 * 		https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
 */


public class SW2115 {

	static int N, M, C;
	static int[][] map;
	static int[][] select;
	static int[] A;
	static int[] B;
	static int[] valueA;
	static int[] valueB;
	static boolean[] visit;
	static int r, result;

	public static void solve(int use) {

		if (use == 2) { //일꾼 두명이 벌통을 선택 했다면

			clac();

			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean flag = true;
				for (int j2 = 0; j2 < M; j2++) { // 가로로 M개 선택 가능한지 확인
					if (j + j2 >= N || select[i][j + j2] != 0) {
						flag = false;
						break;
					}
				}
				if (flag == true) {
					for (int j2 = 0; j2 < M; j2++) { // M개 선택
						select[i][j + j2] = use + 1;
					} 
					solve(use + 1);
					for (int j2 = 0; j2 < M; j2++) {
						select[i][j + j2] = 0;
					}
				}

			}
		}
	}

	public static void clac() {

		A = new int[M];
		B = new int[M];
	
		int ai = 0;
		int bi = 0;
		int nowSum = 0;

		for (int i = 0; i < N; i++) { // 일꾼 각각의 벌통을 1차원 배열로 변환
			for (int j = 0; j < N; j++) {

				if (select[i][j] == 1) {
					A[ai] = map[i][j];
					ai++;
				}
				if (select[i][j] == 2) {
					B[bi] = map[i][j];
					bi++;
				}
			}
		}
		
		r = 0;
		visit = new boolean[M];
		build(A, 0); // A일꾼의 최대값을 구함
		nowSum += r;
		
		r = 0;
		visit = new boolean[M];
		build(B, 0); // B일꾼의 최대값을 구함

		nowSum += r;
	

		if (nowSum > result) // A일꾼의 최대값과 B일꾼의 최대값을 합산한 값이 현재 최종결과 값보다 크면 갱신
			result = nowSum;
	}

	public static void build(int[] arr, int sum) {

		if (sum > C)
			return;


		for (int j = 0; j < M; j++) { // 합이 C보다 작은 조합선택

			if (visit[j] == false) {
				visit[j] = true;
				build(arr, sum + arr[j]);
				visit[j] = false;
			}

		}

		int res = 0;
		for (int j = 0; j < M; j++) { //제곱의 합을 구함
			if (visit[j] == true) {
				res += (arr[j] * arr[j]);
			}
		}
		
		
		if (res > r) { //현재값을 갱신
			r = res;
			return;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 1; i <= T; i++) {
			String[] input = br.readLine().split(" ");
			N = Integer.parseInt(input[0]);
			M = Integer.parseInt(input[1]);
			C = Integer.parseInt(input[2]);
			map = new int[N][N];
			select = new int[N][N];
			result = 0;
			for (int j = 0; j < N; j++) {
				String[] line = br.readLine().split(" ");
				for (int k = 0; k < N; k++) {
					map[j][k] = Integer.parseInt(line[k]);
				}
			} //  입력값 초기화

			solve(0);

			System.out.println("#" + i +" " +result);
		}
	}

}
