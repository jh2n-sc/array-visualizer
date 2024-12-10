import java.awt.event.*;


public class InputListener implements KeyListener{

    public boolean isSpacePressed = false;
    public boolean keyboardPressed = true;
    public boolean isCPressed = false;
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            isSpacePressed = true;
        }
        keyboardPressed = true;
        if(key == KeyEvent.VK_C){
            isCPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_SPACE){
            isSpacePressed = false;
        }
        keyboardPressed = false;
        if(key == KeyEvent.VK_C){
            isCPressed = false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
