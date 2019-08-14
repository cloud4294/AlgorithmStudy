import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 		Baekjoon Online Judge 1068번 - 트리
 *
 * 		https://www.acmicpc.net/problem/1068
 */

public class Main1068 {

	static class node {
		int num;
		List<node> child;

		public node(int num) {
			super();
			this.num = num;
			this.child = new ArrayList<>();
		}	
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		node[] tree = new node[N];
		String[] input = br.readLine().split(" ");
		int leaf = 0;
		int root = -1;
		
		for (int i = 0; i < N; i++) { // 트리 구성
			int p = Integer.parseInt(input[i]);
			if (tree[i] == null)
				tree[i] = new node(i);
			if (p == -1) {
				root = i;
			} else {
				if (tree[p] == null)
					tree[p] = new node(p);
				tree[p].child.add(tree[i]);

			}
		}
		int target = Integer.parseInt(br.readLine());

		Queue<node> queue = new LinkedList<>();
		queue.offer(tree[root]);
		while (!queue.isEmpty()) { 
			node now = queue.poll();
		
			if (now.num == target) { // 제거할 노드일 경우 더 이상 탐색하지 않음 
				continue;
			}

			if (now.child.isEmpty()) {
				leaf++;
			} else {
				for (int i = 0; i < now.child.size(); i++) {
					node next = now.child.get(i);
					if(next.num == target) {
						if(now.child.size() == 1) { // 제거할 노드가 유일한 자식인 경우
							leaf++;
							break;							
						}
						continue;
						
					}
					queue.offer(next);
				}
			}

		}

		System.out.println(leaf);

	}

}
