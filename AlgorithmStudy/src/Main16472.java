import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
 * 		Baekjoon Online Judge 16472	- 고냥이
 * 
 * 		https://www.acmicpc.net/problem/16472
 */

public class Main16472 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String input = br.readLine();

		System.out.println(solve(N, input));
		
	}

	public static int solve(int n, String input) {

		int left = 0;
		int right = 0;

		int result = 0;
		HashMap<Character, Integer> alpha = new HashMap<Character, Integer>();
	
		while (right < input.length()) {

			if (alpha.containsKey(input.charAt(right))) {
				alpha.replace(input.charAt(right), alpha.get(input.charAt(right)) + 1);
			} else {
				alpha.put(input.charAt(right), 1);
			}
			right++;

			while (alpha.size() > n) {
				alpha.replace(input.charAt(left), alpha.get(input.charAt(left)) - 1);
				if (alpha.get(input.charAt(left)) == 0) {
					alpha.remove(input.charAt(left));
				}
				left++;
			}

			result = Math.max(result, right - left);
	
		}

		
		return result;
	}

}
