public class Poly {
    static void animalShout(Animal a){
        a.shout();
    }
    public static void main(String[] args) {
        Dog dog = new Dog();
        animalShout(dog);
    }
}
