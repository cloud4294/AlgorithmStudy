import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 1484 - 다이어트
 * 		
 * 		https://www.acmicpc.net/problem/1484
 */

public class Main1484 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int G = sc.nextInt();

		int right = 2;
		int left = 1;

		boolean flag = false;
		while (right > left) {

			int value = (right * right) - (left * left);

			if (value < G)
				right++;
			else if (value == G) {
				flag = true;
				System.out.println(right);
				left++;
			} else if (value > G)
				left++;

		}
		
		if(flag == false)
			System.out.println(-1);

	}

}
