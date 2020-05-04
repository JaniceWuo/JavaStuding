import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestStream {
    public static void makeDir(File file){
        if(file.exists()==false) {
            file.getParentFile().mkdir();
        }
        try {
            FileOutputStream fs = new FileOutputStream(file);
            byte[] data = {65,66,97};
            fs.write(data);
            fs.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        File f = new File("E:/JavaStuding/IO/data/test.txt");
        makeDir(f);
//        try {
//            File f  = new File("test2.txt");
//            FileOutputStream fs = new FileOutputStream(f);
//            byte[] data = {97,98};  //写入的是ab
//            fs.write(data);
//            fs.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
