import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 		Baekjoon Online Judge 9252 - LCS 2
 * 
 * 		https://www.acmicpc.net/problem/9252
 */

public class Main9252 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String line1 = br.readLine();
		String line2 = br.readLine();
		List<Character> output = new ArrayList<>();
		int[][] LCS = new int[line2.length()+1][line1.length()+1];
		
		for (int i = 1; i <= line2.length(); i++) { // dp 
			for (int j = 1; j <= line1.length(); j++) {
				
				if(line1.charAt(j-1) == line2.charAt(i-1)) {
					LCS[i][j] = LCS[i-1][j-1] + 1;
				}else {
					LCS[i][j] = Math.max(LCS[i-1][j], LCS[i][j-1]);
				}
				
			}
		}
		
		int y = line2.length();
		int x = line1.length();
		
		while(true) {
			if(LCS[y][x] == 0)
				break;
			if(LCS[y][x-1] == LCS[y][x]) {
				x--;
			}else if(LCS[y-1][x] == LCS[y][x]) {
				y--;
			}else {
				output.add(line1.charAt(x-1));
				x--;
				y--;
			}
		}
		
		System.out.println(output.size());
		for (int i = output.size()-1; i >=0 ; i--) {
			System.out.print(output.get(i));
		}
		
		
	}

}
