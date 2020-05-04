import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileSplit {
    public static void split(File f,int eachSize){
        if(f.length()==0){
            throw new RuntimeException("文件长度为0，不可分");
        }
        byte[] fileData = new byte[(int)f.length()];
        //执行了下面几步才是真正读到了数组中
        try {
            FileInputStream fs = new FileInputStream(f);
            fs.read(fileData);
            fs.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        int fileNum = 0;
        if(f.length()%eachSize==0){
            fileNum = (int)(f.length()/eachSize);
        }
        else {
            fileNum = (int)(f.length()/eachSize)+1;
        }

        for(int i = 0; i < fileNum; i++){
            String eachFileName = f.getName() + '-' + i;
            File eachSmallFile = new File(f.getParent(),eachFileName);
            System.out.println(eachSmallFile.getAbsolutePath());
            byte[] eachFileData;
            if(i!=fileNum-1){
                eachFileData = Arrays.copyOfRange(fileData,eachSize*i,eachSize*(i+1));
            }else{
                eachFileData = Arrays.copyOfRange(fileData,eachSize*i,fileData.length);
            }
            try{
                FileOutputStream ws = new FileOutputStream(eachSmallFile);
                ws.write(eachFileData);
                ws.close();
                System.out.println("每个子文件的字节为:"+eachSmallFile.length());
            }catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        File file = new File("test.txt");
        split(file,7);
    }
}
