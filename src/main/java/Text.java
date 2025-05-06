// Author: Ashley Cáceres Pagán
// Date: September 17, 2024
// Project: CMSC 330 Project 1
// Description: This class represents a text object to be drawn in the scene. It stores the text,
// its color, and its position in the window. It extends Image and implements the draw method to
// display the string using Graphics' drawString method.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Text extends Image {
    private final Point location;
    private final String text;

    public Text(Color color, Point location, String text) {
        super(color);
        this.location = location;
        this.text = text;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(super.getColor());  // Set color
        g.drawString(text, location.x, location.y);  // Draw the text at the specified location
    }
}
