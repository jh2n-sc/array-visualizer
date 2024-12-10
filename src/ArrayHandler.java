import java.util.Random;

public class ArrayHandler {
    private int size;
    private int[] array;
    private int i;
    private int j;
    private final Random rand = new Random();
    public int indexI;
    public int indexJ;
    private boolean done = false;

    public ArrayHandler() {
        this.i = 0;
        this.j = 0;
    }

    /**
     * Basic builders.
     * */
    public ArrayHandler buildSize(int size) {
        this.size = size;
        this.array = new int[size];
        return this;
    }

    public ArrayHandler buildArray() {
        this.array = new int[this.size];
        for (int i = 0; i < this.size; i++) {
            this.array[i] = i;
        }
        return this;
    }

    /**
     *  The sort that I used is selection sort, you can
     * implement your own sort, but I didn't study DSA much yet
     * so I chose the most basic of them all.
     */
    public void sortOneTick() {
        tickI();
    }
    private void tickI() {
        if(i >= size - 1) {
            done = true;
            return;
        }
        if(j == size) {
            i++;
            j = i + 1;
            tickJ();
        }
        else if (j < size){
            tickJ();
            j++;
        }
        indexI = i;
        indexJ = j;

    }
    private void tickJ() {
        if (j >= size) {
            return;
        }
        if (array[i] > array[j]) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }


    /**
     * Shuffle not really shuffling, instead it generates a number within
     * the 'size' range and then check whether there is a dupe
     * inside.
     * */
    public void shuffle() {
        for (int i = 0; i < this.size; i++) {
            int temp = rand.nextInt(size) + 1;
            while (dupeCheck(temp, i)) {
                 temp = rand.nextInt(size) + 1;
            }

            this.array[i] = temp;
        }
        this.i = 0;
        this.j = 0;
        this.done = false;
    }
    private boolean dupeCheck(int number, int max) {
        for (int i = 0; i < max; i++) {
            if (array[i] == number) {
                return true;
            }
        }
        return false;
    }

    public int[] getArray() {
        return array;
    }

    public boolean isDone() {
        return done;
    }
}
