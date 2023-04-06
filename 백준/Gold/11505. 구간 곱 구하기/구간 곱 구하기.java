import java.io.*;
import java.util.*;

public class Main {
	static long[] segtree;
	static long p = 1000000007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		long[] arr = new long[n+1];
		segtree = new long[n * 4];
		for(int i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		init(arr, 1, 1, n);
		for(int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == 1) { // 변경
				update(1, b, 1, n, c);
			} else { // 출력
				System.out.println(mul(1, b, c, 1, n));
			}
		}
	}
	static long init(long[] arr, int node, int start, int end) {
		if(start == end) {
			return segtree[node] = arr[start];
		}
		return segtree[node] = (init(arr, node * 2, start, (start+end)/2)
				* init(arr, node * 2 + 1, (start+end)/2 + 1, end)) % p;
	}
	static long mul(int node, int left, int right, int start, int end) {
		if(start > right || end < left) { // 전부 구하는 구간 x
			return 1;
		} else {
			if(start >= left && end <= right) { // 모두 구하는 구간
				return segtree[node];
			} else {
				return (mul(node * 2, left, right, start, (start+end)/2) *
						mul(node * 2 + 1, left, right, (start+end)/2 + 1, end)) % p;
			}
		}
	
	}
	static long update(int node, int index, int start, int end, int diff) {
		if(start > index || end < index) { // 아예 없는 범위
			return segtree[node];
		} else {
			if(start == end) { // 찾음
				return segtree[node] = diff;
			}
			return segtree[node] = (update(node * 2, index, start, (start+end)/2, diff) *
					update(node * 2 + 1, index, (start+end)/2 + 1, end, diff)) % p;
		}

	}
}
