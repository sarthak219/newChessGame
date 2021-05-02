package main.model.pieces;

import main.model.Board;
import main.model.Square;

/**
 * represents the Queen piece of a chess game
 */
public class Queen extends Piece {
    public Queen(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.QUEEN;
    }

    @Override
    public void assignImage(PieceColour colour) {
        this.image = colour == PieceColour.WHITE ? "./images/wQ.png" : "./images/bQ.png";
    }

    @Override
    public void calculateValidSquares() {
        Square square = board.getSquareAt(5, 3);
        this.validSquares.add(square);

    }
}
