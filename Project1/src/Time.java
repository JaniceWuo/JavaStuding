import java.util.Date;
import java.text.SimpleDateFormat;


public class Time {
    public static void main(String[] args) {
        Date d1 = new Date();
        System.out.println("当前时间：" + d1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d2 = new Date();
        String s1 = sdf.format(d2);
        System.out.println("格式化后的时间："+s1);
    }
}
