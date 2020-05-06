import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TestComputer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("计算器");
        frame.setSize(331,278);
        frame.setLocation(760,340);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel p = new JPanel();
        p.setBounds(8,8,300,225);
        frame.add(p);
        p.setLayout(new GridLayout(4,5,8,8));

        String[] arr={"7","8","9","/","sq","4","5","6","*","%","1","2","3","-","1/x","0","+/-",".","+","="};
        for(int i = 0;i<20;i++){
            JButton button = new JButton(arr[i]);
            p.add(button);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
