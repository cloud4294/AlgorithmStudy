import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 		Baekjoon Online Judge 2456 - 나는 학급회장이다
 * 
 * 		https://www.acmicpc.net/problem/2456
 */

public class Main2456 {

	static class point implements Comparable<point> {
		
		int index;
		int x;
		int y;
		int z;
		int sum;

		public point(int index,int x, int y, int z, int sum) {
			super();
			this.index = index;
			this.x = x;
			this.y = y;
			this.z = z;
			this.sum = sum;
		}

		@Override
		public int compareTo(point o) {
			if (o.sum < this.sum)
				return -1;
			else if (o.sum == this.sum) {
				if (o.x < this.x)
					return -1;
				else if (o.x == this.x) {
					
						return -1;
						
				}

			}

			return 1;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<point> points = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			points.add(new point(i,0, 0, 0, 0));
		}

		for (int i = 0; i < N; i++) {
			String[] vote = br.readLine().split(" ");
			int a = Integer.parseInt(vote[0]);
			int b = Integer.parseInt(vote[1]);
			int c = Integer.parseInt(vote[2]);
			if (a == 3)
				points.get(0).x++;
			else if (a == 2)
				points.get(0).y++;
			else if (a == 1)
				points.get(0).z++;
			points.get(0).sum +=a;
			
			if (b == 3)
				points.get(1).x++;
			else if (b == 2)
				points.get(1).y++;
			else if (b == 1)
				points.get(1).z++;
			points.get(1).sum +=b;
			
			if (c == 3)
				points.get(2).x++;
			else if (c == 2)
				points.get(2).y++;
			else if (c == 1)
				points.get(2).z++;
			points.get(2).sum +=c;
		}
	
		Collections.sort(points);
		
		if(points.get(0).sum == points.get(1).sum && points.get(0).x == points.get(1).x && points.get(0).y == points.get(1).y)
			System.out.println(0 +" "+ points.get(0).sum);
		else
			System.out.println(points.get(0).index +" " +points.get(0).sum);
		
	}

}
