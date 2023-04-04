
import java.io.*;
import java.util.*;

public class Main {
	static int length, door1, door2, open[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		door1 = Integer.parseInt(st.nextToken());
		door2 = Integer.parseInt(st.nextToken());
		length = Integer.parseInt(br.readLine());
		open = new int[length];
		for(int i = 0; i < length; i++) {
			open[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(solve(0, door1, door2));
	}
	static int solve(int l, int d1, int d2) {
		if(l == length) {
			return 0;
		}
		int tmp1 = Math.abs(d1 - open[l]);
		int tmp2 = Math.abs(d2 - open[l]);
		return Math.min(tmp1 + solve(l + 1, open[l], d2), tmp2 + solve(l + 1, d1, open[l]));
	}
} 
