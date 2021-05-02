package main.model.pieces;

import main.model.Board;

/**
 * represents the Bishop piece of a chess game
 */
public class Bishop extends Piece {
    public Bishop(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.BISHOP;
    }

    @Override
    public void assignImage(PieceColour colour) {
        this.image = colour == PieceColour.WHITE ? "./images/wB.png" : "./images/bB.png";
    }

    @Override
    public void calculateValidSquares() {

    }
}
