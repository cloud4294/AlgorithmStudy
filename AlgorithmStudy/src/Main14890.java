import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 14890 - 경사로
 * 
 * 		https://www.acmicpc.net/problem/14890
 */

public class Main14890 {

	static int N, L;
	static int[][] map;
	static int result;

	public static void solve() {

		for (int i = 0; i < N; i++) { // 가로
			int upCount = 1;
			int downCount = -1;
			int height = map[i][0];
			boolean flag = true;
			for (int j = 1; j < N; j++) {
				if(map[i][j] == height) { // 동일 높이 갯수 
					if(downCount == -1)
						upCount++;
					else 
						downCount++;
				}else if(map[i][j] == height + 1) { // 위 경사로 놓는 경우 
					if(upCount >= L) {
						upCount = 1;
						height++;
					}else {
						flag = false;
						break;
					}
					
				}else if(map[i][j] == height -1) { // 아래 경사로 놓는 경우
					if(flag == false)
						break;
					upCount = 0;
					downCount = 1;
					height--;
					flag = false;
				}else if (map[i][j] > height + 1 || map[i][j] < height - 1) { // 경사로를 놓을 수 없는 경우
					flag = false;
					break;
				}
				
				if(downCount == L) {
					downCount = -1;
					flag = true;
				}
				
			}
			if (flag == true) {
				result++;
			}

		}

		for (int i = 0; i < N; i++) { // 세로
			int upCount = 1;
			int downCount = -1;
			int height = map[0][i];
			boolean flag = true;
			for (int j = 1; j < N; j++) {
				if(map[j][i] == height) {
					if(downCount == -1)
						upCount++;
					else 
						downCount++;
				}else if(map[j][i] == height + 1) {
					if(upCount >= L) {
						upCount=1;
						height++;
					}else {
						flag = false;
						break;
					}
					
				}else if(map[j][i] == height -1) {
					if(flag == false)
						break;
					upCount = 0;
					downCount = 1;
					height--;
					flag = false;
				}else if (map[j][i] > height + 1 || map[j][i] < height - 1) {
					flag = false;
					break;
				}
				
				if(downCount == L) {
					downCount = -1;
					flag = true;
				}
				
			}
			if (flag == true) {
				result++;
			}
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve();

		System.out.println(result);
	}

}
