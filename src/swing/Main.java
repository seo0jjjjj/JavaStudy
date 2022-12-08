package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
       new LayoutTest();
    }
}

class LayoutTest extends JFrame implements ActionListener {
    int a;
    JLabel count;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("내가 객체!");
    }

    class Add implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button pushed");

        }
    }
    public LayoutTest(){
        setLayout(new FlowLayout());
       a=0;
       setVisible(true);
       setSize(400,150);
       add(new JLabel("현재의 카운터 값: "));
       count = new JLabel("0");
        add(count);
       JButton jButton = new JButton();
       jButton.setSize(20,20);
       add(jButton);
       jButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println("하하하하");
           }
       });
        System.out.println(this.getLayout().toString());

    }


}
