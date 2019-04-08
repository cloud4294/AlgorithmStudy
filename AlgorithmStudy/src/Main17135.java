import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 17135 - 캐슬 디펜스
 * 
 * 		https://www.acmicpc.net/problem/17135
 */

public class Main17135 {

	static int N, M, D;
	static int count, res;
	static List<enemy> targets;
	static List<enemy> copyTargets;

	static class enemy implements Comparable<enemy> { // 적군의 좌표, 거리

		int r;
		int c;
		int dist;

		public enemy(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(enemy o) { // 거리 오름차순, 거리가 같을시 열이 작은순으로 오름차순 정렬

			if (o.dist < dist)
				return 1;
			else if (o.dist == dist) {
				if (o.c < c)
					return 1;
			}

			return -1;
		}
	}

	public static void solve(int i, List<Integer> archer) { // 궁수의 위치의 모든 조합을 찾음

		if (archer.size() == 3) { // 궁수 세명의 배치가 끝나면 
			count = 0;
			
			targets.clear();
			for (int j = 0; j < copyTargets.size(); j++) {
				targets.add(new enemy(copyTargets.get(j).r, copyTargets.get(j).c));
			} // 적군 위치 초기화
			
			while (!targets.isEmpty()) {
				attack(archer); // 적군 저격
				falling(); // 적군 전진
			}

			if (count > res) {
				res = count;
			}

			return;
		} else {
			for (int j = i + 1; j < M; j++) {
				archer.add(new Integer(j));
				solve(j, archer);
				archer.remove(new Integer(j));
			}

		}

	}

	public static void falling() {
		List<enemy> delete = new ArrayList<>();
		for (int i = 0; i < targets.size(); i++) {
			enemy e = targets.get(i);
			if (e.r == N-1) { // 벽에 도달하면 삭제
				delete.add(e);
			} else {
				e.r++;
			}
		}

		for (int i = 0; i < delete.size(); i++) {
			targets.remove(delete.get(i));
		}

	}

	public static void attack(List<Integer> archer) {

		List<enemy> delete = new ArrayList<>();
		for (int i = 0; i < archer.size(); i++) { 
			int now = archer.get(i);

			for (int j = 0; j < targets.size(); j++) {
			
				targets.get(j).dist = Math.abs(N - targets.get(j).r) + Math.abs(now - targets.get(j).c);

			} // 궁수별로 모든 적군의 거리를 계산

			Collections.sort(targets); // 거리별, 거리가 같은 경우 왼쪽 우선으로 정렬
 
			
			
			for (int j = 0; j < targets.size(); j++) {
				enemy e = targets.get(j);
				if (e.dist <= D) { // 적의 거리가 D보다 작으면
					if (!delete.contains(e)) // 삭제 대상리스트에 있지않으면 리스트에 추가
						delete.add(e);
					break;
				}
			}
		}

		for (int i = 0; i < delete.size(); i++) { // 처치 횟수를 증가시키면서 적군 제거
			count++;
			targets.remove(delete.get(i));
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();

		targets = new ArrayList<>();
		copyTargets = new ArrayList<>();
		res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int now = sc.nextInt();

				if (now == 1) {
					targets.add(new enemy(i, j));
				}
			}
		} // 입력갑 초기화
		
		for (int i = 0; i < targets.size(); i++) {
			copyTargets.add(new enemy(targets.get(i).r, targets.get(i).c));
		} // 적군 초기 위치 복사

		solve(-1, new ArrayList<Integer>());
		System.out.println(res);
	}

}
