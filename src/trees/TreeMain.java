package trees;

import javax.swing.*;
import java.awt.*;

public class TreeMain {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = null;
                frame = new SimpleTreeFrame();
                frame.setTitle("SimpleTreeFrame");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
