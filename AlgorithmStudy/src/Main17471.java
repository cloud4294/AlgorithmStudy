import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 17471번 - 게리맨더링 
 * 	
 * 		https://www.acmicpc.net/problem/17471
 */

public class Main17471 {

	static int N;
	static int[] cost;
	static List<Integer>[] map;
	static int[] select;
	static List<Integer> left;
	static List<Integer> right;
	static boolean[] visit;
	static int result;

	public static boolean isConnect(List<Integer> connect) { //간선으로 연결되어 있는지 확인, BFS
		if(connect.size() == 0)
			return false;
		
		Arrays.fill(visit, false);
		int start = connect.get(0);
		visit[start] = true;

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int i = 0; i < map[now].size(); i++) {
				int next = map[now].get(i);
				
				if (connect.contains(next) && visit[next] == false) {
					visit[next] = true;
					queue.offer(next);
				}
			}

		}
		
		for (int i = 0; i < connect.size(); i++) {
			if (visit[connect.get(i)] == false)
				return false;
		}
		return true;
	}

	public static void solve(int j, int count) {

		if (count > N / 2) // 절반만 선택, 나머지절반 선택과 동일 하기 때문
			return;

		left.clear();
		right.clear();
		for (int i = 0; i < N / 2; i++) { // 선택된 그룹
			if (select[i] != 0)
				left.add(select[i]);
		}
		for (int i = 1; i <= N; i++) { //선택되지 않은 그룹
			if (!left.contains(i))
				right.add(i);
		}

		if (isConnect(left) && isConnect(right)) { // 두 그룹이 각각 간선으로 연결되어있는지 확인

			int leftSum = 0;
			int rightSum = 0;

			for (int i = 0; i < left.size(); i++) {
				leftSum += cost[left.get(i)];
			}
			for (int i = 0; i < right.size(); i++) {
				rightSum += cost[right.get(i)];
			}

			result = Math.min(result, Math.abs(leftSum - rightSum)); // 그룹 인구차이 갱신

		}

		for (int i = j; i <= N; i++) { // 조합 
			select[count] = i;
			solve(i + 1, count + 1);
			select[count] = 0;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		cost = new int[N + 1];
		map = new ArrayList[N + 1];
		select = new int[N / 2 + 1];
		left = new ArrayList<>();
		right = new ArrayList<>();
		visit = new boolean[N+1];
		result = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			map[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				map[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		

		solve(1, 0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

}
