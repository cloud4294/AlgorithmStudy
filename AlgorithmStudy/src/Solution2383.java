import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		SW Expert Academy 2383 - [모의 SW 역량테스트] 점심 식사시간
 * 
 * 		https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl&categoryId=AV5-BEE6AK0DFAVl&categoryType=CODE
 */


public class Solution2383 {

	static int N;

	static List<int[]> person;
	static List<int[]> exit;
	static boolean[] visit;
	static List<Integer> exit1;
	static List<Integer> exit2;
	static int result;

	public static void solve(int i) {

		if (i == person.size()) {
			exit1.clear();
			exit2.clear();
			for (int j = 0; j < person.size(); j++) { //선택한 계단을 거리별로 정렬
				if (visit[j] == true) {
					int dist = Math.abs(person.get(j)[0] - exit.get(0)[0]) + Math.abs(person.get(j)[1] - exit.get(0)[1])
							+ 1;
					exit1.add(dist);
				} else {
					int dist = Math.abs(person.get(j)[0] - exit.get(1)[0]) + Math.abs(person.get(j)[1] - exit.get(1)[1])
							+ 1;
					exit2.add(dist);
				}
			}
			Collections.sort(exit1);
			Collections.sort(exit2);

			int[] arrival_time1 = new int[exit1.size()];
			int[] arrival_time2 = new int[exit2.size()];

			for (int k = 0; k < arrival_time1.length; k++) { // 시간 계산
				if (k < 3) {
					arrival_time1[k] = exit1.get(k);
				} else {
					arrival_time1[k] = Math.max(exit1.get(k), arrival_time1[k - 3] + exit.get(0)[2]);
				}
			}

			for (int k = 0; k < arrival_time2.length; k++) {
				if (k < 3) {
					arrival_time2[k] = exit2.get(k);
				} else {
					arrival_time2[k] = Math.max(exit2.get(k), arrival_time2[k - 3] + exit.get(1)[2]);
				}
			}

			if (arrival_time1.length > 0 && arrival_time2.length > 0) {
				result = Math.min(result, Math.max((arrival_time1[arrival_time1.length - 1] + exit.get(0)[2]),
						(arrival_time2[arrival_time2.length - 1]) + exit.get(1)[2]));
		
			}
			else if (exit1.size() > 0)
				result = Math.min(result, arrival_time1[arrival_time1.length - 1] + exit.get(0)[2]);
			else
				result = Math.min(result, arrival_time2[arrival_time2.length - 1] + exit.get(1)[2]);
			

			return;

		}

		visit[i] = true; // 조합
		solve(i + 1);
		visit[i] = false;
		solve(i + 1);

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test_case = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		person = new ArrayList<>();
		exit = new ArrayList<>();
		exit1 = new ArrayList<>();
		exit2 = new ArrayList<>();

		for (int t = 1; t <= test_case; t++) {
			person.clear();
			exit1.clear();
			exit2.clear();
			exit.clear();
			N = Integer.parseInt(br.readLine());
			result = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int now = Integer.parseInt(st.nextToken());
					if (now == 0)
						continue;
					else if (now == 1) {
						int[] insert = new int[2];
						insert[0] = i;
						insert[1] = j;
						person.add(insert);
					} else if (now != 1) {
						int[] insert = new int[3];
						insert[0] = i;
						insert[1] = j;
						insert[2] = now;
						exit.add(insert);
					}
				}
			}
			visit = new boolean[person.size()];
			solve(0);
			System.out.println("#"+t+" "+result);
		}

	}

}
