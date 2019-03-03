import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 		SW Expert Academy 2477번 - [모의 SW 역량테스트] 차량 정비소
 * 
 * 		https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV6c6bgaIuoDFAXy
 */


public class SW2477 {

	static int N, M, K, A, B, result;
	static int[] receptime;
	static int[] repairtime;

	static class customer implements Comparable<customer>{

		int num;
		int recepin;
		int recep;
		int repairin;
		

		public customer(int num,int recepin) {
			this.num = num;
			this.recepin = recepin;
		}

		@Override
		public int compareTo(customer o) { // 정비소 대기시 먼저 온 시간이 우선, 시간이 같을경우 접수 창구번호가 작은게 우선됨 
			
			if(this.repairin != o.repairin)
				return Integer.compare(this.repairin, o.repairin);
			else 
				return Integer.compare(this.recep, o.recep);
		}
	}

	public static void solve(Queue<customer> reception) {

		PriorityQueue<customer> repair = new PriorityQueue<>();
		int[] useA = new int[N+1];
		int[] useB = new int[M+1];
		int t = 0;

		while (true) {

			while (!reception.isEmpty() && reception.peek().recepin <= t) { // 접수 큐에 접수시간이 현재시간보다 작은 경우 

				boolean flag = false;
				for (int i = 1; i <= N; i++) { // 접수 창구가 비어있는지 확인 
					if (useA[i] == 0) { // 비어있으면 해당 창구에서 걸리는 시간만큼 창구사용배열에 추가 
						flag = true;
						customer now = reception.poll();
						useA[i] = receptime[i];
						now.recep = i; // i번째 접수 창구 사용
						now.repairin = t + receptime[i]; // 정비소에 들어가는 시간 
						
						repair.offer(now); // 정비 큐에 넣음 
						break;
					}
				}
				if(flag == false)
					break;
				
			} 

			while (!repair.isEmpty() && repair.peek().repairin <= t) { // 정비 큐에 정비시간이 현재 시간보다 작은경우

				boolean flag = false;
				for (int i = 1; i <= M; i++) { // 정비소가 비어있는지 확인 
					if (useB[i] == 0) { // 비어있으면 해당 정비소에서 걸리는 시간만큼 정비소 사용배열에 추가 
						flag = true;
						customer now = repair.poll();
						useB[i] = repairtime[i];
						if (now.recep == A && i == B) { // 접수창구가 A이고 정비소가 B인 손님번호를 최종결과에 더함
							result += now.num;
						}
						break;

					}
				}
				if(flag == false)
					break;

			}
			
			if(reception.isEmpty() && repair.isEmpty()) // 큐가 모두 비어있으면 종료 
				break;

			for (int i = 1; i <= N; i++) {
				if (useA[i] > 0) {
					useA[i]--;
				}
			} // 접수창구 시간이 1 지나감
			for (int i = 1; i <= M; i++) {
				if (useB[i] > 0) {
					useB[i]--;
				}
			} // 정비소 시간이 1 지나감

			t++; // 현재 시간이 1지나감
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 1; i <= T; i++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			// 입력값 초기화
			receptime = new int[N+1];
			repairtime = new int[M+1];

			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				receptime[j] = Integer.parseInt(st.nextToken());
			} // 접수창구별 접수시간

			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				repairtime[j] = Integer.parseInt(st.nextToken());
			} // 정비소별 정비시간 

			Queue<customer> reception = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= K; j++) {
				reception.offer(new customer(j,Integer.parseInt(st.nextToken())));
			} // 고객번호와 도착시간을 큐에 저장 

			solve(reception);

			System.out.println("#"+i +" " + (result == 0? -1 : result));
		}

	}

}
