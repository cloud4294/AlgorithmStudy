import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 1057 - 토너먼트
 * 
 * 		https://www.acmicpc.net/problem/1057
 */

public class Main1057 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int[] round = new int[r+1];
		int p1 = sc.nextInt();
		int p2 = sc.nextInt();
		int count = 1;
		int temp = 2;
		int result = 1;
		for (int j = 1; j <= r; j++) {
			round[j] = count;
			if(j % temp == 0) {
				count++;
			}
		} // 대전번호 초기화 
		
		
		while(true) {
			if(round[p1] == round[p2]){ // p1과 p2의 대전번호가 같으면 결과를 출력하고 종료
				System.out.println(result);
				break;
			}else {
				count = 1;
				temp *= 2;
				result++;	// 라운드가 진행될수록  2배씩 같은 대전번호를 가지도록 증가 
				for (int j = 1; j <= r; j++) {
					round[j] = count;
					if(j % temp == 0) {
						count++;
					}
				} 
			} 
			
			
			
		}
		
	}

}
