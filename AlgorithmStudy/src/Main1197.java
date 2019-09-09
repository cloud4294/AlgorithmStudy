import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 		Baekjoon Online Judge 1197번 - 최소 스패닝 트리
 * 
 * 		https://www.acmicpc.net/problem/1197
 */

public class Main1197 {
	
	static int V,E;
	static List<edge> tree;
	static int[] parent;
	static int result;
	
	static class edge implements Comparable<edge>{
		int src;
		int dst;
		int weight;
		public edge(int src, int dst, int weight) {
			super();
			this.src = src;
			this.dst = dst;
			this.weight = weight;
		}
		@Override
		public int compareTo(edge o) {
			return weight - o.weight;
		}
		
	}
	
	public static int find(int i) { // 파인드 최적화
		
		if(parent[i] == i)
			return i;
		else 
			return parent[i] = find(parent[i]);

	}
	
	public static void union() { // 유니온 파인드 
		
		for (int i = 0; i < E; i++) {
			edge now = tree.get(i);
			
			int src = find(now.src);
			int dst = find(now.dst);
			
			if(src != dst) {
				parent[src] = dst;
				result += now.weight;
			}
			
		}
		
	}
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parent = new int[10001];
		tree = new ArrayList<>();
		result = 0;
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			tree.add(new edge(src, dst, weight));
			parent[src] = src;
			parent[dst] = dst;
			
		}
		
		Collections.sort(tree); // 크루스칼 알고리즘 : 간선을 가중치로 정렬한 뒤 유니온 파인드로 연결해서 MST 찾는것 
		
		union();
		
		System.out.println(result);
		
	}

	

}
