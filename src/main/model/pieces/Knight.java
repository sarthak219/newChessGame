package main.model.pieces;

import main.model.Board;
import main.model.Square;

import java.util.ArrayList;

/**
 * represents the Knight piece of a chess game
 */
public class Knight extends Piece {
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
        this.validSquares = new ArrayList<>();
        checkZigZag(1);
        checkZigZag(-1);
    }

    private void checkZigZag(int x) {
        Square square;
        int i = this.assignedSquare.getRow() - x;
        int j = this.assignedSquare.getColumn();
        for (int k = j - 2; k <= j + 2; ++k) {
            if (k != j && k >= 0 && k <= 7 && i <= 7 && i >= 0) {
                square = board.getSquareAt(i, k);
                if (!checkPieceValidity(square)) this.validSquares.add(square);
            }
            i -= x;
            x *= -1;
        }
    }
}
