import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static List<Edge>[] graph;
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight-o.weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			ArrayList<int[]> node = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int homeX = Integer.parseInt(st.nextToken());
			int homeY = Integer.parseInt(st.nextToken());
			node.add(new int[] {homeX,homeY});
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int conX = Integer.parseInt(st.nextToken());
				int conY = Integer.parseInt(st.nextToken());
				node.add(new int[] {conX, conY});
			}
			st = new StringTokenizer(br.readLine());
			int dX = Integer.parseInt(st.nextToken());
			int dY = Integer.parseInt(st.nextToken());
			node.add(new int[] {dX, dY});
			
			graph = new ArrayList[n+2];
			for(int i = 0; i < n+2; i++) {
				graph[i] = new ArrayList<>();
			}
			for(int i = 0; i < n+2; i++) {
				int[] cur = node.get(i);
				for(int j = 0; j < n+2; j++) {
					if(i != j) {
						int[] next = node.get(j);
						int d = dist(cur[0], cur[1], next[0], next[1]);
						//System.out.println(i + " " + j + " " + d);
						if(d <= 1000) graph[i].add(new Edge(j, d));
					}
				}
			}
			System.out.println(dijkstra()?"happy":"sad");
		}
	}
	static boolean dijkstra() {
		Queue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));
		boolean[] v = new boolean[n+2];
		int[] dijk = new int[n+2];
		Arrays.fill(dijk, 100000000);
		dijk[0] = 0;
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			if(cur.to == n + 1) {
				return true;
			}
			if(!v[cur.to]) v[cur.to] = true;
			for(Edge next : graph[cur.to]) {
				if(!v[next.to] && dijk[next.to] > dijk[cur.to] + next.weight) {
					dijk[next.to] = dijk[cur.to] + next.weight;
					pq.offer(new Edge(next.to, dijk[next.to]));
				}
			}
		}
		return false;
	}
	
	static int dist(int x, int y, int dx, int dy) {
		return(Math.abs(x-dx) + Math.abs(y-dy));
	}
}
