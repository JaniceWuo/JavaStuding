import java.util.LinkedList;

public class StackMethod implements Stack {
    LinkedList<Hero> hero = new LinkedList<>();
    @Override
    public void push(Hero h) {
        hero.addLast(h);
    }

    @Override
    public Hero pull() {
        return hero.removeLast();
    }

    @Override
    public Hero peek() {
        return hero.getLast();
    }

    public static void main(String[] args) {
        StackMethod heroStack = new StackMethod();
        for(int i = 0; i < 5;i++){
            String name = "hero"+i;
            Hero h = new Hero(name);
            heroStack.push(h);
            System.out.println(h);
        }
//        System.out.println(heroStack);
        for(int j = 0;j<5;j++){
            Hero h = heroStack.pull();
            System.out.println(h);
        }
    }
}
