import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sirma's Pair Extractor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new MyPanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }
}