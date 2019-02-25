import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * 		Baekjoon Online Judge 1195번 - 킥다운
 * 
 *		https://www.acmicpc.net/problem/1195
 */

public class Main1195 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String A = br.readLine();
		String B = br.readLine();

		if (A.length() > B.length()) {
			String temp = B;
			B = A;
			A = temp;
		} // 길이가 작은 문자열을 A에 오도록 함 

		int[] partA = new int[A.length()];
		int[] partB = new int[B.length() + 2 * (A.length())];
		for (int i = 0; i < A.length(); i++) {
			partA[i] = Integer.parseInt(A.substring(i, i + 1));
		}

		for (int i = 0; i < A.length(); i++) {
			partB[i] = 0;
		}
		for (int i = A.length(); i < B.length() + A.length(); i++) {
			partB[i] = Integer.parseInt(B.substring(i - A.length(), i - A.length() + 1));
		}

		for (int i = B.length() + A.length(); i < B.length() + 2 * A.length(); i++) {
			partB[i] = 0;
		} // 길이가 긴 문자열 양옆에 길이가 작은 문자열의 길이만큼 0을 채워넣음 
		
		int res = A.length() + B.length();
		for (int i = 0; i <= B.length() + A.length(); i++) {
			int dup = 0;
			for (int j = 0; j < A.length(); j++) {
				if (partB[i + j] >= 1 && partA[j] >= 1){
					if(partB[i + j] + partA[j] > 3) {
						dup =-1;
						break;
					}
					dup++;
				}
			
			} // 작은 문자열의 크기만큼 유효한지 확인 한후 겹치는 부분을 구함 
			if(dup == -1)
				continue;
			res = Math.min(res, A.length() + B.length() - dup);
		}

		System.out.println(res);
	}

}
