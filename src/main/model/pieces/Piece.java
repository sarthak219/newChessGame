package main.model.pieces;

import main.model.Board;
import main.model.Square;

import java.util.ArrayList;
import java.util.List;

/**
 * represents a piece of chess with a name, assigned square, colour, image icon and a list of squares it can move to
 */
public abstract class Piece {
    protected Name name;
    protected String image;
    protected Square assignedSquare;
    protected List<Square> validSquares;
    protected PieceColour colour;
    protected Board board;

    //EFFECTS: makes a new piece with the given colour without an assigned square or a
    // valid square it can go to, assigns an icon to the piece
    public Piece(PieceColour colour, Board board) {
        this.assignedSquare = null;
        this.validSquares = new ArrayList<>();
        this.colour = colour;
        this.board = board;
        assignImage(colour);
    }

    public String getImage() {
        return image;
    }

    public void setAssignedSquare(Square assignedSquare) {
        this.assignedSquare = assignedSquare;
    }

    public Square getAssignedSquare() {
        return assignedSquare;
    }

    public PieceColour getColour() {
        return colour;
    }

    public Name getName() {
        return name;
    }

    //EFFECTS: assigns the bg image to the piece
    public abstract void assignImage(PieceColour colour);

    //EFFECTS: return true if the given piece is in this.validSquares
    public boolean canGoTo(Square square) {
        return this.validSquares.contains(square);
    }

    //MODIFIES: this
    //EFFECTS: adds all teh squares the piece can go to to this.validSquares
    public abstract void calculateValidSquares();
}