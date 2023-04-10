import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n][3];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][][] dp = new int[n][3][3];
		for(int i = 0; i < 3; i++) {
			Arrays.fill(dp[0][i], 1000001);
		}
		// dp[i][0][0] : i번째, 현재 빨간색, 빨간색 시작 
		dp[0][0][0] = cost[0][0];
		dp[0][1][1] = cost[0][1];
		dp[0][2][2] = cost[0][2];
 		for(int i = 1; i < n; i++) {
 			for(int j = 0; j < 3; j++) {
 				dp[i][0][j] = Math.min(dp[i-1][1][j], dp[i-1][2][j]) + cost[i][0];
 				dp[i][1][j] = Math.min(dp[i-1][0][j], dp[i-1][2][j]) + cost[i][1];
 				dp[i][2][j] = Math.min(dp[i-1][0][j], dp[i-1][1][j]) + cost[i][2];
 			}
		}
 		int nth = Integer.MAX_VALUE;
 		for(int i = 0; i < 3; i++) {
 			for(int j = 0; j < 3; j++) {
 				if(i != j) nth = Math.min(nth, dp[n-1][i][j]);
 			}
 		}
 		System.out.println(nth);
	}

}
