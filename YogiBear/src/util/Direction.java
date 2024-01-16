package util;

import java.awt.event.KeyEvent;

/**
 *
 * @author Balkányi Lajos
 */
public class Direction {

    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    public Direction() {
        left = right = up = down = false;
    }
    
    public Direction(boolean up, boolean down, boolean left, boolean right) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return right;
    }

    public boolean getUp() {
        return up;
    }

    public boolean getDown() {
        return down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    private void setDirectionWithCode(int keyCode, boolean value) {
        switch (keyCode) {
            case KeyEvent.VK_W -> up = value;
            case KeyEvent.VK_A -> left = value;
            case KeyEvent.VK_S -> down = value;
            case KeyEvent.VK_D -> right = value;
        }
    }
    
    public void setDirectionWithPressedKeyboardCode(int keyCode) {
        setDirectionWithCode(keyCode, true);
    }
    
    public void setDirectionWithReleasedKeyboardCode(int keyCode) {
        setDirectionWithCode(keyCode, false);
    }
    
    public void setReverseDirection() {
        up = !up;
        down = !down;
        left = !left;
        right = !right;
    }
    
    public boolean isCrossing() {
        return up && left || up && right || down && left || down && right;
    }

}
