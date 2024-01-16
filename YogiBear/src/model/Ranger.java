package model;

import gui.BoardPanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import util.Direction;
import util.ResourceLoader;

/**
 *
 * @author Balkányi Lajos
 */
public class Ranger extends MovableFigure {
    private final ArrayList<Figure> staticFigures;
    private Image actualImage;
    private final Image left;
    private final Image front;
    private final Image right;
    private boolean isPlayerInside;

    public Ranger(BoardPanel bp, int x, int y, int speed, int width, int height, Direction direction) {
        super(bp, x, y, speed, width, height, direction, new Ellipse2D.Double(x, y, width, height).getPathIterator(null));
        left = ResourceLoader.loadImage("data/picture/ranger-left.png");
        front = ResourceLoader.loadImage("data/picture/ranger-front.png");
        right = ResourceLoader.loadImage("data/picture/ranger-right.png");
        actualImage = (direction.getRight()) ? right : (direction.getLeft() ? left : front);
        staticFigures = bp.getStaticFigures();
        isPlayerInside = false;
    }

    /**
     * It moves in the right direction
     */
    public void move(double deltaTime) {
        if (direction.getUp()) {
            moveUp(deltaTime);
        }
        if (direction.getDown()) {
            moveDown(deltaTime);
        }
        if (direction.getLeft()) {
            moveLeft(deltaTime);
        }
        if (direction.getRight()) {
            moveRight(deltaTime);
        }
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
        else if(!isPlayerInside) {
            direction.setReverseDirection();
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
        else if(!isPlayerInside) {
            direction.setReverseDirection();
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
        else if(!isPlayerInside) {
            direction.setReverseDirection();
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
        else if(!isPlayerInside) {
            direction.setReverseDirection();
        }
    }

    private boolean isMoveable(GeneralPath gp) {
        if(isInsideTheBorder(gp)) {
            return false;
        }
        
        if (bp.getPlayer().isIntersect(gp)) {
            isPlayerInside = true;
            return false;
        }

        for (Figure staticFigure : staticFigures) {
            if (staticFigure.isIntersect(gp)) {
                return false;
            }
        }

        return true;
    }

    public boolean isPlayerInside() {
        return isPlayerInside;
    }

    @Override
    public void draw(Graphics2D shape) {
        //shape.fillOval((int)center.getX() - (int)radius, (int)center.getY() - (int)radius, size, size);
        //if(visionFieldCenter != null) {
        //    shape.fillOval((int)visionFieldCenter.getX() - (int)radius, (int)visionFieldCenter.getY() - (int)radius, size, size);
        //}
        shape.drawImage(actualImage, (int) point.getX(), (int) point.getY(), width, height, null);
    }
}
