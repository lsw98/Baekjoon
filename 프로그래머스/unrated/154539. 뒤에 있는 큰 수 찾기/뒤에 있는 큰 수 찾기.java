import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<int[]> stk = new Stack<>();       
        for(int i = 0; i < numbers.length; i++) {
            if(stk.isEmpty()) {
                stk.push(new int[] {i, numbers[i]});
            } else {
                if(stk.peek()[1] >= numbers[i]) {
                    stk.push(new int[] {i, numbers[i]});    
                } else {
                    while(!stk.isEmpty() && stk.peek()[1] < numbers[i]) {
                        int[] item = stk.pop();
                        answer[item[0]] = numbers[i];
                    }
                    stk.push(new int[] {i, numbers[i]});
                }
            }
        }
        while(!stk.isEmpty()) {
            int[] item = stk.pop();
            answer[item[0]] = -1;
        }
        return answer;
    }
}