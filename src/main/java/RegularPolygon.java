import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Polygon;

public class RegularPolygon extends Polygon_ {
    private final Point center;
    private final int sides;
    private final int radius;

    public RegularPolygon(Color color, Point center, int sides, int radius) {
        super(color, sides);  // Number of vertices equals the number of sides
        this.center = center;
        this.sides = sides;
        this.radius = radius;

        calculateVertices();
    }

    private void calculateVertices() {
        int[] xPoints = new int[sides];
        int[] yPoints = new int[sides];

        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI * i / sides;
            xPoints[i] = (int) (center.x + radius * Math.cos(angle));
            yPoints[i] = (int) (center.y + radius * Math.sin(angle));
        }

        createPolygon(xPoints, yPoints);
    }

    @Override
    public void drawPolygon(Graphics graphics, Polygon polygon) {
        colorDrawing(graphics); // Set the color
        graphics.drawPolygon(polygon);
    }
}
