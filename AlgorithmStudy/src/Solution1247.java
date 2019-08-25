import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 		SW Expert Academy 1247 - 최적 경로
 * 
 * 		https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD&categoryId=AV15OZ4qAPICFAYD&categoryType=CODE&&&
 */

public class Solution1247 {
	
	static int N;
	static List<point> pos;
	static List<Integer> order; 
	static point start,end;
	static int result; 
	static class point{
		int r;
		int c;
		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "point [r=" + r + ", c=" + c + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= t; i++) {
			N = Integer.parseInt(br.readLine());
			pos = new ArrayList<>();
			order = new ArrayList<>();
			result = Integer.MAX_VALUE;
			String[] input = br.readLine().split(" ");
			start = new point(Integer.parseInt(input[0]),Integer.parseInt(input[1]));
			end = new point(Integer.parseInt(input[2]),Integer.parseInt(input[3]));
			for (int j = 4; j < input.length; j+=2) {
				pos.add(new point(Integer.parseInt(input[j]),Integer.parseInt(input[j+1])));
			}
			
			solve(start,0);
			System.out.println("#"+i+" "+result);
		}
		
	}

	public static void solve(point now,int length) { // DFS
		
		if(order.size() == N) {
			
			int dist = Math.abs(now.r - end.r) + Math.abs(now.c - end.c);
			result = Math.min(result, dist+length);
			return;
		}
		if(length > result ) // 가지 치기
			return;
		
		for (int i = 0; i < N; i++) {
			if(!order.contains(i)) {
				order.add(i);
				int dist = Math.abs(now.r - pos.get(i).r) + Math.abs(now.c - pos.get(i).c);
				solve(pos.get(i),length + dist);
				order.remove(Integer.valueOf(i));
			}
		}
	}


}
