import java.util.*;
import java.io.*;

public class Main {
	static int n, m, map[][], dx[] = {1, -1, 0, 0}, dy[] = {0, 0, 1, -1};
	static int score = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		/*for(int[] m : map) System.out.println(Arrays.toString(m));
		System.out.println();*/
		while(true) {
			boolean isBlock = simulation();
			if(!isBlock) break;
		}
		System.out.println(score);
		
 	}
	static class group {
		int size; // 그룹 크기
		int rainbow; // 무지개블록 개수
		int row; // 기준 블록 좌표
		int col;
		ArrayList<int[]> blocks; // 그룹 블록들 좌표
		public group(int row, int col) {
			blocks = new ArrayList<>();
			this.row = row;
			this.col = col;
		}
	}
	
	// 1. 그룹 찾기 : bfs 실행 -> 시작한 위치가 그룹의 기준점이 됨 (왼쪽 위)
	// 2. 블록 제거
	// 3. 중력 작용 (아래로 당기기)
	// 4. 반시계 회전 (배열 돌리기)
	// 5. 중력 작용 (아래로 당기기)
	static boolean simulation() {
		List<group> groups = new ArrayList<>();	
		boolean[][] visited = new boolean[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(!visited[i][j] && map[i][j] > 0) { // 무지개 블록 아닌 블록에서 탐색 시작
					// 크기가 2 이상이면 groups에 넣기
					group g = bfs(i, j, visited);
					if(g.size >= 2) groups.add(g);			
				}
			}
		}
		// groups 우선순위로 정렬해서 제일 높은 것 선택하기
		// 블록 크기 -> 무지개 블록 개수 -> 행 -> 열
		if(groups.size() > 0) {
			groups.sort((g1, g2) -> {
				if(g1.size == g2.size) {
					if(g1.rainbow == g2.rainbow) {
						if(g1.row == g2.row) {
							return -Integer.compare(g1.col, g2.col);
						}
						return -Integer.compare(g1.row, g2.row);
					}
					return -Integer.compare(g1.rainbow, g2.rainbow);
				}
				return -Integer.compare(g1.size, g2.size);
			});
			getScore(groups.get(0));
			gravity();
			turn();
			gravity();
			return true;
		} 
		return false;
	}
	static group bfs(int x, int y, boolean[][] visited) {
		group g = new group(x, y);
		int groupColor = map[x][y];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			// 무지개 블록 개수 세기
			if(map[cx][cy] == 0) {
				g.rainbow++;
			}
			g.blocks.add(cur);
			g.size++;
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				// 범위 안, 방문 x, 검은 블록 포함 x, 무지개거나 그룹 색깔이여야함
				if(checkRange(nx, ny) && !visited[nx][ny] && (map[nx][ny] == 0 || map[nx][ny] == groupColor) ) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		
		// 무지개 블록은 다른 그룹에서 다시 찾을 수 있음 -> visited false
		for(int[] pos : g.blocks) {
			if(map[pos[0]][pos[1]] == 0) visited[pos[0]][pos[1]] = false;
		}
		return g;
	}
	static void getScore(group g){
		for(int[] pos : g.blocks) {
			int x = pos[0];
			int y = pos[1];
			map[x][y] = -2; // 빈칸 : -2
		}
		score += (g.size * g.size);
	}
	static void gravity() {
		// 검은 블록 제외하고 아래로
		// 각 열마다 실행
		// 한칸씩 당겨지므로 최대 n-1번 까지 실행해야 한다.
		for(int cnt = 0; cnt < n-1; cnt++) {
			for(int i = 1; i <= n; i++) {
				for(int j = n; j >= 2; j--) {
					if(map[j][i] == -2 && map[j-1][i] >= 0) {
						map[j][i] = map[j-1][i]; // 아래로 한칸
						map[j-1][i] = -2; // 당긴 칸 빈칸으로
					}
				}
			}
		}
		
	}
	static void turn() {
		// 반시계 90도 회전
		int[][] temp = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				temp[n+1-j][i] = map[i][j];
			}
		}
		map = temp;
	}
	static boolean checkRange(int x, int y) {
		if(1 <= x && x <= n && 1 <= y && y <= n) return true;
		return false;
	}
}
