import java.util.HashSet;

/*
找重复词
 */
public class TestHashSet {
    public static void main(String[] args) {
        HashSet<String> s = new HashSet<>();
        HashSet<String> s1 = new HashSet<>();
        for(int j = 0;j<100;j++){
            String str = getString(2);
            if(!s.add(str)){
                s1.add(str);
            }
        }
        System.out.println(s1);
    }
    public static String getString(int length){
        String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String str = "";
        for(int i = 0; i < length;i++){
            str+=s.charAt((int)(Math.random()*62));
//            System.out.println(s.charAt(i));
        }
        return str;

    }
}
