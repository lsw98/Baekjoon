import java.io.*;
import java.util.*;

public class Main {
	static long[] tree;
	static int n;
	static long sum(int i) {
		long ans = 0;
		while(i > 0) {
			ans += tree[i];
			i -= (i & -i);
		}
		return ans;
	}
	static void update(int i, long num) {
		while(i <= n) {
			tree[i] += num;
			i += (i & -i);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());	
		int k = Integer.parseInt(st.nextToken());
		long arr[] = new long[n+1];
		tree = new long[n+1];
		for(int i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(br.readLine());	
			update(i, arr[i]);
		}
		String c;
		int a, b;
		for(int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = st.nextToken();
			if(a == 1) {
				long diff = Long.parseLong(c) - arr[b];
				arr[b] = Long.parseLong(c);
				update(b, diff);
			}
			if(a == 2) {
				System.out.println(sum(Integer.parseInt(c)) - sum(b-1));
			}
		}
	}

}
