import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 		SW Expert Academy 5633.[모의 SW 역량테스트] - 줄기세포배양
 * 
 * 		https://www.swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRJ8EKe48DFAUo
 */


public class SW5653 {

	static int N, M, K;
	static List<sell> greed;
	static boolean[][] visited;
	
	static int[] my = { -1, 0, 1, 0 };
	static int[] mx = { 0, 1, 0, -1 };
	static int res;

	static class sell {
		int y;
		int x;
		int health;
		int time;
		boolean active;
		int dead;

		public sell(int y, int x, int health, int time, boolean active, int dead) {
			super();
			this.y = y;
			this.x = x;
			this.health = health;
			this.time = time;
			this.active = active;
			this.dead = dead;
		}

	} // 좌표, 생명력, 활성화 시간 , 활성화 여부, 활성화된후 수명을 저장할 클래스

	public static void solve(int level) {

		if (level == K) {
			for (sell s : greed) {
				if (s.dead != 0)
					res++;
			} // K번째 단계에서 살아있는 세포의 수를 계산
			return;
		} else {

			List<sell> add = new ArrayList<>();
			for (sell s : greed) {

				if (s.active == false) { // 세포가 비활성화 상태일때 
					s.time--;
					if (s.time == 0) {
						s.active = true; // 세포가 가진 생명력만큼 시간이 지나면 활성화 
					}
				} else {
					if (s.dead == s.health) { // 세포가 최초 활성화될때 
						for (int i = 0; i < 4; i++) {
							int ny = s.y + my[i];
							int nx = s.x + mx[i];
							boolean check = false;
							if(visited[ny+500][nx+500] == true) // 이미 방문한적이있으면 넘어감 
								continue;
							visited[ny+500][nx+500] = true; //현재 지점 방문처리 

							for (sell d : add) {
								if (d.y == ny && d.x == nx) { // 이번단계에서 같은지점에 확장되면 
									check = true; // 지점이 같지만 추가될 리스트가 가진 값이 더 큰 경우 
									if (s.health >= d.health) { // 생명력이 낮은세포를 추가될 리스트에서 삭제
										add.remove(d);
										check = false;
										break;
									}
								}
							}
							if (check == false) // 추가될 리스트에 없거나 생명력이 낮아 삭제된경우 추가 
								add.add(new sell(ny, nx, s.health, s.health, false, s.health));
						}
						s.dead--;
					} else if (s.dead > 0) { // 활성화된 수명 감소 
						s.dead--;
					} 
				}

			}
			greed.addAll(add); // 전체 세포에 이번단계에 추가된 리스트를 합침 

			solve(level + 1); // 다음단계로 넘어감 
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int i = 1; i <= T; i++) {
			
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			res = 0;
			greed = new ArrayList<>();
			visited = new boolean[1001][1001];

			for (int j = 0; j < N; j++) {
				for (int j2 = 0; j2 < M; j2++) {
					int now = sc.nextInt();
					if (now > 0) {
						greed.add(new sell(j, j2, now, now, false, now)); // 분열가능한 세포를 List에 저장
						visited[j+500][j2+500] = true; // 방문 처리 
					}
				}
			} 

			solve(0);

			System.out.println("#" + i + " " + res);
		}

	}

}
