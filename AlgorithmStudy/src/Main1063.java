import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 1063 - 킹
 * 
 * 		https://www.acmicpc.net/problem/1063
 */

public class Main1063 {
	
	static position king;
	static position stone;
	
	static class position{
		int r;
		char c;
		public position(int r, char c) {
			super();
			this.r = r;
			this.c = c;
		}
	} // 좌표 저장 클래스 
	
	public static boolean isRange(int row,char col) {
		if(row < 1 || row > 8 || col < 'A' || col > 'H') 
			return false;
		return true;
		
	} // 범위 확인 메소드 
	
	public static position move(String com,int row,char col) {
		
		if(com.contains("R")) {
			col++;
		}
		if(com.contains("L")) {
			col--;
		}
		if(com.contains("T")) {
			row++;
		}
		if(com.contains("B")) {
			row--;
		}
		
		return new position(row, col);
		
	} // 이동 메소드
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] pos = br.readLine().split(" ");
		king = new position(Integer.parseInt(pos[0].substring(1, 2)), pos[0].charAt(0));
		stone = new position(Integer.parseInt(pos[1].substring(1, 2)), pos[1].charAt(0));
		int N = Integer.parseInt(pos[2]);
	
		
		for (int i = 0; i < N; i++) {
			String com = br.readLine();	
			position nking = move(com,king.r,king.c); // 킹을 이동
			if(isRange(nking.r, nking.c) == false) {
				continue; 
			} // 범위를 벗어나면 다음명령어 수행
			
			if(nking.r == stone.r && nking.c == stone.c) { // 돌이 킹이 이동할 위치에있다면 
				position nstone = move(com,stone.r,stone.c); // 돌을 이동	
				if(isRange(nstone.r, nstone.c) == false) // 돌이 범위를 벗어나면 다음명령어 수행
					continue;
				stone.c = nstone.c; 
				stone.r = nstone.r; // 돌 위치 갱신
			}
			king.c = nking.c;
			king.r = nking.r; //king 위치 갱신
			
		}
		
		System.out.println(king.c+""+ king.r);
		System.out.println(stone.c+""+ stone.r);
	}

}
