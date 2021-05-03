package main.model.pieces;

import main.model.Board;
import main.model.Square;

import java.util.ArrayList;

/**
 * represents the Rook piece of a chess game
 */
public class Rook extends Piece {
    public Rook(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.ROOK;
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
