import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        });
        int answer = 0;
        int e = 0;
        for(int i = 0; i < targets.length; i++) {
            if(targets[i][0] >= e) {
                answer++;
                e = targets[i][1];
            }
        }
        return answer;
    }
}