import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 2023번 - 신기한 소수
 * 
 * 		https://www.acmicpc.net/problem/2023
 */

public class Main2023 {

	static int N;

	private static boolean checkPrime(int num) {

		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	} // 소수인지 판별하는 메소드

	public static void solve(int now, int count) {

		if (count == N - 1) { // 숫자의 길이가 N이면 출력
			System.out.println(now);
			return;
		}

		for (int i = 1; i <= 9; i++) {	
			if (checkPrime(i + now * 10) == true) { // 다음숫자가 소수이면  재귀 호출
				solve(i + now * 10, count + 1);
			}
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 2; i <= 9; i++) {
			if(checkPrime(i) == true)
				solve(i,0);
		}
	}

}
