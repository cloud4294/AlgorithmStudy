import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 2252번 - 줄 세우기
 * 
 * 		https://www.acmicpc.net/problem/2252
 */

public class Main2252 {

	static List<Integer>[] graph;
	static boolean[] root;
	static boolean[] visit;
	static int n;
	static Stack<Integer> output;
	
	public static void topologicalSort() {
		
		for (int i = 1; i <= n; i++) {
			if(root[i] == false) {
				DFS(i);
			}
		}
		
	}
	
	public static void DFS(int i) {
		visit[i] = true;
		if(graph[i].size() == 0)
			output.push(i);
		else {
			for (int j = 0; j < graph[i].size(); j++) {
				if(visit[graph[i].get(j)] == false)
					DFS(graph[i].get(j));
			}
			output.push(i);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n+1];
		root = new boolean[n+1];
		visit = new boolean[n+1];
		output = new Stack<>();
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			graph[src].add(dst);
			root[dst] = true;
		}
		
		topologicalSort(); // 위상 정렬 
		
		while(!output.empty()) {
			System.out.print(output.pop()+" ");			
		}
	}

	

}
