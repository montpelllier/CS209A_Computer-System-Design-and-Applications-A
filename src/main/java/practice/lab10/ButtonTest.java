package practice.lab10;

import javax.swing.*;

/**
 * @author Cay Horstmann
 * @version 1.02 2018-05-01
 */
public class ButtonTest {
    public static void main(String[] args) {
        var frame = new ButtonFrame();
        frame.setTitle("ButtonTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
