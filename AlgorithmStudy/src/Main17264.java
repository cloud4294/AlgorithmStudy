import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 17264 - I AM IRONMAN
 * 
 * 		https://www.acmicpc.net/problem/17264
 */

public class Main17264 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int P = sc.nextInt();
		int W = sc.nextInt();
		int L = sc.nextInt();
		int G = sc.nextInt();
		int point = 0;
		List<String> winner = new ArrayList<>();
		for (int i = 0; i < P; i++) { // 같이 플레이하면 반드시 이기는 플레이어 이름을 리스트에 저장
			String name = sc.next();
			String flag = sc.next();
			if (flag.equals("W")) {
				winner.add(name);
			} 
		}
		for (int i = 0; i < N; i++) {
			String name = sc.nextLine();
			if (winner.contains(name)) 
				point += W;
			else {
				point -= L;
				if (point < 0)
					point = 0;
			}
			
			if(point >= G) { 
				System.out.println("I AM NOT IRONMAN!!");
				return;
			}
		}
		System.out.println("I AM IRONMAN!!");

	}

}
