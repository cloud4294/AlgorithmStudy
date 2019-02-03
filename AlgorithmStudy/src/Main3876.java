import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * 		Baekjoon Online Judge 3876번 - sed 이용
 * 
 * 		https://www.acmicpc.net/problem/3876
 */

public class Main3876 {

	static HashMap<String, String> map;
	
	static class count{
		String str;
		int cnt;
		public count(String str, int cnt) {
			super();
			this.str = str;
			this.cnt = cnt;
		}
	} // 현재문자열과 변환횟수를 저장할 클래스 
	
	public static int solve(String src, String dst) {
		
		Queue<count> queue = new LinkedList<>();
		
		
		queue.offer(new count(src,0));
		
		
		while(!queue.isEmpty()) {
			count now = queue.poll();
			String str = now.str;
			int cnt = now.cnt;
			
			
			if(str.equals(dst)) { // 현재 문자열이 목적문자열과 같으면 현재의 변환횟수를 리턴 
				return cnt;
			}else if(str.length() > dst.length()) { // 현재 문자열이 목적문자열 보다 길면 continue;
				continue;
			} 
			Set<String> set = map.keySet();
			Iterator<String> i = set.iterator();
			while(i.hasNext()) { // map의 모든 키값을 비교하여 현재 문자열이 키값을 가지고 있으면 value값으로 치환한후 큐에넣음 
				
				String check = i.next();
				String next = new String(str);
				int dc = 1;
				if(next.contains(check)) {
					next = next.replaceAll(check, map.get(check));
					queue.offer(new count(next,cnt+dc++));
				}
				
			}
		}
		
		return -1; // 변환이 불가하면 -1 리턴
		
	}
	
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = 0;
		while((n = Integer.parseInt(br.readLine())) != 0) { 
			map = new HashMap<String, String>();
			for (int i = 0; i < n; i++) {
				String[] input = br.readLine().split(" ");
				map.put(input[0], input[1]);
			} // 변환가능한 문자열 쌍을 map에 저장
			String src = br.readLine();
			String dst = br.readLine();
			
			
			System.out.println(solve(src,dst));
		}

	}


}
