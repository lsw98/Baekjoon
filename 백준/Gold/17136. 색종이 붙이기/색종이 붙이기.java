import java.io.*;
import java.util.*;
public class Main {
	static int[][] map = new int[10][10];
	static int[] paper = {0, 5, 5, 5, 5, 5};
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		System.out.println(ans==Integer.MAX_VALUE?-1:ans);
	}
	static void dfs(int num, int cnt) {
		if(num == 100) {
			ans = Math.min(ans, cnt);
			return;
		}
		if(cnt >= ans) {
			return;
		}	
		int x = num / 10;
		int y = num % 10;
		if(map[x][y] == 1) {
			for(int i = 1; i <= 5; i++) {
				boolean pos = true;
				for(int nx = x; nx < x + i; nx++) {
					for(int ny = y; ny < y + i; ny++) {
						if(!check(nx, ny) || map[nx][ny] == 0) pos = false; 
					}
				}
				if(pos && paper[i] > 0) {
					for(int nx = x; nx < x + i; nx++) {
						for(int ny = y; ny < y + i; ny++) {
							map[nx][ny] = 0;
						}
					}
					paper[i]--;
					dfs(num + 1, cnt + 1);
					paper[i]++;
					for(int nx = x; nx < x + i; nx++) {
						for(int ny = y; ny < y + i; ny++) {
							map[nx][ny] = 1;
						}
					}
				}			
			}
		} else {
			dfs(num + 1, cnt);
		}
	}

	static boolean check(int x, int y) {
		if(0<= x && x < 10 && 0 <= y && y < 10) return true;
		return false;
	}
}
