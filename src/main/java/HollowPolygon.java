// CMSC 330 Advanced Programming Languages
// Project 1 Skeleton
// UMGC CITE
// August 2021

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Point;

public class HollowPolygon extends Polygon_ {
    private final Point center;

    public HollowPolygon(Color color, int sides, int radius, Point center) {
        super(color, sides); // Set the number of vertices
        this.center = center;
        calculateVertices(sides, radius); // Calculate vertices
    }

    private void calculateVertices(int sides, int radius) {
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];

        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            xPoints[i] = (int) (center.x + radius * Math.cos(angle));
            yPoints[i] = (int) (center.y + radius * Math.sin(angle));
        }

        createPolygon(xPoints, yPoints); // Call createPolygon with xPoints and yPoints
    }

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        graphics.drawPolygon(polygon); // Draw the outline of the polygon
    }
}
