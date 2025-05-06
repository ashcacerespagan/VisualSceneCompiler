// Author: Ashley Cáceres Pagán
// Date: September 17, 2024
// Project: CMSC 330 Project 1
// Description: This class defines an isosceles triangle object. It is created using the specified
// color, a point representing the top vertex, height, and width. The triangle's x and y coordinates
// are computed and stored. It extends Polygon_.

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.Polygon;

public class IsoscelesTriangle extends Polygon_ {
    private final Point top;
    private final int height;
    private final int width;

    public IsoscelesTriangle(Color color, Point top, int height, int width) {
        super(color, 3);  // Isosceles Triangle has 3 vertices
        this.top = top;
        this.height = height;
        this.width = width;

        calculateVertices();
    }

    private void calculateVertices() {
        int[] xPoints = {top.x - width / 2, top.x + width / 2, top.x};
        int[] yPoints = {top.y + height, top.y + height, top.y};

        System.out.println("Isosceles xPoints: " + Arrays.toString(xPoints));
        System.out.println("Isosceles yPoints: " + Arrays.toString(yPoints));

        createPolygon(xPoints, yPoints);
    }
    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        graphics.fillPolygon(polygon); // Draw the outline of the triangle
    }
}
