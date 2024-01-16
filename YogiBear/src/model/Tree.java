package model;

import util.ResourceLoader;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.io.IOException;

/**
 *
 * @author Balkányi Lajos
 */ 
public class Tree extends Figure {
    private final Image tree;
    
    public Tree(int x, int y, int width, int height) throws IOException {
        super(x, y, width, height, new Ellipse2D.Double(x, y, width, height).getPathIterator(null));
        tree = ResourceLoader.loadImage("data/picture/tree.png");
    }
    
    @Override
    public void draw(Graphics2D shape) {
        //shape.fillOval((int)center.getX() - (int)radius, (int)center.getY() - (int)radius, size, size);
        shape.drawImage(tree, (int)point.getX(), (int)point.getY(), width, height, null);
    }
    
} 
