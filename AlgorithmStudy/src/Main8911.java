import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 8911번 - 거북이
 * 
 * 		https://www.acmicpc.net/problem/8911
 */

public class Main8911 {

	static String com;
	static int maxY, maxX, minY, minX;
	static int[] my = { 1, 0, -1, 0 };
	static int[] mx = { 0, 1, 0, -1 };

	public static void solve(int y, int x, int dir, int now) {// 좌표,방향, 현재수행 중인 명령어 인덱스 

		if (y > maxY)
			maxY = y;
		if (y < minY)
			minY = y;
		if (x > maxX)
			maxX = x;
		if (x < minX)
			minX = x;
		
		
		if(now == com.length()) { // 모든 명령 수행후 종료
			return;
		} 

		char command = com.charAt(now);
		if (command == 'F') {
			solve(y+my[dir],x+mx[dir],dir,now+1); // 현재방향으로 전진, 명령어 인덱스 증가
		}else if (command == 'B') {
			solve(y-my[dir],x-mx[dir],dir,now+1); // 현재방향으로 후진, 명령어 인덱스 증가
		}else if (command == 'R') {
			solve(y,x,(dir+1)%4,now+1); // 오른쪽으로 회전 
		}else if (command == 'L') {
			solve(y,x,(dir+3)%4,now+1); // 왼쪽으로 회전
		}
		

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {

			com = br.readLine(); 

			maxY = 0;
			maxX = 0;
			minY = 0;
			minX = 0;
			solve(0, 0, 0, 0);
			
			System.out.println(Math.abs(maxY-minY)*Math.abs(maxX-minX)); 
		}

	}

}
