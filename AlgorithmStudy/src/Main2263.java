import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 		Baekjoon Online Judge 2263 - 트리의 순회
 * 
 * 		https://www.acmicpc.net/problem/2263
 */
public class Main2263 {

	static int[] inorder;
	static int[] postorder;
	static int[] index;
	static int N;

	public static void solve(int in_s, int in_e,int post_s,int post_e) { // 분할 정복

		if(in_s > in_e || post_s > post_e)
			return;
		
		int root = postorder[post_e];
		System.out.print(root+ " ");
		int now = index[root];
		
		int left = now - in_s;
		
		solve(in_s,now-1,post_s,post_s+left-1);
		solve(now+1,in_e,post_s+left,post_e-1);
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		inorder = new int[N];
		postorder = new int[N];
		index = new int[N+1];

		String[] line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			inorder[i] = Integer.parseInt(line[i]);
		}
		line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			postorder[i] = Integer.parseInt(line[i]);
		}
		for (int i = 0; i < N; i++) {
			index[inorder[i]] = i;
		}

		solve(0, N - 1, 0, N - 1);
	}

}
