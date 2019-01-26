import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 		Baekjoon Online Judge 1239 - 차트
 * 
 * 		https://www.acmicpc.net/problem/1239
 */

public class Main1239 {
	
	static Map<Integer, Integer> data;
	static int res;
	
	public static void solve(List<Integer> list) {
		
		if (list.size() == data.size()) { // 리스트가 완성되면 
			int count = 0;
			for (int i = 0; i < list.size(); i++) {// 리스트의 i번째부터 전체 가능한 합을 구함 
				int now = 0;
				for (int j = i; j < i+list.size(); j++) { // 리스트의 길이만큼 탐색
					j %= list.size();
					now += data.get(list.get(j)); // map에서 value를 찾아 현재 합에 더함
					
					if(now == 50) { // 현재 합이 50이면  선의 개수 추가 
						count++;
					}else if(now > 50) {
						break;
					}
				}
			}
			count /= 2; // 현재 합이 50이 될때  두가지 부분합이 생기므로 2로 나눔 
			if(count > res)
				res = count;
			
			
		}else {
			for (int i = 0; i < data.size(); i++) {
				if(!list.contains(i)) {
					list.add(i);
					solve(list);
					list.remove((Integer)i);
				}
			}
		} // 가능한 모든 조합을 리스트에 저장
		
		
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		data = new HashMap<Integer, Integer>();
		res = 0;
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			int now = Integer.parseInt(input[i]);
			data.put(i, now);
		} // 초기값을 해쉬맵에 저장

		solve(new ArrayList<>());
		

		System.out.println(res);

	}

}
