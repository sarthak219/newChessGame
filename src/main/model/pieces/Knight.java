package main.model.pieces;

import main.model.Board;

/**
 * represents the Knight piece of a chess game
 */
public class Knight extends Piece{
    public Knight(PieceColour colour, Board board) {
        super(colour, board);
        this.name = Name.KNIGHT;
    }

    @Override
    public void assignImage(PieceColour colour) {
        this.image = colour == PieceColour.WHITE ? "./images/wN.png" : "./images/bN.png";
    }

    @Override
    public void calculateValidSquares() {

    }
}
