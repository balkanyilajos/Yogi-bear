package model;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

/**
 *
 * @author balka
 */
public abstract class Figure {
    protected GeneralPath path;
    protected Point2D.Double point;
    protected int width;
    protected int height;
    
    public Figure(int x, int y, int width, int height, PathIterator path) {
        GeneralPath gp = new GeneralPath();
        gp.append(path, true);
        this.path = gp;
        this.point = new Point2D.Double(x, y);
        this.width = width;
        this.height = height;
    }

    public abstract void draw(Graphics2D shape);
    
    protected GeneralPath getPath() {
        return path;
    }
    
    protected Area getArea() {
        return new Area(getPath());
    }
    
    protected boolean isIntersect(Figure figure) {
        Area a1 = getArea();
        Area a2 = figure.getArea();
        a1.intersect(a2);
        return !a1.isEmpty();
    }
    
    protected boolean isIntersect(GeneralPath gp) {
        Area a1 = getArea();
        Area a2 = new Area(gp);
        a1.intersect(a2);
        return !a1.isEmpty();
    }
    
}
