import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 		Baekjoon Online Judge 2668 - 숫자고르기
 * 
 * 		https://www.acmicpc.net/problem/2668
 */

public class Main2668 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] map = new int[N+1];
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			map[i] = Integer.parseInt(br.readLine());
		} // 초기화 
		
		for (int i = 1; i <= N; i++) { // 모든 지점에 대해서 
			boolean[] checked = new boolean[N+1];
			checked[i] = true;
			int next = map[i]; // 현재지점을 방문처리하고 다음지점을 가리킴 
			
			while(true) {  
				if(next == i) { // 현재지점이 시작지점과 동일하면 리스트에 추가 
					list.add(i);
					break;
				}else if(checked[next] == true) { // 현재지점이 방문한적있으면 종료 
					break;
				}else {
					checked[next] = true;
					next = map[next];
				}// 현재지점이 방문한적 없으면 다음지점을 탐색
				
			}
			
		}
		System.out.println(list.size());
		for(int i:list) {
			System.out.println(i);
		}
		
	}

}
