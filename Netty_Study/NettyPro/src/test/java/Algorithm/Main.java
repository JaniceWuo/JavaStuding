package Algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        char[][] matrix = {{'1','1','1'},{'0','1','1'},{'1','1','1'},{'1','1','1'}};
        int[] dp = new int[matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == '1'){
                    dp[j]+=1;
                }else{
                    dp[j] = 0;
                }
            }
            res = Math.max( res,getMaxRec( dp ) );
        }
        System.out.println(res);
    }
    public static int getMaxRec(int[] heights){
        int[] tmp = new int[heights.length + 2];
        System.arraycopy( heights,0,tmp,1,heights.length );
        int area = 0;
        Stack<Integer> stack = new Stack<Integer>();

        for(int i = 0; i < tmp.length; i++){
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]){
//                System.out.println("i:" + i);
//                System.out.println("peek:"+stack.peek());
                int h = tmp[stack.pop()];
                area = Math.max( area, ( i - stack.peek() - 1) * h );

//                System.out.println(h);

            }
            stack.push( i );
        }
        return area;
    }
}

