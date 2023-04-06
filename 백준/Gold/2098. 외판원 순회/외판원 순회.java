import java.io.*;
import java.util.*;

public class Main {
	static int[][] w, dp;
	static int inf = Integer.MAX_VALUE / 100, n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		w = new int[n][n];
		StringTokenizer st = null;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());	
			}
		}
		dp = new int[1 << n][n];
		for(int i = 0; i < 1<<n; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(tsp(1, 0));

	}
	static int tsp(int visited, int city) {
		if(visited == ((1 << n) -1)) {
			if(w[city][0] == 0) return inf;
			return w[city][0];
		}
		
		if(dp[visited][city] != -1) {
			return dp[visited][city];
		}
		dp[visited][city] = inf;
		for(int i = 0; i < n; i++) {
			// 방문하지 않은 다른 도시 선택
			if((visited & (1 << i)) == 0 && w[city][i] != 0) {
				int res = tsp(visited | (1 << i), i) + w[city][i];
				dp[visited][city] = Math.min(dp[visited][city], res);
			}
		}
		return dp[visited][city];
	}
}
