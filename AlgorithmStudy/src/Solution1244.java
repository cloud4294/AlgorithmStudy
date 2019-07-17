import java.util.Scanner;

/*
 * 		SW Expert Academy 1244 - 최대상금
 * 
 * 		https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15Khn6AN0CFAYD&categoryId=AV15Khn6AN0CFAYD&categoryType=CODE
 */

public class Solution1244 {

	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		sc.nextLine();
		for (int i = 1; i <= t; i++) {
			max = 0;
			String[] input = sc.nextLine().split(" ");

			int[] money = new int[input[0].length()];
			for (int j = 0; j < input[0].length(); j++) {
				money[j] = (Integer.parseInt(input[0].substring(j, j + 1)));
			}

			int n = Integer.parseInt(input[1]);

			solve(money, n);
			System.out.println("#"+i+" "+max);
		}

	}

	public static void solve(int[] money, int n) { // DFS

		if (n == 0) {
			int mul = 1;
			int sum = 0;
			for (int i = money.length - 1; i >= 0; i--) {
				sum += (mul * money[i]);
				mul *= 10;
			}
			max = Math.max(max, sum);
			return;
		} else {

			for (int i = 0; i < money.length; i++) {
				for (int j = i+1; j < money.length; j++) {
					if(money[i] <= money[j]) {
						int temp = money[i];
						money[i] = money[j];
						money[j] = temp;
						solve(money, n - 1);
						temp = money[i];
						money[i] = money[j];
						money[j] = temp;
					}
				}
			}

		}
	}

}
