import java.util.Scanner;

/*
 * 		Baekjoon Online Judge 5639 - 이진 검색 트리
 * 
 * 		https://www.acmicpc.net/problem/5639
 */
public class Main5639 {

	static class node {
		int num;
		node left;
		node right;

		public node(int num) {
			super();
			this.num = num;
		}
	}

	public static node insertNode(node tree, int num) {

		node next = null;

		if (tree == null) {
			return new node(num);
		}

		if (num < tree.num) {
			next = insertNode(tree.left, num);
			tree.left = next;
		} else if (num > tree.num) {
			next = insertNode(tree.right, num);
			tree.right = next;
		}

		return tree;
	}

	public static void findpostOrder(node tree) {

		if (tree == null)
			return;

		findpostOrder(tree.left);
		findpostOrder(tree.right);
		System.out.println(tree.num);

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int start = sc.nextInt();
		node tree = new node(start);

		while (sc.hasNext()) {
			int num = sc.nextInt();
			if (num < 0)
				break;
			tree = insertNode(tree, num);

		}
		findpostOrder(tree);
	}

}
