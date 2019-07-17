import java.util.Scanner;

/*
 * 		SW Expert Academy 7854 - 최약수
 * 
 * 		https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWttVkiq5jEDFASy
 */


public class Solution7854 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int i = 1; i <= t; i++) {
			
			int x = sc.nextInt();
			
			int result = 0;
			
			
			int du = 1;
			for (int j = 0; j < 10; j++) {
				if(du <= x) 
					result++;
				else
					break;
				du *= 10;
			}
			
			du = 2;
			for (int j = 0; j < 9; j++) {
				if(du <= x) 
					result++;
				else
					break;
				du *= 10;
			}
			
			du = 5;
			for (int j = 0; j < 9; j++) {
				if(du <= x) 
					result++;
				else
					break;
				du *= 10;
			}
			
			du = 25;
			for (int j = 0; j < 8; j++) {
				if(du <= x) 
					result++;
				else
					break;
				du *= 10;
			}
			
			du = 125;
			for (int j = 0; j < 7; j++) {
				if(du <= x) 
					result++;
				else
					break;
				du *= 10;
			}
			
			System.out.println("#"+i+" "+result);
		}

	}

}
