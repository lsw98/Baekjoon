import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, segtree[], height[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		height = new int[n+1];
		for(int i = 1; i <= n; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		segtree = new int[n * 4];
		init(1, 1, n);
		System.out.println(getMax(1, n));
		
		
	}
	// segtree에 각 범위 높이 최소값 저장
	// 분할정복으로 * 2, * 2 + 1 탐색하면서 범위 x 최소값으로 사각형 넓이 구하기
	static void init(int node, int start, int end) {
		if(start == end) {
			segtree[node] = start;
		} else {
			int mid = (start + end) / 2;
			init(node * 2, start, mid);
			init(node * 2 + 1, mid + 1, end);
			
			if(height[segtree[node * 2]] <= height[segtree[node * 2 + 1]]) {
				segtree[node] = segtree[node * 2];
			} else {
				segtree[node] = segtree[node * 2 + 1];
			}
			
		}

	}
	static int getMinHeight(int node, int left, int right, int start, int end) {
		if(start > right || end < left) { // 범위 안에 없을 때
			 return -1;
		} 
		if (left <= start && end <= right) { // 전부 범위 안일때
			return segtree[node];
		}
		
		int mid = (start + end) / 2;
		int h1 = getMinHeight(node * 2, left, right, start, mid);
		int h2 = getMinHeight(node * 2 + 1, left, right, mid + 1, end);
		if(h1 == -1) {
			return h2;
		} else if (h2 == -1) {
			return h1;
		} else {
			if(height[h1] <= height[h2]) {
				return h1;
			} else {
				return h2;
			}
		}
		
	}
	static long getMax(int start, int end) {
		int m = getMinHeight(1, start, end, 1, n);
		long area = (end - start + 1) * height[m];

		if(start <= m - 1) {
			long leftArea = getMax(start, m-1);	
			area = Math.max(area, leftArea);
		}
		if(end >= m + 1) {
			long rightArea = getMax(m+1, end);
			area = Math.max(area, rightArea);
		}
		
		return area;
	}
}
