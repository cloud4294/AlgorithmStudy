import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 1655번 - 가운데를 말해요
 * 
 * 		https://www.acmicpc.net/problem/1655
 */

public class Main1655 {

	static class leftNum implements Comparable<leftNum> {

		int num;

		public leftNum(int num) {
			super();
			this.num = num;
		}

		@Override
		public int compareTo(leftNum o) {
			if (o.num > this.num)
				return 1;
			return -1;
		}

	}

	static class rightNum implements Comparable<rightNum> {

		int num;

		public rightNum(int num) {
			super();
			this.num = num;
		}

		@Override
		public int compareTo(rightNum o) {
			if (o.num < this.num)
				return 1;
			return -1;
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int mid = 0;
		PriorityQueue<leftNum> left = new PriorityQueue<leftNum>();  // Max Heap
		PriorityQueue<rightNum> right = new PriorityQueue<rightNum>(); // Min Heap

		for (int i = 0; i < N; i++) { 
			int now = sc.nextInt();

			if (i == 0) {
				mid = now;
				System.out.println(mid);
				continue;
			}

			if (now < mid)
				left.offer(new leftNum(now));
			else if (now >= mid)
				right.offer(new rightNum(now));

			if (left.size() > right.size()) {
				right.add(new rightNum(mid));
				mid = left.poll().num;
			}

			if (right.size() > left.size() + 1) {
				left.add(new leftNum(mid));
				mid = right.poll().num;
			}

			System.out.println(mid);
		}

	}

}
