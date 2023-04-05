import java.util.*;
import java.io.*;
public class Main {
	static int n, segtree[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String s = br.readLine();
			if(s == null || s.length() == 0) break;
			StringTokenizer st = new StringTokenizer(s);
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int[] input = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				input[i] = Integer.parseInt(st.nextToken());
				if(input[i] > 0) input[i] = 1;
				else if (input[i] == 0) input[i] = 0;
				else input[i] = -1;
			}
			segtree = new int[n * 4];
			init(input, 1, 1, n);
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(command.equals("C")) { // 변경
					if(b > 0) b = 1;
					else if (b == 0) b = 0;
					else b = -1;
					update(1, 1, n, a, b);
				} else { // 곱셈
					if(getValue(1, a, b, 1, n) > 0) {
						System.out.print("+");
					} else if(getValue(1, a, b, 1, n) < 0) {
						System.out.print("-");
					} else if(getValue(1, a, b, 1, n) == 0) {
						System.out.print("0");
					} 

				}
			}
			System.out.println();
		}
				
	}
	static int init(int[] arr, int node, int start, int end) {
		if(start == end) {
			return segtree[node] = arr[start];
		} 
		
		return segtree[node] = init(arr, node * 2, start, (start+end)/2) * init(arr, node*2 + 1, (start+end)/2+1, end);
		
	}
	static int update(int node, int start, int end, int index, int diff) {
		if(start > index || end < index) {
			return segtree[node];
		} else {		
			if(start == end) {
				return segtree[node] = diff;
			}
			return segtree[node] = update(node * 2, start, (start+end)/2, index, diff)
					* update(node * 2+1, (start+end)/2 + 1, end, index, diff);
		}
	}
	static int getValue(int node, int left, int right, int start, int end) {
		// left, right : 구하는 구간
		if(end < left || start > right) {
			return 1;
		} else if (left <= start && end <= right) {
			// 구하는 구간에 속할 때
			return segtree[node];
		} else {
			// 1. 노드 구간이 구하는 구간에 일부 속할때
			// 2. 노드가 가지는 구간이 구하는 구간보다 클 때
			// 자식노드 탐색
			return getValue(node * 2, left, right, start, (start+end)/2)
					* getValue(node * 2 + 1, left, right, (start+end)/2+1, end);
		}
	}
}
