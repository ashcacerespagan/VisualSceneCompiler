// Author: Ashley Cáceres Pagán
// Date: September 17, 2024
// Project: CMSC 330 Project 1
// Description: This class represents a solid polygon, which is drawn by filling the polygon's area.
// It extends Polygon_ and overrides the drawPolygon method to fill the shape with a color.

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;
import java.awt.Point;
import java.util.Arrays; // Import Arrays to use Arrays.toString()

public class SolidPolygon extends Polygon_ {
    private final Point center;

    public SolidPolygon(Color color, int sides, int radius, Point center) {
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

        System.out.println("SolidPolygon xPoints: " + Arrays.toString(xPoints));
        System.out.println("SolidPolygon yPoints: " + Arrays.toString(yPoints));

        createPolygon(xPoints, yPoints); // Call createPolygon with xPoints and yPoints
    }

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        colorDrawing(graphics); // Ensure the color is set before filling
        graphics.fillPolygon(polygon); // Fill the polygon
    }
}
