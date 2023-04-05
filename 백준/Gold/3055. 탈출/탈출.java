import java.io.*;
import java.util.*;

public class Main {
	static int n, m, suX, suY, destX, destY;
	static char map[][];
	static int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
	static List<int[]> devils;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		devils = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				if (s.charAt(j) == 'S') {
					suX = i;
					suY = j;
				}
				if (s.charAt(j) == '*') {
					devils.add(new int[] { i, j });
				}
				if (s.charAt(j) == 'D') {
					destX = i;
					destY = j;
				}
				map[i][j] = s.charAt(j);
			}
		}
		map[suX][suY] = '.';
		int ans = bfs();
		if (ans > 0)
			sb.append(ans);
		else
			sb.append("KAKTUS");
		sb.append("\n");

		System.out.print(sb.toString());
	}

	static int bfs() {
		// bfs로 악마의 손아귀 퍼트리기, 시간 기록
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] v = new boolean[n][m];
		int[][] timeTable = new int[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(timeTable[i], Integer.MAX_VALUE);
		}
		if (devils.size() > 0) {
			for (int[] devil : devils) {
				q.offer(new int[] { devil[0], devil[1], 0 });
				v[devil[0]][devil[1]] = true;
			}
			while (!q.isEmpty()) {
				int[] d = q.poll();
				int x = d[0];
				int y = d[1];
				int time = d[2];
				timeTable[x][y] = time;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (checkRange(nx, ny) && !v[nx][ny] && map[nx][ny] == '.') {
						v[nx][ny] = true;
						q.offer(new int[] { nx, ny, time + 1 });
					}
				}
			}
			q.clear();
		}
		// 수연이 이동, 해당 위치에 손아귀 퍼진 시간이 수연이가 간 시간보다 늦어야 한다.
		q.offer(new int[] { suX, suY, 0 });
		v = new boolean[n][m];
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int time = cur[2];
			if (x == destX && y == destY)
				return time;
			if (timeTable[x][y] <= time)
				continue;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (checkRange(nx, ny) && !v[nx][ny] && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
					v[nx][ny] = true;
					q.offer(new int[] { nx, ny, time + 1 });
				}
			}
		}
		return -1;
	}

	static boolean checkRange(int x, int y) {
		if (0 <= x && x < n && 0 <= y && y < m)
			return true;
		return false;
	}
}
