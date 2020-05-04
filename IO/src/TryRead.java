import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TryRead {
    public static void main(String[] args) {
        File file = new File("test.txt");
        try(FileInputStream fs = new FileInputStream(file)) {
            byte[] data = new byte[(int)file.length()];
            fs.read(data);
            for (byte b:data) System.out.println(b);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
