import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryTree<T extends Comparable<T>> {
    public BinaryTree<T> leftNode;
    public BinaryTree<T> rightNode;
    public T value;

    public void buildTree(T h){
        if(value == null) value = h;
        else {
            if(h.compareTo(value)<=0){
                if(leftNode == null) leftNode = new BinaryTree();
                leftNode.buildTree(h); //这里不能加else，因为就算执行了if语句也要执行这一句
            }
            else {
                if(rightNode==null) rightNode = new BinaryTree();

                rightNode.buildTree(h);

            }
        }
    }

    public List<T> values(){
        List<T> res = new ArrayList<>();
        if(leftNode!=null){
            res.addAll(leftNode.values());
        }
        res.add(value);
        if(rightNode!=null){
            res.addAll(rightNode.values());
        }
        return res;
    }

    public static void main(String[] args) {
        Random r = new Random();
        BinaryTree<Hero> heros = new BinaryTree<Hero>();
        for(int i = 0;i<10;i++) heros.buildTree(new Hero("hero"+i, r.nextInt(100),r.nextInt(100)));
//        List<Hero> treeSortedHeros = btree.values();
//        System.out.println(treeSortedHeros);
        System.out.println(heros.values());

    }
}
