package model;

import gui.BoardPanel;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import javax.swing.Timer;
import util.Direction;

/**
 *
 * @author Balkányi Lajos
 */
public abstract class MovableFigure extends Figure {

    protected BoardPanel bp;
    protected int speed;
    protected Direction direction;

    public MovableFigure(BoardPanel bp, int x, int y, int speed, int width, int height, PathIterator path) {
        this(bp, x, y, speed, width, height, new Direction(), path);
    }

    public MovableFigure(BoardPanel bp, int x, int y, int speed, int width, int height, Direction direction, PathIterator path) {
        super(x, y, width, height, path);
        this.bp = bp;
        this.speed = speed;
        this.direction = direction;
    }

    protected abstract void moveUp(double deltaTime);

    protected abstract void moveDown(double deltaTime);

    protected abstract void moveLeft(double deltaTime);

    protected abstract void moveRight(double deltaTime);

    protected boolean isInsideTheBorder(GeneralPath gp) {
        double[] coords = new double[6];
        for (PathIterator pi = gp.getPathIterator(null); !pi.isDone(); pi.next()) {
            pi.currentSegment(coords);
            if(coords[0] < 0 || coords[0] > bp.getWidth() || coords[1] < 0 || coords[1] > bp.getHeight()) {
                return true;
            }
        }
        return false;
    }
}
