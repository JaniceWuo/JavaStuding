import java.io.File;
import java.util.Arrays;
import java.util.Date;

public class TestFile {
    public static void main(String[] args) {
        File f1 = new File("E:/JavaStuding");
        System.out.println("f1的绝对路径:"+f1.getAbsolutePath());
        File[] fs= f1.listFiles();
        System.out.println(Arrays.toString(fs));
        String parent = f1.getParent(); //得到父目录
        System.out.println(parent);
        File f2 = new File("test.txt");
        System.out.println(f2.getAbsolutePath());
        System.out.println("文件长度："+f2.length());
        long time = f2.lastModified();
        Date d = new Date(time);
        System.out.println("最后修改时间："+d);
        f2.renameTo(new File("test2.txt"));


    }
}
