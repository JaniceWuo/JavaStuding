package listener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestKeyListen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LOL");
        frame.setSize(800,500);
        frame.setLocation(200,200);
        frame.setLayout(null);

        final JLabel l = new JLabel();
        ImageIcon ico = new ImageIcon("img.png");
        l.setIcon(ico);
        l.setBounds(50,50,ico.getIconWidth(),ico.getIconHeight());

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==39){
                    l.setLocation(l.getX()+10,l.getY());
                }
                else if(e.getKeyCode()==37){
                    l.setLocation(l.getX()-10,l.getY());
                }else if(e.getKeyCode()==38){
                    l.setLocation(l.getX(),l.getY()-10);
                } else {
                    l.setLocation(l.getX(),l.getY()+10);
                    }
                }

        });
        frame.add(l);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
