import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestStream2 {
    public static void encodeFile(File encodingFile,File encodedFile){
        try (FileReader fr = new FileReader(encodingFile)){
            FileWriter wr = new FileWriter(encodedFile);
            char[] allData = new char[(int)encodingFile.length()];
            fr.read(allData);
            for(char b:allData){
//                System.out.println(b);
                if(b>='0' && b<'9'){
                    b+=1;
                    System.out.println(b);
                }
                else if(b=='9'){
                    b='0';
                }
                else if((b>='a' && b<'z') || (b>='A' && b<'Z')){
                    b+=1;
                }
                else if(b == 'z' || b == 'Z'){
                    b-=25;
                }
                wr.write(b);

            }

            wr.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        File f = new File("test.txt");
        File wf = new File("encoded.txt");
        encodeFile(f,wf);
        //读取
//        File file = new File("test.txt");
//        try(FileReader fr = new FileReader(file)) {
//            char[] data = new char[(int)file.length()];
//            fr.read(data);
//            for(char b : data){
//                System.out.println(b);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        //写入
//        File fileW =  new File("write.txt");
//        try (FileWriter wr = new FileWriter(fileW)){
//            String data = "abcdefg";
//            char[] cs = data.toCharArray();
//            wr.write(cs);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
