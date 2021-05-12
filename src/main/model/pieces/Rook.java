package main.model.pieces;

import main.model.Board;

import java.util.ArrayList;

/**
 * represents the Rook piece of a chess game
 */
public class Rook extends Piece {
    private boolean hasMoved;

    public Rook(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.ROOK;
        this.hasMoved = false;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public void assignImage(PieceColour colour) {
        this.image = colour == PieceColour.WHITE ? "./images/wR.png" : "./images/bR.png";
    }

    @Override
    public void calculateValidSquares() {
        this.validSquares = new ArrayList<>();
        checkSquaresAbove();
        checkSquaresBelow();
        checkSquaresToTheLeft();
        checkSquaresToTheRight();
    }
}
