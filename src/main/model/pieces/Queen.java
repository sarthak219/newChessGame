package main.model.pieces;

import main.model.Board;

import java.util.ArrayList;

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
        this.validSquares = new ArrayList<>();
        checkVertically();
        checkHorizontally();
        checkDiagonally();
    }

    private void checkVertically() {
        checkSquaresBelow();
        checkSquaresAbove();
    }

    private void checkHorizontally() {
        checkSquaresToTheLeft();
        checkSquaresToTheRight();
    }

    private void checkDiagonally() {
    }

}
