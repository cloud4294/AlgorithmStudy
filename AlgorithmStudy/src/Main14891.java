import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 		Baekjoon Online Judge 14891번 - 톱니바퀴 
 * 
 * 		https://www.acmicpc.net/problem/14891
 */

public class Main14891 {

	static List<Character>[] gear;
	static boolean[] visit;

	public static void solve(int target, int dir) {
		visit[target] = true; // 현재 톱니바퀴 방문처리

		if (dir == 1) { // target 톱니바퀴를 시계방향으로 돌림
			char temp = gear[target].get(gear[target].size() - 1);
			gear[target].remove(gear[target].size() - 1);
			gear[target].add(0, temp);
		} else { // target 톱니바퀴를 반시계방향으로 돌림
			char temp = gear[target].get(0);
			gear[target].remove(0);
			gear[target].add(temp);
		}

		if (target - 1 >= 0 && visit[target - 1] == false) { // 범위 체크 && 방문체크 
			if (gear[target].get(6 + dir) != gear[target - 1].get(2)) { // 왼쪽 톱니바퀴 확인		
				solve(target - 1, dir * (-1)); // 방향을 바꾸어 재귀 호출
			}
		}

		if (target + 1 < 4 && visit[target + 1] == false) {
			if (gear[target].get(2 + dir) != gear[target + 1].get(6)) { // 오른쪽 톱니바퀴 확인	
				solve(target + 1, dir * (-1)); // 방향을 바꾸어 재귀 호출
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		gear = new List[4];

		int res = 0;
		int point = 1;

		for (int i = 0; i < 4; i++) {
			gear[i] = new ArrayList<>();
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				gear[i].add(input.charAt(j));
			}
		} // 입력값 초기화 

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			visit = new boolean[4];
			String[] com = br.readLine().split(" ");
			solve(Integer.parseInt(com[0]) - 1, Integer.parseInt(com[1]));
		} 

		for (int i = 0; i < 4; i++) {
			if (gear[i].get(0) == '1')
				res += point;
			point *= 2;
		} // 점수 계산 

		System.out.println(res);
	}

}
