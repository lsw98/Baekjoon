import java.io.*;
import java.util.*;

public class Main {
	static int m, n, map[][], dp[][];
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[m][n];
		for(int i = 0; i < m; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(dfs(0, 0));
	}
	static int dfs(int x, int y) {
		if(x == m-1 && y == n - 1) {
			return 1;
		}
		if(dp[x][y] != -1) { // 이미 방문
			return dp[x][y];
		} else {
			dp[x][y] = 0;
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(0 <= nx && nx < m && 0 <= ny && ny < n && map[x][y] > map[nx][ny]) {
					dp[x][y] += dfs(nx, ny);
				}
			}
		}
		return dp[x][y];
	}
}