package main.model.pieces;

import main.model.Board;

/**
 * represents the King piece of a chess game
 */
public class King extends Piece{

    public King(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.KING;
    }

    @Override
    public void assignImage(PieceColour colour) {
        this.image = colour == PieceColour.WHITE ? "./images/wK.png" : "./images/bK.png";
    }

    @Override
    public void calculateValidSquares() {

    }
}
