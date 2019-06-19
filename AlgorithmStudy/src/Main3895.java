import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 		Baekjoon Online Judge 3895 - 그리고 하나가 남았다.
 * 
 * 		https://www.acmicpc.net/problem/3895
 */

public class Main3895 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = new ArrayList<Integer>();
		while(true) {
			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int k = Integer.parseInt(input[1]);
			int m = Integer.parseInt(input[2]);
			
			if(n == 0 && k == 0 && m == 0) // 0 0 0 입력시 종료
				break;
			
			int index = m - 1;
			for (int i = 1; i <= n; i++) { // m위치의 돌을 제거 
				if(i != m)
					list.add(i);
			}
			
			while(list.size() > 1) { //제거된 돌부터 k번째 돌을 제거
				index = (index + k -1) % list.size();
				list.remove(index);
			}
			System.out.println(list.get(0));
			list.clear();
		}

	}

}
