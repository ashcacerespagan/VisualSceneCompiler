// CMSC 330 Advanced Programming Languages
// Project 1 Skeleton
// UMGC CITE
// August 2021

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Polygon;


public class RightTriangle extends Polygon_ {
    private final Point bottomLeft;
    private final int height;
    private final int width;

    public RightTriangle(Color color, Point bottomLeft, int height, int width) {
        super(color, 3);  // The Right Triangle has 3 vertices
        this.bottomLeft = bottomLeft;
        this.height = height;
        this.width = width;

        calculateVertices();
    }

    private void calculateVertices() {
        // Flip the triangle by adjusting the yPoints
        int[] xPoints = {bottomLeft.x, bottomLeft.x + width, bottomLeft.x};
        int[] yPoints = {bottomLeft.y, bottomLeft.y, bottomLeft.y - height};  // Subtract height to flip upwards

        createPolygon(xPoints, yPoints);
    }

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        colorDrawing(graphics); // Ensure the color is set
        graphics.drawPolygon(polygon);  // Use fillPolygon instead of drawPolygon
    }
}
