// CMSC 330 Advanced Programming Languages
// Project 1 Skeleton
// UMGC CITE
// August 2021

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.Polygon;

public class Rectangle extends Polygon_ {
    private final Point topLeft;
    private final int height;
    private final int width;

    public Rectangle(Color color, Point topLeft, int height, int width) {
        super(color, 4);  // Rectangle has 4 vertices
        this.topLeft = topLeft;
        this.height = height;
        this.width = width;

        calculateVertices();
    }

    // Calculate and create the polygon using xPoints and yPoints
    private void calculateVertices() {
        int[] xPoints = {topLeft.x, topLeft.x + width, topLeft.x + width, topLeft.x};
        int[] yPoints = {topLeft.y, topLeft.y, topLeft.y + height, topLeft.y + height};

        // Debugging printout
        System.out.println("Rectangle xPoints: " + Arrays.toString(xPoints));
        System.out.println("Rectangle yPoints: " + Arrays.toString(yPoints));

        createPolygon(xPoints, yPoints);
    }

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        graphics.drawPolygon(polygon); // Fill the rectangle
    }
}
