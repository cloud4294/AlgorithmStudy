import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
 * 		Baekjoon Online Judge 4195번 - 친구 네트워크
 * 
 * 		https://www.acmicpc.net/problem/4195
 */

public class Main4195 {

	static int MAX = 100001;
	static int[] group;
	static int[] groupCount;
	static HashMap<String, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine());

		for (int i = 0; i < test_case; i++) {

			map = new HashMap<String, Integer>();
			group = new int[MAX];
			groupCount = new int[MAX];

			for (int j = 0; j < MAX; j++) {
				group[j] = j;
				groupCount[j] = 1;
			}

			int F = Integer.parseInt(br.readLine());

			int index = 0;
			for (int j = 0; j < F; j++) {  // 문자열 숫자 변환
				String[] link = br.readLine().split(" ");

				String src = link[0];
				int s = index;
				if (!map.containsKey(src))
					map.put(src, index++);
				else
					s = map.get(src);

				String dst = link[1];
				int e = index;
				if (!map.containsKey(dst))
					map.put(dst, index++);
				else
					e = map.get(dst);

				System.out.println(union(s, e));
			
			}
		}
	}

	public static int union(int s, int e) { // 유니온 파인드

		s = find(s);
		e = find(e);

		if (s != e) {

			group[e] = s;
			groupCount[s] += groupCount[e]; 
		}
		
		return groupCount[s];
	}

	public static int find(int num) {

		if (num == group[num])
			return num;
		else
			return group[num] = find(group[num]);
	}

}
