package model;

import gui.BoardPanel;
import util.ResourceLoader;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.util.ArrayList;

/**
 *
 * @author Balkányi Lajos
 */
public class Player extends MovableFigure {
    private Ellipse2D border;
    private final ArrayList<Figure> staticFigures;
    private final ArrayList<Figure> supplies;
    private Image actualImage;
    private final Image left;
    private final Image front;
    private final Image right;

    public Player(BoardPanel bp, int x, int y, int speed, int width, int height) {
        super(bp, x, y, speed, width, height, bp.getPlayerDirection(), new Ellipse2D.Double(x, y, width, height).getPathIterator(null));
        border = new Ellipse2D.Double(x, y, width, height);
        left = ResourceLoader.loadImage("data/picture/yogi-bear-left.png");
        front = ResourceLoader.loadImage("data/picture/yogi-bear-front.png");
        right = ResourceLoader.loadImage("data/picture/yogi-bear-right.png");
        actualImage = front;
        this.staticFigures = bp.getStaticFigures();
        this.supplies = bp.getSupplies();
    }

    @Override
    public void moveUp(double deltaTime) {
        PathIterator p = new Ellipse2D.Double(point.getX(), point.getY() - deltaTime * speed, width, height).getPathIterator(null);
        GeneralPath gp = new GeneralPath();
        gp.append(p, true);

        if (isMoveable(gp)) {
            path = gp;
            point.y -= deltaTime * speed;
            actualImage = front;
        }
    }

    @Override
    public void moveDown(double deltaTime) {
        PathIterator p = new Ellipse2D.Double(point.getX(), point.getY() + deltaTime * speed, width, height).getPathIterator(null);
        GeneralPath gp = new GeneralPath();
        gp.append(p, true);

        if (isMoveable(gp)) {
            path = gp;
            point.y += deltaTime * speed;
            actualImage = front;
        }
    }

    @Override
    public void moveLeft(double deltaTime) {
        PathIterator p = new Ellipse2D.Double(point.getX() - deltaTime * speed, point.getY(), width, height).getPathIterator(null);
        GeneralPath gp = new GeneralPath();
        gp.append(p, true);

        if (isMoveable(gp)) {
            path = gp;
            point.x -= deltaTime * speed;
            actualImage = left;
        }
    }

    @Override
    public void moveRight(double deltaTime) {
        PathIterator p = new Ellipse2D.Double(point.getX() + deltaTime * speed, point.getY(), width, height).getPathIterator(null);
        GeneralPath gp = new GeneralPath();
        gp.append(p, true);

        if (isMoveable(gp)) {
            path = gp;
            point.x += deltaTime * speed;
            actualImage = right;
        }
    }

    private boolean isMoveable(GeneralPath gp) {
        if(isInsideTheBorder(gp)) {
            return false;
        }
        
        for (Figure staticFigure : staticFigures) {
            if(staticFigure.isIntersect(gp)) {
                return false;
            }
        }

        for (int i = supplies.size() - 1; i >= 0; i--) {
            if (supplies.get(i).isIntersect(gp)) {
                supplies.remove(i);
                bp.increasePoints();
            }
        }
        return true;
    }

    @Override
    public void draw(Graphics2D shape) {
        //shape.fillOval((int)center.getX() - (int)radius, (int)center.getY() - (int)radius, size, size);
        shape.drawImage(actualImage, (int) point.getX(), (int) point.getY(), width, height, null);
    }

}
