import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 12015번 - 가장 긴 증가하는 부분 수열 2
 * 	
 * 		https://www.acmicpc.net/problem/12015
 */

public class Main12015 {

	static List<Integer> lis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		lis = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (lis.size() == 0 || num > lis.get(lis.size() - 1))
				lis.add(num);
			else
				insert(num, 0, lis.size()-1);
		}
		
		System.out.println(lis.size());
	}

	public static void insert(int num, int left, int right) { // 이분 탐색

		int mid = (left + right) / 2;

		if(left >= right) 
			lis.set(mid, num);
		else if (lis.get(mid) >= num)
			insert(num, left, mid);
		else
			insert(num, mid + 1, right);

	}

}
