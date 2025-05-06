// CMSC 330 Advanced Programming Languages
// Project 1 Skeleton
// UMGC CITE
// August 2021

import java.awt.*;
import java.io.*;

// This class provides the skeleton parser for project 1

class Parser {
    private Token token;
    private final Lexer lexer;

    // Constructor to create a new lexical analyzer from input files

    public Parser(File file) throws IOException {
        lexer = new Lexer(file);
    }

    // Parses the production
    // scene → SCENE IDENTIFIER number_list images END '.'

    public Scene parseScene() throws LexicalError, SyntaxError, IOException {
        verifyNextToken(Token.SCENE);
        verifyNextToken(Token.IDENTIFIER);
        String window = lexer.getLexeme();
        int[] dimensions = getNumberList(2);
        Scene scene = new Scene(window, dimensions[0], dimensions[1]);
        parseImages(scene, lexer.getNextToken());
        verifyNextToken(Token.PERIOD);
        return scene;
    }

    // Parses the following productions

    // images -> image images | image
    // image -> right_triangle | rectangle
    // right_triangle -> RIGHT_TRIANGLE COLOR number_list AT number_list HEIGHT NUMBER WIDTH NUMBER ';'
    // rectangle -> RECTANGLE_ COLOR number_list AT number_list HEIGHT NUMBER WIDTH NUMBER ';'

    private void parseImages(Scene scene, Token imageToken) throws SyntaxError, IOException {
        int height, width, offset, radius, sides;
        verifyNextToken(Token.COLOR);
        int[] colors = getNumberList(3);
        Color color = new Color(colors[0], colors[1], colors[2]);
        verifyNextToken(Token.AT);
        int[] location = getNumberList(2);
        Point point = new Point(location[0], location[1]);

        // Process the current image token
        switch (imageToken) {
            case RIGHT_TRIANGLE -> {
                verifyNextToken(Token.HEIGHT);
                height = getNextNumber();
                verifyNextToken(Token.WIDTH);
                width = getNextNumber();
                scene.addImage(new RightTriangle(color, point, height, width));
            }
            case RECTANGLE -> {
                verifyNextToken(Token.HEIGHT);
                height = getNextNumber();
                verifyNextToken(Token.WIDTH);
                width = getNextNumber();
                scene.addImage(new Rectangle(color, point, height, width));
            }
            case PARALLELOGRAM -> {
                int[] secondPoint = getNumberList(2);
                Point p2 = new Point(secondPoint[0], secondPoint[1]);
                verifyNextToken(Token.OFFSET);
                offset = getNextNumber();
                scene.addImage(new Parallelogram(color, point, p2, offset));
            }
            case REGULAR_POLYGON -> {
                verifyNextToken(Token.SIDES);
                sides = getNextNumber();
                verifyNextToken(Token.RADIUS);
                radius = getNextNumber();
                scene.addImage(new RegularPolygon(color, point, sides, radius));
            }
            case ISOSCELES -> {
                verifyNextToken(Token.HEIGHT);
                height = getNextNumber();
                verifyNextToken(Token.WIDTH);
                width = getNextNumber();
                scene.addImage(new IsoscelesTriangle(color, point, height, width));
            }
            case SOLID_POLYGON -> {
                verifyNextToken(Token.SIDES);
                sides = getNextNumber();
                verifyNextToken(Token.RADIUS);
                radius = getNextNumber();
                System.out.println("Adding SolidPolygon with sides: " + sides + ", radius: " + radius);
                scene.addImage(new SolidPolygon(color, sides, radius, point));
            }
            case HOLLOW_POLYGON -> {
                verifyNextToken(Token.SIDES);
                sides = getNextNumber();
                verifyNextToken(Token.RADIUS);
                radius = getNextNumber();
                scene.addImage(new HollowPolygon(color, sides, radius, point));
            }
            case TEXT -> {
                verifyNextToken(Token.STRING);
                String text = lexer.getLexeme();
                scene.addImage(new Text(color, point, text));
            }
            default -> throw new SyntaxError(lexer.getLineNo(), "Unexpected image name " + imageToken);
        }

        // After adding the image, expect the semicolon
        verifyNextToken(Token.SEMICOLON);

        // Fetch the next token, which should be either another shape or END
        token = lexer.getNextToken();  // Fetch the next token
        if (token != Token.END) {  // Recursively call only if there are more images
            System.out.println("Recursing with token: " + token);
            parseImages(scene, token);  // Pass the new token to process the next image
        }
    }

    private int getNextNumber() throws SyntaxError, IOException {
        verifyNextToken(Token.NUMBER);
        return lexer.getNumber();
    }

    // Parses the following productions

    // number_list → '(' numbers ')'
    // numbers -> NUMBER | NUMBER ',' numbers

    // Returns an array of the numbers in the number list

    private int[]  getNumberList(int count) throws SyntaxError, IOException {
        int[] list = new int[count];
        verifyNextToken(Token.LEFT_PAREN);
        for (int i = 0; i < count; i++) {
            verifyNextToken(Token.NUMBER);
            list[i] = lexer.getNumber();
            token = lexer.getNextToken();
            if (i < count - 1)
                verifyCurrentToken(Token.COMMA);
            else
                verifyCurrentToken(Token.RIGHT_PAREN);
        }
        return list;
    }

    // Returns a list of numbers

    // Verifies that the next token is the expected token

    private void verifyNextToken(Token expectedToken) throws SyntaxError, IOException {
        token = lexer.getNextToken();
        verifyCurrentToken(expectedToken);
    }

    // Verifies that the current token is the expected token

    private void verifyCurrentToken(Token expectedToken) throws SyntaxError {
        if (token != expectedToken)
            throw new SyntaxError(lexer.getLineNo(), "Expecting token " + expectedToken + " not " + token);
    }
}