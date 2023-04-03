import java.io.*;
import java.util.*;

// 반례 못찾겠다... 포기
public class Main {
	static int[][] wheel = new int[5][8];
	static int[] check = new int[5];
	static int k;

	class wheel {
		public List<Integer> wheel = new ArrayList<>();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= 4; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = line.charAt(j) - '0';
			}
		}

		k = Integer.parseInt(br.readLine());

		// k번 회전
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			turn(num, dir);
			// 다 돌렸으므로 초기상태로
			for (int k = 0; k < 5; k++) {
				check[k] = 0;
			}
		}
		int ans = 0;
		if (wheel[1][0] == 1)
			ans += 1;
		if (wheel[2][0] == 1)
			ans += 2;
		if (wheel[3][0] == 1)
			ans += 4;
		if (wheel[4][0] == 1)
			ans += 8;
		bw.write(Integer.toString(ans));
		bw.flush();
	}

	// 이번에 돌렸는지 확인하는 것 필요
	static void turn(int num, int dir) {
		// 이번 회차에 회전했으므로 더 회전 안함
		check[num] = 1;
		
		// 시계로 돌리면 앞에 7번을 삽입 후 7번 제거
		if (dir == 1) {
			// 오른쪽 극 다르면 반시계 회전 시키기
			if (num != 4 && wheel[num][2] != wheel[num + 1][6] && check[num + 1] == 0)
				turn(num + 1, -1);
			// 왼쪽 극 다르면 반시계 회전 시키기
			if (num != 1 && wheel[num][6] != wheel[num - 1][2] && check[num - 1] == 0)
				turn(num - 1, -1);
			// 선택된 바퀴 회전
			int tmp = wheel[num][7];
			int[] arr = new int[8];
			for (int i = 1; i <= 7; i++) {
				arr[i] = wheel[num][i-1];
			}
			arr[0] = tmp;
			wheel[num] = arr;
		}
		// 반시계로 돌리면 뒤에 0번을 삽입 후 0번 제거
		else {
			// 오른쪽 극 다르면 시계 회전 시키기
			if (num != 4 && wheel[num][2] != wheel[num + 1][6] && check[num + 1] == 0)
				turn(num + 1, 1);
			// 왼쪽 극 다르면 시계 회전 시키기
			if (num != 1 && wheel[num][6] != wheel[num - 1][2] && check[num - 1] == 0)
				turn(num - 1, 1);
			// 선택된 바퀴 회전
			int tmp = wheel[num][0];
			int[] arr = new int[8];
			for (int i = 0; i < 7; i++) {
				arr[i] = wheel[num][i + 1];
			}
			arr[7] = tmp;
			wheel[num] = arr;
		}
	}
}