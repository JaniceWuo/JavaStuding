import javax.swing.*;
import java.io.*;

public class TestGUI {

    public static void main(String[] args) {
        JFrame f = new JFrame("LOL");
        File file = new File("location.txt");
        f.setSize(400,400);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            f.setLocation(Integer.valueOf(br.readLine()).intValue(),Integer.valueOf(br.readLine()).intValue());
        }catch (Exception e){
            e.printStackTrace();
        }
//        f.setLocation(200,200);
        f.setLayout(null);
        JButton b = new JButton("一键秒你");
        b.setBounds(50,50,230,50);
        f.add(b);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Thread(){
            public void run(){
                while (true){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    int x = f.getX();
                    int y = f.getY();
                    try (PrintWriter pw = new PrintWriter(new FileWriter(file))){
                        pw.println(x);
                        pw.println(y);
                        pw.flush();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
