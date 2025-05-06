// CMSC 330 Advanced Programming Languages
// Project 1 Skeleton
// UMGC CITE
// August 2021

import java.io.*;

// This class provides the lexical analyzer for project 1

class Lexer {

    private final StreamTokenizer tokenizer;
    private final Token[] punctuationTokens = {Token.COMMA, Token.SEMICOLON, Token.PERIOD,
            Token.LEFT_PAREN, Token.RIGHT_PAREN };

    // Constructor that creates a lexical analyzer object given the source file

    public Lexer(File file) throws FileNotFoundException {
        tokenizer = new StreamTokenizer(new FileReader(file));
        tokenizer.ordinaryChar('.');
        tokenizer.quoteChar('"');
    }

    // Returns the next token in the input stream

    public Token getNextToken() throws IOException {
        int token = tokenizer.nextToken();

        String punctuation = ",;.()";
        switch (token) {
            case StreamTokenizer.TT_NUMBER:
                return Token.NUMBER;

            case StreamTokenizer.TT_WORD:
                // Add new shape tokens here by matching their corresponding keywords
                String word = tokenizer.sval.toUpperCase();
                return switch (word) {
                    case "SCENE" -> Token.SCENE;
                    case "RIGHTTRIANGLE" -> Token.RIGHT_TRIANGLE;
                    case "RECTANGLE" -> Token.RECTANGLE;
                    case "PARALLELOGRAM" -> Token.PARALLELOGRAM;
                    case "REGULARPOLYGON" -> Token.REGULAR_POLYGON;
                    case "ISOSCELES" -> Token.ISOSCELES;
                    case "TEXT" -> Token.TEXT;
                    case "COLOR" -> Token.COLOR;
                    case "AT" -> Token.AT;
                    case "HEIGHT" -> Token.HEIGHT;
                    case "WIDTH" -> Token.WIDTH;
                    case "OFFSET" -> Token.OFFSET;
                    case "SIDES" -> Token.SIDES;
                    case "RADIUS" -> Token.RADIUS;
                    case "END" -> Token.END;
                    case "HOLLOWPOLYGON" -> Token.HOLLOW_POLYGON;
                    case "SOLIDPOLYGON" -> Token.SOLID_POLYGON;
                    default -> Token.IDENTIFIER;
                };

            case StreamTokenizer.TT_EOF:
                return Token.EOF;

            case '"':  // Handle string literals
                return Token.STRING;

            default:
                for (int i = 0; i < punctuation.length(); i++) {
                    if (token == punctuation.charAt(i))
                        return punctuationTokens[i];
                }
        }

        return Token.EOF; // Return EOF if no valid token is found
    }

    // Returns the lexeme associated with the current token

    public String getLexeme() {
        return tokenizer.sval;
    }

    // Returns the numeric value of the current token for numeric tokens

    public int getNumber() {
        return (int) tokenizer.nval;
    }

    // Returns the current line of the input file

    public int getLineNo() {
        return tokenizer.lineno();
    }
}
