import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		SW Expert Academy 5644번 - [모의 SW 역량테스트] 무선 충전
 * 
 * 		https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo
 */


public class SW5644 {

	static int[] my = { 0, -1, 0, 1, 0 };
	static int[] mx = { 0, 0, 1, 0, -1 };

	static class ap {
		int y;
		int x;
		int c;
		int p;

		public ap(int y, int x, int c, int p) {
			super();
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
	} // 무선충전기의 좌표, 범위 , 충전양을 저장할 클래스

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		for (int i = 1; i <= N; i++) {
			int sum = 0;
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int[] da = new int[M + 1];	
			int[] db = new int[M + 1]; 	

			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				da[j] = Integer.parseInt(st.nextToken());
			}//a의 이동방향 

			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				db[j] = Integer.parseInt(st.nextToken());
			}//b의 이동방향
			ap[] aps = new ap[A]; 

			for (int j = 0; j < A; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				aps[j] = new ap(y, x, c, p);
			}// 무선충전기 초기화 
			int ay = 1, ax = 1;	// a의 현재 좌표
			int by = 10, bx = 10; // b의 현재 좌표

			
			for (int j = 0; j <= M; j++) {
				ay += my[da[j]];
				ax += mx[da[j]];
				by += my[db[j]];
				bx += mx[db[j]];
				
				int add = 0;
				int[] addA = new int[A]; // a가 충전받을수 있는 값을 저장하는 배열
				int[] addB = new int[A]; // b .. 
				

				for (int j2 = 0; j2 < A; j2++) { // 모든 무선충전기 탐색하여 무선충전기의 범위 안에 a,b가 있으면 add배열에 저장  
					ap now = aps[j2];
					int d1 = Math.abs(now.y - ay) + Math.abs(now.x - ax); 
					int d2 = Math.abs(now.y - by) + Math.abs(now.x - bx);
					if(d1 <= now.c)
						addA[j2] = now.p;
					if(d2 <= now.c)
						addB[j2] = now.p;
				} 
				
				for (int k = 0; k < A; k++) { // 두 사용자가 각각 하나씩 무선충전기를 사용할수 있으므로 가능한 조합중 최대값을 찾는다.
					for (int k2 = 0; k2 < A; k2++) { // a또는 b가 하나의 무선충전기를 사용하는 경우 혹은 a와 b가 하나의 무선충전기를 사용하는경우 
						if(k == k2) {
							add = Math.max(add, addA[k]); 
							add = Math.max(add, addB[k]);
						}else {
							add = Math.max(add, addA[k] + addB[k2]); // k번재 무선충전기와 k2번째 무선충전기를 각각 사용하는경우
						}
					}
				}
				sum += add;

			}

			System.out.println("#" + i + " " + sum);
		}

	}

}
