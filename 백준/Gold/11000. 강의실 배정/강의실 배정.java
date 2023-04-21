import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] lecture = new int[n][2];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lecture[i][0] = Integer.parseInt(st.nextToken());
			lecture[i][1] = Integer.parseInt(st.nextToken());
		}
		// 시작하는 시간 - 끝나는 시간 순으로 정렬
		Arrays.sort(lecture, (l1, l2) -> {
			return l1[0] == l2[0] ? Integer.compare(l1[1], l2[1]) : Integer.compare(l1[0], l2[0]);
		});
		
		Queue<Integer> pq = new PriorityQueue<>();
		pq.offer(lecture[0][1]);
		
		for(int i = 1; i < n; i++) {
			if(pq.peek() <= lecture[i][0]) {
				pq.poll();
			}
			pq.offer(lecture[i][1]);
		}
		System.out.println(pq.size());
	}

}
