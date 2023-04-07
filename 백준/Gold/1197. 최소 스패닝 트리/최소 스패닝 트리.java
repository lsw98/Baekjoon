import java.io.*;
import java.util.*;

public class Main {
	static int v, e, edges[][], parent[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		edges = new int [e][3];
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[i] = new int[] {from, to, weight};
		}
		make();
		Arrays.sort(edges, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		int sum = 0;
		int cnt = 0;
		for(int i = 0; i < e; i++) {
			if(union(edges[i][0], edges[i][1])) {
				sum += edges[i][2];
				if(++cnt == v-1) break;
			}
		}
		System.out.println(sum);
	}
	static void make() {
		parent = new int[v+1];
		for(int i = 1; i<= v; i++) {
			parent[i] = i;
		}
	} 
	static int find(int a) {
		if(parent[a] == a) return a; // 부모가 없을때
		return parent[a] = find(parent[a]);
	}
	static boolean union(int a, int b) {
		if(find(a) == find(b)) return false;
		parent[find(b)] = parent[find(a)];
		return true;
	}
}
