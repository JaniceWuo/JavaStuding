package Algorithm;

import java.util.*;

public class Main5 {
    static class Node{
        int a;
        int b;
        double val;
        public Node(int a,int b,double val){
            this.a = a;
            this.b = b;
            this.val = val;
        }
    }
    public static double getC(int x,int y){
        int chushu = 1;
        for(int i = 1;i<=y;i++){
            chushu *= i;
        }
        int beichu = 1;
        for(int i = x - y + 1;i<=x;i++){
            beichu *= i;
        }
        return (double)(beichu * 1.0 / chushu)%(1000000007);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in );
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Node> list = new ArrayList<Node>(  );
        for(int i = 0; i < m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
//            System.out.println(getC( a,b ));
            list.add(new Node(u,v,getC( a,b )));
        }
//        System.out.println(list.get( 0 ).val)
//        System.out.println(-1);
    }
}
