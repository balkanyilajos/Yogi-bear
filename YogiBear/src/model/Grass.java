package model;

import util.ResourceLoader;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * Class for background
 * @author Balkányi Lajos
 */
public class Grass {
    private final Image grass;
    private final int height;
    private final int width;
    
    public Grass(int width, int height) {
        this.width = width;
        this.height = height;
        grass = ResourceLoader.loadImage("data/picture/grass.jpg");
    }
    
    /**
     * It generates the background
     */
    public void draw(Graphics2D shape) {
        for(int y = 0; y < height; y += grass.getHeight(null)) {
            for(int x = 0; x < width; x += grass.getWidth(null)) {
                shape.drawImage(grass, x, y, null);
            }
        }
    }
}
