package listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class TestMounseListen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LOL");
        frame.setSize(800,600);
        frame.setLocation(100,100);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        final JLabel l = new JLabel();
        ImageIcon icon = new ImageIcon("img.png");
        l.setIcon(icon);
        l.setBounds(200,100, icon.getIconWidth(),icon.getIconHeight());

        frame.add(l);

//        frame.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                Random r = new Random();
//                int x = r.nextInt(frame.getWidth()-l.getWidth());
//                int y = r.nextInt(frame.getHeight()-l.getHeight());
//                System.out.println(x);
//                System.out.println(y);
//                l.setLocation(x,y);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });

        //鼠标监听适配器
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
//                super.mouseEntered(e);
                Random r = new Random();
                int x = r.nextInt(frame.getWidth()-l.getWidth());
                int y = r.nextInt(frame.getHeight()-l.getHeight());
                l.setLocation(x,y);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
