import java.util.LinkedList;
import java.util.Queue;

public class TestQueue {
    public static void main(String[] args) {
        Queue<Hero> q = new LinkedList<>();
        q.offer(new Hero("hero0"));
        q.offer(new Hero("hero1"));
        System.out.println(q);
        Hero h = q.poll();
        System.out.println(h);
        System.out.println("取出一个元素之后:"+q);
        q.peek();
        System.out.println("使用peek看一看之后:"+q);
    }
}
