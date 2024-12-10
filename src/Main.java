import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        //  Boilerplate below
        JFrame frame = new JFrame();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Array Visualizer");

        Panel panel = new Panel();
        frame.getContentPane().add(panel);
        panel.setThread();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}