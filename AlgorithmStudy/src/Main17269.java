import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 		Baekjoon Online Judge 17269번 - 이름궁합 테스트
 * 
 * 		https://www.acmicpc.net/problem/17269
 */

public class Main17269 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		
		Map<String,Integer> map =  new HashMap<>();
		map.put("A", 3);
		map.put("B", 2);
		map.put("C", 1);
		map.put("D", 2);
		map.put("E", 4);
		map.put("F", 3);
		map.put("G", 1);
		map.put("H", 3);
		map.put("I", 1);
		map.put("J", 1);
		map.put("K", 3);
		map.put("L", 1);
		map.put("M", 3);
		map.put("N", 2);
		map.put("O", 1);
		map.put("P", 2);
		map.put("Q", 2);
		map.put("R", 2);
		map.put("S", 1);
		map.put("T", 2);
		map.put("U", 1);
		map.put("V", 1);
		map.put("W", 1);
		map.put("X", 2);
		map.put("Y", 2);
		map.put("Z", 1);
	
		int N = Integer.parseInt(size[0]);
		int M = Integer.parseInt(size[1]);
		String[] name = br.readLine().split(" ");
		int[] A = new int[N];
		int[] B = new int[M];
		
		for (int i = 0; i < N; i++) {
			A[i] = map.get(name[0].substring(i, i+1));
		}
		
		for (int i = 0; i < M; i++) {
			B[i] = map.get(name[1].substring(i, i+1));
		}
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < Math.max(N, M); i++) {
			if(i < N)
				list.add(A[i]);
			if(i < M)
				list.add(B[i]);
		}
		solve(list);
	}

	public static void solve(List<Integer> list) {

		if(list.size() == 2) {
			if(list.get(0) != 0)
				System.out.print(list.get(0));
			System.out.println(list.get(1)+"%");
			return;
		}
		
		List<Integer> next = new ArrayList<>();
		for (int i = 0; i < list.size()-1; i++) {
			int now = list.get(i) + list.get(i+1);
			next.add(now%10);
		}
		solve(next);
	}

}
