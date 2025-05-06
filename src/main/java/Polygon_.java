// CMSC 330 Advanced Programming Languages
// Project 1 Skeleton
// UMGC CITE
// August 2021

import java.awt.*;

// Abstract base class for all polygon classes
abstract class Polygon_ extends Image {

    // Protected fields to allow access to subclasses
    protected int vertexCount;
    protected Polygon polygon;

    // Constructor that initializes color and vertexCount of a polygon
    public Polygon_(Color color, int vertexCount) {
        super(color);
        this.vertexCount = vertexCount;
    }

    // Creates a polygon object given its vertices
    public void createPolygon(int[] x_points, int[] y_points) {
        this.polygon = new Polygon(x_points, y_points, vertexCount);
    }

    // Draws polygon using the polygon object
    @Override
    public void draw(Graphics graphics) {
        colorDrawing(graphics); // Ensures the color is set before drawing
        drawPolygon(graphics, polygon); // Calls abstract method implemented by subclass
    }

    // Abstract method to be implemented by subclasses
    // Determines how the polygon is drawn (e.g., fillPolygon or drawPolygon)
    abstract public void drawPolygon(Graphics graphics, Polygon polygon);
}
