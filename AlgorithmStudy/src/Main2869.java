import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 2869 - 달팽이는 올라가고 싶다.
 * 
 * 		https://www.acmicpc.net/problem/2869
 */


public class Main2869 {

	public static void main(String[] args) {// 이분탐색시 시간초과, 공식 추론하여 해결
		
		Scanner sc = new Scanner(System.in);
		
		Double A = sc.nextDouble();
		Double B = sc.nextDouble();
		Double V = sc.nextDouble();
		
		System.out.println((int)Math.ceil(((V-A) / (A-B))) + 1);
		
		
		

	}

	

}
