package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import util.ResourceLoader;

/**
 *
 * @author Balkányi Lajos
 */ 
public class Basket extends Figure {
    private final Image basket;
    
    public Basket(int x, int y, int width, int height) {
        super(x, y, width, height, new Ellipse2D.Double(x+width*3/8, y+height*3/8, width/4, height/4).getPathIterator(null));
        basket = ResourceLoader.loadImage("data/picture/picnic-basket.png");
    }
    
    @Override
    public void draw(Graphics2D shape) {
        //shape.fillOval((int)center.getX() - (int)radius, (int)center.getY() - (int)radius, size, size);
        shape.drawImage(basket, (int)point.getX(), (int)point.getY(), width, height, null);
    }
}
