package listener;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//按钮监听
public class TestBttonListen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LOL");
        frame.setSize(440,440);
        frame.setLocation(200,200);
        frame.setLayout(null);

        final JLabel l = new JLabel();
        ImageIcon icon = new ImageIcon("img.png");
        l.setIcon(icon);
        l.setBounds(20,20,icon.getIconWidth(),icon.getIconHeight());

        JButton button = new JButton("隐藏图片");
//        button.setText();
        button.setBounds(150, 300, 100, 30);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(button.getText().equals("隐藏图片")){
                    button.setText("显示图片");
                    l.setVisible(false);
                }
                else {
                    button.setText("隐藏图片");
                    l.setVisible(true);
                }
            }
        });

        frame.add(l);
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
