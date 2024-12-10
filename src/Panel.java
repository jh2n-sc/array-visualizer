import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class Panel extends JPanel implements Runnable{
    public final int HEIGHT = 600;
    public final int WIDTH = 1000;
    public final int SIZE = 1000;
    public final int SCALE = WIDTH / SIZE;
    public final int CELL_SIZE = SCALE;
    private boolean start = false;

    /**
     * 'SPEED' is actually slowness. if you increase the value,
     * it will decrease in speed.
     * */
    public final int SPEED = 100;

    public final int FPS = 60;
    public final double TPS = 60;

    /**
     *  counter for update, computer is too fast so we need to
     *  delay it a bit.
     * */
    int counter = 0;

    /**
     * clicker is actually removable
     * */
    boolean clicked = false;

    Thread thread;
    InputListener input = new InputListener();
    ArrayHandler array;

    // Boilerplate
    public Panel() {

        array = new ArrayHandler()
                .buildSize(SIZE)
                .buildArray();

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(input);
        array.shuffle();
    }


   public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);

        int[] tmp = array.getArray();
       /*
        * blue is for visualizing the location of 'i' and
        * red is for 'j'
        * */
        for (int i = 0; i < tmp.length; i++) {
            g2d.setColor(Color.GREEN);
            if(array.indexI == i) {

                g2d.setColor(Color.BLUE);
            }
            if(array.indexJ == i) {
                g2d.setColor(Color.RED);
            }
            g2d.fillRect(i * CELL_SIZE,HEIGHT -  (CELL_SIZE * (tmp[i] * HEIGHT/WIDTH)), CELL_SIZE,CELL_SIZE * (tmp[i] * HEIGHT/WIDTH));
        }
        g2d.setColor(Color.RED);

        g2d.dispose();
   }


   public void setThread() {
        thread = new Thread(this);
        thread.start();
   }

   /**
    * if you are new to delta accumulator, check my code below
    * */
   @Override
   public void run() {
        long prevTime = System.nanoTime();
        long currentTime;
        double interval= 1_000_000_000.0/FPS;
        double intervalT = 1_000_000_000.0/TPS;
        double accumulator = 0;
        double accumulatorT = 0;
        while(thread != null && thread.isAlive()) {
            currentTime = System.nanoTime();
            accumulator += currentTime - prevTime / interval;
            accumulatorT += currentTime - prevTime / intervalT;
            prevTime = currentTime;

            if (accumulatorT >= 1) {
                update();
                accumulatorT--;
            }
            if (accumulator >= 1) {
                repaint();
                accumulator--;
            }
        }
   }

   public void setArray() {

   }

   /**
    * I tried to use my keyboard as a trigger for
    * updating the sorting algorithm. It turns out that
    * it is bad because it's a hassle.
    * */

   public void update() {
        if (!input.keyboardPressed) {
            clicked = false;
        }
        if (input.isCPressed && !clicked) {
            start = true;
            clicked = true;
        }

        if (!start) {
            return;
        }
        if(input.isSpacePressed && !clicked) {
            array.shuffle();
            clicked = true;
            start = false;
        }
        if (counter >= SPEED) {
            array.sortOneTick();
            counter = 0;
        }
        counter++;
   }
}
