import java.io.*;
import java.util.*;
	
public class Main {
	static int n, m, d[][];
	static boolean v[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = new int[n+1][n+1];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			d[a][b] = 1;
		}
		v = new boolean[n+1][n+1];
		int ans = 0;
		for(int i = 1; i <= n; i++) {
			if(bfs(i) + bfs2(i) == n-1) ans++;
        }			
		System.out.println(ans);		
	}
	static int bfs(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n+1];
		q.offer(start);
		visited[start] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && d[cur][i] == 1) {
					visited[i] = true;
					q.offer(i);
					cnt++;
				}
			}
		}
		return cnt;
	}
	static int bfs2(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n+1];
		q.offer(start);
		visited[start] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i = 1; i <= n; i++) {
				if(!visited[i] && d[i][cur] == 1) {
					visited[i] = true;
					q.offer(i);
					cnt++;
				}
			}
		}
		return cnt;
	}
}
