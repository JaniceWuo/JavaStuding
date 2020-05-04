import java.util.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<Hero> ll = new LinkedList<>();
        ll.addLast(new Hero("hero1"));
        System.out.println(ll);
        ll.addFirst(new Hero("hero0"));
        System.out.println(ll);
        //查看最前面和最后面的元素
        System.out.println(ll.getFirst());
        System.out.println(ll.getLast());
        System.out.println(ll.removeFirst());
        System.out.println(ll);
    }
}
