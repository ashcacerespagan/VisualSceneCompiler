// Author: Ashley Cáceres Pagán
// Date: September 17, 2024
// Project: CMSC 330 Project 1
// Description: This class defines a parallelogram object, calculated from two points and an offset value.
// The x and y coordinates of the parallelogram are computed and stored. It extends Polygon_.

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.Polygon;

public class Parallelogram extends Polygon_ {
    private final Point p1;
    private final Point p2;
    private final int offset;

    public Parallelogram(Color color, Point p1, Point p2, int offset) {
        super(color, 4);  // Parallelogram has 4 vertices
        this.p1 = p1;
        this.p2 = p2;
        this.offset = offset;

        calculateVertices();
    }

    private void calculateVertices() {
        int[] xPoints = {p1.x, p2.x, p2.x - offset, p1.x - offset};
        int[] yPoints = {p1.y, p1.y, p2.y, p2.y};

        System.out.println("Parallelogram xPoints: " + Arrays.toString(xPoints));
        System.out.println("Parallelogram yPoints: " + Arrays.toString(yPoints));

        createPolygon(xPoints, yPoints);
    }

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        graphics.fillPolygon(polygon); // Fill the parallelogram
    }
}
