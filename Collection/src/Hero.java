public class Hero implements Comparable<Hero> {
    public String name;
    public float hp;
    public int damage;
    public Hero(){

    }
    public Hero(String name,float hp, int damage){
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }
    public Hero(String name){
        this.name = name;
    }
    public int compareTo(Hero anothoer){
        if(damage<anothoer.damage) return 1;
        else return -1;
    }
    public String toString(){
        return "name："+name+"\thp："+hp+"\tdamage："+damage+"\n";
    }

}
